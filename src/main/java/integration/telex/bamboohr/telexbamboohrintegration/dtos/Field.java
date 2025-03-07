package integration.telex.bamboohr.telexbamboohrintegration.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Field {
    @JsonProperty
    private String id;

    @JsonProperty("type")
    private String type;

    @JsonProperty("name")
    private String name;
}
