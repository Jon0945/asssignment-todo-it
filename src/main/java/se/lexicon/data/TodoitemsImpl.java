package se.lexicon.data;


import se.lexicon.models.Todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public class TodoitemsImpl implements Todoitems {
    private static Collection<Todo> todoitems;

    public TodoitemsImpl(){
        todoitems = new ArrayList<>();}

    @Override
    public Todo create(Todo item) {
        todoitems.add(item);
        return item;
    }

    @Override
    public Todo findById(int id) {
        for(Todo t : todoitems) {
            if(t.getId() == id) {
                return t;
            }
        }
        return null;
    }
    @Override
    public Collection<Todo> findAll() {
        return todoitems;
    }

    @Override
    public Collection<Todo> findAllByDoneStatus(boolean done) {
        Collection<Todo> result = new ArrayList<>();
        for(Todo t: todoitems){
            if(t.isDone()) {
                result.add(t);
            }
        }
        return result;
    }

    @Override
    public Collection<Todo> findByTitleContains(String title) {
        Collection<Todo> result = new ArrayList<>();
        for(Todo t: todoitems){
            if(t.getTitle().contains(title)) {
                result.add(t);
            }
        }
        return result;
    }
    @Override
    public Collection<Todo> findByPersonId(int id) {
        Collection<Todo> result = new ArrayList<>();
        for(Todo t: todoitems){
            if(t.getCreator().getId() == id) {
                result.add(t);
            }
        }
        return result;
    }

    @Override
    public Collection<Todo> findByDeadLineBefore(LocalDate date) {
        Collection<Todo> result = new ArrayList<>();
        for(Todo t: todoitems){
            if(t.getDeadline().isBefore(date)) {
                result.add(t);
            }
        }
        return result;
    }

    @Override
    public Collection<Todo> findByDeadLineAfter(LocalDate date) {
        Collection<Todo> result = new ArrayList<>();
        for (Todo t : todoitems) {
            if (t.getDeadline().isAfter(date)) {
                result.add(t);
            }
        }
        return result;
    }

    @Override
    public void remove(int id) {
        todoitems.removeIf(t -> t.getId() == id);}

}
