package org.postwork.er.madingamotion.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
public class NotificationService {

    private final WebClient webClient;
    private final RetryService retryService;

    public NotificationService(WebClient webClient, RetryService retryService) {
        this.webClient = webClient;
        this.retryService = retryService;
    }

    public void send(String studentId) {
//        retryService.execute(() -> webClient.post()
//                .uri("http://localhost:9002/webhook")
//                .bodyValue("{\"studentId\":\"" + studentId + "\"}")
//                .retrieve()
//                .toBodilessEntity()
//                .block());

        System.out.println("Promotion Eligible: " + studentId);
    }
}

