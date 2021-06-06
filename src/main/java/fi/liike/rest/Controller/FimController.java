package fi.liike.rest.Controller;

import fi.liike.rest.Service.FimHenkiloService;
import fi.liike.rest.Service.HenkiloService;
import fi.liike.rest.api.ContentDto;
import fi.liike.rest.api.dto.HenkiloDto;
import io.swagger.annotations.Api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.io.IOException;
import java.util.List;

import static java.lang.String.format;

@Api(value = "FIM")
@Path("/FIM/")
public class FimController extends MainController {
    private final Logger LOG = LoggerFactory.getLogger(FimController.class);

    private FimHenkiloService fimHenkiloService;
    private HenkiloService henkiloService;

    public FimController() {
        this.fimHenkiloService = new FimHenkiloService();
        this.henkiloService = new HenkiloService();
    }

    //For testing
    public FimController(FimHenkiloService fimHenkiloService, HenkiloService henkiloService) {
        this.fimHenkiloService = fimHenkiloService;
        this.henkiloService = henkiloService;
    }

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(@DefaultValue("") @QueryParam("filterproperty") String filterProperty,
                            @DefaultValue("") @QueryParam("filter") String filter) throws IOException {
        filter = filter.replace("%20", " ");
        LOG.info(format("FIM Search: filterproperty=%s, filter=%s", filterProperty, filter));
        List<HenkiloDto> personList = henkiloService.getHenkiloList(filterProperty, filter);
        return buildHenkiloResponse(personList, personList.size());
    }

    //This is only temporary, probably
    @GET
    @Path("fetchFIM")
    public Response fetchAndSaveFromFIM() throws IOException {
        LOG.info("Fetch and save from FIM");
        fimHenkiloService.fetchAndSaveAllPersonsFromFIM();
        LOG.info("Fetching FIM persons is complete");
        return Response.ok().build();
    }

    @Override
    public Response create(HttpServletRequest httpRequest, ContentDto content) {
        return null;
    }

    @Override
    public Response get(HttpServletRequest httpRequest, String id) {
        return null;
    }

    @Override
    public Response update(HttpServletRequest httpRequest, ContentDto newEntry) {
        return null;
    }

    @Override
    public Response delete(HttpServletRequest httpRequest, String string) {
        return null;
    }

    @Override
    public Response getKasite() {
        return null;
    }
}
