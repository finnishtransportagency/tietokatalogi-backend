package fi.liike.rest.auth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.JwtParser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.commons.codec.binary.Base64;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtRequestFilter {
    private final Logger LOG = LoggerFactory.getLogger(JwtRequestFilter.class);

    private static String publicKey = null;
    private static PublicKey ecPublicKey = null;

    private String getPublicKey(String kid, boolean isForce) throws Exception {
        if (isForce || publicKey == null) {
            String url = "https://public-keys.auth.elb.eu-west-1.amazonaws.com/" + kid;
            HttpsURLConnection httpClient = (HttpsURLConnection) new URL(url).openConnection();
            httpClient.setRequestMethod("GET");
            httpClient.setRequestProperty("User-Agent", "Mozilla/5.0");
            int responseCode = httpClient.getResponseCode();

            LOG.debug(String.format("Sending request %s", url));
            LOG.debug(String.format("Response code %s", responseCode));

            String key = "";
            try (BufferedReader in = new BufferedReader(new InputStreamReader(httpClient.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    response.append(line);
                }
                key = response.toString()
                        .replaceAll("-----BEGIN PUBLIC KEY-----", "")
                        .replaceAll("-----END PUBLIC KEY-----", "");

                LOG.debug(String.format("Public key %s", key));
            }
            publicKey = key;
            ecPublicKey = null;
        }
        return publicKey;
    }

    private Claims decodeJWT(String jwt, String key) throws Exception {
        if (ecPublicKey == null) {
            LOG.debug("ecPublicKey is null");
            byte[] publicKeyBytes = Base64.decodeBase64(key);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("EC");
            ecPublicKey = keyFactory.generatePublic(keySpec);
            LOG.debug("generated ecPublicKey: " + ecPublicKey);
        }
        LOG.debug("using ecPublicKey: " + ecPublicKey);
        JwtParser parser = Jwts.parserBuilder().setSigningKey(ecPublicKey).build();
        return parser.parseClaimsJws(jwt).getBody();
    }

    public UserInfo getUserInfo(HttpServletRequest request) {
        List<UserGroup> userGroups = new ArrayList<>();
        String userName = null;
        try {
            LOG.debug(String.format("Path %s", request.getServletPath()));

            String jwt = request.getHeader("x-amzn-oidc-data");
            if (jwt == null || jwt.length() == 0) {
                jwt = request.getHeader("x-iam-data");
            }
            if (jwt != null) {
                String jwt_headers = jwt.split("\\.")[0];
                String decoded_jwt_headers = new String(Base64.decodeBase64(jwt_headers));
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(decoded_jwt_headers);

                LOG.debug(String.format("JWT headers json %s", jsonNode.toString()));

                String key = getPublicKey(jsonNode.get("kid").asText(),false);
                LOG.debug("getPublicKey: " + key);
                Claims claims;
                try {
                    claims = decodeJWT(jwt, key);
                } catch (SignatureException e) {
                    LOG.debug("Invalid key, trying again");
                    key = getPublicKey(jsonNode.get("kid").asText(),true);
                    claims = decodeJWT(jwt, key);
                }

                if (claims != null) {
                    String userNameClaim = (String) claims.get("username");
                    String uid = (String) claims.get("custom:uid");
                    userName = (uid != null) ? uid : userNameClaim;
                    LOG.debug(String.format("Username %s", userName));

                    String[] roles = ((String) claims.get("custom:rooli")).split("\\,");
                    LOG.debug(String.format("Roles %s", StringUtils.join(roles, ",")));

                    // claims.forEach((k, v) -> LOG.debug(String.format("Claim %s=%s", k, v)));
                    
                    for(String role:roles){
                        switch (role) {
                            case "tk_tietoturva":
                                userGroups.add(UserGroup.SUPER_USER);
                            case "tk_muokkaus":
                                userGroups.add(UserGroup.MODIFY_USER);
                            default:
                                LOG.error("JWT tokenissa ei ollut yhtaan tunnettua roolia"); // TODO: fix
                        }
                    }
                }
            } else {
                LOG.error("No JWT header found");
            }
            return new UserInfo(userName, userGroups);
        } catch (Exception ex) {
            LOG.error("Autorisointi epaonnistui", ex);
            return new UserInfo(userName, userGroups);
        }
    }


}
