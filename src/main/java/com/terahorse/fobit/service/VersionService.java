package com.terahorse.fobit.service;

import com.terahorse.fobit.model.Version;
import com.terahorse.fobit.util.ResourceUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VersionService {

    private static final String VERSION_TXT = "version.txt";
    private static final String BUILD_TXT = "build.txt";
    public static final String NOT_AVAILABLE = "NOT-AVAILABLE";

    @Autowired
    private ResourceUtil resourceUtil;

    public Version getVersion(){
        String version = resourceUtil.readFile(VERSION_TXT);
        String build = resourceUtil.readFile(BUILD_TXT);
        if (build != null && version != null) {
            return new Version(version, build);
        }
        return new Version(NOT_AVAILABLE, NOT_AVAILABLE);
    }

}
