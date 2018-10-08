package com.ylz.cache.cache.dao;

import com.ylz.cache.cache.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
