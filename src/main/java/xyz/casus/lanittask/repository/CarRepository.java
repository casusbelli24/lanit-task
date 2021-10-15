package xyz.casus.lanittask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import xyz.casus.lanittask.entity.Car;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    @Modifying
    @Query(value = "TRUNCATE TABLE Car", nativeQuery = true)
    void truncate();

    List<Car> findByOwnerId(long id);

}
