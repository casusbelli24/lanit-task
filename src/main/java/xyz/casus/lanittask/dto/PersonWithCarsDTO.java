package xyz.casus.lanittask.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import xyz.casus.lanittask.entity.Car;
import xyz.casus.lanittask.entity.Person;

import java.time.LocalDate;
import java.util.Arrays;


public class PersonWithCarsDTO {

    private Long id;

    private String name;

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @JsonFormat(pattern = "dd.MM.yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate birthdate;

    private CarDTO[] cars;

    public PersonWithCarsDTO() {
    }

    public PersonWithCarsDTO(Person person) {
        this.id = person.getId();
        this.name = person.getName();
        this.birthdate = person.getBirthdate();
        this.cars = person.getCars().stream()
                .map(Car::convertToDto)
                .toArray(CarDTO[]::new);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public CarDTO[] getCars() {
        return cars;
    }

    public void setCars(CarDTO[] cars) {
        this.cars = cars;
    }

    @Override
    public String toString() {
        return "PersonWithCars{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthdate=" + birthdate +
                ", cars=" + Arrays.toString(cars) +
                '}';
    }

}
