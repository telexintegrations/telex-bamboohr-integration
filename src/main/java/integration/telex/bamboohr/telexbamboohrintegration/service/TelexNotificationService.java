package integration.telex.bamboohr.telexbamboohrintegration.service;

import integration.telex.bamboohr.telexbamboohrintegration.config.AppConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class TelexNotificationService {
    private final AppConfig appConfig;
    private final RestTemplate restTemplate;

    public void sendNotificationToTelex(String message) {
        String payload = String.format("{\"text\": \"%s\"}", message);
        try {
            sendPostRequest(appConfig.getTelexWebhookUrl(), payload);
        } catch (Exception e) {
            log.error("Failed to send notification to Telex: {}", e.getMessage());
        }

    }

    private void sendPostRequest(String url, String payload) {
        try {
            restTemplate.postForEntity(url, payload, String.class);
            log.info("Successfully send notification to Telex");
        } catch (Exception e) {
            log.error("Failed to send POST request: {}", e.getMessage());
        }
    }
}
