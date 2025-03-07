package integration.telex.bamboohr.telexbamboohrintegration.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class BambooHRPayload {
    @JsonProperty("fields")
    private List<Field> fields;

    @JsonProperty("employees")
    private List<Employee> employees;
}
