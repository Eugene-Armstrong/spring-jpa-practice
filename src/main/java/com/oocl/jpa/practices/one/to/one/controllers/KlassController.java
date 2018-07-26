package com.oocl.jpa.practices.one.to.one.controllers;

import com.oocl.jpa.practices.one.to.one.controllers.dto.KlassDTO;
import com.oocl.jpa.practices.one.to.one.entities.Klass;
import com.oocl.jpa.practices.one.to.one.repositories.KlassRepository;
import com.oocl.jpa.practices.one.to.one.repositories.LeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    //添加Klass
    @Transactional
    @PostMapping(path = "",produces = MediaType.APPLICATION_JSON_VALUE)
    public Klass save(@RequestBody Klass klass){
        klass.getLeader().setKlass(klass);
        return repository.save(klass);
    }

    //获取所有Klass
    @Transactional
    @GetMapping(path="", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Klass> findAll(){
        return repository.findAll();
    }

    //获取某个Klass
    @Transactional
    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public KlassDTO get(@PathVariable("id")Long id) {
        Klass klass = repository.findById(id).get();
        return new KlassDTO(klass);
    }
//
//    //更新klass
//    @Transactional
//    @PutMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity update(@RequestBody Klass klass) {
//        klass.getLeader().setKlass(klass);
//        repository.save(klass);
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//    }
//
//    //删除Klass
//    @Transactional
//    @DeleteMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public Klass delete(@PathVariable("id")Long id) {
//        Klass one = repository.findById(id).get();
//        repository.delete(one);
//        return one;
//    }
}
