package integration.telex.bamboohr.telexbamboohrintegration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@RestController
@RequestMapping("/integration.json")
@Slf4j
public class IntegrationJsonController {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping
    public Map<String, Object> telexJson() {
        try {
            InputStream inputStream = new ClassPathResource("IntegrationJson.json").getInputStream();
            return objectMapper.readValue(inputStream, Map.class);
        } catch (IOException e) {
            log.error("Error reading IntegrationJson.json file", e);
            return Map.of("error", "Failed to load JSON file");
        }
    }
}
