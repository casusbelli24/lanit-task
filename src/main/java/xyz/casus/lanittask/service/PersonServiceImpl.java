package xyz.casus.lanittask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.casus.lanittask.entity.Person;
import xyz.casus.lanittask.repository.PersonRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    private PersonRepository repository;

    @Autowired
    public PersonServiceImpl(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Person person) {
        repository.save(person);
    }

    @Override
    public Optional<Person> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public List<Person> findAll() {
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
    public boolean isPersonOfLegalAge(long id) {
        Optional<Person> person = findById(id);

        if (!person.isPresent()) {
            return false;
        }

        Date birthdate = person.get().getBirthdate();
        LocalDate birthdateDate = birthdate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate now = LocalDate.now();

        long fullYears = ChronoUnit.YEARS.between(birthdateDate, now);

        return fullYears >= 18;
    }

    @Override
    public boolean isIdExist(long id) {
        return findById(id).isPresent();
    }

}
