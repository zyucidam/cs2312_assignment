public class Leave implements Comparable<Leave>{
    
    private Day startDay;
    private Day endDay;
    private int dayslength;
    private int startDaysDays;
    private int endDaysDays;

    public Leave(Day fDay,Day eDay)
    {
        this.startDay = fDay;
        this.endDay = eDay;
        this.dayslength = endDay.getDays() - startDay.getDays() + 1;
        this.startDaysDays = startDay.getDays();
        this.endDaysDays = endDay.getDays();
    }

    public int getDayslength() {return dayslength;}

    public int getStartDaysDays() {return startDaysDays;}

    public int getEndDaysDays() {return endDaysDays;}

    public String toString()
    {
        return startDay + " to " + endDay;
    }

    @Override
    public int compareTo(Leave anoter) {
        if(this.startDaysDays == anoter.startDaysDays) return 0;
        else if (this.startDaysDays > anoter.startDaysDays) return 1;
        else return -1;
    }

}
