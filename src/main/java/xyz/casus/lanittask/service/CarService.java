package xyz.casus.lanittask.service;

import xyz.casus.lanittask.entity.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {

    void save(Car car);

    Optional<Car> findById(long id);

    List<Car> findAll();

    void deleteAll();

    long count();

    long countUniqueVendors();

    boolean isIdExist(long id);

}
