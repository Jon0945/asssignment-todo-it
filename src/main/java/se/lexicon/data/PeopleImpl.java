package se.lexicon.data;

import se.lexicon.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PeopleImpl implements People {
    private static final String CREATE_PERSON = "INSERT INTO person(first_name,last_name) VALUES(?,?)";
    private static final String FIND_ALL = "SELECT * FROM person";
    private static final String FIND_BY_ID = "SELECT * FROM person WHERE ID = ?";
    private static final String FIND_BY_NAME = "SELECT * FROM person WHERE first_name,last_name = ?";
    private static final String UPDATE = "UPDATE person SET first_name = ?, last_name = ? WHERE person_id = ?";
    private static final String DELETE = "DELETE FROM person WHERE person_id = ?";

    private PreparedStatement createCreatePerson(Connection connection, Person newPerson) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(CREATE_PERSON, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1, newPerson.getFirstName());
        statement.setString(2, newPerson.getLastName());
        statement.executeUpdate();
        return statement;
    }
    private PreparedStatement createFindAll(Connection connection) throws SQLException {
        return connection.prepareStatement(FIND_ALL);
    }
    private PreparedStatement createFindById(Connection connection, int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(FIND_BY_ID);
        statement.setInt(1,id);
        return statement;
    }
    private PreparedStatement createFindByName(Connection connection, String name) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(FIND_BY_NAME);
        statement.setString(1,name);
        return statement;
    }

    private PreparedStatement createUpdate(Connection connection, Person person) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(UPDATE);
        statement.setString(1,person.getFirstName());
        statement.setString(2,person.getLastName());
        statement.setInt(3,person.getId());
        return statement;
    }
    private PreparedStatement createDeleteById(Connection connection, int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(DELETE);
        statement.setInt(1,id);
        return statement;
    }
    private Person personFromResultSet(ResultSet resultSet) throws SQLException {
        return new Person(
                resultSet.getInt("person_id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name")
        );
    }
    @Override
    public Person create(Person newPerson) {
        if(newPerson.getId() !=0) {
            return newPerson;
        }
        //Create database connection
        try(
                Connection connection = Database.getConnection();
                PreparedStatement statement = createCreatePerson(connection, newPerson);
                ResultSet generatedKeys = statement.getGeneratedKeys()
                ){
            int personId = 0;
            while(generatedKeys.next()) {
                personId = generatedKeys.getInt(1);
            }
            newPerson = new Person(
                    personId,
                    newPerson.getFirstName(),
                    newPerson.getLastName()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newPerson;
    }
    @Override
    public Collection<Person> findAll() {
        List<Person> resultList = new ArrayList<>();
        //Create database connection
        try(
                Connection connection = Database.getConnection();
                PreparedStatement statement = createFindAll(connection);
                ResultSet resultSet = statement.executeQuery()
        ){
            while (resultSet.next()) {
                resultList.add(personFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }
    @Override
    public Person findById(int inputId) {
        Person found = null;
        //Create database connection
        try(
                Connection connection = Database.getConnection();
                PreparedStatement statement = createFindById(connection, inputId);
                ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                found = personFromResultSet(resultSet);
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
        if(found == null) {
            System.out.println("Person not found!");
        }
        return found;
    }
    @Override
    public Collection<Person> findByName(String name) {
        List<Person> resultList = new ArrayList<>();
        //Create database connection
        try(
                Connection connection = Database.getConnection();
                PreparedStatement statement = createFindByName(connection,name);
                ResultSet resultSet = statement.executeQuery()
        ){
            while (resultSet.next()) {
                resultList.add(personFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }
    @Override
    public Person update(Person person) throws IllegalArgumentException {
        if(person.getId() == 0) {
            throw new IllegalArgumentException("Can not update object. Person is not yet persisted");
        }
        //Create database connection
        try (
                Connection connection = Database.getConnection();
                PreparedStatement statement = createUpdate(connection, person)
        ){
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

    @Override
    public boolean deleteById(int inputId) {
        int result = 0;
        try (
                Connection connection = Database.getConnection();
                PreparedStatement statement = createDeleteById(connection, inputId)
        ){
            result = statement.executeUpdate();
            if(result == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
