package com.demo.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.exception.RecordNotFoundException;
import com.demo.model.PersonsEntity;
import com.demo.service.PersonsService;
 
@RestController
@RequestMapping("/persons")
public class PersonsController
{
    @Autowired
    PersonsService service;
 
    @GetMapping
    public ResponseEntity<List<PersonsEntity>> getAll() throws RecordNotFoundException {
        List<PersonsEntity> list = service.getAllPersons();
       
        return new ResponseEntity<List<PersonsEntity>>(list, new HttpHeaders(), HttpStatus.OK);
    }
 
    @GetMapping("/age")
    public ResponseEntity<List<PersonsEntity>> getPersonsByAge()
                                                    throws RecordNotFoundException {
    	List<PersonsEntity> list = service.getPersonsByAge();
 
    	 return new ResponseEntity<List<PersonsEntity>>(list, new HttpHeaders(), HttpStatus.OK);
    }
 
    @PostMapping
    public ResponseEntity<PersonsEntity> createOrUpdatePersons(@RequestBody PersonsEntity persons)
                                                    throws RecordNotFoundException {
        PersonsEntity updated = service.createOrUpdatePersons(persons);
        return new ResponseEntity<PersonsEntity>(updated, new HttpHeaders(), HttpStatus.OK);
    }
 
    @DeleteMapping("/{id}")
    public HttpStatus deleteEmployeeById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
        service.deletePersonsById(id);
        return HttpStatus.OK;
    }
 
}