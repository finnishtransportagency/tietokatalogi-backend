package fi.liike.rest.Service;

import java.util.*;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fi.liike.rest.Dao.Hibernate.*;
import fi.liike.rest.Dao.MainDao;
import fi.liike.rest.Model.*;
import fi.liike.rest.Model.linkitys.LinkitysHierarkia;
import io.swagger.annotations.Api;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;


import fi.liike.rest.Dao.HierarkiaDao;
import fi.liike.rest.Model.Dto.MolekyyliLinkkiDto;

@Api(value = "Linkitys")
@Path("/linkitys")
public class MolekyyliLinkkiService {

    private final Logger LOG = LoggerFactory.getLogger(MolekyyliLinkkiService.class);
    
    private final HierarkiaDao hierarkiaDao;
    private final TietoryhmaDaoImpl tietoryhmaDao;
    private final PaatietoryhmaDaoImpl paatietoryhmaDao;
    private final TietolajiDaoImpl tietolajiDao;
    private final LooginenDaoImpl looginenDao;
    private final FyysinenDaoImpl fyysinenDao;
    private final JarjestelmaDaoImpl jarjestelmaDao;
    private final SovellusDaoImpl sovellusDao;
    private final TietojarjestelmapalveluDaoImpl tietojarjestelmapalveluDao;
    private final ToimintaprosessiDaoImpl toimintaprosessiDao;
    private final TietovarantoDaoImpl tietovarantoDao;
    private final TermilomakeDaoImpl termilomakeDao;

    public MolekyyliLinkkiService(){
        this.hierarkiaDao = new HibernateHierarkiaDao();
        this.tietoryhmaDao = new TietoryhmaDaoImpl();
        this.paatietoryhmaDao = new PaatietoryhmaDaoImpl();
        this.tietolajiDao = new TietolajiDaoImpl();
        this.looginenDao = new LooginenDaoImpl();
        this.fyysinenDao = new FyysinenDaoImpl();
        this.jarjestelmaDao = new JarjestelmaDaoImpl();
        this.sovellusDao = new SovellusDaoImpl();
        this.tietojarjestelmapalveluDao = new TietojarjestelmapalveluDaoImpl();
        this.toimintaprosessiDao = new ToimintaprosessiDaoImpl();
        this.tietovarantoDao = new TietovarantoDaoImpl();
        this.termilomakeDao = new TermilomakeDaoImpl();
    }

//    @Deprecated
//    @GET
//    @Path("/tyyppi/{tyyppi}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public MolekyyliLinkkiDto haeTyypilla(@PathParam("tyyppi") String tyyppiNimi) {
//        if (!tyyppiNimi.isEmpty()){
//            List<MolekyyliHierarkia> hierarkiat = hierarkiaDao.haeTyypilla(tyyppiNimi);
//            return hierarkiastaDto(hierarkiat);
//        }
//        return null;
//    }

    @POST
    @Path("/poista/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response poistaLinkitys(@PathParam("id") String id) throws JSONException{
        if (StringUtils.isEmpty(id)){
            return Response.notModified().build();
        }

        else {
            int poistettuja = hierarkiaDao.poista(id);
            JSONObject paluuArvo = new JSONObject();
            paluuArvo.put("ok", true);
            paluuArvo.put("removed", poistettuja);
            return Response.ok(paluuArvo).build();
        }
    }

    @POST
    @Path("/paivita/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response paivitaKuvaus(@PathParam("id") String id, String data) throws JSONException{
        if (StringUtils.isEmpty(id)){
            return Response.notModified().build();
        }

        else {
            JSONObject o = new JSONObject(data);
            JSONObject params = o.getJSONObject("params");
            String info = null;
            try {
                info = params.getString("info");
            } catch (JSONException e){
            	LOG.error("Unable to do a create json object. Error message: " + e.getMessage());
            }

            int paivitettyja = hierarkiaDao.paivita(id, info);
            JSONObject paluuArvo = new JSONObject();
            paluuArvo.put("ok", true);
            paluuArvo.put("updated", paivitettyja);
            return Response.ok(paluuArvo).build();
        }
    }

