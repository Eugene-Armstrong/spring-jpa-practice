package com.oocl.jpa.practices.one.to.one.controllers.dto;

import com.oocl.jpa.practices.one.to.one.entities.Klass;
import com.oocl.jpa.practices.one.to.one.entities.Leader;

public class KlassDTO {
    private final Long id;
    private final String name;
    private final Leader leader;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Leader getLeader() {
        return leader;
    }

    public KlassDTO(Klass klass) {
        this.id = klass.getId();
        this.name = klass.getName();
        this.leader = klass.getLeader();
    }
}
