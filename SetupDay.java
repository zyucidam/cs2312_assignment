public class SetupDay extends Day {
    
    private static SetupDay instance = new SetupDay();
    public static SetupDay getInstance() {return instance;}

    public SetupDay()
    {
        super("0-Jan-0");
    }

    public void StartNewDay(String StringDate)
    {
        super.SetDate(StringDate);

    }

    public String getStringDay() {return super.toString();}

}
