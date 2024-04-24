public class Project implements Comparable<Project>{
    
    private String name;
    private Day startDay;
    private Day endDay;
    private int dayslength;
    private Team assginTeam;

    public Project(String aName,Day aDay,int length)
    {
        this.name = aName;
        this.startDay = aDay;
        this.dayslength = length;
        this.endDay = startDay.dayAfterNdays(dayslength);
        this.assginTeam = null;
    }

    public void assgin(Team aTeam)
    {
        this.assginTeam = aTeam;
    }

    public String toString()
    {
        if(assginTeam == null) return String.format("%-20s%-20s%-20s%-20s", name,startDay,endDay,"--");
        else return String.format("%-20s%-20s%-20s%-20s", name,startDay,endDay,assginTeam.shortListing());
    }

    public String getName() {return name;}

    public Day getStartDay() {return startDay;}

    public Day getEndDay() {return endDay;}

    public Team getAssginTeam() {return assginTeam;}

    public void setAssginTeam(Team aTeam) {assginTeam = aTeam;}

    public int getFinalStageStartDays() {
        if(dayslength <= 5) return startDay.getDays();
        else return endDay.getDays() - 4;
    }

    public int getFinalStageEndDays() {
        return endDay.getDays();
    }



    @Override
    public int compareTo(Project anoter) {
        if(this.name.equals(anoter.name)) return 0;
        else if (this.name.compareTo(anoter.name)>0) return 1;
        else return -1;
    }

}
