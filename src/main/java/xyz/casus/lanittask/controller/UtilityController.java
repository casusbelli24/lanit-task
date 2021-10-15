package xyz.casus.lanittask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.casus.lanittask.dto.Statistics;
import xyz.casus.lanittask.service.CarService;
import xyz.casus.lanittask.service.PersonService;

@RestController
public class UtilityController {

    private CarService carService;
    private PersonService personService;

    @Autowired
    public UtilityController(CarService carService, PersonService personService) {
        this.carService = carService;
        this.personService = personService;
    }

    @GetMapping("/clear")
    public void clear() {
        carService.deleteAll();
        personService.deleteAll();
    }


    @GetMapping("/statistics")
    public Statistics showStatistics() {
        Long personCount = personService.count();
        Long carCount = carService.count();
        Long uniqueVendorCount = carService.countUniqueVendors();

        return new Statistics(personCount, carCount, uniqueVendorCount);
    }

}
