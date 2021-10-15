package xyz.casus.lanittask.service;

import xyz.casus.lanittask.entity.Person;

import java.util.List;

public interface PersonService {

    void save(Person person);

    Person findById(long id);

    List<Person> findAll();

    void deleteAll();

    Long count();

    boolean isPersonOfLegalAge(long id);

    boolean isIdExist(long id);

}
