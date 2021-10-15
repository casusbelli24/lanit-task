package xyz.casus.lanittask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import xyz.casus.lanittask.entity.Car;
import xyz.casus.lanittask.service.CarService;
import xyz.casus.lanittask.service.PersonService;

import javax.validation.Valid;
import java.util.List;

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
    public ResponseEntity<Void> saveCar(@Valid @RequestBody Car car, BindingResult result) {
        Long carId = car.getId();
        Long ownerId = car.getOwnerId();
        if (result.hasErrors() || carService.isIdExist(carId)
                || !personService.isIdExist(ownerId)
                || !personService.isPersonOfLegalAge(ownerId)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        carService.save(car);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/car")
    public List<Car> getAll() {
        return carService.findAll();
    }

}
