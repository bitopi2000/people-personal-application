package com.person.repository;

import com.person.domain.Address;
import com.person.domain.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PersonRepositoryTest {
    @Autowired
    private PersonRepository personRepository;

    @Test
    public void testFindAll() {
        List<Person> personList = personRepository.findAll();
        assertThat(personList.size()).isEqualTo(3);
        Person person = personList.stream().filter(per -> per.getName().equalsIgnoreCase("Elle")).findAny().get();
        assertThat(person.getName()).isEqualTo("Elle");
        assertThat(person.getDateOfBirth()).contains("12-22");
        assertThat(person.getAddress().getCity()).isEqualTo("Oslo");
    }

    @Test
    public void testFindById() {
        Optional<Person> person = personRepository.findById(3l);
        assertThat(person.isPresent()).isTrue();
    }

    @Test
    public void testIncorrectIdGivesNoPerson() {
        Optional<Person> person = personRepository.findById(2l);
        assertThat(person.isPresent()).isFalse();
    }

    @Test
    public void testSave(){
        Person person = new Person("Jack", "1994-11-22T19:53:00.000Z", new Address("street6", "Oslo", "4456"));
        Person savedPerson = personRepository.save(person);
        assertThat(savedPerson.getName()).isEqualTo(person.getName());
        assertThat(savedPerson.getAddress().getZipCode()).isEqualTo(person.getAddress().getZipCode());
    }
}