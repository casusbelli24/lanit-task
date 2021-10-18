package xyz.casus.lanittask.service;

import xyz.casus.lanittask.entity.Car;

import java.util.List;

public interface CarService {

    void save(Car car);

    Car findById(long id);

    List<Car> findAll();

    void deleteAll();

    long count();

    long countUniqueVendors();

    boolean isIdExist(long id);

}
