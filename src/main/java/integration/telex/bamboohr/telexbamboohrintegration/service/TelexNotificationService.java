package integration.telex.bamboohr.telexbamboohrintegration.service;

import integration.telex.bamboohr.telexbamboohrintegration.config.AppConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class TelexNotificationService {
    @Value("telex.bamboohr.webhook.url")
    private AppConfig appConfig;
    private final RestTemplate restTemplate;

    public void sendNotificationToTelex(String message) {
        String payload = String.format("{\"text\": \"%s\"}", message);
        sendPostRequest(appConfig.getTelexWebhookUrl(), payload);
    }

    private void sendPostRequest(String url, String payload) {
        restTemplate.postForEntity(url, payload, String.class);
    }
}
