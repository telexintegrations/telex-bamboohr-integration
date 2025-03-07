package integration.telex.bamboohr.telexbamboohrintegration.service;

import integration.telex.bamboohr.telexbamboohrintegration.dtos.Employee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class BambooHRService {
    private final TelexNotificationService telexNotificationService;

    public void processBambooHRRequest(List<Employee> employees) {
        for (Employee employee : employees) {
            processEmployee(employee);
        }
    }

    private void processEmployee(Employee employee) {
        String message = String.format(
                "Employee %s %s with ID %s has been added to the team. Their job title is %s.",
                employee.getFirstName(),
                employee.getLastName(),
                employee.getId(),
                employee.getJobTitle()
        );

        telexNotificationService.sendNotificationToTelex(message);
//        if (employee == null || !employee.containsKey("fields")) {
//            log.warn("Invalid employee data: {}", employee);
//            return;
//        }
//        Map<String, Object> fields = (Map<String, Object>) employee.get("fields");
//
//        String employeeNumber = extractField(fields, "Employee #");
//        String firstName = extractField(fields, "First Name");
//        String lastName = extractField(fields, "Last Name");
//        String jobTitle = extractField(fields, "Job Title", "N/A");
//
//        String message = String.format("Employee %s %s with Employee Number %s has been added to the team. Their job title is %s.",
//                firstName, lastName, employeeNumber, jobTitle);
//
//        telexNotificationService.sendNotificationToTelex(message);
    }

    private String extractField(Map<String, Object> fields, String key) {
        return extractField(fields, key, "Unknown");
    }

    private String extractField(Map<String, Object> fields, String key, String defaultValue) {
        return fields.containsKey(key) ? ((Map<String, Object>) fields.get(key)).get("value").toString() : defaultValue;
    }
}