    @POST
    @Path("/lisaa")
    @Produces(MediaType.APPLICATION_JSON)
    public Response lisaaLinkitys(String data) throws JSONException{
        if (StringUtils.isEmpty(data)){
            return Response.notModified().build();
        }

        else {
            JSONObject o = new JSONObject(data);
            JSONObject params = o.getJSONObject("params");
            int lahde = params.getInt("source");
            int kohde = params.getInt("target");

            String info = null;
            try {
                info = params.getString("info");
            } catch (JSONException e){
            	LOG.error("Unable to do a create json object. Error message: " + e.getMessage());
            }

            String id = lahde + "|" + kohde;
            int lisattyja = hierarkiaDao.lisaa(id, lahde, kohde, info);
            JSONObject paluuArvo = new JSONObject();
            paluuArvo.put("ok", true);
            paluuArvo.put("added", lisattyja);
            paluuArvo.put("id", id);
            return Response.ok(paluuArvo).build();
        }
    }

//    @Deprecated
//    @GET
//    @Path("/")
//    @Produces(MediaType.APPLICATION_JSON)
//    public MolekyyliLinkkiDto haeKaikki(
//            @DefaultValue("10") @QueryParam("size") String size,
//            @DefaultValue("0") @QueryParam("offset") String offset
//            ){
//        List<MolekyyliHierarkia> hierarkiat = hierarkiaDao.haeKaikki();
//        return hierarkiastaDto(hierarkiat);
//    }

//    @Deprecated
//    private MolekyyliLinkkiDto hierarkiastaDto(List<MolekyyliHierarkia> hierarkiat) {
//        MolekyyliLinkkiDto dto = new MolekyyliLinkkiDto();
//
//        if (hierarkiat == null) return dto;
//
//        for (MolekyyliHierarkia m : hierarkiat){
//            dto.lisaa(m);
//        }
//        return dto;
//    }

    @GET
    @Path("/tunnus/paatietoryhma/{tunnus}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response haePaatietoryhmaTunnuksella(@PathParam("tunnus") String tunnus,
                                                @DefaultValue("false") @QueryParam("showRelated") String filter) {
        List<LinkitysHierarkia> paatietoTietoryhmaLinks = getPaatietoryhmaLinks(tunnus, true);

        List<LinkitysHierarkia> linkitysHierarkiaList = new ArrayList<LinkitysHierarkia>();
        linkitysHierarkiaList.addAll(paatietoTietoryhmaLinks);

        for (LinkitysHierarkia linkitysHierarkia : paatietoTietoryhmaLinks) {
            String tietoryhmaTunnus = linkitysHierarkia.getLahdeTunnus();
            List<LinkitysHierarkia> tietolajiTietoryhmaLinks = getTietoryhmaLinks(tietoryhmaTunnus, false);
            linkitysHierarkiaList.addAll(tietolajiTietoryhmaLinks);
        }

        return linkitysHierarkiaListToResponse(linkitysHierarkiaList);
    }

    private List<LinkitysHierarkia> getPaatietoryhmaLinks(String tunnus, Boolean parentLinks) {
        if (parentLinks) {
            HashMap<String, Object> propertyRestictionMap = new HashMap<String, Object>();
            propertyRestictionMap.put("parentNode", Integer.parseInt(tunnus));
            return getLinkitysHierarkiaList(tunnus,
                    JoinTietoryhmaPaatietoryhma.class, propertyRestictionMap, tietoryhmaDao, paatietoryhmaDao,
                    "tietoryhma", "paatietoryhma");
        } else {
            HashMap<String, Object> propertyRestictionMap = new HashMap<String, Object>();
            propertyRestictionMap.put("childNode", Integer.parseInt(tunnus));
            return getLinkitysHierarkiaList(tunnus,
                    JoinTietoryhmaPaatietoryhma.class, propertyRestictionMap, tietoryhmaDao, paatietoryhmaDao,
                    "tietoryhma", "paatietoryhma");
        }
    }

    @GET
    @Path("/tunnus/tietoryhma/{tunnus}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response haeTietoryhmaTunnuksella(@PathParam("tunnus") String tunnus,
                                   @DefaultValue("false") @QueryParam("showRelated") String filter) {
        List<LinkitysHierarkia> tietoryhmaPaatietoryhmaLinks = getTietoryhmaLinks(tunnus, true);
        List<LinkitysHierarkia> tietolajiTietoryhmaLinks = getTietoryhmaLinks(tunnus, false);

        List<LinkitysHierarkia> linkitysHierarkiaList = new ArrayList<LinkitysHierarkia>();
        linkitysHierarkiaList.addAll(tietoryhmaPaatietoryhmaLinks);
        linkitysHierarkiaList.addAll(tietolajiTietoryhmaLinks);

        for (LinkitysHierarkia linkitysHierarkia : tietolajiTietoryhmaLinks) {
            String tietolajiTunnus = linkitysHierarkia.getLahdeTunnus();
            List<LinkitysHierarkia> tietolajiLinks = getTietolajiLinks(tietolajiTunnus, false);
            linkitysHierarkiaList.addAll(tietolajiLinks);
        }

        return linkitysHierarkiaListToResponse(linkitysHierarkiaList);
    }

