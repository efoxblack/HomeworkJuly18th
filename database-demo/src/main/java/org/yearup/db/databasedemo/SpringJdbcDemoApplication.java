package org.yearup.db.databasedemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.yearup.db.databasedemo.jdbc.PersonJdbcDao;
import org.yearup.db.databasedemo.models.Person;

import java.util.Date;

//@SpringBootApplication
public class SpringJdbcDemoApplication implements CommandLineRunner {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PersonJdbcDao personJdbcDao;

	public static void main(String[] args) {
		SpringApplication.run(SpringJdbcDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("All users => {}", personJdbcDao.findAll());
		log.info("User Id 1001 => {}", personJdbcDao.findById(1001));
		log.info("Deleting User 1002 => {}", personJdbcDao.deleteById(1002));
		log.info("Creating 1004 => {}", personJdbcDao.createPerson(new Person(1004, "John", "New York", new Date())));
		log.info("Updating 1003 => {}", personJdbcDao.updatePersonById(new Person(1003, "Aaron", "Atlanta", new Date())));
	}
}
