package com.oocl.jpa.practices.one.to.n.controllers;


import com.oocl.jpa.practices.one.to.n.controllers.dto.CompanyDTO;
import com.oocl.jpa.practices.one.to.n.entities.Company;
import com.oocl.jpa.practices.one.to.n.repositories.CompanyRepository;
import com.oocl.jpa.practices.one.to.n.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/v1/companies")
public class CompanyController {

    private CompanyRepository repository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    public CompanyController(CompanyRepository repository) {
        this.repository = repository;
    }

    //添加company
    @Transactional
    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Company save(@RequestBody Company company) {
        company.getEmployees().stream().forEach(employee -> {
            employee.setCompany(company);
        });
        return  repository.save(company);
    }

    //获取所有company
    @Transactional
    @GetMapping(path="", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Company> findAll(){
        return repository.findAll();
    }

    //更新company
    @Transactional
    @PutMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(@RequestBody Company company) {
        company.getEmployees().stream().filter(employee -> employee.getCompany() == null).forEach(employee -> {
            employee.setCompany(company);
        });
        repository.save(company);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //获取某个company
    @Transactional
    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CompanyDTO get(@PathVariable("id")Long id) {
        Company company = repository.findById(id).get();
        return new CompanyDTO(company);
    }

    //删除company
    @Transactional
    @DeleteMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Company delete(@PathVariable("id")Long id) {
        Company one = repository.findById(id).get();
        repository.delete(one);
        return one;
    }

}