    private List<LinkitysHierarkia> getTietoryhmaLinks(String tunnus, Boolean parentLinks) {
        if (parentLinks) {
            HashMap<String, Object> propertyRestictionMap = new HashMap<String, Object>();
            propertyRestictionMap.put("childNode", Integer.parseInt(tunnus));
            return getLinkitysHierarkiaList(tunnus,
                    JoinTietoryhmaPaatietoryhma.class, propertyRestictionMap, tietoryhmaDao, paatietoryhmaDao,
                    "tietoryhma", "paatietoryhma");
        } else {
            HashMap<String, Object> propertyRestictionMap = new HashMap<String, Object>();
            propertyRestictionMap.put("parentNode", Integer.parseInt(tunnus));
            return getLinkitysHierarkiaList(tunnus,
                    JoinTietolajiTietoryhma.class, propertyRestictionMap, tietolajiDao, tietoryhmaDao,
                    "tietolaji", "tietoryhma");
        }
    }


    @GET
    @Path("/tunnus/tietolaji/{tunnus}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response haeTietolajiTunnuksella(@PathParam("tunnus") String tunnus,
                                             @DefaultValue("false") @QueryParam("showRelated") String filter) {
        List<LinkitysHierarkia> tietolajiTietoryhmaLinks = getTietolajiLinks(tunnus, true);

        List<LinkitysHierarkia> tietolajiLooginenLinks = getTietolajiLinks(tunnus, false);

        List<LinkitysHierarkia> linkitysHierarkiaList = new ArrayList<LinkitysHierarkia>();
        linkitysHierarkiaList.addAll(tietolajiTietoryhmaLinks);
        linkitysHierarkiaList.addAll(tietolajiLooginenLinks);

        for (LinkitysHierarkia linkitysHierarkia : tietolajiTietoryhmaLinks) {
            String tietoryhmaTunnus = linkitysHierarkia.getKohdeTunnus();
            List<LinkitysHierarkia> tietoryhmaLinks = getTietoryhmaLinks(tietoryhmaTunnus, true);
            linkitysHierarkiaList.addAll(tietoryhmaLinks);
        }

        for (LinkitysHierarkia linkitysHierarkia : tietolajiLooginenLinks) {
            String looginenTunnus = linkitysHierarkia.getKohdeTunnus();
            List<LinkitysHierarkia> looginenLinks = getLooginenTietovarantoLinks(looginenTunnus, false);
            linkitysHierarkiaList.addAll(looginenLinks);
        }

        return linkitysHierarkiaListToResponse(linkitysHierarkiaList);
    }

    private List<LinkitysHierarkia> getTietolajiLinks(String tunnus, Boolean parentLinks) {
        if (parentLinks) {
            HashMap<String, Object> propertyRestictionMap = new HashMap<String, Object>();
            propertyRestictionMap.put("childNode", Integer.parseInt(tunnus));
            return getLinkitysHierarkiaList(tunnus,
                    JoinTietolajiTietoryhma.class, propertyRestictionMap, tietolajiDao, tietoryhmaDao,
                    "tietolaji", "tietoryhma");
        } else {
            HashMap<String, Object> propertyRestictionMap = new HashMap<String, Object>();
            propertyRestictionMap.put("childNode", Integer.parseInt(tunnus));
            return getLinkitysHierarkiaList(tunnus,
                    JoinTietolajiLooginen.class, propertyRestictionMap, tietolajiDao, looginenDao,
                    "tietolaji", "looginen");
        }
    }

    @GET
    @Path("/tunnus/looginentietovaranto/{tunnus}")
    public Response haeLooginenTietovarantoTunnuksella(@PathParam("tunnus") String tunnus,
                                                       @DefaultValue("false") @QueryParam("showRelated") String filter) {
        List<LinkitysHierarkia> looginenTietolajiLinks = getLooginenTietovarantoLinks(tunnus, true);

        List<LinkitysHierarkia> looginenFyysinenLinks = getLooginenTietovarantoLinks(tunnus, false);

        List<LinkitysHierarkia> linkitysHierarkiaList = new ArrayList<LinkitysHierarkia>();
        linkitysHierarkiaList.addAll(looginenTietolajiLinks);
        linkitysHierarkiaList.addAll(looginenFyysinenLinks);

        for (LinkitysHierarkia linkitysHierarkia : looginenTietolajiLinks) {
            String tietolajiTunnus = linkitysHierarkia.getLahdeTunnus();
            List<LinkitysHierarkia> tietolajiLinks = getTietolajiLinks(tietolajiTunnus, true);
            linkitysHierarkiaList.addAll(tietolajiLinks);
        }

        return linkitysHierarkiaListToResponse(linkitysHierarkiaList);
    }

