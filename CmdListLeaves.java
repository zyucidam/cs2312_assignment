public class CmdListLeaves implements Command {

    @Override
    public void execute(String[] cmdParts) {
        try {
            Company c = Company.getInstance();
            if(!c.existEmployee(cmdParts[1].trim())) {throw new ExEmployeeNotFound();}
            Employee e = c.findEmployee(cmdParts[1].trim());
            e.printLeaves();
        } catch (ExEmployeeNotFound e){
            System.out.println("Employee not found!");
        }
    }
    
}