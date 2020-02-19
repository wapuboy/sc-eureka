package com.example.sc_eureka_server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
public class FeignHelloController {
    @Autowired
    DiscoveryClient client;

    @RequestMapping("/echo")
    public String echoTest() {
        List<ServiceInstance> instances = client.getInstances("eureka-client-service");

        ServiceInstance selectedInstance = instances
                .get(new Random().nextInt(instances.size()));

        return "Echo : " + selectedInstance.getServiceId() + ":"
                + selectedInstance.getHost() + ":" + selectedInstance.getPort();
    }

}
