package com.oocl.jpa.practices.n.to.n.repositories;

import com.oocl.jpa.practices.n.to.n.entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group,Long> {
}
