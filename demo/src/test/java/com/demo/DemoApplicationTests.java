package com.demo;



import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.model.PersonsEntity;
import com.demo.repository.PersonsRepository;
import com.demo.service.PersonsService;
import com.demo.web.PersonsController;

/*@ExtendWith(SpringExtension.class)*/
@RunWith(SpringRunner.class)
@SpringBootTest()
public class DemoApplicationTests {
	@Autowired
	private PersonsService service;
	

	@MockBean
	private PersonsRepository repository;

	@Test
	public void getUsersTest() {
		when(repository.findAll()).thenReturn(Stream
				.of(new PersonsEntity("ani", "Danile","female", 23), new PersonsEntity("honr", "Huy", "male", 23)).collect(Collectors.toList()));
		assertEquals(2, service.getAllPersons().size());
	}
	
	@Test
	public void saveUserTest() {
		PersonsEntity user = new PersonsEntity("ani", "Danile","female", 23);
		when(repository.save(user)).thenReturn(user);
		assertEquals(user, service.createOrUpdatePersons(user));
	}
	

	@Test
	public void getUserbyAge() {
		
		when(repository.findAll())
				.thenReturn(Stream.of(new PersonsEntity("ani", "Danile","female", 23), new PersonsEntity("honr", "Huy", "male", 23)).collect(Collectors.toList()));
		assertEquals(2, service.getPersonsByAge().size());
	}
	

	
}