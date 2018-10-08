package com.ylz.cache.cache.controller;

import com.ylz.cache.cache.domain.Person;
import com.ylz.cache.cache.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("person")
public class PersonCacheController {
    @Autowired
    PersonService personService;

    @RequestMapping("put")
    public Person put(Person person){
        return personService.save(person);
    }

    @RequestMapping("able")
    public Person cacheable(Person person){
        Person result = personService.findOne(person);
        return result == null ? new Person() : result;
    }

    @RequestMapping("evict")
    public String evict(Person person){
        personService.remove(person);
        return "ok";
    }
}
