package integration.telex.bamboohr.telexbamboohrintegration.service;

import integration.telex.bamboohr.telexbamboohrintegration.dtos.BambooHRRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TelexNotificationService {
    @Value("telex.bamboohr.webhook.url")
    private String telexWebhookUrl;
    private final RestTemplate restTemplate;

    public void sendNotificationToTelex(BambooHRRequest notification) {
        Map<String, String> payload = new HashMap<>();
        payload.put("message", formatMessage(notification));
        restTemplate.postForEntity(telexWebhookUrl, payload, String.class);
    }

    private String formatMessage(BambooHRRequest notification) {
        return String.format("Employee %s with ID %s has been added to the team. Their job title is %s and works in " +
                        "the %s department and are located in %s. You can reach them at %s.",
                notification.employeeName(),
                notification.employeeId(),
                notification.employeeJobTitle(),
                notification.employeeDepartment(),
                notification.employeeLocation(),
                notification.employeeEmail());
    }
}
