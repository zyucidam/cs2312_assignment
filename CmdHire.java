public class CmdHire extends RecordedCommand{
    
    Employee e;

@Override
public void execute(String[] cmdParts)  
{
    try{
    Company c = Company.getInstance();
   
    e = new Employee(cmdParts[1],Integer.parseInt(cmdParts[2]),new RNormalMember());

    if(c.existEmployee(e.getName()))
    {
        throw new ExEmployeeAlreadyExist();
    }
    
    c.addEmployee(e);
    
    addUndoCommand(this);
    clearRedolist();
    System.out.println("Done.");
    } catch (ExEmployeeAlreadyExist e){
        System.out.println("Employee already exists!");
    } catch (NumberFormatException e) {
        System.out.println("Wrong number format for annual leaves!");
    }

}
@Override
public void undoMe()
{
    try{
        Company c = Company.getInstance();
        if (!c.existEmployee(e.getName())) {throw new ExEmployeeNotFound();}
        c.removeEmployee(e.getName());
        addRedoCommand(this);

    } catch (ExEmployeeNotFound e){
        System.out.println("Employee not found!");
    } 
    

    
}
@Override
public void redoMe()
{
    try{
        Company c = Company.getInstance();
        if (c.existEmployee(e.getName())) {throw new ExEmployeeAlreadyExist();}
        c.addEmployee(e);
        addUndoCommand(this);
        
    } catch (ExEmployeeAlreadyExist e){
        System.out.println("Employee already exists!");
    } 
    
}

}
