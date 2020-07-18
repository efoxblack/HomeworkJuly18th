package org.yearup.db.databasedemo.jpa;

import org.springframework.stereotype.Repository;
import org.yearup.db.databasedemo.models.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class PersonJpaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Person> findAll() {
        TypedQuery<Person> namedQuery = entityManager.createNamedQuery("find_all_people", Person.class);
        return namedQuery.getResultList();
    }

    public Person findById(int id) {
        return entityManager.find(Person.class, id);
    }

    public Person updatePersonById(Person person) {
        return entityManager.merge(person);
    }

    public Person createPerson(Person person) {
        return entityManager.merge(person);
    }

    public void deletePersonById(int id) {
        Person person = findById(id);
        entityManager.remove(person);
    }

}
