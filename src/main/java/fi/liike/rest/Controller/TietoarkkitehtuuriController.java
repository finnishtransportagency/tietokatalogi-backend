package fi.liike.rest.Controller;

import fi.liike.rest.Service.*;
import fi.liike.rest.api.ContentDto;
import fi.liike.rest.api.DtoResults;
import fi.liike.rest.api.SearchContent;
import fi.liike.rest.api.dto.RightsDtoImpl;
import fi.liike.rest.api.dto.TietoarkkitehtuuriDto;
import fi.liike.rest.auth.InsufficientRightsException;
import io.swagger.annotations.Api;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.io.IOException;
import java.sql.SQLException;

@Api(value = "Tietoarkkitehtuuri")
@Path("/tietoarkkitehtuuri/")
public class TietoarkkitehtuuriController extends MainController {

    private TietoarkkitehtuuriService service;
    private FyysinenService fyysinenService;
    private LooginenService looginenService;
    private TietolajiService tietolajiService;
    private TietoryhmaService tietoryhmaService;
    private PaatietoryhmaService paatietoryhmaService;

    public TietoarkkitehtuuriController() {
        this.service = new TietoarkkitehtuuriService();
        this.fyysinenService = new FyysinenService();
        this.looginenService = new LooginenService();
        this.tietolajiService = new TietolajiService();
        this.tietoryhmaService = new TietoryhmaService();
        this.paatietoryhmaService = new PaatietoryhmaService();
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Context HttpServletRequest httpRequest, TietoarkkitehtuuriDto content) {
        try {
            validateModificationRights(httpRequest);
            service.create(content);
        }
        catch (InsufficientRightsException e) {
            return Response.status(Response.Status.FORBIDDEN).entity(e.getMessage()).build();
        }
        catch (IOException | SQLException e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@Context HttpServletRequest httpRequest, @DefaultValue("100") @QueryParam("size") String size,
                           @DefaultValue("0") @QueryParam("offset") String offset,
                           @DefaultValue("") @QueryParam("filter") String filter, @DefaultValue("") @QueryParam("sort") String sort) {
        SearchContent searchContent = new SearchContent(filter, sort);
        DtoResults fyysinenResults = super.getAllResults(fyysinenService, searchContent, size, offset);
        DtoResults looginenResults = super.getAllResults(looginenService, searchContent, size, offset);
        DtoResults tietolajiResults = super.getAllResults(tietolajiService, searchContent, size, offset);
        DtoResults tietoryhmaResults = super.getAllResults(tietoryhmaService, searchContent, size, offset);
        DtoResults paatietoryhmaResults = super.getAllResults(paatietoryhmaService, searchContent, size, offset);
        RightsDtoImpl rightsDto = new RightsDtoImpl();
        super.setUpNoRightsToModify(rightsDto, httpRequest);

        return super.buildTietoarkkitehtuuriResponse(fyysinenResults, looginenResults, tietolajiResults,
                tietoryhmaResults, paatietoryhmaResults, rightsDto);
    }



    @Override
    public Response create(HttpServletRequest httpRequest, ContentDto content) throws IOException {
        return null;
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
