public class ExEmployeeHasJoinedATeamAlready extends Exception{
    public ExEmployeeHasJoinedATeamAlready() {super(" has already joined another team: ");}   
    public ExEmployeeHasJoinedATeamAlready(String message) {super(message);}
}