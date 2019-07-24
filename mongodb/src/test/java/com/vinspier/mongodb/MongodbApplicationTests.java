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
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongodbApplicationTests {

	private static final Log log = LogFactory.getLog(MongodbApplicationTests.class);

	@Test
	public void contextLoads() {

	}

	MongoOperations mongoOps = new MongoTemplate(new MongoClient(), "vinspier");
	Person p = new Person("sdd", 23);

	@Test
	public void save(){
		// Insert is used to initially store the object into the database.
		mongoOps.insert(p);
		log.info("=========================================== person id: " + p.getId());
		log.info("=========================================== insert into " + p);
	}

	@Test
	public void find(){
		// Find
		p = mongoOps.findById(p.getId(), Person.class);
		log.info("=========================================== Found: " + p);
	}

	@Test
	public void updateP(){
		// Update
		mongoOps.updateFirst(query(where("name").is("fxb")), update("age", 35), Person.class);
		p = mongoOps.findOne(query(where("name").is("fxb")), Person.class);
		log.info("=========================================== Updated: " + p);
	}

	@Test
	public void delete(){
		// Delete
		p.setId("5d367f4478d7571eb802e643");
		p = mongoOps.findById(p.getId(), Person.class);
		mongoOps.remove(p);
	}

	@Test
	public void findAll(){
		// Check that deletion worked
		List<Person> peopleList =  mongoOps.findAll(Person.class);
		StringBuilder sb = new StringBuilder();
		for (Person p : peopleList){
			sb.append(p).append("\n");
		}
		log.info("=====================================Number of people = : " + sb);
	}

	@Test
	public void drop(){
		mongoOps.dropCollection(Person.class);
	}

	@Test
	public void findAndModify(){
		Query query = new Query(Criteria.where("name").is("fxb"));
		Update update = new Update().inc("age", 100);
		Person p = mongoOps.findAndModify(query, update, Person.class); // return's old person object
		log.info("===================================== " + p);
	}

	@Test
	public void testOptimisticLock(){
		Person person = new Person("optLock",100);
		mongoOps.insert(person);
		log.info("======================== id" + person.getId());
		Person tmp = mongoOps.findOne(query(where("id").is(person.getId())),Person.class);
		person.setAge(200);
		tmp.setAge(150);
		mongoOps.save(person);
		mongoOps.save(tmp);
	}

}
