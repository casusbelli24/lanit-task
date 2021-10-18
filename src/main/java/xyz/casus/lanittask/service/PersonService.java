package xyz.casus.lanittask.service;

import xyz.casus.lanittask.entity.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    void save(Person person);

    Optional<Person> findById(long id);

    List<Person> findAll();

    void deleteAll();

    long count();

    boolean isPersonOfLegalAge(long id);

    boolean isIdExist(long id);

}
