public class Day {
    
    private int mouth;
    private int day;
    private int year;
    private int days;
    public static String StringToNumL = "JanFebMarAprMayJunJulAugSepOctNovDec";
    public static String[] NumToStringL = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};

    public Day(String StringDate)
    {
        String[] DatePart = StringDate.split("-");
        day = Integer.parseInt(DatePart[0]);
        mouth = StringToNumL.indexOf(DatePart[1]) / 3 + 1;
        year = Integer.parseInt(DatePart[2]);
        days = this.getDays();
    }

    public Day(int y,int m,int d)
    {
        this.year=y;
        this.mouth=m;
        this.day=d;
    }

    public Day(int y,int ad)
    {
        this.year = y;
        int[] mouthDay = new int[] {31,29,31,30,31,30,31,31,30,31,30,31};;
        if(!isLeapYear(y))
        {
            mouthDay[1] = 28;
        }
        for(int i=0;i<12;i++)
        {
            if(ad > mouthDay[i])
            {
                ad -= mouthDay[i];
            } else {
                this.mouth = (i + 1);
                this.day = ad;
                break;
            }
        }
    }

    public int getYear() {return year;}
    public int getMouth() {return mouth;}
    public int getDay() {return day;}

    public void SetDate(String StringDate)
    {
        String[] DatePart = StringDate.split("-");
        day = Integer.parseInt(DatePart[0]);
        mouth = StringToNumL.indexOf(DatePart[1]) / 3 + 1;
        year = Integer.parseInt(DatePart[2]);
    }

    public boolean isLeapYear(int y)
    {
        return (y % 400 == 0) || ((y % 4 == 0)&&(y % 100 !=0)?true:false);
    }

    public int getDays()
    {
        
        int numOfDays = 0;
        int[] mouthDays = new int[] {31,29,31,30,31,30,31,31,30,31,30,31};
        if(!isLeapYear(year))
        {
            mouthDays[1] = 28;
        }
        for(int i=0;i<12;i++)
        {
            if(mouth - 1 > i)
            {
                numOfDays += mouthDays[i];
            } else {
                numOfDays += day;
                break;
            }
        }
        return numOfDays;
    }

    public Day dayAfterNdays(int num)
    {
        int anotherDays = this.days + num - 1;
        int anotherYear = this.year;
        int anotherMouth = 0;
        int anotherDay = 0;
        int[] mouthDay = new int[] {31,29,31,30,31,30,31,31,30,31,30,31};;
        if(!isLeapYear(anotherYear))
        {
            mouthDay[1] = 28;
        }
        for(int i=0;i<12;i++)
        {
            if(anotherDays > mouthDay[i])
            {
                anotherDays -= mouthDay[i];
            } else {
                anotherMouth = (i + 1);
                anotherDay = anotherDays;
                break;
            }
        }
        return new Day(anotherYear,anotherMouth,anotherDay);
    }


    

    public String toStringDateFormat() {return year+"-"+mouth+"-"+day;}

    public String toString()
    {
        return day+"-"+NumToStringL[mouth-1]+"-"+year;
    }



}
