package se.lexicon.sequencer;

public class TodoItemIdSequencer {
    private static int currentId = 0;

    public static int getCurrentId() {return currentId;}
    public static void setCurrentId(int currentId) {
        TodoItemIdSequencer.currentId = currentId;}

    public static int nextId() {
        ++currentId;
        return currentId;
    }
}
