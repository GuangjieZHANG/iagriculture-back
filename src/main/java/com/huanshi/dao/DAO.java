package com.huanshi.dao;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Stereotype;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@ApplicationScoped
@Named
@Transactional
@Stereotype
@Target(TYPE)
@Retention(RUNTIME)
public @interface DAO {
}