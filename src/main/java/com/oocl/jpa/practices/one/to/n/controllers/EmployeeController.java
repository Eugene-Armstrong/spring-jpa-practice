package com.oocl.jpa.practices.one.to.n.controllers;

import com.oocl.jpa.practices.one.to.n.controllers.dto.EmployeeDTO;
import com.oocl.jpa.practices.one.to.n.entities.Employee;
import com.oocl.jpa.practices.one.to.n.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository){this.employeeRepository = employeeRepository;}

    //获取employee列表
    @Transactional
    @GetMapping(path = "",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> findAll() {return employeeRepository.findAll();}

    //获取某个employee
    @Transactional
    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee getEmployeeById(@PathVariable("id")Long id) {
        Employee employee = employeeRepository.findById(id).get();
        return employee;
    }

    //分页查询
    @Transactional
    @GetMapping(path = "page/{page}/pageSize/{pageSize}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> handlePage(@PathVariable("page")int page, @PathVariable("pageSize")int pageSize) {
        ArrayList<Employee> employees = new ArrayList<>();
        int start = (page-1)*pageSize,
                end = start+pageSize>findAll().size()
                        ?findAll().size():start+pageSize;
        for(int i=start;i<end;i++){
            employees.add(findAll().get(i));
        }
        return employees;
    }

    //筛选出所有男性employee
    @Transactional
    @GetMapping(path = "male", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getAllMale() {
        List<Employee> allMale =  new ArrayList<>();
        for(Employee employee:findAll()){
            if(employee.getGender().equals("male")){
                allMale.add(employee);
            }
        }
        return allMale;
    }

    //添加employee
    @Transactional
    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee save(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    //更新employee
    @Transactional
    @PutMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(@RequestBody Employee employee) {
        employeeRepository.save(employee);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //删除employee
    @Transactional
    @DeleteMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee delete(@PathVariable("id")Long id) {
        Employee one = employeeRepository.findById(id).get();
        employeeRepository.delete(one);
        return one;
    }
}
