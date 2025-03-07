package integration.telex.bamboohr.telexbamboohrintegration.controller;


import integration.telex.bamboohr.telexbamboohrintegration.dtos.BambooHRPayload;
import integration.telex.bamboohr.telexbamboohrintegration.service.BambooHRService;
import integration.telex.bamboohr.telexbamboohrintegration.service.BambooHRWebhookSetupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/integration")
@Slf4j
@RequiredArgsConstructor
public class TelexController {
    private final BambooHRWebhookSetupService bambooHRWebhookSetupService;
    private final BambooHRService bambooHRService;

    @RequestMapping("/setup")
    public void setUp() {
        bambooHRWebhookSetupService.createWebhook();
    }

//    @PostMapping("/process")
//    public void processBambooHrRequest(@RequestBody Map<String, Object> request) {
//        bambooHRService.processBambooHRRequest(request);
//    }

    @PostMapping("/process")
    public void processBambooHrRequest(@RequestBody BambooHRPayload payload) {
        log.info("Processing request from controller: {}", payload);
        bambooHRService.processBambooHRRequest(payload.getEmployees());
    }

}

