public class Main
{
    public static void main(String[] args){
        Organization org = new Organization("Τυχαίος Οργανισμός");
        Material gala = new Material("Γάλα", "Πλήρες", 001, 5, 10, 15);
        Material zaxari = new Material("Ζάχαρη", "Κρυσταλική", 002, 2, 5, 7);
        Material rizi = new Material("Ρύζι", "Μπασμάτι", 003, 1, 15, 20);
        Service medicalSupport = new Service("Ιατρική Περίθαλψη", "", 004);
        Service nurserySupport = new Service("Nοσηλευτική Περίθαλψη", "", 005);
        Service babySitting = new Service("Μπέιμπι Σιτινγκ", "", 006);
        
        RequestDonation rd1 = new RequestDonation(gala, 6);
        RequestDonation rd2 = new RequestDonation(rizi, 8);
        RequestDonation rd3 = new RequestDonation(medicalSupport, 48);
        RequestDonation rd4 = new RequestDonation(zaxari, 5);
        try{
            org.addEntity(gala);
            org.addEntity(rizi);
            org.addEntity(zaxari);
            org.addEntity(medicalSupport);
            org.addEntity(nurserySupport);
            org.addEntity(babySitting);
        }
        catch(EntityAlreadyExistsException e){e.print();}
        Admin admin = new Admin("Γρηγόρης", "123456");
        org.setAdmin(admin);
        Beneficiary ben1 = new Beneficiary("Γιάννης", "555", 3);
        Beneficiary ben2 = new Beneficiary("Γιώργος", "222", 5);
        try{
            org.insertBeneficiary(ben1);
            org.insertBeneficiary(ben2);
        }
        catch(BeneficiaryAlreadyExistsException e){e.print();}
        Donator don1 = new Donator("Τάσος", "333");
        try{org.insertDonator(don1);}
        catch(DonatorAlreadyExistsException e){e.print();} 
        try{
            //vazw duo requests sta currentdonations tou org 
            org.getCurrentDonations().add(org, rd1);  
            org.getCurrentDonations().add(org, rd2); 
            //vazw duo requests gia ton ben1 
            RequestDonation rd1_1 = (RequestDonation)rd1.clone();
            RequestDonation rd2_1 = (RequestDonation)rd2.clone();
            ben1.addToRequestsList(org, rd1_1); 
            ben1.addToRequestsList(org, rd2_1);
            //vazw duo offers sto offerslist tou don1 
            don1.add(org, rd3);
            don1.add(org, rd4);
        }
        catch(EntityDoesNotExistException e){e.print();}
        catch(EntityAmountDoesNotExistException e){e.print();}
        catch(InvalidRequestException e){e.print();}
        Menu menu = new Menu();
        menu.Login(org);
    }
}