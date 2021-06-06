package fi.liike.config;

import io.swagger.jaxrs.config.BeanConfig;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

// Sets configurations for swagger. This is loaded in web.xml.
public class Bootstrap extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.0");
        beanConfig.setTitle("Tietokatalogin REST-rajapinta");
        beanConfig.setDescription("Sovelluksen käyttämä rajapinta");
        beanConfig.setBasePath("/tietokatalogi/rest");
        beanConfig.setResourcePackage("fi.liike.rest");
        beanConfig.setScan(true);
    }
}