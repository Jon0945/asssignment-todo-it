package se.lexicon.data;

import se.lexicon.models.TodoItemTask;

import java.util.ArrayList;
import java.util.Collection;

public class TodoItemTaskDAOImpl implements TodoITemTaskDAO {
    private static Collection<TodoItemTask> itemTasks;

    public TodoItemTaskDAOImpl(){itemTasks = new ArrayList<>();}

    @Override
    public TodoItemTask persist(TodoItemTask itemTask) {
        itemTasks.add(itemTask);
        return  itemTask;
    }
    @Override
    public TodoItemTask findById(int id) {
        for (TodoItemTask tsk : itemTasks) {
            if(tsk.getId() == id) {
                return tsk;
            }
        }
        return null;
    }
    @Override
    public Collection<TodoItemTask> findAll() {
        return itemTasks;
    }

    @Override
    public Collection<TodoItemTask> findAllByAssignedStatus(boolean assigned) {
        Collection<TodoItemTask> result = new ArrayList<>();
        for (TodoItemTask tsk : itemTasks){
            if(tsk.isAssigned()) {
                result.add(tsk);
            }
        }
        return result;
    }

    @Override
    public Collection<TodoItemTask> findByPersonId(int id) {
        Collection<TodoItemTask> result = new ArrayList<>();
        for(TodoItemTask tsk : itemTasks){
            if(tsk.getAssignee().getId() == id) {
                result.add(tsk);
            }
        }
        return result;
    }

    @Override
    public void remove(int id){itemTasks.removeIf(tsk -> tsk.getId() == id); }



}
