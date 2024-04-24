public class CmdStartNewDay extends RecordedCommand{
    
    String NewDate;
    String OldDate;

@Override
public void execute(String[] cmdParts)
{
    sDay = SetupDay.getInstance();
   
    NewDate = cmdParts[1];

    OldDate = sDay.getStringDay();    
    sDay.StartNewDay(NewDate);
    
    if(!OldDate.equals("0-Jan-0"))
    {
        addUndoCommand(this);
    }
    
    clearRedolist();
    if(!OldDate.equals("0-Jan-0")) System.out.println("Done.");
}
@Override
public void undoMe()
{
    
    sDay.StartNewDay(OldDate);
    addRedoCommand(this);

}
@Override
public void redoMe()
{
    sDay.StartNewDay(NewDate);
    addUndoCommand(this);

}

}
