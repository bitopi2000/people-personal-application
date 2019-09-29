package com.person.service;

import com.person.domain.Address;
import com.person.domain.Person;
import com.person.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    PersonRepository personRepository;
    @InjectMocks
    PersonService personService;

    @BeforeEach
    public void setup() {
        personService = new PersonService(personRepository);
    }

    @Test
    void testFindAll() {
        Person person = new Person("name","dob",new Address("st","city","zip"));
        List<Person> personList = new ArrayList<>();
        personList.add(person);

        when(personRepository.findAll()).thenReturn(personList);
        List<Person> actualPersonList = personService.findAll();

        assertThat(actualPersonList.size()).isEqualTo(personList.size());
    }

    @Test
    void testSave() {
        Person person = new Person("name1","2010-01-10T19:53:00.000Z",new Address("st1","city1","zip1"));

        when(personRepository.save(any(Person.class))).thenReturn(person);

        Person savedPerson = personService.save(person);

        assertThat(savedPerson).isEqualTo(person);
    }

    @Test
    void testFindAllGivesEmptyResultWhenNoDataPresent() {
        when(personRepository.findAll()).thenReturn(Collections.emptyList());

        List<Person> personList = personService.findAll();

        assertThat(personList.isEmpty()).isTrue();
    }

    @Test
    void testSaveThrowsError() {
        Person person = new Person("name","dob",new Address("st","city","zip"));
        assertThrows(StringIndexOutOfBoundsException.class, () -> personService.save(person));
    }
}