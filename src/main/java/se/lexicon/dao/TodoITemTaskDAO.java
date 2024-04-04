package se.lexicon.dao;

import se.lexicon.models.TodoItemTask;

import java.util.Collection;

public interface TodoITemTaskDAO {
    TodoItemTask persist(TodoItemTask task);
    TodoItemTask findById(int id);
    Collection<TodoItemTask> findAll();
    Collection<TodoItemTask> findAllByAssignedStatus(boolean assigned);
    Collection<TodoItemTask> findByPersonId(int id);
    void remove(int id);
}
