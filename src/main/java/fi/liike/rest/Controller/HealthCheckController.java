package fi.liike.rest.Controller;

import fi.liike.rest.Dao.HibernateDao;
import io.swagger.annotations.Api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Api(value = "Health")
@Path("/health/")
public class HealthCheckController extends HibernateDao {
    private final Logger LOG = LoggerFactory.getLogger(HealthCheckController.class);

    @GET
    @Path("")
    public Response getHealth() {
        LOG.debug("Health check called");
//        testSimpleSQLquery();
        return Response.ok().build();
    }
}
