package fi.liike.rest.Controller;

import com.google.common.collect.ImmutableSet;
import fi.liike.rest.Model.SovellusImportMetadata;
import fi.liike.rest.Service.SovellusService;
import fi.liike.rest.api.ContentDto;
import fi.liike.rest.api.DtoResults;
import fi.liike.rest.api.DtoSovellusResults;
import fi.liike.rest.api.SearchContent;
import fi.liike.rest.api.dto.SovellusDto;
import fi.liike.rest.api.dto.SovellusUpdateDto;
import fi.liike.rest.auth.InsufficientRightsException;
import fi.liike.rest.auth.InvalidTietokatalogiDataException;
import io.swagger.annotations.Api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Api(value = "Sovellushallinta")
@Path("/sovellushallinta/")
public class SovellusController extends MainController {

    private final Logger LOG = LoggerFactory.getLogger(SovellusController.class);

    private SovellusService service;

    public SovellusController() {
        service = new SovellusService();
    }


    //This is used for testing
    // TODO: probably unnecessary
    public Response get(String id) {
        return get(id, true);
    }

    public Response get(String id, Boolean filterDisabled) {
        //Do not make FIM request
        int contentId = 0;
        try {
            contentId = Integer.valueOf(id);
        } catch (NumberFormatException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return buildResponse(service.get(contentId, filterDisabled));
    }

    @Override
    public Response getKasite() {
        return getResources();
    }

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@DefaultValue("100") @QueryParam("size") String size,
                           @DefaultValue("0") @QueryParam("offset") String offset,
                           @DefaultValue("") @QueryParam("filter") String filter, @DefaultValue("") @QueryParam("sort") String sort,
                           @QueryParam("span") List<String> span, @QueryParam("type") List<String> type,
                           @QueryParam("region") List<String> region) {
        LOG.info("Järjestelmä hakee kaiken sohvasta");
        SearchContent searchContent = new SearchContent(filter, sort, true, ImmutableSet.of("nimi", "aliasNimet"));
        DtoSovellusResults results = getAllSovellusResults(searchContent, size, offset);
        if (results == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return buildResponse(results.getHaettavat(), results.getTotalCount(),
                results.getLatestImport(), results.getLatestSuccessfulImport());
    }

    private DtoSovellusResults getAllSovellusResults(SearchContent searchContent, String size, String offset) {
        Integer sizeInt = convertStringToInteger(size);
        Integer offsetInt = convertStringToInteger(offset);
        if (sizeInt == null || offsetInt == null) {
            return null;
        }
        searchContent.setSize(sizeInt);
        searchContent.setOffset(offsetInt);
        DtoResults dtoResults = this.service.getFiltered(searchContent);
        SovellusImportMetadata importMetadata = this.service.getImportMetadata();
        DtoSovellusResults dtoSovellusResults = new DtoSovellusResults(dtoResults.getHaettavat(), dtoResults.getTotalCount(),
                importMetadata.getLatestImport(), importMetadata.getLatestSuccessfulImport());
        return dtoSovellusResults;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@Context HttpServletRequest httpRequest, @PathParam("id") String id) {
        return super.getWithRights(service, id, httpRequest);
    }

    @GET
    @Path("kasite")
    public Response getResources() {
        //return super.getResources(service);
        return Response.status(Response.Status.OK).build();
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Context HttpServletRequest httpRequest, SovellusDto content) {
        try {
            validateWithRights(content, httpRequest);
        }
        catch (InsufficientRightsException e) {
            return Response.status(Response.Status.FORBIDDEN).entity(e.getLocalizedMessage()).build();
        }
        catch (InvalidTietokatalogiDataException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getLocalizedMessage()).build();
        }
        return createAndFetchWithRights(service, content, httpRequest);
    }

    // For abstract testing purpose only
    @Override
    public Response create(HttpServletRequest httpRequest, ContentDto content) {
        return create(httpRequest, (SovellusDto) content);
    }

    @PUT
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@Context HttpServletRequest httpRequest, SovellusUpdateDto content)
            throws IOException, SQLException {
        try {
            validateModificationRights(httpRequest);
        }
        catch (InsufficientRightsException e) {
            return Response.status(Response.Status.FORBIDDEN).entity(e.getLocalizedMessage()).build();
        }
        setRemoteUserToContent(httpRequest, content);
        ContentDto updatedContent = service.update(content);
        setUpNoRightsToModify(content, httpRequest);
        return buildResponse(updatedContent);
    }

    // For abstract testing purpose only
    @Override
    public Response update(HttpServletRequest httpRequest, ContentDto content) throws IOException {
        try {
            return update(httpRequest, (SovellusUpdateDto) content);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @DELETE
    @Path("{id}")
    public Response deactive(@Context HttpServletRequest httpRequest, @PathParam("id") String id) throws IOException, SQLException {
        try {
            validateModificationRights(httpRequest);
        }
        catch (InsufficientRightsException e) {
            return Response.status(Response.Status.FORBIDDEN).entity(e.getLocalizedMessage()).build();
        }
        if (service.deactivateSovellus(Integer.valueOf(id), true) == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.status(Response.Status.OK).build();
    }

    @Override
    public Response delete(HttpServletRequest httpRequest, String id) {
        try {
            return deactive(httpRequest, id);
        } catch (IOException | SQLException e) {
            LOG.error("There was an error: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
