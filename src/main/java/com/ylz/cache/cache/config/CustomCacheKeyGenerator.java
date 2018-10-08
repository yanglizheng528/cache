package com.ylz.cache.cache.config;

import com.ylz.cache.cache.domain.Person;
import org.springframework.cache.interceptor.KeyGenerator;

import java.lang.reflect.Method;
import java.util.UUID;

public class CustomCacheKeyGenerator implements KeyGenerator {
    /**
     * 自定义缓存key生成策略。
     *
     * @param target 被调用的方法所在的类
     * @param method 被调用的方法
     * @param params 被调用的方法的参数
     * @return
     */
    @Override
    public Object generate(Object target, Method method, Object... params) {
        System.out.println("------------" + target.getClass().toGenericString());
        System.out.println("------------" + method.getName());
        for (int i = 0; i < params.length; i++) {
            if(params[i] instanceof Person){
                Person person = (Person)params[i];
                System.out.println(person.getId());
            }else{
                System.out.println("------------" + params[i]);
            }
        }
        return UUID.randomUUID().toString();
    }
}
