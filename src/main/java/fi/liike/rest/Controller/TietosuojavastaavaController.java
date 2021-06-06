package fi.liike.rest.Controller;

import fi.liike.rest.Service.TietosuojavastaavaService;
import fi.liike.rest.api.ContentDto;
import fi.liike.rest.api.SearchContent;
import fi.liike.rest.api.dto.TietosuojavastaavaDto;
import io.swagger.annotations.Api;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(value = "Tietosuojavastaava")
@Path("/tietosuojavastaava/")
public class TietosuojavastaavaController extends MainController {
    private final TietosuojavastaavaService service;

    public TietosuojavastaavaController() {
        service = new TietosuojavastaavaService();
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Context HttpServletRequest httpRequest, TietosuojavastaavaDto content) {
        return create(service, content, httpRequest);
    }

    // For abstract testing purpose only
    @Override
    public Response create(HttpServletRequest httpRequest, ContentDto content) {
        return create(httpRequest, (TietosuojavastaavaDto) content);
    }

    @PUT
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@Context HttpServletRequest httpRequest, TietosuojavastaavaDto content) {
        return super.updateWithRights(this.service, content, httpRequest);
    }

    // For abstract testing purpose only
    @Override
    public Response update(HttpServletRequest httpRequest, ContentDto content) {
        return update(httpRequest, (TietosuojavastaavaDto) content);
    }

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@DefaultValue("100") @QueryParam("size") String size,
                           @DefaultValue("0") @QueryParam("offset") String offset,
                           @DefaultValue("") @QueryParam("filter") String filter, @DefaultValue("") @QueryParam("sort") String sort) {
        SearchContent searchContent = new SearchContent(filter, sort);

        return super.getAll(service, searchContent, size, offset);
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@Context HttpServletRequest httpRequest, @PathParam("id") String id) {
        return super.getWithRights(service, id, httpRequest);
    }


    @Override
    @DELETE
    @Path("{id}")
    public Response delete(@Context HttpServletRequest httpRequest, @PathParam("id") String id) {
        return super.delete(service, id, httpRequest);
    }

    @GET
    @Path("kasite")
    public Response getResources() {
        return super.getResources(service);
    }

    @Override
    public Response getKasite() {
        return getResources();
    }
}
