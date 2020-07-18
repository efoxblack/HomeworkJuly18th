package org.yearup.db.databasedemo.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.yearup.db.databasedemo.models.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class PersonJdbcDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    class PersonRowMapper implements RowMapper<Person> {

        @Override
        public Person mapRow(ResultSet resultSet, int i) throws SQLException {
            Person person = new Person();
            person.setId(resultSet.getInt("id"));
            person.setName(resultSet.getString("name"));
            person.setLocation(resultSet.getString("location"));
            person.setBirthDate(resultSet.getTimestamp("birth_date"));
            return person;
        }
    }

    // SELECT * FROM PERSON;
    public List<Person> findAll() {
        return jdbcTemplate.query("SELECT * FROM PERSON", new PersonRowMapper());
    }

    public Person findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM PERSON WHERE ID=?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class));
    }

    public int createPerson(Person person) {
        return jdbcTemplate.update("INSERT INTO PERSON VALUES(?, ?, ?, ?)",
                new Object[] {person.getId(),
                        person.getName(),
                        person.getLocation(),
                        new Timestamp(person.getBirthDate().getTime()) });
    }

    public int updatePersonById(Person person) {
        return jdbcTemplate.update("UPDATE PERSON SET NAME = ?, LOCATION = ?, BIRTH_DATE = ?" +
                        "WHERE ID = ?",
                new Object[] {person.getName(),
                        person.getLocation(),
                        new Timestamp(person.getBirthDate().getTime()),
                        person.getId() });
    }

    public int deleteById(int id) {
        return jdbcTemplate.update("DELETE FROM PERSON WHERE ID=?",
                new Object[]{id});
    }

}
