package se.lexicon.data;

import se.lexicon.models.Person;
import se.lexicon.models.Todo;

import java.util.Collection;

public interface TodoItems {
    Todo create(Todo item);
    Collection<Todo> findAll();
    Todo findById(int id);
    Collection<Todo> findAllByDoneStatus(boolean done);
    Collection<Todo> findByAssignee(int id);
    Collection<Todo> findByAssignee(Person person);
    Collection<Todo> findByUnassignedTodoItems();
    Todo update(Todo todo);
    boolean deleteById(int id);
}
