public class RLeader implements Role{


    public String getNameAndRole(Employee member) 
    {
        return member.getName()+"[Leader]";
    } 

    public String toString() {return "Leader";}

    public String getRoleClass() {return "L";}
}
