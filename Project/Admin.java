public class Admin extends User
{
    public boolean isAdmin=false;

    Admin(String name,String phone_number)
    {
        super(name,phone_number);
        isAdmin=true;
    }

    public boolean getIsAdmin(){
        return isAdmin;
    }
    protected void reset(Beneficiary beneficiary)
    {
        beneficiary.getReceivedList().getRdEntities().clear();
        //Ginetai clear olh h lista me auta pou exei labei o beneficiary
    }
}