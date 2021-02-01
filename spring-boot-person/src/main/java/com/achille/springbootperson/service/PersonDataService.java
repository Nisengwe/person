/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.achille.springbootperson.service;

import com.achille.springbootperson.model.Person;
import com.achille.springbootperson.repository.PersonRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author achil
 */
@Service
public class PersonDataService {

    @Autowired
    PersonRepository personRepository;


    public Person retrievePerson(long id) {
        try {
            return personRepository.findById(id).get();
        } catch (NoSuchElementException ex) {
            return new Person();
        }
    }
    
    
    //Create and update 
    public Person createPerson(Person person) {
        try {
            return personRepository.save(person);
        } catch (Exception ex) {
           return new Person();
        }
    }

    

   
    public List<Person> retriePersons() {
        return personRepository.findAll();
    }
    
   

    public Map<String, Long> getPersonsCount() {
        Map<String, Long> count = new HashMap<>();
        count.put("count", personRepository.count());
        return count;
    }

   

    public boolean deletePerson(long id) {
        if (personRepository.existsById(id)) {
            personRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
