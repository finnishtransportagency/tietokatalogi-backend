package fi.liike.rest.Controller;

import fi.liike.rest.Service.TietojarjestelmapalveluService;
import fi.liike.rest.api.ContentDto;
import fi.liike.rest.api.SearchContent;
import fi.liike.rest.api.dto.TietojarjestelmapalveluDto;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Api(value = "Tietojärjestelmapalvelu")
@Path("/tietojarjestelmapalvelu/")
public class TietojarjestelmapalveluController extends MainController {

    private final Logger LOG = LoggerFactory.getLogger(TietojarjestelmapalveluController.class);
    private TietojarjestelmapalveluService service;

    public TietojarjestelmapalveluController() {
        this.service = new TietojarjestelmapalveluService();
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Context HttpServletRequest httpRequest, TietojarjestelmapalveluDto content) throws IOException {
        return super.create(service, content, httpRequest);
    }

    @Override
    public Response create(HttpServletRequest httpRequest, ContentDto content) throws IOException {
        return create(httpRequest, (TietojarjestelmapalveluDto) content);
    }


    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@Context HttpServletRequest httpRequest, @PathParam("id") String id) {
        return super.getWithRights(this.service, id, httpRequest);
    }

    @Override
    public Response update(HttpServletRequest httpRequest, ContentDto newEntry) throws IOException {
        return this.update(httpRequest, (TietojarjestelmapalveluDto) newEntry);
    }

    @Override
    @DELETE
    @Path("{id}")
    public Response delete(@Context HttpServletRequest httpRequest, @PathParam("id") String id) {
        return super.delete(service, id, httpRequest);
    }

    @Override
    public Response getKasite() {
        return getResources();
    }

    @GET
    @Path("kasite")
    public Response getResources() {
        return super.getResources(this.service);
    }

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@DefaultValue("100") @QueryParam("size") String size,
                           @DefaultValue("0") @QueryParam("offset") String offset,
                           @DefaultValue("") @QueryParam("filter") String filter,
                           @DefaultValue("") @QueryParam("sort") String sort) {
        LOG.info("Haetaan tietojärjestelmäpalvelut");
        SearchContent searchContent = new SearchContent(filter, sort);
        return super.getAll(service, searchContent, size, offset);
    }

    @GET
    @Path("minimalAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllMinimal(@DefaultValue("100") @QueryParam("size") String size,
                                  @DefaultValue("0") @QueryParam("offset") String offset,
                                  @DefaultValue("") @QueryParam("filter") String filter,
                                  @DefaultValue("") @QueryParam("sort") String sort) {
        LOG.info("Järjestelmä hakee tietojärjestelmäpalveluja");
        SearchContent searchContent = new SearchContent(filter, sort);
        return super.getAllMinimal(service, searchContent, size, offset);
    }

    @PUT
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@Context HttpServletRequest httpRequest, TietojarjestelmapalveluDto content) throws IOException {
        return super.updateWithRights(this.service, content, httpRequest);
    }
}
