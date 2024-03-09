public class Offers extends RequestDonationList 
{
    public void commit(Organization o)
    {
        //enimerwsi twn currentDonations tou organismou me tis prosfores stin lista rdEntities
        for (int i = 0; i < getRdEntities().size(); i++)
        {
            try{o.getCurrentDonations().add(o,getRdEntities().get(i));}
            catch(EntityDoesNotExistException e){e.print();}
            catch(EntityAmountDoesNotExistException e){e.print();}
            catch(InvalidRequestException e){e.print();}
        }
        //diagrafi tis listas rdEntities
        getRdEntities().clear();
    }
}