    private List<LinkitysHierarkia> getLooginenTietovarantoLinks(String tunnus, Boolean parentLinks) {
        if (parentLinks) {
            HashMap<String, Object> propertyRestictionMap = new HashMap<String, Object>();
            propertyRestictionMap.put("parentNode", Integer.parseInt(tunnus));
            return getLinkitysHierarkiaList(tunnus,
                    JoinTietolajiLooginen.class, propertyRestictionMap, tietolajiDao, looginenDao,
                    "tietolaji", "looginen");
        } else {
            HashMap<String, Object> propertyRestictionMap = new HashMap<String, Object>();
            propertyRestictionMap.put("childNode", Integer.parseInt(tunnus));
            return getLinkitysHierarkiaList(tunnus,
                    JoinLooginenFyysinen.class, propertyRestictionMap, looginenDao, fyysinenDao,
                    "looginen", "fyysinen");
        }
    }

    /**
     * Gets links related to the given tietojärjestelmäpalvelu, that is,
     * gets links of the jarjestelma of the tjp such that only those links of the jarjestelma
     * are selected that use the given tjp.
     * For example, if tjp1 is related to jarjestelma j1, and j1 has links (j1, tjp1, j2), (j1, tjp2, j2), (j1, tjp1, j3),
     * then calling this endpoint on tjp1 returns the first and last link but not the second one.
     */
    @GET
    @Path("/tunnus/tietojarjestelmapalvelu/{tunnus}")
    public Response haeTietojarjestelmapalveluLinkitTunnuksella(@PathParam("tunnus") String tunnus) {
        TietojarjestelmapalveluFetch tjp = (TietojarjestelmapalveluFetch) this.tietojarjestelmapalveluDao.get(Integer.parseInt(tunnus));
        Integer jarjestelmaId = tjp.getTietojarjestelmatunnus();
        List<LinkitysHierarkia> linkitysHierarkiaList = getTietojarjestelmapalveluLinks(jarjestelmaId.toString(), tunnus);
        return linkitysHierarkiaListToResponse(linkitysHierarkiaList);
    }


    @GET
    @Path("/tunnus/fyysinentietovaranto/{tunnus}")
    public Response haefyysinenTietovarantoTunnuksella(@PathParam("tunnus") String tunnus,
                                                       @DefaultValue("false") @QueryParam("showRelated") String filter) {
        List<LinkitysHierarkia> looginenFyysinenLinks = getFyysinenTietovarantoLinks(tunnus);

        List<LinkitysHierarkia> linkitysHierarkiaList = new ArrayList<LinkitysHierarkia>();
        linkitysHierarkiaList.addAll(looginenFyysinenLinks);

        for (LinkitysHierarkia linkitysHierarkia : looginenFyysinenLinks) {
            String looginenTunnus = linkitysHierarkia.getLahdeTunnus();
            List<LinkitysHierarkia> tietolajiLinks = getLooginenTietovarantoLinks(looginenTunnus, true);
            linkitysHierarkiaList.addAll(tietolajiLinks);

            for (LinkitysHierarkia tietolajiLinkitysHierarkia : tietolajiLinks) {
                String tietolajiTunnus = tietolajiLinkitysHierarkia.getLahdeTunnus();
                List<LinkitysHierarkia> tietoryhmaLinks = getTietolajiLinks(tietolajiTunnus, true);
                linkitysHierarkiaList.addAll(tietoryhmaLinks);

                for (LinkitysHierarkia tietoryhmaLinkitysHierarkia : tietoryhmaLinks) {
                    String tietoryhmaTunnus = tietoryhmaLinkitysHierarkia.getKohdeTunnus();
                    List<LinkitysHierarkia> paatietoryhmaLinks = getTietoryhmaLinks(tietoryhmaTunnus, true);
                    linkitysHierarkiaList.addAll(paatietoryhmaLinks);
                }
            }
        }

        return linkitysHierarkiaListToResponse(linkitysHierarkiaList);
    }

    private List<LinkitysHierarkia> getFyysinenTietovarantoLinks(String tunnus) {
        HashMap<String, Object> propertyRestictionMap = new HashMap<String, Object>();
        propertyRestictionMap.put("parentNode", Integer.parseInt(tunnus));
        List<LinkitysHierarkia> looginenFyysinenLinks = hierarkiaDao.haeTunnuksella(tunnus,
                JoinLooginenFyysinen.class, propertyRestictionMap, looginenDao, fyysinenDao);
        for (LinkitysHierarkia linkitysHierarkia : looginenFyysinenLinks) {
            setTaulus(linkitysHierarkia, "looginen", "fyysinen");
        }
        return looginenFyysinenLinks;
    }

