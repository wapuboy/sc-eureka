package com.example.sc_eureka_server;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SayHelloCOntroller {
    @RequestMapping(value="/hello/{name}")
    public String sayHello(@PathVariable("name") String name){
        return "Hello ".concat(name).concat(", computer 5678 !");
    }
}
