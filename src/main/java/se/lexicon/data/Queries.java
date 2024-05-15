package se.lexicon.data;

public class Queries {
    public static final String CREATE_PERSON = "INSERT INTO person(first_name,last_name) VALUES(?,?)";
    public static final String FIND_ALL_PERSONS = "SELECT * FROM person";
    public static final String FIND_PERSON_BY_ID = "SELECT * FROM person WHERE ID = ?";
    public static final String FIND_PERSON_BY_NAME = "SELECT * FROM person WHERE first_name,last_name = ?";
    public static final String UPDATE_PERSON = "UPDATE person SET first_name = ?, last_name = ? WHERE person_id = ?";
    public static final String DELETE_PERSON = "DELETE FROM person WHERE person_id = ?";
    private static final String CREATE_TODO_ITEM = "INSERT INTO todo_item(title,description,deadline) VALUES(?,?,?)";
    private static final String FIND_ALL_TODO_ITEMS = "SELECT * FROM todo_items";
    private static final String FIND_ITEM_BY_ID = "SELECT * FROM todo_items WHERE ID = ?";
    private static final String FIND_ALL_BY_DONE_STATUS = "SELECT * from todo_items WHERE done = ?";
    private static final String FIND_ITEM_BY_ASSIGNEE = "SELECT * FROM todo_items WHERE assignee_id = ?";
    private static final String FIND_BY_UNASSIGNED_TODO_ITEMS = "SELECT * FROM todo_items WHERE assignee_id IS NULL";
    private static final String UPDATE_ITEM = "UPDATE todo_items SET title = ?, description = ?, deadline = ? WHERE todo_id = ?";
    private static final String DELETE_ITEM = "DELETE FROM todo_items WHERE todo_id = ?";
}
