package com.terahorse.fobit.controller.pages;

import com.terahorse.fobit.service.VersionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
class MainController {

    private static final String MAIN_PAGE = "main";

    @Autowired
    private VersionService versionService;

    @RequestMapping("/")
    public String getMainPage(Model model) {
        model.addAttribute("version", versionService.getVersion().getVersion());
        model.addAttribute("build", versionService.getVersion().getBuild());
        return MAIN_PAGE;
    }

}
