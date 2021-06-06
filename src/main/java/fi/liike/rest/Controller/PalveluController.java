package fi.liike.rest.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fi.liike.rest.Service.PalveluService;
import fi.liike.rest.api.ContentDto;
import fi.liike.rest.api.SearchContent;
import fi.liike.rest.api.dto.PalveluDto;
import io.swagger.annotations.Api;

@Api(value = "Palvelu")
@Path("/palvelu/")
public class PalveluController extends MainController {

	private final Logger LOG = LoggerFactory.getLogger(PalveluController.class);
	private PalveluService service;

	public PalveluController() {
		service = new PalveluService();
	}

	@POST
	@Path("")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(@Context HttpServletRequest httpRequest, PalveluDto content) throws IOException {
		return create(service, content, httpRequest);
	}

	// For abstract testing purpose only
	@Override
	public Response create(HttpServletRequest httpRequest, ContentDto content) throws IOException {
		return create(httpRequest, (PalveluDto) content);
	}

	@PUT
	@Path("")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(@Context HttpServletRequest httpRequest, PalveluDto content) throws IOException {
		return super.updateWithRights(this.service, content, httpRequest);
	}

	// For abstract testing purpose only
	@Override
	public Response update(HttpServletRequest httpRequest, ContentDto content) throws IOException {
		return update(httpRequest, (PalveluDto) content);
	}

	@GET
	@Path("")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll(@DefaultValue("100") @QueryParam("size") String size,
			@DefaultValue("0") @QueryParam("offset") String offset, @DefaultValue("") @QueryParam("filter") String filter,
			@DefaultValue("") @QueryParam("sort") String sort, @QueryParam("toplevel") List<String> topLevel,
			@QueryParam("header") List<String> header) {
		SearchContent searchContent = new SearchContent(filter, sort);
		searchContent.addFields("otsikko", header);
		searchContent.addFields("ylataso", topLevel);

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
