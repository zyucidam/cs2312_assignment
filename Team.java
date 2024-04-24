import java.util.ArrayList;
import java.util.Collections;

public class Team implements Comparable<Team>{
    
    private ArrayList<Employee> allMembers;
    private ArrayList<Project> allProjects;
    private String teamName;
    private Employee teamLeader;
    private String setupDay;
    private double loadingFactor;

    public Team(String aTeamName,Employee leader)
    {
        this.teamName = aTeamName;
        setLeader(leader);
        setupDay = SetupDay.getInstance().getStringDay();
        allMembers = new ArrayList<Employee>();
        allProjects = new ArrayList<Project>();
    }

    

    public void setLeader(Employee leader)
    {
        leader.setRole("leader");
        teamLeader = leader;
    }

    public void setLoadingFactor(double lF) {this.loadingFactor = lF;}

    public String getName() {return teamName;}

    public Employee getLeader() {return teamLeader;}

    public double getLoadingFactor() {return loadingFactor;}

    public boolean existEmployee(String name){
        for(Employee e:allMembers)
        {
            if(e.getName().equals(name)) return true;
        }
        return false;
    }

    public void addEmployee(Employee e)
    {
        allMembers.add(e);
    }

    public void addProject(Project p)
    {
        allProjects.add(p);
    }

    public void removeEmployee(Employee e){
        allMembers.remove(e);
    }

    public void removeProject(Project p)
    {
        allProjects.remove(p);
    }

    public Project finalStageProjectCheck(Leave aLeave)
    {
        int lbarrier = aLeave.getStartDaysDays();
        int rbarrier = aLeave.getEndDaysDays();
        for(Project p:allProjects)
        {
            int StartDayBarrier = p.getFinalStageStartDays();
            int EndDayBarrier = p.getFinalStageEndDays();
            if(!((rbarrier < StartDayBarrier) 
            || (lbarrier > EndDayBarrier))) return p;
        }
        return null;
    }

    public double calculateTeamManpower(int lbarrier,int rbarrier)
    {
        double sum = 0;
        for(Employee e:allMembers)
        {
            sum = sum + e.calculateEmployeeManpower(lbarrier,rbarrier);
        }
        return sum;
    }

    public double calculateProjectCount(int lbarrier,int rbarrier)
    {
        double sum = 0;
        for(Project p:allProjects)
        {
            int StartDayBarrier = p.getStartDay().getDays();
            int EndDayBarrier = p.getEndDay().getDays();
            if(!((rbarrier < StartDayBarrier) 
            || (lbarrier > EndDayBarrier))){
                sum = sum + (min(EndDayBarrier,rbarrier) - max(lbarrier,StartDayBarrier) + 1.0)/ (rbarrier - lbarrier + 1.0);
            }
        }
        return sum;
    }

    private int max(int a, int b) {
        return a > b ? a : b;
    }

    private int min(int a, int b) {
        return a < b ? a : b;
    }

    public void listTeamMembers()
    {
        Collections.sort(allMembers);
        System.out.printf("%-20s %-20s %-20s\n","Role","Name","Current / coming leaves");
        teamLeader.printDetail();
        for(Employee e:allMembers)
        {
            if(e.getRole().getRoleClass().equals("M")) e.printDetail();
        }
    }

    public String shortListing()
    {
        Collections.sort(allMembers);
        String res = teamName + " (" +teamLeader.getName();
        if(allMembers.size() == 1)
        {
            res += ")";
            return res;
        } else if (allMembers.size() == 2) {
            res += ", ";
            for(int i=0;i<allMembers.size();i++)
            {
                if(allMembers.get(i).getRole().getRoleClass().equals("M")) res += allMembers.get(i).getName() + ")";
            } 
            return res;
        } else {
            res += ", ";
            for(int i=0;i<allMembers.size()-1;i++)
            {
                if(allMembers.get(i).getRole().getRoleClass().equals("M")) res += allMembers.get(i).getName() + ", ";
            }
            if(allMembers.get(allMembers.size()-1).getRole().getRoleClass().equals("M")) {
                res += (allMembers.get(allMembers.size()-1)).getName()+")";
            }
            return res;
        }
        
    }


    public String toString()
    {
        return String.format("%-20s %-20s %-20s",teamName,teamLeader.getName(),setupDay);
    }

    @Override
    public int compareTo(Team anoter) {
        if(this.teamName.equals(anoter.teamName)) return 0;
        else if (this.teamName.compareTo(anoter.teamName)>0) return 1;
        else return -1;
    }
}