    @GET
    @Path("/tunnus/toimintaprosessi/{tunnus}")
    public Response haeToimintaprosessiLinkitTunnuksella(@PathParam("tunnus") String tunnus) {
        List<LinkitysHierarkia> tietovarantoToimintaprosessiLinks = getToimintaprosessiLinks(tunnus, false);

        List<LinkitysHierarkia> linkitysHierarkiaList = new ArrayList<>();
        linkitysHierarkiaList.addAll(tietovarantoToimintaprosessiLinks);

        for (LinkitysHierarkia linkitysHierarkia : tietovarantoToimintaprosessiLinks) {
            String tietovarantoTunnus = linkitysHierarkia.getLahdeTunnus();
            List<LinkitysHierarkia> tietoryhmaLinks = getTietovarantoLinks(tietovarantoTunnus, false);
            linkitysHierarkiaList.addAll(tietoryhmaLinks);

            for (LinkitysHierarkia tietoryhmaLinkitysHierarkia : tietoryhmaLinks) {
                String tietoryhmaTunnus = tietoryhmaLinkitysHierarkia.getLahdeTunnus();
                List<LinkitysHierarkia> tietolajiLinks = getTietoryhmaLinks(tietoryhmaTunnus, false);
                // linkitysHierarkiaList.addAll(tietolajiLinks);

                for (LinkitysHierarkia tietolajiLinkitysHierarkia : tietolajiLinks) {
                    String tietolajiTunnus = tietolajiLinkitysHierarkia.getLahdeTunnus();
                    List<LinkitysHierarkia> looginenLinks = getTietolajiLinks(tietolajiTunnus, false);
                    // linkitysHierarkiaList.addAll(looginenLinks);

                    for (LinkitysHierarkia looginenLinkitysHierarkia : looginenLinks) {
                        String looginenTunnus = looginenLinkitysHierarkia.getKohdeTunnus();
                        List<LinkitysHierarkia> jarjestelmaLinks = getLooginenJarjestelmaLinks(looginenTunnus, true);

                        // TKYP-160: This diagram should show tietoryhmä nodes directly connected to järjestelmä nodes
                        // So modify the links so that the source is tietoryhmä instead of looginen
                        for (LinkitysHierarkia link : jarjestelmaLinks) {
                            link.setLahdeTaulu(tietoryhmaLinkitysHierarkia.getLahdeTaulu());
                            link.setLahdeNimi(tietoryhmaLinkitysHierarkia.getLahdeNimi());
                            link.setLahdeTunnus(tietoryhmaLinkitysHierarkia.getLahdeTunnus());
                        }

                        linkitysHierarkiaList.addAll(jarjestelmaLinks);
                    }
                }
            }

            // TKYP-160: The diagram should also show other toimintaprosessi that use the same tietovaranto
            List<LinkitysHierarkia> otherToimintaprosessiLinks = getTietovarantoLinks(tietovarantoTunnus, true);
            linkitysHierarkiaList.addAll(otherToimintaprosessiLinks);
        }

        return linkitysHierarkiaListToResponse(linkitysHierarkiaList);
    }

    @GET
    @Path("/tunnus/tietovaranto/{tunnus}")
    public Response haeTietovarantoLinkitTunnuksella(@PathParam("tunnus") String tunnus) {
        List<LinkitysHierarkia> tietoryhmaTietovarantoLinks = getTietovarantoLinks(tunnus, false);

        List<LinkitysHierarkia> linkitysHierarkiaList = new ArrayList<>();
        linkitysHierarkiaList.addAll(tietoryhmaTietovarantoLinks);

        for (LinkitysHierarkia tietoryhmaLinkitysHierarkia : tietoryhmaTietovarantoLinks) {
            String tietoryhmaTunnus = tietoryhmaLinkitysHierarkia.getLahdeTunnus();
            List<LinkitysHierarkia> tietolajiLinks = getTietoryhmaLinks(tietoryhmaTunnus, false);
            // linkitysHierarkiaList.addAll(tietolajiLinks);

            for (LinkitysHierarkia tietolajiLinkitysHierarkia : tietolajiLinks) {
                String tietolajiTunnus = tietolajiLinkitysHierarkia.getLahdeTunnus();
                List<LinkitysHierarkia> looginenLinks = getTietolajiLinks(tietolajiTunnus, false);
                // linkitysHierarkiaList.addAll(looginenLinks);

                for (LinkitysHierarkia looginenLinkitysHierarkia : looginenLinks) {
                    String looginenTunnus = looginenLinkitysHierarkia.getKohdeTunnus();
                    List<LinkitysHierarkia> jarjestelmaLinks = getLooginenJarjestelmaLinks(looginenTunnus, true);

                    // TKYP-160: This diagram should show tietoryhmä nodes directly connected to järjestelmä nodes
                    // So modify the links so that the source is tietoryhmä instead of looginen
                    for (LinkitysHierarkia link : jarjestelmaLinks) {
                        link.setLahdeTaulu(tietoryhmaLinkitysHierarkia.getLahdeTaulu());
                        link.setLahdeNimi(tietoryhmaLinkitysHierarkia.getLahdeNimi());
                        link.setLahdeTunnus(tietoryhmaLinkitysHierarkia.getLahdeTunnus());
                    }

                    linkitysHierarkiaList.addAll(jarjestelmaLinks);
                }
            }
        }

        return linkitysHierarkiaListToResponse(linkitysHierarkiaList);
    }

