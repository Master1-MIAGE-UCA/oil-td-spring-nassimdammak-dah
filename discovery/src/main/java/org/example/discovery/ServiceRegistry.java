package org.example.discovery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class ServiceRegistry {

    private static final Logger logger = LoggerFactory.getLogger(ServiceRegistry.class);
    private final ConcurrentMap<String, Long> serviceTimestamps = new ConcurrentHashMap<>();
    private final ConcurrentMap<String, ServiceInfo> services = new ConcurrentHashMap<>();
    private static final long EXPIRATION_TIME = 60000; // 1 minute

    public void registerService(ServiceInfo serviceInfo) {
        services.put(serviceInfo.getName(), serviceInfo);
        serviceTimestamps.put(serviceInfo.getName(), System.currentTimeMillis());
        logger.info("Service registered: {} at {}", serviceInfo.getName(), serviceInfo.getUrl());
    }

    public List<ServiceInfo> getServices() {
        return new ArrayList<>(services.values());
    }

    @Scheduled(fixedRate = 30000) // Run every 30 seconds
    public void cleanupServices() {
        long now = System.currentTimeMillis();
        serviceTimestamps.forEach((name, timestamp) -> {
            if (now - timestamp > EXPIRATION_TIME) {
                services.remove(name);
                serviceTimestamps.remove(name);
                logger.info("Service removed due to expiration: {}", name);
            }
        });
    }
}