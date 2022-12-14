package fi.liike.rest.Controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fi.liike.rest.auth.InsufficientRightsException;
import fi.liike.rest.auth.InvalidTietokatalogiDataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fi.liike.rest.Service.JarjestelmaService;
import fi.liike.rest.api.ContentDto;
import fi.liike.rest.api.SearchContent;
import fi.liike.rest.api.dto.JarjestelmaDto;
import io.swagger.annotations.Api;

import static java.lang.String.format;

@Api(value = "Järjestelmä")
@Path("/jarjestelma/")
public class JarjestelmaController extends MainController {
	private final Logger LOG = LoggerFactory.getLogger(JarjestelmaController.class);
	private JarjestelmaService service;

	@Context
	private ServletContext servletContext;

	public JarjestelmaController() {
		service = new JarjestelmaService();
	}

	@POST
	@Path("")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(@Context HttpServletRequest httpRequest, JarjestelmaDto content) {
		try {
			validateModificationRights(httpRequest);
			validateWithRights(content, httpRequest);
		}
		catch (InsufficientRightsException e) {
			return Response.status(Response.Status.FORBIDDEN).entity(e.getMessage()).build();
		}
		catch (InvalidTietokatalogiDataException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
		return createAndFetchWithRights(service, content, httpRequest);
	}

	// For abstract testing purpose only
	@Override
	public Response create(HttpServletRequest httpRequest, ContentDto content) {
		return create(httpRequest, (JarjestelmaDto) content);
	}

	@PUT
	@Path("")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(@Context HttpServletRequest httpRequest, JarjestelmaDto content) {
		try {
			validateModificationRights(httpRequest);
			validateWithRights(content, httpRequest);
			setRemoteUserToContent(httpRequest, content);
			ContentDto updatedContent = service.update(content);
			setUpNoRightsToModify(updatedContent, httpRequest);
			return buildResponse(updatedContent);
		}
		catch (InsufficientRightsException e) {
			return Response.status(Response.Status.FORBIDDEN).entity(e.getMessage()).build();
		}
		catch (InvalidTietokatalogiDataException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
		 catch (IOException | SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	// For abstract testing purpose only
	@Override
	public Response update(HttpServletRequest httpRequest, ContentDto content) {
		return update(httpRequest, (JarjestelmaDto) content);
	}

	@GET
	@Path("")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll(@DefaultValue("100") @QueryParam("size") String size,
						   @DefaultValue("0") @QueryParam("offset") String offset,
						   @DefaultValue("") @QueryParam("filter") String filter, @DefaultValue("") @QueryParam("sort") String sort,
						   @QueryParam("span") List<String> span, @QueryParam("type") List<String> type,
						   @QueryParam("region") List<String> region,
						   @QueryParam("owning_organization") List<String> owning_organization) {
		LOG.info("Järjestelmä hakee kaiken");
		SearchContent searchContent = new SearchContent(filter, sort);
		searchContent.addFields("elinkaaritila", span);
		searchContent.addFields("jarjestelmatyyppi", type);
		searchContent.addFields("jarjestelmaalue", region);
		searchContent.addFields("omistava_organisaatio", owning_organization);

		return super.getAll(service, searchContent, size, offset);
	}

	@GET
	@Path("minimalAll")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllMinimal(@DefaultValue("100") @QueryParam("size") String size,
								  @DefaultValue("0") @QueryParam("offset") String offset,
								  @DefaultValue("") @QueryParam("filter") String filter,
								  @DefaultValue("") @QueryParam("sort") String sort,
								  @QueryParam("span") List<String> span, @QueryParam("type") List<String> type,
								  @QueryParam("region") List<String> region) {
		LOG.info("Järjestelmä hakee kaiken");
		SearchContent searchContent = new SearchContent(filter, sort);
		searchContent.addFields("elinkaaritila", span);
		searchContent.addFields("jarjestelmatyyppi", type);
		searchContent.addFields("jarjestelmaalue", region);

		return super.getAllMinimal(service, searchContent, size, offset);
	}

	@GET
	@Path("generalAll")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllGeneral(@DefaultValue("100") @QueryParam("size") String size,
								  @DefaultValue("0") @QueryParam("offset") String offset,
								  @DefaultValue("") @QueryParam("filter") String filter,
								  @DefaultValue("") @QueryParam("sort") String sort,
								  @QueryParam("span") List<String> span, @QueryParam("type") List<String> type,
								  @QueryParam("region") List<String> region) {
		LOG.info("Järjestelmä hakee kaiken");
		SearchContent searchContent = new SearchContent(filter, sort);
		searchContent.addFields("elinkaaritila", span);
		searchContent.addFields("jarjestelmatyyppi", type);
		searchContent.addFields("jarjestelmaalue", region);

		return super.getAllGeneral(service, searchContent, size, offset);
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


	@GET
	@Path("/generateReport/{name}/{id}")
	@Produces("application/pdf")
	public Response tietojarjestelmaseloste(@PathParam("name") String name, @PathParam("id") int id) {
		ByteArrayOutputStream document = service.createDescriptionDocument(name, id, servletContext);
		if (document == null)
			return Response.serverError().build();

		Response.ResponseBuilder responseBuilder = Response.ok(document.toByteArray());
		responseBuilder.type("application/pdf");
		responseBuilder.header("Content-Disposition", format("filename=%s.pdf", name));
		return responseBuilder.build();
	}


}
