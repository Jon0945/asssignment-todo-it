package se.lexicon.data;

import se.lexicon.models.Person;
import se.lexicon.models.Todo;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TodoItemsImpl implements TodoItems {



    private PreparedStatement createCreateTodoItem(Connection connection, Todo newItem) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(CREATE_TODO_ITEM, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1, newItem.getTitle());
        statement.setString(2, newItem.getDescription());
        statement.setDate(3, Date.valueOf(newItem.getDeadline()));
        statement.executeUpdate();
        return statement;
    }
    private PreparedStatement createFindAll(Connection connection) throws SQLException {
        return connection.prepareStatement(FIND_ALL);
    }
    private PreparedStatement createFindById(Connection connection, int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(FIND_BY_ID);
        statement.setInt(1, id);
        return statement;
    }

    private PreparedStatement createFindAllByDoneStatus(Connection connection, boolean done) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(FIND_ALL_BY_DONE_STATUS);
        statement.setBoolean(1,done);
        return statement;
    }
    private PreparedStatement createFindByAssignee(Connection connection, int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(FIND_BY_ASSIGNEE);
        statement.setInt(1,id);
        return statement;
    }

    private PreparedStatement createFindByUnassignedTodoItem(Connection connection) throws SQLException {
        return connection.prepareStatement(FIND_BY_UNASSIGNED_TODO_ITEMS);
    }

    private PreparedStatement createUpdate(Connection connection, Todo todo) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(UPDATE);
        statement.setString(1,todo.getTitle());
        statement.setString(2,todo.getDescription());
        statement.setDate(3, Date.valueOf(todo.getDeadline()));
        statement.setInt(4,todo.getId());
        return statement;
    }

    private PreparedStatement createDeleteById(Connection connection, int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(DELETE);
        statement.setInt(1,id);
        return statement;
    }
    private Todo TodoFromResultSet(ResultSet resultSet) throws SQLException {
        return new Todo(
                resultSet.getInt("todo_id"),
                resultSet.getString("title"),
                resultSet.getString("description"),
                resultSet.getDate("deadline").toLocalDate()
        );
    }
    @Override
    public Todo create(Todo newItem) {
            if(newItem.getId() !=0) {
                return newItem;
            }
            //Create database connection
            try(
                    Connection connection = Database.getConnection();
                    PreparedStatement statement = createCreateTodoItem(connection, newItem);
                    ResultSet generatedKeys = statement.getGeneratedKeys()
            ){
                int todoId = 0;
                while(generatedKeys.next()) {
                    todoId = generatedKeys.getInt(1);
                }
                newItem = new Todo(
                        todoId,
                        newItem.getTitle(),
                        newItem.getDescription(),
                        newItem.getDeadline()
                );
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return newItem;
        }

    @Override
    public Collection<Todo> findAll() {
        List<Todo> resultlist = new ArrayList<>();
        //Create database connection
        try(
                Connection connection = Database.getConnection();
                PreparedStatement statement = createFindAll(connection);
                ResultSet resultSet = statement.executeQuery()
        ){
            while (resultSet.next()) {
                resultlist.add(TodoFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultlist;
    }

    @Override
    public Todo findById(int id) {
        Todo found = null;
        //Create database connection
        try(
                Connection connection = Database.getConnection();
                PreparedStatement statement = createFindById(connection,id);
                ResultSet resultSet = statement.executeQuery()
        ){
            while(resultSet.next()) {
                found = TodoFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(found == null) {
            System.out.println("Item not found!");
        }
        return found;
    }

    @Override
    public Collection<Todo> findAllByDoneStatus(boolean done) {
        Collection<Todo> result = new ArrayList<>();
        try(
                Connection connection = Database.getConnection();
                PreparedStatement statement = createFindAllByDoneStatus(connection, done);
                ResultSet resultSet = statement.executeQuery()
        ){
            while (resultSet.next()) {
                result.add(TodoFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Collection<Todo> findByAssignee(int id) {
        Collection<Todo> result = new ArrayList<>();
        try(
                Connection connection = Database.getConnection();
                PreparedStatement statement = createFindByAssignee(connection, id);
                ResultSet resultSet = statement.executeQuery()
        ){
            while (resultSet.next()) {
                result.add(TodoFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Collection<Todo> findByAssignee(Person person) {
        Collection<Todo> result = new ArrayList<>();
        int inputId = person.getId();
        try(
                Connection connection = Database.getConnection();
                PreparedStatement statement = createFindByAssignee(connection, inputId);
                ResultSet resultSet = statement.executeQuery()
        ){
            while (resultSet.next()) {
                result.add(TodoFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Collection<Todo> findByUnassignedTodoItems() {
        Collection<Todo> result = new ArrayList<>();
        try(
                Connection connection = Database.getConnection();
                PreparedStatement statement = createFindByUnassignedTodoItem(connection);
                ResultSet resultSet = statement.executeQuery()
        ){
            while (resultSet.next()) {
                result.add(TodoFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Todo update(Todo todo) throws IllegalArgumentException {
        if(todo.getId() == 0) {
            throw new IllegalArgumentException("Can not update object. Item is not yet persisted");
        }
        try(
                Connection connection = Database.getConnection();
                PreparedStatement statement = createUpdate(connection,todo)
        ){
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todo;
    }

    @Override
    public boolean deleteById(int id) {
        int result = 0;
        try(
                Connection connection = Database.getConnection();
                PreparedStatement statement = createDeleteById(connection,id)
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
