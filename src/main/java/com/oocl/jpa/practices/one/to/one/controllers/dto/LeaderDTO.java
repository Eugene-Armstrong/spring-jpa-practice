package com.oocl.jpa.practices.one.to.one.controllers.dto;

import com.oocl.jpa.practices.one.to.one.entities.Leader;

public class LeaderDTO {
    private final Long id;
    private final String name;
    private final Long klassId;

    public Long getId() { return id; }
    public String getName() { return name; }
    public Long getKlassId() { return klassId; }

    public LeaderDTO(Leader leader) {
        this.id = leader.getId();
        this.name = leader.getName();
        this.klassId = leader.getKlass().getId();
    }
}
