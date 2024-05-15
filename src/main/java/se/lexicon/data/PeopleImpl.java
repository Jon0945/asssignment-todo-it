package se.lexicon.data;

import se.lexicon.exception.CustomSQLException;
import se.lexicon.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PeopleImpl implements People {
    private Connection connection;
    public PeopleImpl(Connection connection) {
        this.connection = connection;
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
        try(
                PreparedStatement statement = connection.prepareStatement(Queries.CREATE_PERSON,
                        Statement.RETURN_GENERATED_KEYS)
        ){
            statement.setString(1, newPerson.getFirstName());
            statement.setString(2, newPerson.getLastName());
            int affectedRows =  statement.executeUpdate();
            if (affectedRows == 0) {
                String errorMessage = "Create Person failed. No rows affected";
                throw new CustomSQLException(errorMessage);
            }
            try(ResultSet resultSet = statement.getGeneratedKeys()){
                if(resultSet.next()) {
                    int personId = resultSet.getInt(1);
                    return new Person(personId, newPerson.getFirstName(), newPerson.getLastName());
                } else {
                    String errorMessage = "Create Person failed. No ID obtained";
                    throw new CustomSQLException(errorMessage);
                }
            }
        } catch (SQLException e) {
            String errorMessage = "An error occurred while creating a person";
            throw new CustomSQLException(errorMessage,e);
        }
    }
    @Override
    public Collection<Person> findAll() {
        List<Person> resultList = new ArrayList<>();
        try(
                PreparedStatement statement = connection.prepareStatement(Queries.FIND_ALL_PERSONS)
        ){
            try(ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()) {
                    resultList.add(personFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            String errorMessage = "An error occurred while trying to find all persons";
            throw new CustomSQLException(errorMessage,e);
        }
        return resultList;
    }
    @Override
    public Person findById(int inputId) {

        try (
                PreparedStatement statement = connection.prepareStatement(Queries.FIND_PERSON_BY_ID)
        ) {
            statement.setInt(1, inputId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return personFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            String errorMessage = "And error occurred while finding person with ID: " + inputId;
            throw new CustomSQLException(errorMessage);
        }
        return null;
    }


    @Override
    public Collection<Person> findByName(String name) {
        List<Person> resultList = new ArrayList<>();
        try(
                PreparedStatement statement = connection.prepareStatement(Queries.FIND_PERSON_BY_NAME)
        ){
            statement.setString(1,name);
                try(ResultSet resultSet = statement.executeQuery()){
                    while(resultSet.next()){
                        resultList.add(personFromResultSet(resultSet));
                    }
                }
        } catch (SQLException e) {
            String errorMessage = "An error occurred while finding person(s) with name: " + name;
            throw new CustomSQLException(errorMessage);
        }
        return resultList;
    }
    @Override
    public Person update(Person person) throws IllegalArgumentException {
        if(person.getId() == 0) {
            throw new IllegalArgumentException("Can not update object. Person is not yet persisted");
        }
        try (
                PreparedStatement statement = connection.prepareStatement(Queries.UPDATE_PERSON);
        ) {
            statement.setString(1, person.getFirstName());
            statement.setString(2, person.getLastName());
            statement.setInt(3, person.getId());
            boolean updateDone = statement.execute();
            if (updateDone) {
                System.out.println("Person updated successfully!");
                return person;
            } else {
                String errorMessage = "An error occurred. Person not updated.";
                throw new CustomSQLException(errorMessage);
            }
        } catch (SQLException e) {
            String errorMessage = "An error occurred. Person not updated.";
            throw new CustomSQLException(errorMessage);
        }
    }

    @Override
    public boolean deleteById(int inputId) {
        try (
                PreparedStatement statement = connection.prepareStatement(Queries.DELETE_PERSON)
        ){
            statement.setInt(1,inputId);
            int result = statement.executeUpdate();
            if(result == 1) {
                System.out.println("Person deleted successfully!");
                return true;
            } else {
                String errorMessage = "An error occured. Person not deleted.";
                throw new CustomSQLException(errorMessage);
            }
        } catch (SQLException e) {
            String errorMessage = "An error occured. Person not deleted.";
            throw new CustomSQLException(errorMessage);
        }
    }
}
