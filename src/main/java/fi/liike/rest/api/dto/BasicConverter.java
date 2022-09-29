package fi.liike.rest.api.dto;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.SqlTimestampConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;

public class BasicConverter {
    private final Logger LOG = LoggerFactory.getLogger(BasicConverter.class);

    protected <T, U> void convert(T source, U target) {
        try {
            // allow null values in custom conversion
            BeanUtilsBean.getInstance().getConvertUtils().register(true, false, 0);
            ConvertUtils.register(new SqlTimestampConverter(), Timestamp.class);
            BeanUtils.copyProperties(target, source);
        } catch (IllegalAccessException | InvocationTargetException e) {
            LOG.error("Could not copy properties to BeanUtils! Error Message: " + e.getMessage());
        }
    }
}
