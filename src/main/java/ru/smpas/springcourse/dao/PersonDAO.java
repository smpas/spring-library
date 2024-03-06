package ru.smpas.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.smpas.springcourse.models.Book;
import ru.smpas.springcourse.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
/*
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id},
                        new BeanPropertyRowMapper<>(Person.class))
                .stream()
                .findAny()
                .orElse(null);
    }

    public Optional<Person> show(String name) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE name=?", new Object[]{name},
                        new BeanPropertyRowMapper(Person.class))
                .stream()
                .findAny();
    }

    public List<Book> showPersonBooks(int personId) {
        return jdbcTemplate.query("SELECT b.id, b.name, b.author, b.year " +
                                      "FROM person p " +
                                          "JOIN book b ON p.id = b.owner " +
                                      "WHERE p.id = ?",
                new Object[]{personId},
                new BeanPropertyRowMapper<>(Book.class));
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person(name, year) VALUES(?, ?)", person.getName(), person.getYear());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET name=?, year=? WHERE id=?", updatedPerson.getName(),
                updatedPerson.getYear(), updatedPerson.getId());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }
*/
}
