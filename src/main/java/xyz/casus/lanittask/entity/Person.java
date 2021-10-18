package xyz.casus.lanittask.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.format.annotation.DateTimeFormat;
import xyz.casus.lanittask.dto.PersonDTO;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Person {

    @NotNull
    @Id
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @Past
    private LocalDate birthdate;

    @OneToMany(mappedBy = "person")
    @Cascade(CascadeType.ALL)
    private List<Car> cars;

    public Person() {
    }

    public Person(Long id, String name, LocalDate birthdate) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
    }

    public PersonDTO convertToDto() {
        return new PersonDTO(id, name, birthdate);
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

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthdate=" + birthdate +
                ", cars=" + cars +
                '}';
    }

}
