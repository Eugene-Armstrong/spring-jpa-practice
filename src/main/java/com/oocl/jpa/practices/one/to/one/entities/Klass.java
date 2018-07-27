package com.oocl.jpa.practices.one.to.one.entities;

import javax.persistence.*;

@Entity
public class Klass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Klass() { }
    public Klass(String name) {
        this.name = name;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Leader getLeader() { return leader; }
    public void setLeader(Leader leader) { this.leader = leader; }

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "klass",fetch = FetchType.LAZY)
    private Leader leader = new Leader();
}
