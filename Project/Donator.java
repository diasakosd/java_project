
public class Donator extends User
{
    private Offers offersList = new Offers(); //lista twn eidwn pou thelei na prosferei 
                                              //petage NullPointerException an den dimiourgousame antikeimeno Offers edw
    Donator(String name,String phone_number)
    {
     super(name,phone_number);
    }

    public Offers getOffersList()
    {
        return offersList;
    }

    public void add(Organization o, RequestDonation r){ //add an offer 
        try{
            offersList.add(o,r);
        }
        catch (EntityDoesNotExistException e1) {e1.print();}
        catch (InvalidRequestException e2) {e2.print();}
        catch (EntityAmountDoesNotExistException e3) {e3.print();}
    }

    public void modifyUpdate(RequestDonation rd,double quantity)
    {
        offersList.modify(rd, quantity);
    }

    public void removeUpdate(Organization o, RequestDonation rd)
    {
        offersList.remove(rd);
    }

    public void commitUpdate(Organization o)
    {
        offersList.commit(o);
    }
    //kanei reset tin offersList και vgazei apo tin currentDonations mono osa diagrafikan 
    public void resetUpdate()
    {
        offersList.reset();
    }
    //wrapper
    public void monitor(){
        offersList.monitor();
        if(offersList.getRdEntities().isEmpty()){
            System.out.println("\nΟ Συγκεκριμένος δωρητής δεν προτίθεται να παρέχει κανένα είδος αυτήν την στιγμή στον οργανισμό.");
        }
    }
}