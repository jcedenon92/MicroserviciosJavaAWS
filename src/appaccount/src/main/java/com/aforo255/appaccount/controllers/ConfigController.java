package com.aforo255.appaccount.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/config")
public class ConfigController {
    
    @Value("${enviroment.name.app}")
    private String envName;

    @GetMapping()
    public String get() {
        return "Running from ... " + envName;
    }
}
