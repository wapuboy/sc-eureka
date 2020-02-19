package com.example.sc_eureka_client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Configuration
public class AskController {

    @Value("${spring.application.name}")
    private String name;

    @Autowired
    RestTemplate restTemplate;

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }


    @RequestMapping(value="/ask")
    public String ask(){
        String res = restTemplate.getForEntity(
                "http://EUREKA-CLIENT-SERVICE/hello/{name}",
                String.class,
                name).getBody();

        return res;
    }

    @RequestMapping(value="/ask2")
    public  String ask2(){
        return "ask2";
    }
    @RequestMapping(value="/ask3/{para}")
    public  String ask3(@PathVariable("para") String para){
        String res = restTemplate.getForEntity(
                "http://"+para+"/hello/{name}",
                String.class,
                name).getBody();

        return res;
    }

}
