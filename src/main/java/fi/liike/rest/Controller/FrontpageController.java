package fi.liike.rest.Controller;

import com.google.gson.Gson;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import fi.liike.rest.Service.FrontpageService;
import fi.liike.rest.api.ContentDto;
import fi.liike.rest.api.dto.FrontpageDto;
import fi.liike.rest.api.dto.FrontpageUploadResponseContents;
import fi.liike.rest.api.dto.FrontpageUploadResponseDto;
import fi.liike.rest.util.S3ClientUtil;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Optional;

@Api(value = "Etusivu")
@Path("/frontpage/")
public class FrontpageController extends MainController {
    private FrontpageService service;
    private final Logger LOG = LoggerFactory.getLogger(FrontpageController.class);


    public FrontpageController() {
        this.service = new FrontpageService();
    }

    @Override
    public Response create(HttpServletRequest httpRequest, ContentDto content) throws IOException {
        return null;
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(@Context HttpServletRequest httpRequest, FrontpageDto frontpageDto) {
        LOG.info("Frontpage save with dto: " + frontpageDto.toString());
        this.service.save(frontpageDto);
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@Context HttpServletRequest httpRequest) {
        return buildResponse(this.service.get());
    }

    private Response buildResponse(Optional<FrontpageDto> frontpageDtoOptional) {
        if (!frontpageDtoOptional.isPresent())
            return Response.status(Response.Status.NOT_FOUND).build();
        Gson gson = new Gson();
        String jsonStr = gson.toJson(frontpageDtoOptional.get());
        return Response.ok(jsonStr, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("buckets")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBuckets(@Context HttpServletRequest httpRequest) {
        S3Client s3 = S3ClientUtil.getInstance();
        ListBucketsRequest listBucketsRequest = ListBucketsRequest.builder().build();
        ListBucketsResponse listBucketsResponse = s3.listBuckets(listBucketsRequest);
        listBucketsResponse.buckets().stream().forEach(x -> System.out.println(x.name()));
        return Response.ok().build();
    }

    @GET
    @Path("image/{name}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getImage(@Context HttpServletRequest httpRequest, @PathParam("name") String name) {
        ByteBuffer imageBytes;
        try {
            imageBytes = service.getImage(name);
        } catch (IOException e) {
            return Response.serverError().entity("Failed conversion to ByteArray").build();
        }
        return Response.ok(imageBytes.array()).header("content-disposition", "inline; filename = " + name).build();
    }

    @POST
    @Path("image")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(
            @Context HttpServletRequest httpRequest,
            @FormDataParam("image") InputStream inputStream,
            @FormDataParam("image") FormDataContentDisposition fileDetail) {
        LOG.info("Save image");
        LOG.debug(inputStream.toString());
        LOG.debug(fileDetail.toString());

        String generatedName;
        try {
            generatedName = service.uploadImage(inputStream, fileDetail);
        } catch (IOException e) {
            return Response.serverError().entity("Failed conversion to ByteArray").build();
        }
        FrontpageUploadResponseDto uploadResponseDto = new FrontpageUploadResponseDto(
                new FrontpageUploadResponseContents(
                "tietokatalogi/rest/frontpage/image/" + generatedName
        )); // {"data": {"filePath": "<filePath>"}}
        Gson gson = new Gson();
        String responseString = gson.toJson(uploadResponseDto);
        return Response.ok(responseString).build();
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
