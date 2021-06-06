package fi.liike.externalApi;

import fi.liike.rest.Controller.JarjestelmaController;
import fi.liike.rest.Controller.MainController;
import fi.liike.rest.Service.JarjestelmaService;
import fi.liike.rest.api.ContentDto;
import fi.liike.rest.api.SearchContent;
import fi.liike.rest.util.Utils;
import io.swagger.annotations.Api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.text.ParseException;

@Api
@Path("/jarjestelma/")
public class ExternalJarjestelmaController extends MainController {

    private final Logger LOG = LoggerFactory.getLogger(JarjestelmaController.class);
    private JarjestelmaService jarjestelmaService;

    public ExternalJarjestelmaController() {
        jarjestelmaService = new JarjestelmaService();
    }

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFilteredForSahke(@DefaultValue("-1") @QueryParam("size") Integer size,
                                  @DefaultValue("0") @QueryParam("offset") Integer offset,
                                  @DefaultValue("") @QueryParam("filter") String filter,
                                  @DefaultValue("") @QueryParam("sort") String sort,
                                  @DefaultValue("null") @QueryParam("filterModifiedAfterDate") String modAfterDate) throws IOException {
        LOG.info("Haetaan Sähkettä varten filtteröidyt tiedot external-rajapinnan kautta");

        SearchContent searchContent = null;

        try {
            searchContent = new SearchContent(filter, sort, Utils.urlParamStrToDate(modAfterDate));
        } catch (ParseException e) {
            LOG.error("There was an error: " + e.getMessage());
            LOG.error("Date format is invalid: " + modAfterDate);
            throw new IOException("Date format is invalid: " + modAfterDate);
        }

        return super.getFilteredForSahke(jarjestelmaService, searchContent, size, offset);
    }

    @Override
    public Response create(HttpServletRequest httpRequest, ContentDto content) throws IOException {
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @Override
    public Response update(HttpServletRequest httpRequest, ContentDto content) throws IOException {
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @Override
    public Response get(HttpServletRequest httpRequest, String id) {
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @Override
    @DELETE
    @Path("{id}")
    public Response delete(@Context HttpServletRequest httpRequest, @PathParam("id") String id) {
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @Override
    public Response getKasite() {
        return Response.status(Response.Status.FORBIDDEN).build();
    }
}
