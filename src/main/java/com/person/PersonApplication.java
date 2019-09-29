package com.person;

import com.person.domain.Address;
import com.person.domain.Person;
import com.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PersonApplication implements CommandLineRunner{
	@Autowired
	private PersonRepository personRepository;

	public static void main(String[] args) {
		SpringApplication.run(PersonApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		personRepository.save(new Person("Elle", "1990-12-22", new Address("street1", "Oslo", "1234")));
		personRepository.save(new Person("Alaia", "1993-10-16", new Address("street2", "Oslo", "9987")));
		personRepository.save(new Person("Rose", "1980-08-14", new Address("street3", "Tokyo", "12377")));
	}
}
