package integration.telex.bamboohr.telexbamboohrintegration.service;

import integration.telex.bamboohr.telexbamboohrintegration.config.AppConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class BambooHRWebhookSetupService {
    private final AppConfig appConfig;
    private final RestTemplate restTemplate;

    public void createWebhook() {
        String webhookPayload = """
                {
                    "name": "New Employee Webhook",
                    "monitorFields": ["employeeNumber", "firstName"],
                    "postFields": {
                        "employeeId": "Employee #",
                        "firstName": "First Name",
                        "lastName": "Last Name",
                        "17": "Job Title",
                        "acaStatus": "Employment Status: ACA Full-Time"
                    },
                    "url": "https://heorkuapp.com/telex-webhook",
                    "format": "json",
                    "frequency": {
                        "minute": 1
                    },
                    "limit": {
                        "times": 5,
                        "seconds": 1200
                    }
                }
                """;

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(appConfig.getBamboohrApiKey(), "x");
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(webhookPayload, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(appConfig.getBamboohrWebhookUrl(), request, String.class);
            log.info("Webhook created successfully: {}", response.getBody());
        } catch (Exception e) {
            log.error("Failed to create webhook", e);
        }
    }
}
