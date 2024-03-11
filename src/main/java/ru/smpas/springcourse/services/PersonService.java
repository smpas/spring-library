package ru.smpas.springcourse.services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.smpas.springcourse.models.Book;
import ru.smpas.springcourse.models.Person;
import ru.smpas.springcourse.repositories.PersonRepository;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@Service
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> index() {
        return personRepository.findAll();
    }

    public Person show(int id) {
        Person person = personRepository.findById(id).orElse(null);
        if (person == null) {
            return null;
        }

        Hibernate.initialize(person.getBooks());
        for (Book book : person.getBooks()) {
            book.updateOverdue();
        }
        return person;
    }

    public Optional<Person> showByName(String name) {
        return personRepository.findByName(name);
    }

    @Transactional(readOnly = false)
    public void save(Person person) {
        personRepository.save(person);
    }

    @Transactional(readOnly = false)
    public void update(int id, Person person) {
        person.setId(id);
        personRepository.save(person);
    }

    @Transactional(readOnly = false)
    public void delete(int id) {
        personRepository.deleteById(id);
    }
}
