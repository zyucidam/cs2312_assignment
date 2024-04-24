public class ExProjectHasAssignedAlready extends Exception{
    public ExProjectHasAssignedAlready() {super("Project has already assigned by another team: ");}   
    public ExProjectHasAssignedAlready(String message) {super(message);}
}