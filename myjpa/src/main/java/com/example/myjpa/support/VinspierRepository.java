package com.example.myjpa.support;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean //指明当前接口不是领域类的接口
  /** 继承了JpaSpecificationExecutor，具备了specification的能力 */
public interface VinspierRepository<T,ID extends Serializable> extends JpaRepository<T,ID>,JpaSpecificationExecutor<T> {
    Page<T> findByAuto(T example, Pageable pageable);
}
