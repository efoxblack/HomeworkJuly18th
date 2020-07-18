package org.yearup.jpa.hibernate.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.yearup.jpa.hibernate.app.model.Course;
import org.yearup.jpa.hibernate.app.repository.CourseRepository;

@SpringBootApplication
public class AppApplication implements CommandLineRunner {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CourseRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Course course = repository.findById(1001L);
		log.info("Course Details 1001 => {}", course);
	}
}
