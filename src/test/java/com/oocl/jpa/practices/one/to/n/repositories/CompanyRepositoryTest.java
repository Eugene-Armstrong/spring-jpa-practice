package com.oocl.jpa.practices.one.to.n.repositories;

import com.oocl.jpa.practices.one.to.n.entities.Company;
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
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CompanyRepositoryTest {

    @Autowired
    private CompanyRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void findAllCompany(){
        //given
        entityManager.persist(new Company("ali"));
        entityManager.persist(new Company("wanda"));
        entityManager.persist(new Company("baidu"));
        //when
        List<Company> companies = repository.findAll();
        //then
        assertThat(companies.size(), is(3));
    }

    @Test
    public void getCompanyById(){
        //given
        entityManager.persist(new Company("ali"));
        entityManager.persist(new Company("wanda"));
        //when
        List<Company> companies = repository.findAll();
        //then
        assertThat(companies.size(), is(2));
        assertThat(companies.get(0).getName(), is("ali"));
        assertThat(companies.get(1).getName(), is("wanda"));
    }

    @Test
    public void getCompaniesByPage(){
        //given
        entityManager.persist(new Company("ali"));
        entityManager.persist(new Company("wanda"));
        entityManager.persist(new Company("google"));
        entityManager.persist(new Company("facebook"));
        //when
        List<Company> companies = repository.findAll(PageRequest.of(1, 2)).getContent();
        //then
        assertThat(companies.size(), is(2));
        assertThat(companies.get(0).getName(), is("google"));
        assertThat(companies.get(1).getName(), is("facebook"));
    }


    @Test
    public void addCompany(){
        //given
        Company newCompany = new Company("ali");
        entityManager.persist(newCompany);
        //when
        //then
        assertThat(repository.save(newCompany), is(newCompany));
    }

    @Test
    public void deleteCompanyById(){
        //given
        entityManager.persist(new Company("ali"));
        Company company = entityManager.persistAndFlush(new Company("wanda"));
        //when
        repository.delete(company);
        //then
        assertThat(repository.findAll().size(), is(1));
    }
}