/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.achille.springbootperson.controller;

import com.achille.springbootperson.model.Address;
import com.achille.springbootperson.model.Person;
import com.achille.springbootperson.service.PersonDataService;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.springframework.test.web.servlet.MvcResult;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author achille
 */
@WebMvcTest(PersonController.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonDataService personDataService;

    /**
     * Test of getPerson method, of class PersonController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetPerson() throws Exception {
        long id = 1001L;
        Person person = new Person(id, "Adore", "Yeoman");
        when(personDataService.retrievePerson(id)).thenReturn(person);
        RequestBuilder request = MockMvcRequestBuilders
                .get("/person/" + id)
                .accept(MediaType.APPLICATION_JSON);

        String expectedResult = "{\"id\":1001,\"firstName\":\"Adore\",\"lastName\":\"Yeoman\"}";

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk()).andReturn();
        JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(), false);
    }

    /**
     * Test of createPerson method, of class PersonController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testCreatePerson() throws Exception {
        Person person = new Person("Alice", "Yeoman");
        when(personDataService.createPerson(person)).thenReturn(person);
        String jsonPerson = "{\"firstName\":\"Alice\",\"lastName\":\"Yeoman\"}";
        RequestBuilder request = MockMvcRequestBuilders.put("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPerson);
        String expectedResult = "{\"firstName\":\"Alice\",\"lastName\":\"Yeoman\"}";
        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk()).andReturn();
        JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(), false);
    }

    /**
     * Test of updatePerson method, of class PersonController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdatePerson() throws Exception {

        Person person = new Person("Alice", "Yeoman");
        when(personDataService.createPerson(person)).thenReturn(person);
        String jsonPerson = "{\"firstName\":\"Alice\",\"lastName\":\"Yeoman\"}";
        RequestBuilder request = MockMvcRequestBuilders.put("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPerson);
        String expectedResult = "{\"firstName\":\"Alice\",\"lastName\":\"Yeoman\"}";
        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk()).andReturn();
        JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(), false);
    }

    /**
     * Test of getPersons method, of class PersonController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetPersons() throws Exception {
        Person p1 = new Person(1001L, "Kevin", "John");
        Person p2 = new Person(1002L, "Paul", "Ray");
        Person p3 = new Person(1003L, "David", "Bob");
        List<Person> persons = Arrays.asList(p1, p2, p3);
        when(this.personDataService.retriePersons()).thenReturn(persons);
        String expectedResult = "[{id:1001},{id:1002},{id:1003}]";
        RequestBuilder request = MockMvcRequestBuilders
                .get("/persons")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk()).andReturn();

        JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(), false);
    }

    /**
     * Test of getPersonsCount method, of class PersonController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetPersonsCount() throws Exception {

        Map<String, Long> count = Map.of("count", 5L);
        when(this.personDataService.getPersonsCount()).thenReturn(count);
        String expectedResult = "{count:5}";
        RequestBuilder request = MockMvcRequestBuilders
                .get("/persons/count")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk()).andReturn();

        JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(), false);
    }

    /**
     * Test of deleteAddress method, of class PersonController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteAddress() throws Exception {

        long id = 10001L;

        Address ad1 = new Address(1L, "1 court", "Tallaght", "Dublin", "24");

        Person person = new Person(id, "Achille", "Nisengwe");
        person.addAddress(ad1);
        when(this.personDataService.retrievePerson(id)).thenReturn(person);

        when(personDataService.createPerson(person)).thenReturn(person);
        RequestBuilder request = MockMvcRequestBuilders
                .delete("/person/" + id + "/address/1")
                .accept(MediaType.APPLICATION_JSON);

        String expectedResult = "{id:" + id + ",address:[]}";

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk()).andReturn();
        JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(), false);
    }

    /**
     * Test of deletePerson method, of class PersonController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testDeletePerson() throws Exception {
        long id = 10001L;
        when(personDataService.deletePerson(id)).thenReturn(Boolean.TRUE);
        RequestBuilder request = MockMvcRequestBuilders
                .delete("/person/" + id)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk()).andReturn();
    }

}
