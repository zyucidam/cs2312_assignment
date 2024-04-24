public class RNormalMember implements Role{
    

    public String getNameAndRole(Employee member) 
    {
        return member.getName();
    }

    public String toString() {return "Member";}

    public String getRoleClass() {return "M";}
}
