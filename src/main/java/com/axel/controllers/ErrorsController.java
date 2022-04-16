package com.axel.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorsController implements ErrorController {

    public static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String foundError() {
        return "error";
    }
}
