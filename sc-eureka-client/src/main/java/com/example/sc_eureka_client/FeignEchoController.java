package com.example.sc_eureka_client;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@EnableDiscoveryClient
@RestController
@EnableFeignClients

public class FeignEchoController {
    @Autowired
    HelloClient client;

    @RequestMapping("/")
    public String hello() {
        return client.hello();
    }

    @RequestMapping("/{name}")
    public String hello(@PathVariable("name") String name) {
        return client.hello0(name);
    }

    @FeignClient("eureka-client-service")
    interface HelloClient {
        @RequestMapping(value = "/echo", method = GET)
        String hello();
        @RequestMapping(value = "/hello/{name}", method = GET)
        String hello0(@PathVariable("name") String name);
    }
}
