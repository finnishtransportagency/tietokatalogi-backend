package fi.liike.rest.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.common.collect.ImmutableSet;
import fi.liike.rest.Service.TietoomaisuusService;
import fi.liike.rest.api.ContentDto;
import fi.liike.rest.api.SearchContent;
import fi.liike.rest.api.dto.TietoomaisuusDto;
import fi.liike.rest.auth.InsufficientRightsException;
import fi.liike.rest.auth.InvalidTietokatalogiDataException;
import io.swagger.annotations.Api;

import java.io.IOException;
import java.sql.SQLException;


@Api(value = "Tieto-omaisuus")
@Path("/tieto-omaisuus/")
public class TietoomaisuusController extends MainController {

    private TietoomaisuusService service;

    public TietoomaisuusController() {
        service = new TietoomaisuusService();
    }


    // For abstract testing purpose only
    @Override
    public Response update(HttpServletRequest httpRequest, ContentDto content) {
        return this.update(httpRequest, (TietoomaisuusDto) content);
    }

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@DefaultValue("100") @QueryParam("size") String size,
                           @DefaultValue("0") @QueryParam("offset") String offset,
                           @DefaultValue("") @QueryParam("filter") String filter, @DefaultValue("") @QueryParam("sort") String sort) {
        SearchContent searchContent = new SearchContent(filter, sort, ImmutableSet.of("jarjestelman_nimi"));

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
        return buildResponse(service.getScoredResources());
    }

    @Override
    public Response getKasite() {
        return getResources();
    }

    @PUT
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@Context HttpServletRequest httpRequest, TietoomaisuusDto content) {
        // Tieto-omaisuus has an empty name field, so the validation of that field is skipped
        try {
            validateModificationRights(httpRequest);
            validateTietoomaisuusDto(content);
            setRemoteUserToContent(httpRequest, content);
            return buildResponse(service.update(content));
        }
        catch (InsufficientRightsException e) {
            return Response.status(Response.Status.FORBIDDEN).entity(e.getMessage()).build();
        }
        catch (InvalidTietokatalogiDataException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getLocalizedMessage()).build();
        }
        catch (IOException | SQLException e) {
            return Response.serverError().entity(e.getLocalizedMessage()).build();
        }
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Context HttpServletRequest httpRequest, TietoomaisuusDto content) {
        // Tieto-omaisuus has an empty name field, so the validation of that field is skipped
        try {
            validateTietoomaisuusDto(content);
            validateModificationRights(httpRequest);
            setRemoteUserToContent(httpRequest, content);
            return buildResponse(service.save(content));
        }
        catch (InsufficientRightsException e) {
            return Response.status(Response.Status.FORBIDDEN).entity(e.getMessage()).build();
        }
        catch (InvalidTietokatalogiDataException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        catch (SQLException e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    // For abstract testing purpose only
    @Override
    public Response create(HttpServletRequest httpRequest, ContentDto content) {
        return this.create(httpRequest, (TietoomaisuusDto) content);
    }

    private void validateTietoomaisuusDto(TietoomaisuusDto content) throws InvalidTietokatalogiDataException {
        if (content.getTietojarjestelma_tunnus() == null)
            throw new InvalidTietokatalogiDataException("Tietojärjestelmä must not be null");
    }

}
