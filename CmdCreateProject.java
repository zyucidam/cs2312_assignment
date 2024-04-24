public class CmdCreateProject extends RecordedCommand{
    
    
    private Project thisProject;

    @Override
    public void execute(String[] cmdParts)
    {
        try{
            
        Company c = Company.getInstance();
        if(c.existProject(cmdParts[1])) {throw new ExProjectAlreadyExist();}
        thisProject = new Project (cmdParts[1],new Day(cmdParts[2]),Integer.parseInt(cmdParts[3]));
        
        c.addProject(thisProject);

        addUndoCommand(this);
        clearRedolist();
        System.out.println("Done.");

       
        } catch (ExProjectAlreadyExist e) {
            System.out.println("Project already exists!");
        } catch (NumberFormatException e) {
            System.out.println("Wrong number format for project duration!");
        }
        
    }
    @Override
    public void undoMe()
    { 
        try{
                
            Company c = Company.getInstance();
            if(!c.existProject(thisProject.getName())) {throw new ExProjectNotFound();}
            
            c.removeProject(thisProject.getName());

            addRedoCommand(this);
        
        } catch (ExProjectNotFound e) {
            System.out.println("Project not found!");
        } 
    }
    @Override
    public void redoMe()
    {
        try{
                
            Company c = Company.getInstance();
            if(c.existProject(thisProject.getName())) {throw new ExProjectAlreadyExist();}
            
            c.addProject(thisProject);

            addUndoCommand(this);
        
        } catch (ExProjectAlreadyExist e) {
            System.out.println("Project already exists!");
        } 
    }


}
