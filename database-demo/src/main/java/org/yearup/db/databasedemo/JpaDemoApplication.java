package org.yearup.db.databasedemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.yearup.db.databasedemo.jdbc.PersonJdbcDao;
import org.yearup.db.databasedemo.jpa.PersonJpaRepository;
import org.yearup.db.databasedemo.models.Person;

import java.util.Date;

@SpringBootApplication
public class JpaDemoApplication implements CommandLineRunner {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PersonJpaRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(JpaDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("All users => {}", repository.findAll());
		log.info("User Id 1001 => {}", repository.findById(1001));
		//log.info("Deleting User 1002 => {}", personJdbcDao.deleteById(1002));
		//repository.deletePersonById(1002);
		log.info("Creating 1004 => {}", repository.createPerson(new Person(1004, "John", "New York", new Date())));
		log.info("Updating 1003 => {}", repository.updatePersonById(new Person(1003, "Aaron", "Atlanta", new Date())));
	}
}
