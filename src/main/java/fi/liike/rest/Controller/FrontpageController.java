package fi.liike.rest.Controller;

import fi.liike.rest.Service.FrontpageService;
import fi.liike.rest.api.ContentDto;
import fi.liike.rest.api.dto.FrontpageDto;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Api(value = "Etusivu")
@Path("/frontpage/")
public class FrontpageController extends MainController {
    private FrontpageService service;
    private final Logger LOG = LoggerFactory.getLogger(FrontpageController.class);


    public FrontpageController() {
        this.service = new FrontpageService();
    }

    @Override
    public Response create(HttpServletRequest httpRequest, ContentDto content) throws IOException {
        return null;
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(@Context HttpServletRequest httpRequest, FrontpageDto frontpageDto) {
        LOG.info("Frontpage save with dto: " + frontpageDto.toString());
        this.service.save(frontpageDto);
        return Response.status(Response.Status.OK).build();
    }

    @Override
    public Response get(HttpServletRequest httpRequest, String id) {
        return null;
    }

    @Override
    public Response update(HttpServletRequest httpRequest, ContentDto newEntry) throws IOException {
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
