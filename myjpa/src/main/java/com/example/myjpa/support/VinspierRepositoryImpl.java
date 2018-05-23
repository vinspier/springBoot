package com.example.myjpa.support;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;

import static com.example.myjpa.specification.VinspierSpecification.*;


public class VinspierRepositoryImpl<T,ID extends Serializable> extends SimpleJpaRepository<T,ID> implements VinspierRepository<T,ID> {

    private final EntityManager entityManager;

    public VinspierRepositoryImpl(Class<T> domainClass,EntityManager em) {
        super(domainClass, em);
        this.entityManager = em;
    }

    @Override
    public Page<T> findByAuto(T example, Pageable pageable) {
        return findAll(byAuto(entityManager,example),pageable);
    }
}
