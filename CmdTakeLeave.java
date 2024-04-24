public class CmdTakeLeave extends RecordedCommand{
    
    private Employee myEmployee;
    private Leave myLeave;

       
@Override
public void execute(String[] cmdParts)
{
    Leave returnLeave = null;Project finalStageProject = null;
    try {
        Company c = Company.getInstance();
        if(!c.existEmployee(cmdParts[1])) {throw new ExEmployeeNotFound();}
        myEmployee = c.findEmployee(cmdParts[1]);
        Day sDay = new Day(cmdParts[2]);
        Day eDay = new Day(cmdParts[3].trim());
        myLeave = new Leave(sDay, eDay);
        returnLeave = myEmployee.overlapCheck(myLeave);
        if(returnLeave != null) {throw new ExOverlappedLeaves();}

        if(myEmployee.getTeam() != null) finalStageProject = myEmployee.getTeam().finalStageProjectCheck(myLeave);
        if(finalStageProject != null) {throw new ExLeaveTakingRestiction();}
        
        if(myEmployee.getAnnualLeaves() < myLeave.getDayslength()) {throw new ExInsufficientBalaneOfAnnualLeave();}
        
        myEmployee.addLeave(myLeave);

        addUndoCommand(this);
        clearRedolist();
        System.out.printf("Done.  %s's remaining annual leave: %d days\n",myEmployee.getName(),myEmployee.getAnnualLeaves());

    } catch (ExEmployeeNotFound e) {
        System.out.println("Employee not found!");
    } catch (ExOverlappedLeaves e) {
        System.out.println("Leave overlapped: The leave period "+ returnLeave + " is found!");
    } catch (ExInsufficientBalaneOfAnnualLeave e){
        System.out.println("Insufficient balance of annual leave. "+myEmployee.getAnnualLeaves()+" days left only!");
    } catch (ExLeaveTakingRestiction e){
        System.out.printf("The leave is invalid. Reason: Project %s will be in its final stage during %s to %s.",finalStageProject.getName(),
        new Day(finalStageProject.getStartDay().getYear(),finalStageProject.getFinalStageStartDays()),
        new Day(finalStageProject.getStartDay().getYear(),finalStageProject.getFinalStageEndDays()));
    }
}



@Override
public void undoMe()
{

    try {
        Company c = Company.getInstance();
        if(!c.existEmployee(myEmployee.getName())) {throw new ExEmployeeNotFound();}
        myEmployee = c.findEmployee(myEmployee.getName());
        
        myEmployee.removeLeave(myLeave);

        addRedoCommand(this);


    } catch (ExEmployeeNotFound e) {
        System.out.println("Employee not found!");
    } 
}

@Override
public void redoMe()
{
    Leave returnLeave = null;
    try {
        Company c = Company.getInstance();
        if(!c.existEmployee(myEmployee.getName())) {throw new ExEmployeeNotFound();}
        myEmployee = c.findEmployee(myEmployee.getName());
        returnLeave = myEmployee.overlapCheck(myLeave);
        if(returnLeave != null) {throw new ExOverlappedLeaves();}
        if(myEmployee.getAnnualLeaves() < myLeave.getDayslength()) {throw new ExInsufficientBalaneOfAnnualLeave();}
        
        myEmployee.addLeave(myLeave);

        addUndoCommand(this);

    } catch (ExEmployeeNotFound e) {
        System.out.println("Employee not found!");
    } catch (ExOverlappedLeaves e) {
        System.out.println("Leave overlapped: The leave period "+ returnLeave + " is found!");
    } catch (ExInsufficientBalaneOfAnnualLeave e){
        System.out.println("Insufficient balance of annual leave. "+myEmployee.getAnnualLeaves()+" days left only!");
    }

}

}
