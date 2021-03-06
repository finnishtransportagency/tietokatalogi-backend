package fi.liike.rest.Controller;

import fi.liike.rest.Service.TermilomakeService;
import fi.liike.rest.api.ContentDto;
import fi.liike.rest.api.SearchContent;
import fi.liike.rest.api.dto.TermilomakeDto;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Api(value = "Sanasto")
@Path("/sanasto/")
public class TermilomakeController extends MainController {

    private final Logger LOG = LoggerFactory.getLogger(TermilomakeController.class);
    private TermilomakeService service;

    public TermilomakeController() {
        service = new TermilomakeService();
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Context HttpServletRequest httpRequest, TermilomakeDto content) throws IOException {
        return create(service, content, httpRequest);
    }

    // For abstract testing purpose only
    @Override
    public Response create(HttpServletRequest httpRequest, ContentDto content) throws IOException {
        return create(httpRequest, (TermilomakeDto) content);
    }

    @PUT
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@Context HttpServletRequest httpRequest, TermilomakeDto content) {
        return super.updateWithRights(this.service, content, httpRequest);
    }

    // For abstract testing purpose only
    @Override
    public Response update(HttpServletRequest httpRequest, ContentDto content) throws IOException {
        return update(httpRequest, (TermilomakeDto) content);
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@Context HttpServletRequest httpRequest, @PathParam("id") String id) {
        return super.getWithRights(service, id, httpRequest);
    }

    @GET
    @Path("vid/{vid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getvid(@Context HttpServletRequest httpServletRequest, @PathParam("vid") String vid){
        String id = vid.substring(1);
        return get(httpServletRequest, id);
    }



    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@DefaultValue("100") @QueryParam("size") String size,
                           @DefaultValue("0") @QueryParam("offset") String offset,
                           @DefaultValue("") @QueryParam("filter") String filter,
                           @DefaultValue("") @QueryParam("sort") String sort) {
//        Custom search fields for filter termilomake
        Set<String> searchFields = new HashSet<>(Arrays.asList("nimi", "ensisij_termi"));
        SearchContent searchContent = new SearchContent(filter, sort, searchFields);

        return super.getAll(service, searchContent, size, offset);
    }

    @Override
    @DELETE
    @Path("{id}")
    public Response delete(@Context HttpServletRequest httpRequest, @PathParam("id") String id) {
        return super.delete(service, id ,httpRequest);
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
