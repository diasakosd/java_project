public class Beneficiary extends User
{
    private int noPersons=1;
    private RequestDonationList receivedList = new RequestDonationList(); //είδη και ποσοτητες που έχει ήδη λάβει  
    private Requests requestsList = new Requests(); //τρέχουσα λίστα των ειδών και των ποσοτήτων που ζητά να του δοθούν
    Beneficiary(String name, String phone_number, int noPersons)//, RequestDonationList receivedList, Requests requestsList)
    {
     super(name,phone_number);
     this.noPersons = noPersons;
    }
    public int getNoPersons(){
        return noPersons;
    }
    public RequestDonationList getReceivedList(){
        return receivedList;
    }
    public Requests getRequestsList(){
        return requestsList;
    }
    //wrapper
    public void addToReceivedList(Organization org, RequestDonation rd){
        try{receivedList.add(org, rd);}
        catch(EntityDoesNotExistException e){e.print();}
        catch(EntityAmountDoesNotExistException e){e.print();}
        catch(InvalidRequestException e){e.print();}
    }
    //wrapper
    public void addToRequestsList(Organization org, RequestDonation rd){
        try{requestsList.add(org, rd, this);}
        catch(EntityDoesNotExistException e){e.print();}
        catch(EntityAmountDoesNotExistException e){e.print();}
        catch(InvalidRequestException e){e.print();}
    }
    //wrapper
    public void removeRequest(RequestDonation rd){
        requestsList.remove(rd);
    }
    //wrapper
    public void modifyRequest(Organization o,RequestDonation r, double quantity){
        try{requestsList.modify(o, r, this, quantity);}
        catch(EntityAmountDoesNotExistException e){e.print();}
        catch(InvalidRequestException e){e.print();}
        catch(EntityDoesNotExistException e){e.print();}
    }
    //wrapper
    public void resetRequests(){
        requestsList.reset();
    }
    //wrapper
    public RequestDonationList commit(Organization o){
        RequestDonationList requestsNotReceived;
        requestsNotReceived = requestsList.commit(o, this);
        return requestsNotReceived;
    }
    //wrapper
    public void monitor(){
        if(receivedList.getRdEntities().isEmpty()){
            System.out.println("Κανένα αίτημα σας δεν ικανοποιήθηκε επιτυχώς");
        }
        else{receivedList.monitor();}
    }
    //tupwnei ola ta requests tou beneficiary kai epistrefei mia lista me auta
    public void listRequests(){
        int counter=0;
        if(this.requestsList.getRdEntities().isEmpty()==false){
            for(RequestDonation rd : this.requestsList.getRdEntities()){
            counter++;
            System.out.print(counter + ". ");
            System.out.println(rd.getEntity().getName());
           } 
        }
        else{
            System.out.println("Δεν έχετε κάνει κανένα αίτημα");
        }
    }
    //get quantity qualified for
    public double getQqf(RequestDonation r){
        double qqf = 0;
        switch(getLevel()){
        case 1: qqf = r.getEntity().getLevel1(); break; 
        case 2: qqf = r.getEntity().getLevel2(); break;
        case 3: qqf = r.getEntity().getLevel3(); break;
        }
        return qqf;
    }
    //epistrefei to level tou beneficiary
     public int getLevel(){
        if(noPersons==1){return 1;}
        else if(noPersons>=2 && noPersons<=4){return 2;}
        else if(noPersons>=5){return 3;}
        else return 0;
    }
}