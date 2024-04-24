public class CmdSuggestProject implements Command {

    @Override
    public void execute(String[] cmdParts) {
        try {
            Company c = Company.getInstance();
            if(!c.existProject(cmdParts[1].trim())) {throw new ExProjectNotFound();}
            Project p = c.findProject(cmdParts[1].trim());
            
            System.out.printf("During the period of project %s (%s to %s):\n",p.getName(),p.getStartDay(),p.getEndDay());
            System.out.println("  Average manpower (m) and count of existing projects (p) of each team:");
            c.calculateManpowerProjectCount(p);

            System.out.printf("  Projected loading factor when a team takes this project %s:\n",p.getName());

            c.suggestTeam(p);
        } catch (ExProjectNotFound e){
            System.out.println("Project not found!");
        }
    }
    
}