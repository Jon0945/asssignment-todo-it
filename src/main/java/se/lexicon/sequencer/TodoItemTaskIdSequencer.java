package se.lexicon.sequencer;

public class TodoItemTaskIdSequencer {
    private static int currentId = 0;

    public static int getCurrentId() {return currentId;}
    public static void setCurrentId(int currentId) {TodoItemTaskIdSequencer.currentId = currentId;}

    public static int nextId() {
        ++currentId;
        return currentId;
    }
}
