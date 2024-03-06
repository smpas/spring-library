package ru.smpas.springcourse.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.smpas.springcourse.dao.PersonDAO;
import ru.smpas.springcourse.models.Person;
import ru.smpas.springcourse.services.PersonService;

@Component
public class PersonValidator implements Validator {
    private final PersonService personService;

    @Autowired
    public PersonValidator(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if (personService.showByName(person.getName()).isPresent()) {
            errors.rejectValue("name", "", "This name is already exist");
        }
    }
}
