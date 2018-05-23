package com.example.myjpa.dao;

import com.example.myjpa.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person,Long> {

    List<Person> findByName(String name);//使用方法名查询

    Person findByNameAndAddress(String name,String address);//使用方法名查询
    Person withNameAndAddressNamedQuery(String name,String address);//使用NamedQuery查询 具体定义在实体类中

    @Query("select p from Person p where p.name = :name and p.address = :address")
    Person withNameAndAddress(@Param("name") String name, @Param("address") String address); //在方法名上定义 参数按照名称对应
}
