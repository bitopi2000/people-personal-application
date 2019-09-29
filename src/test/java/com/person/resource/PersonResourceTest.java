package com.person.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.person.domain.Address;
import com.person.domain.Person;
import com.person.repository.PersonRepository;
import com.person.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PersonResource.class)
class PersonResourceTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    PersonService personService;
    @MockBean
    PersonRepository personRepository;

    @BeforeEach
    public void setUp() {
        Person person = new Person("name", "dob", new Address("st","city","zip"));
        Mockito.when(personRepository.findAll()).thenReturn(Arrays.asList(person));
    }

    @Test
    void testFindAll() throws Exception {
        Person person = new Person("name", "dob", new Address("st","city","zip"));
        List<Person> personList = Arrays.asList(person);

        given(personService.findAll()).willReturn(personList);

        mockMvc.perform(get("/api/persons"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(person.getName())));
    }

    @Test
    void testEmptyResultWillGiveResponseNotFound() throws Exception {
        given(personService.findAll()).willReturn(Collections.emptyList());

        mockMvc.perform(get("/api/persons"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testSaveFailReturnsStatusNotSaved() throws Exception {
        Person person = new Person("name", "2010-01-01", new Address("st","city","3445"));
        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(person);

        given(personService.save(person)).willReturn(null);

        mockMvc.perform(post("/api/persons")
                .header("Content-Type", "application/json;charset=UTF-8")
                .accept(MediaType.ALL)
                .content(content))
                .andExpect(status().isUnprocessableEntity());
    }

}