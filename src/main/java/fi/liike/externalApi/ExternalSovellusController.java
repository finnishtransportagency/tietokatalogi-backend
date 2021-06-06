package fi.liike.externalApi;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.common.collect.ImmutableSet;
import fi.liike.rest.Controller.MainController;
import fi.liike.rest.Model.PersonRole;
import fi.liike.rest.Service.SovellusService;
import fi.liike.rest.api.ContentDto;
import fi.liike.rest.api.dto.ExternalSovellusCSVDto;
import fi.liike.rest.api.dto.ExternalSovellusCSVinitialDto;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Api
@Path("/sovellus/")
public class ExternalSovellusController extends MainController {

    private final Logger LOG = LoggerFactory.getLogger(ExternalSovellusController.class);
    private SovellusService sovellusService;

    public ExternalSovellusController() {
        sovellusService = new SovellusService();
    }

    @POST
    @Path("")
    @Consumes("text/csv")
    @Produces(MediaType.APPLICATION_JSON)
    public Response importExternalSovellusList(@Context HttpServletRequest httpRequest, String csv) throws IOException, SQLException {
        LOG.info("Starting to import sovha CSV");
        Set<PersonRole> includeRoles = ImmutableSet.of(PersonRole.TUOTANTOON_HYVAKSYNYT, PersonRole.ASENNUKSEN_HYVAKSYNYT);
        try {
            List<ExternalSovellusCSVDto> sovellusList = mapCSVToList(csv, ExternalSovellusCSVDto.class, ExternalSovellusCSVDto.csvSchema);
            LOG.info("Sovha CSV converted. Size: {}", sovellusList.size());
            sovellusService.importExternalSovellusCSVList(sovellusList, includeRoles);
            sovellusService.saveImportMetadata(true);
        }
        catch (Exception e) {
            LOG.error("Sovha import ended (failure)", e);
            sovellusService.saveImportMetadata(false);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        LOG.info("Sovha import ended (success)");
        return Response.status(Response.Status.CREATED).build();
    }


    @POST
    @Path("initial-csv-import")
    @Consumes("text/csv")
    @Produces(MediaType.APPLICATION_JSON)
    public Response initialImportExternalSovellusList(@Context HttpServletRequest httpRequest, String csv) throws IOException, SQLException {
        LOG.info("Starting to import initial sovha CSV");
        Set<PersonRole> includeRoles = ImmutableSet.copyOf(PersonRole.values());
        try {
            List<ExternalSovellusCSVinitialDto> sovellusList = mapCSVToList(csv, ExternalSovellusCSVinitialDto.class, ExternalSovellusCSVDto.csvSchema);
            LOG.info("Sovha CSV converted. Size: {}", sovellusList.size());
            sovellusService.importExternalSovellusCSVList(sovellusList, includeRoles);
            sovellusService.saveImportMetadata(true);
        } catch (Exception e) {
            LOG.error("Sovha initial import ended (failure)", e);
            sovellusService.saveImportMetadata(false);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        LOG.info("Sovha initial import ended (success)");
        return Response.status(Response.Status.CREATED).build();
    }


    public <T> List<T> mapCSVToList(String csv, Class<T> className, CsvSchema schema) throws IOException {
        CsvMapper mapper = new CsvMapper();

        MappingIterator<T> mappingIterator = mapper.readerFor(className)
                .with(schema.withHeader()).readValues(csv);

        List<T> sovellusList = new ArrayList<>();
        while (mappingIterator.hasNextValue()) {
            sovellusList.add(mappingIterator.nextValue());

        }
        return sovellusList;
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
