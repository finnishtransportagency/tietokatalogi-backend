package fi.liike.rest.Controller;

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

import fi.liike.rest.Service.TietolajiService;
import fi.liike.rest.api.ContentDto;
import fi.liike.rest.api.SearchContent;
import fi.liike.rest.api.dto.TietolajiDto;
import io.swagger.annotations.Api;

import java.io.IOException;

@Api(value = "Tietolaji")
@Path("/tietolaji/")
public class TietolajiController extends MainController {

	private TietolajiService service;

	public TietolajiController() {
		service = new TietolajiService();
	}

	@POST
	@Path("")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(@Context HttpServletRequest httpRequest, TietolajiDto content) throws IOException {
		return create(service, content, httpRequest);
	}

	// For abstract testing purpose only
	@Override
	public Response create(HttpServletRequest httpRequest, ContentDto content) throws IOException {
		return create(httpRequest, (TietolajiDto) content);
	}

	@PUT
	@Path("")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(@Context HttpServletRequest httpRequest, TietolajiDto content) {
		return super.updateWithRights(this.service, content, httpRequest);
	}

	// For abstract testing purpose only
	@Override
	public Response update(HttpServletRequest httpRequest, ContentDto content) throws IOException {
		return update(httpRequest, (TietolajiDto) content);
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