    @GET
    @Path("/tunnus/sanasto/{tunnus}")
    public Response haeTermilomakeLinkitTunnuksella(@PathParam("tunnus") String tunnus) {
        List<LinkitysHierarkia> termilomakeParentLinks = getTermilomakeLinks(tunnus, true);
        List<LinkitysHierarkia> termilomakeChildLinks = getTermilomakeLinks(tunnus, false);

        List<LinkitysHierarkia> linkitysHierarkiaList = new ArrayList<>();
        linkitysHierarkiaList.addAll(termilomakeParentLinks);
        linkitysHierarkiaList.addAll(termilomakeChildLinks);

//        for (LinkitysHierarkia termilomakeHierarkia : termilomakeParentLinks) {
//            String termilomakeTunnus = termilomakeHierarkia.getLahdeTunnus();
//            List<LinkitysHierarkia> loop1 = getTermilomakeLinks(termilomakeTunnus, true);
//            linkitysHierarkiaList.addAll(loop1);
//        }
//        for (LinkitysHierarkia termilomakeHierarkia : termilomakeChildLinks) {
//            String termilomakeTunnus = termilomakeHierarkia.getLahdeTunnus();
//            List<LinkitysHierarkia> loop1 = getTermilomakeLinks(termilomakeTunnus, false);
//            linkitysHierarkiaList.addAll(loop1);
//        }

        return linkitysHierarkiaListToResponse(linkitysHierarkiaList);
    }

    private List<LinkitysHierarkia> getToimintaprosessiLinks(String tunnus, Boolean parentLinks) {
        if (parentLinks) {
            // Not implemented yet
            return new ArrayList<>();
        } else {
            HashMap<String, Object> propertyRestictionMap = new HashMap<>();
            propertyRestictionMap.put("parentNode", Integer.parseInt(tunnus));
            return getLinkitysHierarkiaList(tunnus,
                    JoinToimintaprosessiTietovaranto.class, propertyRestictionMap, tietovarantoDao, toimintaprosessiDao,
                    "tietovaranto", "toimintaprosessi");
        }
    }

    private List<LinkitysHierarkia> getTietovarantoLinks(String tunnus, Boolean parentLinks) {
        if (parentLinks) {
            HashMap<String, Object> propertyRestictionMap = new HashMap<>();
            propertyRestictionMap.put("childNode", Integer.parseInt(tunnus));
            return getLinkitysHierarkiaList(tunnus,
                    JoinToimintaprosessiTietovaranto.class, propertyRestictionMap, tietovarantoDao, toimintaprosessiDao,
                    "tietovaranto", "toimintaprosessi");
        } else {
            HashMap<String, Object> propertyRestictionMap = new HashMap<>();
            propertyRestictionMap.put("parentNode", Integer.parseInt(tunnus));
            return getLinkitysHierarkiaList(tunnus,
                    JoinTietoryhmaTietovaranto.class, propertyRestictionMap, tietoryhmaDao, tietovarantoDao,
                    "tietoryhma", "tietovaranto");
        }
    }

