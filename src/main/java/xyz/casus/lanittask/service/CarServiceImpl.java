package xyz.casus.lanittask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.casus.lanittask.entity.Car;
import xyz.casus.lanittask.repository.CarRepository;

import java.util.List;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    private CarRepository repository;

    @Autowired
    public CarServiceImpl(CarRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Car car) {
        repository.save(car);
    }

    @Override
    public Car findById(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Car> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public long countUniqueVendors() {
        return repository.findAll().stream()
                .map(Car::getModel)
                .map(model -> model.split("-")[0])
                .map(String::toLowerCase)
                .distinct()
                .count();
    }

    @Override
    public boolean isIdExist(long id) {
        return findById(id) != null;
    }
}
