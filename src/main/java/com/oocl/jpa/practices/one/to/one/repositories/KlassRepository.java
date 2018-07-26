package com.thoughtworks.jpa.practices.one.to.one.repositories;

import com.thoughtworks.jpa.practices.one.to.one.entities.Klass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KlassRepository extends JpaRepository<Klass,Long> {
}