    private List<LinkitysHierarkia> getTermilomakeLinks(String tunnus, Boolean parentLinks) {
        HashMap<String, Object> propertyRestictionMap = new HashMap<>();
        if (parentLinks == false) {
            propertyRestictionMap.put("childNode", Integer.parseInt(tunnus));
        } else {
            propertyRestictionMap.put("parentNode", Integer.parseInt(tunnus));
        }

        HashSet<LinkitysHierarkia> linkitysHierarkiaHashSet = new HashSet<>();

        linkitysHierarkiaHashSet.addAll(
            getLinkitysHierarkiaList(tunnus,
                TermilomakeJoinHierarkkinenKasite.class,
                propertyRestictionMap,
                termilomakeDao,
                termilomakeDao,
                "sanasto",
                "sanasto"
            )
        );
        linkitysHierarkiaHashSet.addAll(
            getLinkitysHierarkiaList(tunnus,
                TermilomakeJoinAssosiatiivinenKasite.class,
                propertyRestictionMap,
                termilomakeDao,
                termilomakeDao,
                "sanasto",
                "sanasto"
            )
        );
        linkitysHierarkiaHashSet.addAll(
            getLinkitysHierarkiaList(tunnus,
                TermilomakeJoinKoostumussuhteinenKasite.class,
                propertyRestictionMap,
                termilomakeDao,
                termilomakeDao,
                "sanasto",
                "sanasto"
            )
        );
        List<LinkitysHierarkia> linkitysHierarkiaList = new ArrayList<>(linkitysHierarkiaHashSet);

        return linkitysHierarkiaList;
    }

    private List<LinkitysHierarkia> getLooginenJarjestelmaLinks(String tunnus, Boolean parentLinks) {
        if (parentLinks) {
            HashMap<String, Object> propertyRestictionMap = new HashMap<>();
            propertyRestictionMap.put("childNode", Integer.parseInt(tunnus));
            return getLinkitysHierarkiaList(tunnus,
                    JoinJarjestelmaLooginen.class, propertyRestictionMap, looginenDao, jarjestelmaDao,
                    "looginen", "jarjestelma");
        } else {
            // Not implemented yet
            return new ArrayList<>();
        }
    }

    public MolekyyliLinkkiDto haeMolekyyliLinkkiDtoJarjestelmaTunnuksella(String tunnus) {
        List<LinkitysHierarkia> linkitysHierarkiaList = new ArrayList<LinkitysHierarkia>();
        List<LinkitysHierarkia> sovellusLinks = getSovellusLinks(tunnus);
        List<LinkitysHierarkia> jarjestelmaLinksAsParent = getJarjestelmaLinks(tunnus, true);
        List<LinkitysHierarkia> jarjestelmaLinksAsChild = getJarjestelmaLinks(tunnus, false);
        linkitysHierarkiaList.addAll(sovellusLinks);
        linkitysHierarkiaList.addAll(jarjestelmaLinksAsParent);
        linkitysHierarkiaList.addAll(jarjestelmaLinksAsChild);

        return linksToMolekyyliLinkki(linkitysHierarkiaList);

    }

    private List<LinkitysHierarkia> getTietojarjestelmapalveluLinks(String jarjestelmaId, String tjpId) {
        List<LinkitysHierarkia> linkitysHierarkiaList = new ArrayList<>();
        List<LinkitysHierarkia> sovellusLinks = getSovellusLinks(jarjestelmaId, tjpId);
        List<LinkitysHierarkia> jarjestelmaLinksAsParent = getJarjestelmaLinks(jarjestelmaId,  true, tjpId);
        List<LinkitysHierarkia> jarjestelmaLinksAsChild = getJarjestelmaLinks(jarjestelmaId, false, tjpId);
        linkitysHierarkiaList.addAll(sovellusLinks);
        linkitysHierarkiaList.addAll(jarjestelmaLinksAsParent);
        linkitysHierarkiaList.addAll(jarjestelmaLinksAsChild);
        return linkitysHierarkiaList;

    }

    private List<LinkitysHierarkia> getSovellusLinks(String tunnus) {
        return getSovellusLinks(tunnus, null);
    }

    private List<LinkitysHierarkia> getSovellusLinks(String tunnus, String tjpId) {
        HashMap<String, Object> propertyRestrictionMap = new HashMap<>();
        propertyRestrictionMap.put("tyyppi", "Sovellus");
        propertyRestrictionMap.put("parentNode", Integer.parseInt(tunnus));
        if (tjpId != null)
            propertyRestrictionMap.put("tietojarjestelmapalveluTunnus", Integer.parseInt(tjpId));
        return hierarkiaDao.haeJarjestelmalinkkiTunnuksella(tunnus, propertyRestrictionMap,
                sovellusDao, jarjestelmaDao,
                "sovellus", "jarjestelma"
        );
    }

    private List<LinkitysHierarkia> getJarjestelmaLinks(String tunnus, Boolean asParent) {
        return getJarjestelmaLinks(tunnus, asParent, null);
    }

