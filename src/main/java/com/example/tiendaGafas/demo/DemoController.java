package com.example.tiendaGafas.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/v1")
public class DemoController {

    @PostMapping(value = "demo")
    public String wlcome() {
        
        return "Welcome from secure endpoint";
    }
    
}
