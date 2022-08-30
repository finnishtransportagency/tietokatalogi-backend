package fi.liike.rest.api.dto;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;

public class BasicConverter {
    private final Logger LOG = LoggerFactory.getLogger(BasicConverter.class);

    protected <T, U> void convert(T source, U target) {
        try {
            BeanUtils.copyProperties(source, target);
        } catch (IllegalAccessException | InvocationTargetException e) {
            LOG.error("Could not copy properties to BeanUtils! Error Message: " + e.getMessage());
        }
    }
}
