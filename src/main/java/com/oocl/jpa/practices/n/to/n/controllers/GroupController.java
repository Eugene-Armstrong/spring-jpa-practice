package com.oocl.jpa.practices.n.to.n.controllers;

import com.oocl.jpa.practices.n.to.n.entities.Group;
import com.oocl.jpa.practices.n.to.n.repositories.GroupRepository;
import com.oocl.jpa.practices.n.to.n.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/v1/groups")
public class GroupController {
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public GroupController(GroupRepository groupRepository){this.groupRepository = groupRepository;}

    //获取group列表
    @Transactional
    @GetMapping(path="", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Group> findAll(){ return groupRepository.findAll(); }

    //添加group
    @Transactional
    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Group save(@RequestBody Group group) {
        return  groupRepository.save(group);
    }

    //更新group
    @PutMapping(path = "" , produces = MediaType.APPLICATION_JSON_VALUE)
    public Group updateGroup(@RequestBody Group group){
        return groupRepository.save(group);
    }

    //删除group
    @Transactional
    @DeleteMapping(path = "{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public Group deleteGroupById (@PathVariable Long id){
        Group group = groupRepository.findById(id).get();
        groupRepository.delete(group);
        return group;
    }

    //查询group
    @GetMapping(path = "{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public Group findGroupById(@PathVariable Long id){
        Group group = groupRepository.findById(id).get();
        return group;
    }
}
