package com.thoughtworks.jpa.practices.one.to.one.controllers;

import com.thoughtworks.jpa.practices.one.to.one.entities.Klass;
import com.thoughtworks.jpa.practices.one.to.one.repositories.KlassRepository;
import com.thoughtworks.jpa.practices.one.to.one.repositories.LeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/v1/klasses")
public class KlassController {

    private KlassRepository repository;

    @Autowired
    private LeaderRepository leaderRepository;

    @Autowired
    public KlassController(KlassRepository repository){this.repository = repository;}

    @Transactional
    @PostMapping(path = "",produces = MediaType.APPLICATION_JSON_VALUE)
    public Klass save(@RequestBody Klass klass){
        klass.getLeader().setKlass(klass);
        return repository.save(klass);
    }

    @Transactional
    @GetMapping(path="", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Klass> findAll(){
        return repository.findAll();
    }
}
