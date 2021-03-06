package xyz.casus.lanittask.entity;

import xyz.casus.lanittask.dto.CarDTO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Entity
public class Car {

    @NotNull
    @Id
    private Long id;

    @NotNull
    @Pattern(regexp = "[a-zA-Z0-9]+")
    private String vendor;

    @NotNull
    @Pattern(regexp = "[a-zA-Z0-9-]+")
    private String model;

    @NotNull
    @Positive
    private Integer horsepower;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public Car() {
    }

    public Car(Long id, String vendor, String model, Integer horsepower, Person person) {
        this.id = id;
        this.vendor = vendor;
        this.model = model;
        this.horsepower = horsepower;
        this.person = person;
    }

    public CarDTO convertToDto() {
        StringBuilder builder = new StringBuilder();
        builder.append(vendor);
        builder.append("-");
        builder.append(model);
        return new CarDTO(id, builder.toString(), horsepower, person.getId());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(Integer horsepower) {
        this.horsepower = horsepower;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", horsepower=" + horsepower +
                '}';
    }

}
