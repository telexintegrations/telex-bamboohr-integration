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
        log.info("Processing BambooHR request: {}", employees);
        for (Employee employee : employees) {
            processEmployee(employee);
        }
    }

    private void processEmployee(Employee employee) {
        String message = String.format(
                "Employee %s %s with ID %s has been added to the team. Job title is %s.",
                employee.getFirstName(),
                employee.getLastName(),
                employee.getId(),
                employee.getJobTitle()
        );

        telexNotificationService.sendNotificationToTelex(message);
    }
}
