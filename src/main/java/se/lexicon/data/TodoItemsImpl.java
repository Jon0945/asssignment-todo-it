package se.lexicon.data;

import se.lexicon.exception.CustomSQLException;
import se.lexicon.models.Person;
import se.lexicon.models.Todo;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TodoItemsImpl implements TodoItems {
    private Connection connection;
    public TodoItemsImpl(Connection connection) {
        this.connection = connection;
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
        try(
                PreparedStatement statement = connection.prepareStatement(Queries.CREATE_TODO_ITEM,
                        Statement.RETURN_GENERATED_KEYS)
        ){
            statement.setString(1, newItem.getTitle());
            statement.setString(2, newItem.getDescription());
            statement.setDate(3, Date.valueOf(newItem.getDeadline()));
            int affectedRows = statement.executeUpdate();
            if(affectedRows == 0) {
                String errorMessage = "Create Item failed. No rows affected.";
                throw new CustomSQLException(errorMessage);
            }
            try(ResultSet resultSet = statement.getGeneratedKeys()) {
                if(resultSet.next()) {
                    int todoId = resultSet.getInt(1);
                    return new Todo(todoId, newItem.getTitle(), newItem.getDescription(), newItem.getDeadline());
                } else {
                    String errorMessage = "Create Item failed. No ID Obtained.";
                    throw new CustomSQLException(errorMessage);
                }
            }
        } catch (SQLException e) {
                String errorMessage = "An error occurred while creating a new item.";
                throw new CustomSQLException(errorMessage,e);
            }
    }
    @Override
    public Collection<Todo> findAll() {
        List<Todo> resultlist = new ArrayList<>();
        try(
                PreparedStatement statement = connection.prepareStatement(Queries.FIND_ALL_TODO_ITEMS)
        ){
            try(ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()) {
                    resultlist.add(TodoFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            String errorMessage = "An error occurred while trying to find all items";
            throw new CustomSQLException(errorMessage,e);
        }
        return resultlist;
    }

    @Override
    public Todo findById(int id) {
        try (
                PreparedStatement statement = connection.prepareStatement(Queries.FIND_ITEM_BY_ID)
        ) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return TodoFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            String errorMessage = "And error occurred while finding item with ID: " + id;
            throw new CustomSQLException(errorMessage,e);
        }
        return null;
    }

    @Override
    public Collection<Todo> findAllByDoneStatus(boolean done) {
        List<Todo> result = new ArrayList<>();
        try(
                PreparedStatement statement = connection.prepareStatement(Queries.FIND_ALL_BY_DONE_STATUS)
        ){
            statement.setBoolean(1,done);
            try(ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()) {
                    result.add(TodoFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            String errorMessage = "An error occurred while trying to find all done items";
            throw new CustomSQLException(errorMessage,e);
        }
        return result;
    }

    @Override
    public Collection<Todo> findByAssignee(int id) {
        Collection<Todo> result = new ArrayList<>();
        try(
                PreparedStatement statement = connection.prepareStatement(Queries.FIND_ITEM_BY_ASSIGNEE)
        ){
            statement.setInt(1,id);
            try(ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()) {
                    result.add(TodoFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            String errorMessage = "An error occurred while trying to find all items by assignee id";
            throw new CustomSQLException(errorMessage,e);
        }
        return result;
    }

    @Override
    public Collection<Todo> findByAssignee(Person person) {
        Collection<Todo> result = new ArrayList<>();
        int inputId = person.getId();
        try(
        PreparedStatement statement = connection.prepareStatement(Queries.FIND_ITEM_BY_ASSIGNEE)
        ){
            statement.setInt(1,inputId);
            try(ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()) {
                    result.add(TodoFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            String errorMessage = "An error occurred while trying to find all items by assignee id";
            throw new CustomSQLException(errorMessage,e);
        }
        return result;
    }

    @Override
    public Collection<Todo> findByUnassignedTodoItems() {
        Collection<Todo> result = new ArrayList<>();
        try(
                PreparedStatement statement = connection.prepareStatement(Queries.FIND_BY_UNASSIGNED_TODO_ITEMS)
        ){
            try(ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()) {
                    result.add(TodoFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            String errorMessage = "An error occurred while trying to find all unassigned items";
            throw new CustomSQLException(errorMessage,e);
        }
        return result;
    }

    @Override
    public Todo update(Todo todo) throws IllegalArgumentException {
        if(todo.getId() == 0) {
            throw new IllegalArgumentException("Can not update object. Item is not yet persisted");
        }
        try(
                PreparedStatement statement = connection.prepareStatement(Queries.UPDATE_ITEM)
        ){
            statement.setString(1,todo.getTitle());
            statement.setString(2,todo.getDescription());
            statement.setDate(3, Date.valueOf(todo.getDeadline()));
            statement.setInt(4,todo.getId());
            boolean updateDone = statement.execute();
            if (updateDone) {
                System.out.println("Item updated successfully!");
                return todo;
            } else {
                String errorMessage = "An error occurred. Item not updated.";
                throw new CustomSQLException(errorMessage);
            }
        } catch (SQLException e) {
            String errorMessage = "An error occurred when updating an item.";
            throw new CustomSQLException(errorMessage,e);
        }
    }

    @Override
    public boolean deleteById(int id) {
        try(
                PreparedStatement statement = connection.prepareStatement(Queries.DELETE_ITEM)
        ){
            statement.setInt(1,id);
            int result = statement.executeUpdate();
            if(result == 1) {
                System.out.println("Item deleted successfully!");
                return true;
            } else {
                String errorMessage = "An error occurred. Item not deleted.";
                throw new CustomSQLException(errorMessage);
            }
        } catch (SQLException e) {
            String errorMessage = "An error occurred when deleting an item";
            throw new CustomSQLException(errorMessage,e);
        }
    }
}
