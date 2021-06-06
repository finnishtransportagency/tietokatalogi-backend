package fi.liike.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configurations {
    private static final Logger LOG = LoggerFactory.getLogger(Configurations.class);

    private static Properties props;
    private static final String configPath = "./config.properties";
    public static String baseFIMurl;

    public static void readConfigurations() {
        if (props == null) {
            props = new Properties();
            InputStream input = null;

            try {
                input = Configurations.class.getClassLoader().getResourceAsStream(configPath);
                props.load(input);
                baseFIMurl = props.getProperty("baseFIMurl");
            } catch (IOException e) {
                LOG.error("Couldn't load " + configPath + ". Url was not found.", e);
            } finally {
                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException e) {
                        LOG.error("Could not close InputStream", e);
                    }
                }
            }
        }

    }

}
