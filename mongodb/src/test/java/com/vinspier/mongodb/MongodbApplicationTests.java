package com.vinspier.mongodb;

import com.mongodb.MongoClient;
import com.vinspier.mongodb.pojo.Person;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongodbApplicationTests {

	private static final Log log = LogFactory.getLog(MongodbApplicationTests.class);

	@Test
	public void contextLoads() {

	}

	@Test
	public void save(){
		MongoOperations mongoOps = new MongoTemplate(new MongoClient(), "vinspier");
		mongoOps.insert(new Person("Joe", 34));

		log.info(mongoOps.findOne(new Query(where("name").is("Joe")), Person.class));

		// mongoOps.dropCollection("person");
	}

}
