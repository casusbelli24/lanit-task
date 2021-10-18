package xyz.casus.lanittask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import xyz.casus.lanittask.dto.PersonDTO;
import xyz.casus.lanittask.dto.PersonWithCarsDTO;
import xyz.casus.lanittask.entity.Person;
import xyz.casus.lanittask.service.CarService;
import xyz.casus.lanittask.service.PersonService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public ResponseEntity<Void> savePerson(@Valid @RequestBody PersonDTO personDTO, BindingResult result) {
        Long personId = personDTO.getId();
        if (result.hasErrors() || personService.isIdExist(personId)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        personService.save(new Person(personDTO.getId(), personDTO.getName(), personDTO.getBirthdate()));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/person")
    public List<PersonDTO> getAll() {
        return personService.findAll()
                .stream()
                .map(Person::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/personwithcars")
    public ResponseEntity<PersonWithCarsDTO> getPeopleWithCars(@RequestParam(required = false) Long personid) {
        if (personid == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Optional<Person> person = personService.findById(personid);
        if (!person.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new PersonWithCarsDTO(person.get()), HttpStatus.OK);
    }

}
