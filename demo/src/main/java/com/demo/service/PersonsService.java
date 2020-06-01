package com.demo.service;


import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.demo.exception.RecordNotFoundException;
import com.demo.model.PersonsEntity;
import com.demo.repository.PersonsRepository;

 
@Service
public class PersonsService {
     
    @Autowired
    PersonsRepository repository;
    
   
    public List<PersonsEntity> getAllPersons()
    {
    	List<PersonsEntity> personsList = repository.findAll();
    	if (!personsList.isEmpty()){
    		 return  personsList;
    	}
    	else {
    		  throw new RecordNotFoundException();
    	}
    }
     
    public List<PersonsEntity> getPersonsByAge() throws RecordNotFoundException{
    	List<PersonsEntity> personsList = repository.findAll();
    	List<PersonsEntity> filteredList =personsList.stream().filter(Obj-> Obj.getAge()>20).collect(Collectors.toList());
    	if (!filteredList.isEmpty()){
    		return filteredList;
    	}
    	else {
    		  throw new RecordNotFoundException();
  	}
    	
    }
    
    

     
    public PersonsEntity createOrUpdatePersons(PersonsEntity entity) throws RecordNotFoundException
    {
        Optional<PersonsEntity> person = repository.findById(entity.getId());
         
        if(person.isPresent())
        {
            PersonsEntity newEntity = person.get();
            newEntity.setAge(entity.getAge());
            newEntity.setFirstName(entity.getFirstName());
            newEntity.setLastName(entity.getLastName());
 
            newEntity = repository.save(newEntity);
             
            return newEntity;
        } else {
            entity = repository.save(entity);
             
            return entity;
        }
    }
     
    public void deletePersonsById(Long id) throws RecordNotFoundException
    {
        Optional<PersonsEntity> person = repository.findById(id);
         
        if(person.isPresent())
        {
            repository.deleteById(id);
        } else {
        	  throw new RecordNotFoundException();
        }
    }
}