package com.oocl.jpa.practices.n.to.n.controllers;

import com.oocl.jpa.practices.n.to.n.entities.User;
import com.oocl.jpa.practices.n.to.n.repositories.GroupRepository;
import com.oocl.jpa.practices.n.to.n.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    public UserController(UserRepository userRepository,
                          GroupRepository groupRepository){
        this.userRepository= userRepository;
        this.groupRepository = groupRepository;
    }

    //获取user列表
    @Transactional
    @GetMapping(path="", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> findAll(){ return userRepository.findAll(); }

    //添加user
    @Transactional
    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public User save(@RequestBody User user) {
        if(user.getGroups()!=null) {
            groupRepository.saveAll(user.getGroups());
        }
        return  userRepository.save(user);
    }

    //更新user
    @PutMapping(path = "" , produces = MediaType.APPLICATION_JSON_VALUE)
    public User updateUser(@RequestBody User user){
        if(user.getGroups()!=null) {
            groupRepository.saveAll(user.getGroups());
        }
        return userRepository.save(user);
    }

    //删除user
    @Transactional
    @DeleteMapping(path = "{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public User deleteUserById (@PathVariable Long id){
        User user = userRepository.findById(id).get();
        userRepository.delete(user);
        return user;
    }

    //查询user
    @GetMapping(path = "{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public User findById(@PathVariable Long id){
        User user = userRepository.findById(id).get();
        return user;
    }
}
