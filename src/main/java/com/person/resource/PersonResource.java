package com.person.resource;

import com.person.domain.Person;
import com.person.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonResource {
    private final PersonService personService;

    @Autowired
    public PersonResource(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<Person>> findAll() {
        List<Person> people = personService.findAll();
        if(people.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(people);
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody Person person) {
        Person savedPerson = personService.save(person);
        if(savedPerson == null) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(savedPerson);
    }
}
