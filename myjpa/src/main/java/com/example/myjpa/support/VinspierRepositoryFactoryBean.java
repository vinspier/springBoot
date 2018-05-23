package com.example.myjpa.support;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

public class VinspierRepositoryFactoryBean<T extends JpaRepository<S,ID>,S,ID extends Serializable>
        extends JpaRepositoryFactoryBean<T,S,ID> {

    public VinspierRepositoryFactoryBean(Class<? extends T> repositoryInterface) {
        super(repositoryInterface);
    }

    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        return new VinspierRepositoryFactory(entityManager); // 重写方法 用当前的VinspierRepositoryFactory来创建实例
    }

    public static class VinspierRepositoryFactory extends JpaRepositoryFactory{

        public VinspierRepositoryFactory(EntityManager entityManager) {
            super(entityManager);
        }

        @Override
        @SuppressWarnings({"unchecked"}) // 获得当前自定义的repository的实现
        protected <T, ID extends Serializable> SimpleJpaRepository<?, ?> getTargetRepository(RepositoryInformation information, EntityManager entityManager) {
            return new VinspierRepositoryImpl<T,ID>((Class<T>)information.getDomainType(), entityManager);
        }

        @Override // 获得当前自定义的repository实现的类型
        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
            return VinspierRepositoryImpl.class;
        }
    }
}
