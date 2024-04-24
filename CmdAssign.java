public class CmdAssign extends RecordedCommand{
    
    private Team thisTeam;
    private Project thisProject;

    
    @Override
    public void execute(String[] cmdParts)
    {

        try {
            Company c = Company.getInstance();
            if(!c.existTeam(cmdParts[2].trim())) {throw new ExTeamNotFound();}
            if(!c.existProject(cmdParts[1])) {throw new ExProjectNotFound();}
            thisTeam = c.findTeam(cmdParts[2]);
            thisProject = c.findProject(cmdParts[1]);
            if(thisProject.getAssginTeam() != null) {throw new ExProjectHasAssignedAlready();}
            
            thisProject.setAssginTeam(thisTeam);
            thisTeam.addProject(thisProject);

            addUndoCommand(this);
            clearRedolist();
            System.out.println("Done.");
        } catch (ExProjectNotFound e){
            System.out.println("Project not found!");
        } catch (ExTeamNotFound e) {
            System.out.println("Team not found!");
        } catch(ExProjectHasAssignedAlready e){
            System.out.println( "The project has already assigned by another team!");
        }
    }

    @Override
    public void undoMe()
    {
        try {
            Company c = Company.getInstance();
            if(!c.existTeam(thisTeam.getName())) {throw new ExTeamNotFound();}
            if(!c.existProject(thisProject.getName())) {throw new ExProjectNotFound();}
            
            thisProject.setAssginTeam(null);
            thisTeam.removeProject(thisProject);

            addRedoCommand(this);

        } catch (ExProjectNotFound e){
            System.out.println("Project not found!");
        } catch (ExTeamNotFound e) {
            System.out.println("Team not found!");
        }   
    }

    @Override
    public void redoMe()
    {
        try {
            Company c = Company.getInstance();
            if(!c.existTeam(thisTeam.getName())) {throw new ExTeamNotFound();}
            if(c.existProject(thisProject.getName())) {throw new ExProjectNotFound();}
            if(thisProject.getAssginTeam() != null) {throw new ExProjectHasAssignedAlready();}
            
            thisProject.setAssginTeam(thisTeam);
            thisTeam.addProject(thisProject);

            addUndoCommand(this);

        } catch (ExProjectNotFound e){
            System.out.println("Project not found!");
        } catch (ExTeamNotFound e) {
            System.out.println("Team not found!");
        } catch(ExProjectHasAssignedAlready e){
            System.out.println( "The project has already assigned by another team!");
        }
    }

}
