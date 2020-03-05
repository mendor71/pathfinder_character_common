package com.mendor71.pathfinder.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {

    @RequestMapping(path = "/")
    public String hello() {
        return "<h1>HELLO!<h1>";
    }
}
