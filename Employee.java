import java.util.ArrayList;
import java.util.Collections;

public class Employee implements Comparable<Employee>{
    
    private String name;
    private int annualLeaves;
    private int EntitledAnnualLeaves;
    private Role role;
    private ArrayList<Leave> leavesList;
    private Team myTeam;

    public Employee(String aName,int aDay,Role aRole)
    {
        this.name=aName;
        this.annualLeaves=aDay;
        this.EntitledAnnualLeaves= aDay;
        this.role = aRole;
        this.myTeam = null;
        leavesList = new ArrayList<Leave>();
    }

    public String getName(){return name;}

    public Team getTeam() {return myTeam;}

    public int getAnnualLeaves() {return annualLeaves;}

    public int getEAnnualLeaves() {return EntitledAnnualLeaves;}

    public Role getRole() {return role;}

    public void setTeam(Team aTeam) {myTeam = aTeam;}

    public void setRole(String rName) {
        Role r;
        if(rName == "leader")
        {
            r = new RLeader();    
        } else
        {
            r = new RNormalMember();    
        } 
        this.role = r;
    }

    public void addLeave(Leave aLeave) {
        this.leavesList.add(aLeave);
        this.annualLeaves -= aLeave.getDayslength();
    }

    public void removeLeave(Leave aLeave) {
        this.leavesList.remove(aLeave);
        this.annualLeaves += aLeave.getDayslength();
    }

    public Leave overlapCheck(Leave aLeave)
    {
        int lbarrier = aLeave.getStartDaysDays();
        int rbarrier = aLeave.getEndDaysDays();
        for(Leave l:leavesList)
        {
            int StartDayBarrier = l.getStartDaysDays();
            int EndDayBarrier = l.getEndDaysDays();
            if(!((rbarrier < StartDayBarrier) 
            || (lbarrier > EndDayBarrier))
            ) return l;
        }
        return null;
    }

    public double calculateEmployeeManpower(int lbarrier,int rbarrier)
    {
        int sum = 0;
        for(Leave l:leavesList)
        {
            int StartDayBarrier = l.getStartDaysDays();
            int EndDayBarrier = l.getEndDaysDays();
            if(!((rbarrier < StartDayBarrier) 
            || (lbarrier > EndDayBarrier))){
                sum += min(EndDayBarrier,rbarrier) - max(lbarrier,StartDayBarrier) + 1.0;
            }
        }
        return (1.0 - sum / (rbarrier - lbarrier + 1.0));
    }
    

    private int max(int a, int b) {
        return a > b ? a : b;
    }

    private int min(int a, int b) {
        return a < b ? a : b;
    }

    public String toString() {
        return name + " (Entitled Annual Leaves: " + EntitledAnnualLeaves + " days)";
    }

    public void printDetail()
    {
        Collections.sort(leavesList);
        System.out.printf("%-20s %-20s ",role,name);
        boolean flag = true;
        if(leavesList.size() > 0){
            for(int i=0;i<leavesList.size()-1;i++)
            {
                if(leavesList.get(i).getEndDaysDays() >= SetupDay.getInstance().getDays()) 
                {
                    System.out.printf(leavesList.get(i) + ", ");
                    flag = false;
                }
            }
            if(leavesList.get(leavesList.size()-1).getEndDaysDays() >= SetupDay.getInstance().getDays()) {
                System.out.print(leavesList.get(leavesList.size()-1)+"\n");
                flag = false;
            }
            if(flag) System.out.print(" --\n");
        } else {
            System.out.print(" --\n");
        }
    }

    public void printLeaves()
    {
        Collections.sort(leavesList);
        System.out.printf("%s: ",name);
        if(leavesList.size() < 1) {
            System.out.print(" --\n");
        } else {
            boolean flag = true;
            for(int i=0;i<leavesList.size()-1;i++)
            {
                if(leavesList.get(i).getEndDaysDays() >= SetupDay.getInstance().getDays()) {
                    System.out.print(leavesList.get(i)+", ");
                    flag = false;
                }
            }
            if(leavesList.get(leavesList.size()-1).getEndDaysDays() >= SetupDay.getInstance().getDays()) {
                System.out.print(leavesList.get(leavesList.size()-1) +"\n");
                flag = false;
            }
            if(flag) System.out.print(" --\n");
        }
    }

    @Override
    public int compareTo(Employee anoter) {
        if(this.name.equals(anoter.name)) return 0;
        else if (this.name.compareTo(anoter.name)>0) return 1;
        else return -1;
    }
}
