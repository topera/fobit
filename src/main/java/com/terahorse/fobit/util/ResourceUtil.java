package com.terahorse.fobit.util;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class ResourceUtil {

    private static final Logger LOG = LoggerFactory.getLogger(ResourceUtil.class);

    public String readFile(String fileName) {
        try(InputStream resource = getResource(fileName)) {
            if (resource != null) {
                return IOUtils.toString(resource, "UTF-8");
            }
        } catch (IOException e) {
            LOG.error("Error converting content of file " + fileName, e);
        }
        return null;
    }

    public InputStream getResource(String fileName) {
        return getClass().getClassLoader().getResourceAsStream(fileName);
    }

}
