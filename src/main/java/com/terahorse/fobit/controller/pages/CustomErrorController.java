package com.terahorse.fobit.controller.pages;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
class CustomErrorController implements ErrorController {

    private static final String ERROR_PAGE = "/error.html";

    @Override
    public String getErrorPath() {
        return getPage();
    }

    @RequestMapping("/error")
    public String getErrorPage() {
        return getPage();
    }

    private String getPage() {
        return ERROR_PAGE;
    }

}
