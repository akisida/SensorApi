package springcourse.FirstRestApp.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SensorDTO {
    @NotNull(message = "Name can't be empty")
    @Size(min = 3, max = 50, message = "Name should be between 2 and 30 characters")
    private String name;

    public SensorDTO() {
    }

    public SensorDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}