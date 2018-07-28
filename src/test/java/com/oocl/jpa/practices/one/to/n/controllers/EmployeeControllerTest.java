package com.oocl.jpa.practices.one.to.n.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.jpa.practices.one.to.n.controllers.dto.EmployeeDTO;
import com.oocl.jpa.practices.one.to.n.entities.Employee;
import com.oocl.jpa.practices.one.to.n.repositories.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
@EnableSpringDataWebSupport
public class EmployeeControllerTest {

    @Autowired
    private EmployeeController employeeController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeRepository employeeRepository;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void should_get_all_employees() throws Exception{
        //given
        Employee employee1 = new Employee("Jack","male");
        Employee employee2 = new Employee("Rose","female");
        List<Employee> employees = Arrays.asList(employee1,employee2);
        //when
        when(employeeRepository.findAll()).thenReturn(employees);
        ResultActions resultActions = mockMvc.perform(get("/api/v1/employees"));
        //then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", containsString("Jack")))
                .andExpect(jsonPath("$[0].gender", is("male")));
    }
}