package com.oocl.jpa.practices.one.to.n.repositories;

import com.oocl.jpa.practices.one.to.n.entities.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void findAllEmployees(){
        //given
        entityManager.persist(new Employee("Jack","male"));
        entityManager.persist(new Employee("Rose","female"));
        //when
        List<Employee> employees = repository.findAll();
        //then
        assertThat(employees.size(), is(2));
    }

    @Test
    public void getEmployeeById(){
        //given
        entityManager.persist(new Employee("Jack","male"));
        entityManager.persist(new Employee("Rose","female"));
        //when
        List<Employee> employees = repository.findAll();
        //then
        assertThat(employees.size(), is(2));
        assertThat(employees.get(0).getName(), is("Jack"));
        assertThat(employees.get(1).getName(), is("Rose"));
    }
    
    @Test
    public void getEmployeesByPage(){
        //given
        entityManager.persist(new Employee("Jack", "male"));
        entityManager.persist(new Employee("Rose", "female"));
        entityManager.persist(new Employee("Lucy", "female"));
        entityManager.persist(new Employee("John", "male"));
        //when
        List<Employee> employees = repository.findAll(PageRequest.of(1, 2)).getContent();
        //then
        assertThat(employees.size(), is(2));
        assertThat(employees.get(0).getName(), is("Lucy"));
        assertThat(employees.get(1).getName(), is("John"));
    }

    @Test
    public void addEmployee(){
        //given
        Employee newEmployee = new Employee("Jack","male");
        entityManager.persist(newEmployee);
        //when
        //then
        assertThat(repository.save(newEmployee), is(newEmployee));
    }

    @Test
    public void deleteEmployeeById(){
        //given
        entityManager.persist(new Employee("Jack","male"));
        Employee employee = entityManager.persistAndFlush(new Employee("Rose","female"));
        //when
        repository.delete(employee);
        //then
        assertThat(repository.findAll().size(), is(1));
    }

}