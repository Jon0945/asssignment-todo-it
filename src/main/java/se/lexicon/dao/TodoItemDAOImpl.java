package se.lexicon.dao;


import se.lexicon.models.TodoItem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public class TodoItemDAOImpl implements TodoItemDAO {
    private static Collection<TodoItem> todoItems;

    public TodoItemDAOImpl(){todoItems = new ArrayList<>();}

    @Override
    public TodoItem persist(TodoItem item) {
        todoItems.add(item);
        return item;
    }

    @Override
    public TodoItem findById(int id) {
        for(TodoItem t : todoItems) {
            if(t.getId() == id) {
                return t;
            }
        }
        return null;
    }
    @Override
    public Collection<TodoItem> findAll() {
        return todoItems;
    }

    @Override
    public Collection<TodoItem> findAllByDoneStatus(boolean done) {
        Collection<TodoItem> result = new ArrayList<>();
        for(TodoItem t: todoItems){
            if(t.isDone()) {
                result.add(t);
            }
        }
        return result;
    }

    @Override
    public Collection<TodoItem> findByTitleContains(String title) {
        Collection<TodoItem> result = new ArrayList<>();
        for(TodoItem t: todoItems){
            if(t.getTitle().contains(title)) {
                result.add(t);
            }
        }
        return result;
    }
    @Override
    public Collection<TodoItem> findByPersonId(int id) {
        Collection<TodoItem> result = new ArrayList<>();
        for(TodoItem t: todoItems){
            if(t.getCreator().getId() == id) {
                result.add(t);
            }
        }
        return result;
    }

    @Override
    public Collection<TodoItem> findByDeadLineBefore(LocalDate date) {
        Collection<TodoItem> result = new ArrayList<>();
        for(TodoItem t: todoItems){
            if(t.getDeadline().isBefore(date)) {
                result.add(t);
            }
        }
        return result;
    }

    @Override
    public Collection<TodoItem> findByDeadLineAfter(LocalDate date) {
        Collection<TodoItem> result = new ArrayList<>();
        for (TodoItem t : todoItems) {
            if (t.getDeadline().isAfter(date)) {
                result.add(t);
            }
        }
        return result;
    }

    @Override
    public void remove(int id) {todoItems.removeIf(t -> t.getId() == id);}

}
