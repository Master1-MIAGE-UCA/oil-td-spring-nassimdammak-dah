package org.example.dice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(DiceApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CommandLineRunner registerWithDiscovery(RestTemplate restTemplate) {
        return args -> {
            String discoveryUrl = "http://localhost:8083/register";
            ServiceInfo serviceInfo = new ServiceInfo("dice", "http://localhost:8082");
            restTemplate.postForObject(discoveryUrl, serviceInfo, Void.class);
        };
    }
}