package integration.telex.bamboohr.telexbamboohrintegration.service;

import integration.telex.bamboohr.telexbamboohrintegration.config.AppConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class BambooHRWebhookSetupService {
    private static AppConfig appConfig;

    public void createWebhook() {
        RestTemplate restTemplate = new RestTemplate();

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
                    "url": "https://heorkuapp.com/telex-webhook", //callback url
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
        ResponseEntity<String> response = restTemplate.postForEntity(appConfig.getBamboohrWebhookUrl(), request, String.class);

        System.out.println("Response: " + response.getBody());
    }
}
