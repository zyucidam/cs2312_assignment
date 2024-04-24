public class CmdJoinTeam extends RecordedCommand{
    
    private Team thisTeam;
    private Employee thisEmployee;

        
    @Override
    public void execute(String[] cmdParts)
    {
        try {
            Company c = Company.getInstance();
            if(!c.existTeam(cmdParts[2].trim())) {throw new ExTeamNotFound();}
            if(!c.existEmployee(cmdParts[1])) {throw new ExEmployeeNotFound();}
            thisTeam = c.findTeam(cmdParts[2].trim());
            thisEmployee = c.findEmployee(cmdParts[1]);
            if(thisEmployee.getTeam() != null) {throw new ExEmployeeHasJoinedATeamAlready();}
            thisTeam.addEmployee(thisEmployee);
            thisEmployee.setTeam(thisTeam);

            addUndoCommand(this);
            clearRedolist();
            System.out.println("Done.");
        } catch (ExTeamNotFound e){
            System.out.println("Team not found!");
        } catch (ExEmployeeNotFound e) {
            System.out.println("Employee not found!");
        } catch(ExEmployeeHasJoinedATeamAlready e){
            System.out.println(thisEmployee.getName()+ " has already joined another team: "+thisEmployee.getTeam().getName());
        }
    }

    @Override
    public void undoMe()
    {
        try {
            Company c = Company.getInstance();
            if(!c.existTeam(thisTeam.getName())) {throw new ExTeamNotFound();}
            if(!c.existEmployee(thisEmployee.getName())) {throw new ExEmployeeNotFound();}
            if(!thisTeam.existEmployee(thisEmployee.getName())) {throw new ExEmployeeNotFound();}
            
            thisTeam.removeEmployee(thisEmployee);
            thisEmployee.setTeam(null);

            addRedoCommand(this);
        } catch (ExTeamNotFound e){
            System.out.println("Team not found!");
        } catch (ExEmployeeNotFound e) {
            System.out.println("Employee not found!");
        }
    }

    @Override
    public void redoMe()
    {
        try {
            Company c = Company.getInstance();
            if(!c.existTeam(thisTeam.getName())) {throw new ExTeamNotFound();}
            if(!c.existEmployee(thisEmployee.getName())) {throw new ExEmployeeNotFound();}
            if(thisEmployee.getTeam() != null) {throw new ExEmployeeHasJoinedATeamAlready();}
            
            thisTeam.addEmployee(thisEmployee);
            thisEmployee.setTeam(thisTeam);

            addUndoCommand(this);
        } catch (ExTeamNotFound e){
            System.out.println("Team not found!");
        } catch (ExEmployeeNotFound e) {
            System.out.println("Employee not found!");
        }catch(ExEmployeeHasJoinedATeamAlready e){
            System.out.println(thisEmployee.getName()+ " has already joined another team: "+thisEmployee.getTeam().getName());
        }
    }
}
