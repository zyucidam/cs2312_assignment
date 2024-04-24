import java.util.ArrayList;

public abstract class RecordedCommand implements Command{
    
    private static ArrayList<RecordedCommand> undolist = new ArrayList<>();
    private static ArrayList<RecordedCommand> redolist = new ArrayList<>();
    
    SetupDay sDay;

    public void execute(String[] cmdParts) {}
    public void undoMe() {}
    public void redoMe() {}

    public static void addUndoCommand(RecordedCommand cmd)
    {
        undolist.add(cmd);
    }

    public static void addRedoCommand(RecordedCommand cmd)
    {
        redolist.add(cmd);
    }

    public static void clearRedolist() 
    {
        redolist.clear();
    }

    public static void undoOneCommand()
    {
        if(undolist.size() <= 0) System.out.println("Nothing to undo.");
        else undolist.remove(undolist.size()-1).undoMe();

    }

    public static void redoOneCommand()
    {
        if(redolist.size() <= 0) System.out.println("Nothing to redo.");
        else redolist.remove(redolist.size()-1).redoMe();
    }

}
