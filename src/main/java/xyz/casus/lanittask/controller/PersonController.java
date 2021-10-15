package xyz.casus.lanittask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import xyz.casus.lanittask.entity.Car;
import xyz.casus.lanittask.entity.Person;
import xyz.casus.lanittask.dto.PersonWithCars;
import xyz.casus.lanittask.service.CarService;
import xyz.casus.lanittask.service.PersonService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PersonController {

    private PersonService personService;
    private CarService carService;

    @Autowired
    public PersonController(PersonService personService, CarService carService) {
        this.personService = personService;
        this.carService = carService;
    }

    @PostMapping("/person")
    public ResponseEntity<Void> savePerson(@Valid @RequestBody Person person, BindingResult result) {
        Long personId = person.getId();
        if (result.hasErrors() || personService.isIdExist(personId)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        personService.save(person);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/person")
    public List<Person> getAll() {
        return personService.findAll();
    }

    @GetMapping("/personwithcars")
    public ResponseEntity<PersonWithCars> getPeopleWithCars(@RequestParam(required = false) Long personid) {
        if (personid == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Person person = personService.findById(personid);
        if (person == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Car> personCars = carService.findByOwnerId(personid);

        return new ResponseEntity<>(new PersonWithCars(person, personCars.toArray(new Car[0])), HttpStatus.OK);
    }

}
