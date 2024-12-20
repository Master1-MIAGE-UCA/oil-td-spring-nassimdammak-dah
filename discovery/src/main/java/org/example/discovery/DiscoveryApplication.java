package org.example.discovery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@SpringBootApplication
@EnableScheduling
public class DiscoveryApplication {

    private static final Logger logger = LoggerFactory.getLogger(DiscoveryApplication.class);
    private final ConcurrentMap<String, ServiceInfo> services = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryApplication.class, args);
    }

    @PostMapping("/register")
    public void registerService(@RequestBody ServiceInfo serviceInfo) {
        services.put(serviceInfo.getName(), serviceInfo);
        logger.info("Service registered: {} at {}", serviceInfo.getName(), serviceInfo.getUrl());
    }

    @GetMapping("/services")
    public List<ServiceInfo> getServices() {
        logger.info("Client requested list of services");
        return new ArrayList<>(services.values());
    }
}