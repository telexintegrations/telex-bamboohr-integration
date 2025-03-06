package integration.telex.bamboohr.telexbamboohrintegration.controller;


import integration.telex.bamboohr.telexbamboohrintegration.service.BambooHRWebhookSetupService;
import integration.telex.bamboohr.telexbamboohrintegration.service.TelexNotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/bamboo-hr-integration")
@Slf4j
@RequiredArgsConstructor
public class TelexController {
    private final BambooHRWebhookSetupService bambooHRWebhookSetupService;
    private final TelexNotificationService telexNotificationService;

    @PostMapping("/setup-webhook")
    public void setUp() {
       bambooHRWebhookSetupService.createWebhook();
    }


    @PostMapping("/tick-webhook")
    public ResponseEntity<String> bambooHrCallback(@RequestBody Map<String, Object> request) {
        return ResponseEntity.ok(telexNotificationService.sendNotificationToTelex(request));
    }

    @PostMapping("/integration.json")
    public ResponseEntity<String> integrationJson() {
        return ResponseEntity.ok("IntegrationJson.json");
    }

}

