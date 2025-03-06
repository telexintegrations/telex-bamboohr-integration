package integration.telex.bamboohr.telexbamboohrintegration.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    @Getter
    @Value("${bamboohr.api.key}")
    private String bamboohrApiKey;

    @Value("${bamboohr.domain}")
    private String bamboohrCompanyName;

    @Getter
    @Value("${telex.bamboohr.webhook.url}")
    private String telexWebhookUrl;

    @Value("${bamboohr.webhook.url}")
    private String bamboohrWebhookUrl;

    public String getBamboohrWebhookUrl() {
        return bamboohrWebhookUrl + bamboohrCompanyName + "/v1/webhooks";
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
