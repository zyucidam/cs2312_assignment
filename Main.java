
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String [] args) 
	{	
			Scanner in = new Scanner(System.in);
	System.out.print("Please input the file pathname: ");
	String filepathname = in.nextLine();
	Scanner inFile = null;
	try{
	inFile = new Scanner(new File(filepathname));
	
	Company company = Company.getInstance();
	

	while (inFile.hasNext())		
	{
		try{
			
			String cmdLine = inFile.nextLine(); 
			
			//Blank lines exist in data file as separators.  Skip them.
			if (cmdLine.equals("")) continue;  
			
			System.out.println("\n> "+cmdLine);
			
			//split the words in actionLine => create an array of word strings [!!! LEARN !!!]
			String[] cmdParts = cmdLine.split("\\|"); 
			
			if (cmdParts[0].equals("startNewDay"))
				(new CmdStartNewDay()).execute(cmdParts);
			else if (cmdParts[0].equals("hire"))
				(new CmdHire()).execute(cmdParts);
			else if (cmdParts[0].equals("setupTeam"))
				(new CmdSetupTeam()).execute(cmdParts);
			else if (cmdParts[0].equals("createProject"))
				(new CmdCreateProject()).execute(cmdParts);
			else if (cmdParts[0].equals("joinTeam"))
				(new CmdJoinTeam()).execute(cmdParts);
			else if (cmdParts[0].equals("takeLeave"))
				(new CmdTakeLeave()).execute(cmdParts);
			else if (cmdParts[0].equals("assign"))
				(new CmdAssign()).execute(cmdParts);
			else if (cmdParts[0].equals("listTeamMembers"))
				(new CmdListTeamMembers()).execute(cmdParts);
			else if (cmdParts[0].equals("suggestProjectTeam"))
				(new CmdSuggestProject()).execute(cmdParts);
			else if (cmdParts[0].equals("undo"))
				RecordedCommand.undoOneCommand();
			else if (cmdParts[0].equals("redo"))
				RecordedCommand.redoOneCommand();
			else if (cmdParts[0].equals("listEmployees"))
				company.listEmployees();
			else if (cmdParts[0].equals("listTeams"))
				company.listTeams();
			else if (cmdParts[0].equals("listProjects"))
				company.listProjects();
			else if (cmdParts[0].equals("listLeaves"))
			{
				if(cmdParts.length > 1) (new CmdListLeaves()).execute(cmdParts);
				else company.listLeaves();
			}
				
			else 
				throw new ExWrongCommand();
		} catch (ExWrongCommand e) {
			System.out.println("Insufficient command arguments!");
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Insufficient command arguments!");
		}
	} 
	} catch (FileNotFoundException e) {
		System.out.println("File not found.");
	} catch (InputMismatchException e) {
		System.out.println("File content problem. Program terminated.");
	} finally {
		if(inFile != null) inFile.close();
		in.close();
	}
	}
}



