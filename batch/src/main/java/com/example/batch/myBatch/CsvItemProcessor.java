package com.example.batch.myBatch;

import com.example.batch.domain.Person;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;

public class CsvItemProcessor extends ValidatingItemProcessor<Person> {
    @Override
    public Person process(Person item) throws ValidationException {
        super.process(item);// 必须执行它 才能调用自定义的校验器
        if (item.getAddress().equals("台州")){
            item.setAddress("01");
        }else {
            item.setAddress("02");
        }
        return item;
    }
}
