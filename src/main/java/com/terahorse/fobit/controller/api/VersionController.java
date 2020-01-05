package com.terahorse.fobit.controller.api;

import com.terahorse.fobit.model.Version;
import com.terahorse.fobit.service.VersionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {

    @Autowired
    private VersionService versionService;

    @RequestMapping("/api/version")
    public Version getVersion() {
        return versionService.getVersion();
    }

}