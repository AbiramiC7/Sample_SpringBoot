package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.PersonsEntity;
 
@Repository
public interface PersonsRepository
        extends JpaRepository<PersonsEntity, Long> {
 
}
