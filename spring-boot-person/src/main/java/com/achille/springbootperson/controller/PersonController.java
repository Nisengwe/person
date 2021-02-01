/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.achille.springbootperson.controller;

import com.achille.springbootperson.model.Address;
import com.achille.springbootperson.model.Person;
import com.achille.springbootperson.service.PersonDataService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author achille
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class PersonController {

    @Autowired
    private PersonDataService dataservice;


    @GetMapping("/person/{id}")
    public Person getPerson(@PathVariable long id) {
        return dataservice.retrievePerson(id);
    }

    @PutMapping("/person")
    public Person createPerson(@RequestBody Person person) {
        return dataservice.createPerson(person);
    }

    @PostMapping("/person")
    public Person updatePerson(@RequestBody Person person) {
        return dataservice.createPerson(person);
    }

    @PutMapping("/person/{id}/address")
    public Person createAddress(@RequestBody Address address, @PathVariable long id) {
        Person person = dataservice.retrievePerson(id);
        person.addAddress(address);
       return  this.dataservice.createPerson(person);

    }

    @GetMapping("/persons")
    public List<Person> getPersons() {
        return dataservice.retriePersons();
    }

    @GetMapping("/persons/count")
    public Map<String, Long> getPersonsCount() {
        return dataservice.getPersonsCount();
    }

    @DeleteMapping("person/{id}/address/{addressId}")
    public Person deleteAddress(@PathVariable long id, @PathVariable long addressId) {
        Person person = dataservice.retrievePerson(id);
        Address address = null;
        for (Address d : person.getAddress()) {
            if (d.getId() == addressId) {
                address = d;
                break;
            }
        }

        if (address != null) {
            person.removeAddress(address);
            return dataservice.createPerson(person);
            
        }

        return person;
    }

    @DeleteMapping("/person/{id}")
    public Boolean deletePerson(@PathVariable long id) {
        return dataservice.deletePerson(id);
    }

}
