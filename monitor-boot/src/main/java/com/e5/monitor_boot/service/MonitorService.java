package com.e5.monitor_boot.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class MonitorService {

    private final WebClient webClient = WebClient.builder().baseUrl("http://localhost:9095").build();


    public boolean performHealthCheck(String deploymentId) {
        return Boolean.TRUE.equals(webClient.get()
                .uri("/deploymentId/{value}", deploymentId)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block());
    }

    public String restartDeployment(String deploymentId) {
        return webClient.post()
                .uri("/restart")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
