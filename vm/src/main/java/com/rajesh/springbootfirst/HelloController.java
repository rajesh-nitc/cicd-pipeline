package com.rajesh.springbootfirst;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;

@RestController
public class HelloController {

    @Value("${MYSQL_PORT}")
    private String MYSQL_PORT;

    @RequestMapping("/api")
    public String index() {
        return "hello world!, mysql port: " + MYSQL_PORT;
    }

}