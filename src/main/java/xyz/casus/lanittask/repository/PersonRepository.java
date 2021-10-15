package xyz.casus.lanittask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import xyz.casus.lanittask.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

    @Modifying
    @Query(value = "TRUNCATE TABLE Person", nativeQuery = true)
    void truncate();

}
