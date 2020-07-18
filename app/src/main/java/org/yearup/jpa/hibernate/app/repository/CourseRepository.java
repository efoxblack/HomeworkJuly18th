package org.yearup.jpa.hibernate.app.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.yearup.jpa.hibernate.app.model.Course;

import javax.persistence.EntityManager;

@Repository
public class CourseRepository {

    @Autowired
    private EntityManager em;

    public Course findById(Long id) {
        return em.find(Course.class, id);
    }

}
