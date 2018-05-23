package com.example.myjpa.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.SingularAttribute;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class VinspierSpecification {
    public static <T>Specification<T> byAuto(final EntityManager entityManager,final T example){
        final Class<T> type = (Class<T>)example.getClass();
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();// 存储构造的查询条件
                EntityType<T> entityType = entityManager.getMetamodel().entity(type);//获得实体类的entityType，可从中获得实体类属性
                for(Attribute<T,?> attr:entityType.getDeclaredAttributes()){
                    Object attributeValue = getValue(example,attr);
                    if(attributeValue != null){
                        if(attr.getJavaType() == String.class){ // 若为字符串类型 则选择 like 查询
                            if(!StringUtils.isEmpty(attributeValue)){
                                predicates.add(criteriaBuilder.like(root.get(attribute(entityType,attr.getName(),String.class)),pattern((String) attributeValue)));
                            }
                        }else {
                            // 若不为字符串 则选择 equal 查询
                            predicates.add(criteriaBuilder.equal(root.get(attribute(entityType,attr.getName(),String.class)),attributeValue));
                        }
                    }
                }
                return predicates.isEmpty() ? criteriaBuilder.conjunction() : criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }

            private <T> Object getValue(T example,Attribute<T,?> attribute){
                /** 通过反射获得实体对象对应属性的属性值 */
                return ReflectionUtils.getField((Field)attribute.getJavaMember(),example);
            }

            private <E,T> SingularAttribute<T,E> attribute(EntityType<T> entityType,String fieldName,Class<E> fieldClass){
                /**获得实体类的当前属性SingularAttribute，包含的是实体类的某个单独属性*/
                return entityType.getDeclaredSingularAttribute(fieldName,fieldClass);
            }
        };
    }

    static private String pattern(String string){
        return "%" + string + "%";
    }
}
