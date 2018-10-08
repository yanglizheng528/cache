package com.ylz.cache.cache.service.impl;

import com.ylz.cache.cache.dao.PersonRepository;
import com.ylz.cache.cache.domain.Person;
import com.ylz.cache.cache.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    PersonRepository personRepository;

    // 通过SpEL表达式使用方法参数的属性值作为key【perspn为方法的参数名称】
    // @CachePut(value = {"people"}, key = "#person.id")
    // 通过SpEL表达式使用方法参数的属性值作为key【p0表示方法的第一个参数，p1表示方法的第二个参数，以此类推】
    // @CachePut(value = {"people"}, key = "#p0.id")
    // 通过SpEL表达式使用方法参数作为key【p0表示方法的第一个参数，p1表示方法的第二个参数，以此类推】
    // @CachePut(value = {"people"}, key = "#p0")

    // 通过SpEL表达式使用root对象的mehtod属性的name属性作为key
    // @CachePut(value = {"people"}, key = "#root.method.name")
    // 通过SpEL表达式使用root对象的mehtod属性的name属性作为key【省略#root】
    // @CachePut(value = {"people"}, key = "method.name")

    // 通过SpEL表达式指定条件来确定是否缓存，条件为真时缓存，否则不缓存。
    // 本例的条件是：当id为偶数时缓存数据，否则不缓存。
    // @CachePut(value = {"people"}, key = "#person.id", condition = "#person.id % 2 == 0")


    // 通过SpEL表达式指定条件来确定哪种情况不缓存。当unless属性值为true时不缓存数据，否则缓存数据。
    // 本例的条件是：当id为偶数时不缓存数据，否则缓存。
    //@CachePut(value = {"people"}, key = "#person.id", unless = "#person.id % 2 == 0")

    // cacheManager属性配置当前使用的缓存管理器
    // @CachePut(cacheManager = "cacheManager01", value = "cache01", key = "#person.id")


    // keyGenerator属性指定自定义的默认key生成策略，key属性和keyGenerator属性不能同时指定。
    // @CachePut(cacheManager = "cacheManager01", value = "cache01", keyGenerator = "xxx")

    @CachePut(value = "user", key = "#person.id")
    @Override
    public Person save(Person person) {
        Person result = personRepository.save(person);
        System.out.println("save为id、key【" + result.getId() + "】对应的数据做了缓存。");
        return result;
    }

    @CacheEvict(value = "user", key = "#person.id")
    @Override
    public void remove(Person person) {
        System.out.println("remove删除了id = " + person.getId() + "的缓存数据。");
        personRepository.delete(person);
    }

    // @Cacheable(cacheManager = "cacheManager01", value = "cache01", key = "#person.id", unless = "#person.id % 2 == 0")
    @Cacheable(value = "user", key = "#person.id", unless = "#result == null")
    @Override
    public Person findOne(Person person) {
        Person result = personRepository.getOne(person.getId());
        System.out.println("findOne为id【" + person.getId() + "】数据做了缓存");
        return result;
    }
}
