package xyz.casus.lanittask.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import xyz.casus.lanittask.entity.Car;
import xyz.casus.lanittask.entity.Person;

import java.util.Arrays;
import java.util.Date;


public class PersonWithCars {

    private Long id;

    private String name;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date birthdate;

    private Car[] cars;

    public PersonWithCars() {
    }

    public PersonWithCars(Person person, Car[] cars) {
        this.id = person.getId();
        this.name = person.getName();
        this.birthdate = person.getBirthdate();
        this.cars = cars;
    }

    public PersonWithCars(Long id, String name, Date birthdate, Car[] cars) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.cars = cars;
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

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Car[] getCars() {
        return cars;
    }

    public void setCars(Car[] cars) {
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
