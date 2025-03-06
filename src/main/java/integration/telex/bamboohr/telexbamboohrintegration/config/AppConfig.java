package integration.telex.bamboohr.telexbamboohrintegration.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

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
}
