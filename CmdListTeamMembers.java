public class CmdListTeamMembers implements Command {

    @Override
    public void execute(String[] cmdParts) {
        try {
            Company c = Company.getInstance();
            if(!c.existTeam(cmdParts[1].trim())) {throw new ExTeamNotFound();}
            Team t = c.findTeam(cmdParts[1].trim());
            t.listTeamMembers();
        } catch (ExTeamNotFound e){
            System.out.println("Team not found!");
        }
    }
    
}