package integration.telex.bamboohr.telexbamboohrintegration.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BambooHRService {
    private final TelexNotificationService telexNotificationService;

    public void processBambooHRRequest(Map<String, Object> payload) {
        if (payload.containsKey("employees")) {
            List<Map<String, Object>> employees = (List<Map<String, Object>>) payload.get("employees");
            for (Map<String, Object> employee : employees) {
                processEmployee(employee);
            }
        }
    }

    private void processEmployee(Map<String, Object> employee) {
        Map<String, Object> fields = (Map<String, Object>) employee.get("fields");

        String employeeNumber = extractField(fields, "Employee #");
        String firstName = extractField(fields, "First Name");
        String lastName = extractField(fields, "Last Name");
        String jobTitle = extractField(fields, "Job Title", "N/A");

        String message = String.format("Employee %s %s with Employee Number %s has been added to the team. Their job title is %s.",
                firstName, lastName, employeeNumber, jobTitle);

        telexNotificationService.sendNotificationToTelex(message);
    }

    private String extractField(Map<String, Object> fields, String key) {
        return extractField(fields, key, "Unknown");
    }

    private String extractField(Map<String, Object> fields, String key, String defaultValue) {
        return fields.containsKey(key) ? ((Map<String, Object>) fields.get(key)).get("value").toString() : defaultValue;
    }
}
