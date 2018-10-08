package com.ylz.cache.cache.service;

import com.ylz.cache.cache.domain.Person;

public interface PersonService {
    Person save(Person person);

    void remove(Person person);

    Person findOne(Person person);
}
