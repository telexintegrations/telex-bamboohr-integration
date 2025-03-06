package integration.telex.bamboohr.telexbamboohrintegration.dtos;

import lombok.Builder;

@Builder
public record BambooHRRequest(
        String employeeId,
        String employeeName,
        String employeeEmail,
        String employeeJobTitle,
        String employeeDepartment,
        String employeeLocation
) {
}