    private List<LinkitysHierarkia> getJarjestelmaLinks(String tunnus, Boolean asParent, String tjpId) {
        HashMap<String, Object> propertyRestictionMap = new HashMap<>();
        propertyRestictionMap.put("tyyppi", "Järjestelmä");
        if (tjpId != null)
            propertyRestictionMap.put("tietojarjestelmapalveluTunnus", Integer.parseInt(tjpId));

        if (asParent) {
            propertyRestictionMap.put("parentNode", Integer.parseInt(tunnus));
        }
        else {
            propertyRestictionMap.put("childNode", Integer.parseInt(tunnus));
        }
        return hierarkiaDao.haeJarjestelmalinkkiTunnuksella(tunnus, propertyRestictionMap,
                jarjestelmaDao, jarjestelmaDao, "jarjestelma", "jarjestelma"
        );
    }

    private List<LinkitysHierarkia> getLinkitysHierarkiaList(
            String tunnus,
            Class<? extends JoinTable> joinTableClass,
            Map<String, Object> propertyRestrictionMap,
            MainDao childNodeDao,
            MainDao parentNodeDao,
            String lahdeTaulu,
            String kohdeTaulu) {

        List<LinkitysHierarkia> linkitysHierarkiaList =
                hierarkiaDao.haeTunnuksella(tunnus,
                joinTableClass, propertyRestrictionMap, childNodeDao, parentNodeDao);
        for (LinkitysHierarkia linkitysHierarkia : linkitysHierarkiaList) {
            setTaulus(linkitysHierarkia, lahdeTaulu, kohdeTaulu);
        }

        return linkitysHierarkiaList;
    }

    private Response linkitysHierarkiaListToResponse(List<LinkitysHierarkia> linkitysHierarkiaList) {
        return Response.ok(linksToMolekyyliLinkki(linkitysHierarkiaList),
                MediaType.APPLICATION_JSON).header("Expires", "-1")
                .header("Cache-Control", "no-cache").build();
    }

    private void setTaulus(LinkitysHierarkia linkitysHierarkia, String lahdeTaulu, String kohdeTaulu) {
        linkitysHierarkia.setLahdeTaulu(lahdeTaulu);
        linkitysHierarkia.setKohdeTaulu(kohdeTaulu);
    }

    private MolekyyliLinkkiDto linksToMolekyyliLinkki(List<LinkitysHierarkia> hierarkiat) {
        MolekyyliLinkkiDto dto = new MolekyyliLinkkiDto();

        if (hierarkiat == null) return dto;

        for (LinkitysHierarkia m : hierarkiat){
            dto.lisaa(m);
        }
        return dto;
    }

//    @Deprecated
//    private List<MolekyyliHierarkia> suodataTunnuksella(List<MolekyyliHierarkia> hierarkiat,
//            String tunnus, boolean suodatus) {
//
//        List<MolekyyliHierarkia> suodatetut = Lists.newArrayList();
//        if (!suodatus){
//            Set<String> tunnukset = Sets.newHashSet(tunnus);
//            tunnukset = etsiTunnuksella(hierarkiat, tunnukset);
//            for (MolekyyliHierarkia h : hierarkiat){
//                //MolekyyliPK molekyyli = h.getMolekyyliPK();
//                if(h == null) {
//                    continue;
//                }
//                if (tunnukset.contains(h.getKohdeTunnus()) ||
//                        tunnukset.contains(h.getLahdeTunnus())){
//                    suodatetut.add(h);
//                }
//            }
//        }
//        else {
//            for (MolekyyliHierarkia h : hierarkiat){
//                if (h == null){
//                    continue;
//                }
//                //MolekyyliPK molekyyli = h.getMolekyyliPK();
//                if (tunnus.equals(h.getKohdeTunnus()) ||
//                        tunnus.equals(h.getLahdeTunnus())){
//                    suodatetut.add(h);
//                }
//            }
//        }
//        return suodatetut;
//    }

//    @Deprecated
//    private Set<String> etsiTunnuksella(List<MolekyyliHierarkia> hierarkiat,
//            Set<String> tunnukset) {
//
//            Set<String> tunnuksetCopy = Sets.newHashSet();
//            tunnuksetCopy.addAll(tunnukset);
//            for(String tunnus : tunnuksetCopy){
//                if(tunnus == null) {
//                    continue;
//                }
//                for(MolekyyliHierarkia h : hierarkiat){
//                    //MolekyyliPK molekyyli = h.getMolekyyliPK();
//                    if(h == null) {
//                        continue;
//                    }
//
//                    if(h.getKohdeTunnus().equals(tunnus) || h.getLahdeTunnus().equals(tunnus)){
//                        tunnukset.add(h.getKohdeTunnus());
//                        tunnukset.add(h.getLahdeTunnus());
//                    }
//                }
//            }
//
//        return tunnukset;
//    }

}
