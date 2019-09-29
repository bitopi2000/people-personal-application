package com.person.service;

import com.person.domain.Address;
import com.person.domain.Person;
import com.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person save(Person person) {
        String dob = person.getDateOfBirth().substring(0,10);
        Person newPerson = new Person(person.getName(), dob, new Address(person.getAddress().getStreet(), person.getAddress().getCity(), person.getAddress().getZipCode()));
        return personRepository.save(newPerson);
    }
}
