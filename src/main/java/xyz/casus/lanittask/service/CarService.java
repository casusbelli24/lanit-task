package xyz.casus.lanittask.service;

import xyz.casus.lanittask.entity.Car;

import java.util.List;

public interface CarService {

    void save(Car car);

    Car findById(long id);

    List<Car> findAll();

    void deleteAll();

    Long count();

    Long countUniqueVendors();

    List<Car> findByOwnerId(long id);

    boolean isIdExist(long id);

}
