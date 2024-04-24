import java.util.ArrayList;
import java.util.Collections;

public class Company {
    
    private ArrayList<Employee> allEmployees;
    private ArrayList<Team> allTeams;
    private ArrayList<Project> allProjects;
    private static Company instance = new Company();
    public static Company getInstance() {return instance;}

    private Company() {
        allEmployees = new ArrayList<Employee>();
        allTeams = new ArrayList<Team>();
        allProjects = new ArrayList<Project>();
    } 


    
    
    public Employee findEmployee(String name) throws ExEmployeeNotFound {
        for(Employee e: allEmployees)
        {
            if(e.getName().equals(name)){
                return e;
            } 
        }
        throw new ExEmployeeNotFound();
    } 

    public Team findTeam(String name) throws ExTeamNotFound {
        for(Team t: allTeams)
        {
            if(t.getName().equals(name)) return t;
        }
        throw new ExTeamNotFound();
    } 

    public Project findProject(String name) throws ExProjectNotFound {
        for(Project p: allProjects)
        {
            if(p.getName().equals(name)) return p;
        }
        throw new ExProjectNotFound();
    } 

    public boolean existEmployee(String name) {
        for(Employee e: allEmployees)
        {
            if(e.getName().equals(name)) return true;
        }
        return false;
    }

    public boolean existTeam(String name) {
        for(Team t: allTeams)
        {
            if(t.getName().equals(name)) return true;
        }
        return false;
    }

    public boolean existProject(String name) {
        for(Project p: allProjects)
        {
            if(p.getName().equals(name)) return true;
        }
        return false;
    }

    public void addEmployee(Employee e) {
        allEmployees.add(e);
    } //add an employee to the array list

    public void addTeam(Team t) {
        allTeams.add(t);
    }

    public void addProject(Project p) {
        allProjects.add(p);
    }

    public void removeEmployee(String aName) {
        for(int i=0;i<allEmployees.size();i++)
        {
            if (allEmployees.get(i).getName().equals(aName)) allEmployees.remove(allEmployees.get(i));
        }
    }

    public void removeTeam(String aName) {
        for(int i=0;i<allTeams.size();i++)
        {
            if (allTeams.get(i).getName().equals(aName)) allTeams.remove(allTeams.get(i));
        }
    }

    public void removeProject(String aName) {
        for(int i=0;i<allProjects.size();i++)
        {
            if (allProjects.get(i).getName().equals(aName)) allProjects.remove(allProjects.get(i));
        }
    }

    public void calculateManpowerProjectCount(Project p)
    {
        Collections.sort(allTeams);
        int lbarrier = p.getStartDay().getDays();
        int rbarrier = p.getEndDay().getDays();
        for(Team t:allTeams)
        {
            double m = t.calculateTeamManpower(lbarrier,rbarrier);
            double pC = t.calculateProjectCount(lbarrier, rbarrier);
            t.setLoadingFactor((pC+1.0)/m);
            System.out.printf("    %s: m=%.2f workers, p=%.2f projects\n",t.getName(),m,pC);
        }
    }

    public void suggestTeam(Project p)
    {
        double minlF = 100000000;Team minTeam = null;
        for(Team t:allTeams)
        {
            double lF = t.getLoadingFactor();
            if (minlF > lF) {
                minTeam = t;
                minlF = lF;
            }
            System.out.printf("    %s: (p+1)/m = %.2f\n",t.getName(),lF);
        }
        System.out.printf("Conclusion: %s should be assigned to %s for best balancing of loading\n",p.getName(),minTeam.getName());
    }

    
    public void listEmployees() {
        Collections.sort(allEmployees);
        for(Employee e: allEmployees)
        {
            System.out.println(e);
        }
    } 

    public void listTeams() {
        Collections.sort(allTeams);
        System.out.printf("%-20s %-20s %-20s\n","Team Name","Leader","Setup Date");
        for(Team t: allTeams)
        {
            System.out.println(t);
        }
    } 

    public void listProjects() {
        Collections.sort(allProjects);
        System.out.printf("%-20s%-20s%-20s%-20s\n","Project","Start Day","End Day","Team");
        for(Project p: allProjects)
        {
            System.out.println(p);
        }
    } 

    public void listLeaves() {
        Collections.sort(allEmployees);
        for(Employee e: allEmployees)
        {
            e.printLeaves();
        }
    }

}
