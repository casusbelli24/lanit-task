package xyz.casus.lanittask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import xyz.casus.lanittask.dto.CarDTO;
import xyz.casus.lanittask.entity.Car;
import xyz.casus.lanittask.entity.Person;
import xyz.casus.lanittask.service.CarService;
import xyz.casus.lanittask.service.PersonService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class CarController {

    private CarService carService;
    private PersonService personService;

    @Autowired
    public CarController(CarService carService, PersonService personService) {
        this.carService = carService;
        this.personService = personService;
    }

    @PostMapping("/car")
    public ResponseEntity<Void> saveCar(@Valid @RequestBody CarDTO carDto, BindingResult result) {
        Long carId = carDto.getId();
        Long ownerId = carDto.getOwnerId();
        if (result.hasErrors() || carService.isIdExist(carId)
                || !personService.isIdExist(ownerId)
                || !personService.isPersonOfLegalAge(ownerId)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Optional<Person> person = personService.findById(ownerId);

        carService.save(new Car(carId, carDto.getVendor(), carDto.getModel(), carDto.getHorsepower(), person.get()));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/car")
    public List<CarDTO> getAll() {
        return carService.findAll()
                .stream()
                .map(Car::convertToDto)
                .collect(Collectors.toList());
    }

}
