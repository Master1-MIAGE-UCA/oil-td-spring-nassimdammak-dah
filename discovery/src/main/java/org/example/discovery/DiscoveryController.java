package org.example.discovery.controller;

import org.example.discovery.ServiceInfo;
import org.example.discovery.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DiscoveryController {

    @Autowired
    private ServiceRegistry serviceRegistry;

    @PostMapping("/register")
    public void registerService(@RequestBody ServiceInfo serviceInfo) {
        serviceRegistry.registerService(serviceInfo);
    }

    @GetMapping("/services")
    public List<ServiceInfo> getServices() {
        return serviceRegistry.getServices();
    }
}