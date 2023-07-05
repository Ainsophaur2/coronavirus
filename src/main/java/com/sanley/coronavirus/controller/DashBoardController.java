package com.sanley.coronavirus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

@Controller
public class DashBoardController {
    @RequestMapping(value = "/noAccess")
    public String toNoAccess() {
        return "noAccess";
    }
}
