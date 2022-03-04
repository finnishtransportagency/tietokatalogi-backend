package fi.liike.auth;

import fi.liike.rest.auth.JwtRequestFilter;
import fi.liike.rest.auth.UserGroup;
import fi.liike.rest.auth.UserInfo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.codec.binary.Base64;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import java.security.KeyPair;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.*;


public class JwtRequestFilterTest {
    private static KeyPair keyPair;
    private static String publicKeyString;

    @BeforeClass
    public static void setUp() {
        keyPair = Keys.keyPairFor(SignatureAlgorithm.ES384);
        publicKeyString = Base64.encodeBase64String(keyPair.getPublic().getEncoded());
    }

    @Test
    public void parseUsernameTest() throws Exception {
        String jws = Jwts.builder().setHeaderParam("kid", "42")
                .claim("username", "test user")
                .claim("custom:rooli", "tk_muokkaus")
                .signWith(keyPair.getPrivate())
                .compact();

        JwtRequestFilter jwtRequestFilter = Mockito.spy(new JwtRequestFilter());
        Mockito.doReturn(null).when(jwtRequestFilter).getPublicKey(anyString(), anyBoolean());
        Mockito.doReturn(publicKeyString).when(jwtRequestFilter).getPublicKey(eq("42"), anyBoolean());
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        Mockito.when(request.getHeader("x-amzn-oidc-data")).thenReturn(jws);

        UserInfo userInfo = jwtRequestFilter.getUserInfo(request);
        assertEquals("test user", userInfo.getUserName());
    }

    @Test
    public void getRightsTestNoRights() throws Exception {
        String jws = Jwts.builder().setHeaderParam("kid", "42")
                .claim("username", "test user")
                .claim("custom:rooli", "group1,group2,group3")
                .signWith(keyPair.getPrivate())
                .compact();

        JwtRequestFilter jwtRequestFilter = Mockito.spy(new JwtRequestFilter());
        Mockito.doReturn(null).when(jwtRequestFilter).getPublicKey(anyString(), anyBoolean());
        Mockito.doReturn(publicKeyString).when(jwtRequestFilter).getPublicKey(eq("42"), anyBoolean());
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        Mockito.when(request.getHeader("x-amzn-oidc-data")).thenReturn(jws);

        UserInfo userInfo = jwtRequestFilter.getUserInfo(request);
        assertEquals(0, userInfo.getUserGroups().size());
    }

    @Test
    public void parseRightsModificationRights() throws Exception {
        String jws = Jwts.builder().setHeaderParam("kid", "42")
                .claim("username", "test user")
                .claim("custom:rooli", "group1,tk_muokkaus,group3")
                .signWith(keyPair.getPrivate())
                .compact();

        JwtRequestFilter jwtRequestFilter = Mockito.spy(new JwtRequestFilter());
        Mockito.doReturn(null).when(jwtRequestFilter).getPublicKey(anyString(), anyBoolean());
        Mockito.doReturn(publicKeyString).when(jwtRequestFilter).getPublicKey(eq("42"), anyBoolean());
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        Mockito.when(request.getHeader("x-amzn-oidc-data")).thenReturn(jws);

        UserInfo userInfo = jwtRequestFilter.getUserInfo(request);
        assertEquals(1, userInfo.getUserGroups().size());
        assertTrue(userInfo.getUserGroups().contains(UserGroup.MODIFY_USER));
    }

    @Test
    public void parseRightsSecuredFieldRights() throws Exception {
        String jws = Jwts.builder().setHeaderParam("kid", "42")
                .claim("username", "test user")
                .claim("custom:rooli", "group1,tk_tietoturva,group3")
                .signWith(keyPair.getPrivate())
                .compact();

        JwtRequestFilter jwtRequestFilter = Mockito.spy(new JwtRequestFilter());
        Mockito.doReturn(null).when(jwtRequestFilter).getPublicKey(anyString(), anyBoolean());
        Mockito.doReturn(publicKeyString).when(jwtRequestFilter).getPublicKey(eq("42"), anyBoolean());
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        Mockito.when(request.getHeader("x-amzn-oidc-data")).thenReturn(jws);

        UserInfo userInfo = jwtRequestFilter.getUserInfo(request);
        assertEquals(2, userInfo.getUserGroups().size());
        assertTrue(userInfo.getUserGroups().contains(UserGroup.SUPER_USER));
    }

    @Test
    public void parseRightsBothGroups() throws Exception {
        String jws = Jwts.builder().setHeaderParam("kid", "42")
                .claim("username", "test user")
                .claim("custom:rooli", "tk_muokkaus,tk_tietoturva,group3")
                .signWith(keyPair.getPrivate())
                .compact();

        JwtRequestFilter jwtRequestFilter = Mockito.spy(new JwtRequestFilter());
        Mockito.doReturn(null).when(jwtRequestFilter).getPublicKey(anyString(), anyBoolean());
        Mockito.doReturn(publicKeyString).when(jwtRequestFilter).getPublicKey(eq("42"), anyBoolean());
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        Mockito.when(request.getHeader("x-amzn-oidc-data")).thenReturn(jws);

        UserInfo userInfo = jwtRequestFilter.getUserInfo(request);
        // FIXME: the implementation returns MODIFY_USER twice, which should not happen
        assertEquals(2, userInfo.getUserGroups().size());
        assertTrue(userInfo.getUserGroups().contains(UserGroup.SUPER_USER));
    }

    // TODO: test refreshing token
}
