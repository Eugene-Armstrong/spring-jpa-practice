package com.oocl.jpa.practices.one.to.one.repositories;

import com.oocl.jpa.practices.one.to.one.entities.Klass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class KlassRepositoryTest {

    @Autowired
    private KlassRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void addKlass(){
        //given
        Klass newKlass = new Klass("class1");
        entityManager.persist(newKlass);

        //when
        //then
        assertThat(repository.save(newKlass), is(newKlass));
    }

    @Test
    public void getAllKlass(){

    }
}