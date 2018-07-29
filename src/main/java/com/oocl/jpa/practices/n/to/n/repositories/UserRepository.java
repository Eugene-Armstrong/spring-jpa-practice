package com.oocl.jpa.practices.n.to.n.repositories;

import com.oocl.jpa.practices.n.to.n.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
