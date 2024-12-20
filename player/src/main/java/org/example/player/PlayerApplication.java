package org.example.player;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class PlayerApplication {
    public static void main(String[] args) {
        SpringApplication.run(PlayerApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CommandLineRunner registerWithDiscovery(RestTemplate restTemplate) {
        return args -> {
            String discoveryUrl = "http://localhost:8083/register";
            ServiceInfo serviceInfo = new ServiceInfo("player", "http://localhost:8081");
            restTemplate.postForObject(discoveryUrl, serviceInfo, Void.TYPE);
        };
    }
}