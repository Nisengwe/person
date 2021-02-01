/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.achille.springbootperson.service;

import com.achille.springbootperson.model.Address;
import com.achille.springbootperson.model.Person;
import com.achille.springbootperson.repository.AddressRepository;
import com.achille.springbootperson.repository.PersonRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 *
 * @author achil
 */
@ExtendWith(MockitoExtension.class)
public class PersonDataServiceTest {

    @InjectMocks
    private PersonDataService dataservice;

    @Mock
    private PersonRepository personRespository;

  
    /**
     * Test of retrievePerson method, of class PersonDataService.
     */
    @Test
    public void testRetrievePerson() throws NoSuchElementException {
        long id = 1001L;
        Person expectedPerson = new Person(id, "Adore", "Yeoman");
        when(this.personRespository.findById(id)).thenReturn(Optional.of(expectedPerson));
        Person actualPerson = dataservice.retrievePerson(id);
        assertEquals(expectedPerson, actualPerson);
    }

    /**
     * Test of createPerson method, of class PersonDataService.
     */
    @Test
    public void testCreatePerson() {
        long id = 10002L;
        Person person = new Person(id, "Mary", "Bob");
        when(this.personRespository.save(person)).thenReturn(person);
        Person actaulResult = dataservice.createPerson(person);
        assertEquals(person, actaulResult);
    }


    /**
     * Test of retriePersons method, of class PersonDataService.
     */
    @Test
    public void testRetriePersons() {
        Person p1 = new Person(2000L, "Kevin", "John");
        Person p2 = new Person(20002L, "Paul", "Ray");
        Person p3 = new Person(20003L, "David", "Bob");
        List<Person> persons = Arrays.asList(p1, p2, p3);
        when(this.personRespository.findAll()).thenReturn(persons);
        List<Person> expectedResult = persons;
        List<Person> actualResult = this.dataservice.retriePersons();
        assertEquals(expectedResult, actualResult);
    }

    /**
     * Test of getPersonsCount method, of class PersonDataService.
     */
    @Test
    public void testGetPersonsCount() {
        long count = 5;
        when(this.personRespository.count()).thenReturn(count);
        Map<String, Long> expectedResult = Map.of("count", count);
        Map<String, Long> actualResult = this.dataservice.getPersonsCount();
        assertEquals(expectedResult, actualResult);
    }

   

    

    /**
     * Test of deletePerson method, of class PersonDataService.
     */
    @Test
    public void testDeletePerson() {
        long id = 1001L;
        when(this.personRespository.existsById(id)).thenReturn(Boolean.TRUE);
        boolean expectedResult = true;
        boolean actualResult = this.dataservice.deletePerson(id);
        assertEquals(expectedResult, actualResult);
    }

}
