package se.lexicon.dao;

import se.lexicon.models.TodoItem;

import java.time.LocalDate;
import java.util.Collection;

public interface TodoItemDAO {
    TodoItem persist(TodoItem item);
    TodoItem findById(int id);
    Collection<TodoItem> findAll();
    Collection<TodoItem> findAllByDoneStatus(boolean done);
    Collection<TodoItem> findByTitleContains(String title);
    Collection<TodoItem> findByPersonId(int id);
    Collection<TodoItem> findByDeadLineBefore(LocalDate date);
    Collection<TodoItem> findByDeadLineAfter(LocalDate date);
    void remove(int id);
}
