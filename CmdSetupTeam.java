public class CmdSetupTeam extends RecordedCommand{
    
    private Team thisTeam;
    private Employee thisEmployee;

    @Override
    public void execute(String[] cmdParts)
    {
        try{
            
        Company c = Company.getInstance();
        if(c.existTeam(cmdParts[1])) {throw new ExTeamAlreadyExist();}
        thisEmployee = c.findEmployee(cmdParts[2].trim());
        if(thisEmployee.getTeam() != null) {throw new ExEmployeeHasJoinedATeamAlready();}
        thisTeam = new Team(cmdParts[1],thisEmployee);

        thisTeam.addEmployee(thisEmployee);
        thisEmployee.setTeam(thisTeam);
        c.addTeam(thisTeam);

        addUndoCommand(this);
        clearRedolist();
        System.out.println("Done.");

       
        } catch (ExEmployeeNotFound e) {
            System.out.println("Employee not found!");
        } catch (ExTeamAlreadyExist e) {
            System.out.println("Team already exists!");
        } catch(ExEmployeeHasJoinedATeamAlready e){
            System.out.println(thisEmployee.getName()+ " has already joined another team: "+thisEmployee.getTeam().getName());
        }
        
    }
    @Override
    public void undoMe()
    {
        try {
            Company c = Company.getInstance();
            if (!c.existTeam(thisTeam.getName())) {throw new ExTeamNotFound();}
            
            thisTeam.removeEmployee(thisEmployee);
            thisEmployee.setTeam(null);
            c.removeTeam(thisTeam.getName());
            addRedoCommand(this);

        } catch (ExTeamNotFound e) {
            System.out.println("Team not found!");
        }

    }
    @Override
    public void redoMe()
    {
        try {
            Company c = Company.getInstance();
            if (c.existTeam(thisTeam.getName())) {throw new ExTeamAlreadyExist();}

            thisTeam.addEmployee(thisEmployee);
            thisEmployee.setTeam(thisTeam);
            c.addTeam(thisTeam);

            addUndoCommand(this);

        } catch (ExTeamAlreadyExist e) {
            System.out.println("Team already exists!");
        }
        

    }

}
