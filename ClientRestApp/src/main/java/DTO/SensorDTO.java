package DTO;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SensorDTO {
    private String name;

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
}
