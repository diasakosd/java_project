import java.util.ArrayList;
public class Organization {
    private String name;
    private Admin admin;
    private ArrayList<Entity> entityList = new ArrayList<Entity>(); //lista apo entities pou mporoun na dianemithoun se beneficiaries
    private ArrayList<Donator> donatorList = new ArrayList<Donator>(); //lista apo dorites 
    private ArrayList<Beneficiary> beneficiaryList = new ArrayList<Beneficiary>(); //lista apo beneficiaries
    private RequestDonationList currentDonations = new RequestDonationList(); //diathesimes prosfores gia entities kai posotites tous

    Organization(String name, Admin admin, ArrayList<Entity> entityList, ArrayList<Donator> donatorList, ArrayList<Beneficiary> beneficiaryList, RequestDonationList currentDonations){
        this.name = name;
        this.admin = admin;
        this.entityList = entityList;
        this.donatorList = donatorList;
        this.beneficiaryList = beneficiaryList;
        this.currentDonations = currentDonations;
    }
    Organization(String name){
        this.name = name;
    }
    Organization(){}
    public String getName(){
        return name;
    }
    public ArrayList<Entity> getEntityList(){
        return entityList;
    }
    public ArrayList<Donator> getDonatorList(){
        return donatorList;
    }
    public ArrayList<Beneficiary> getBeneficiaryList(){
        return beneficiaryList;
    }
    public RequestDonationList getCurrentDonations(){
        return currentDonations;
    }
    public Admin getAdmin() {
        return admin;
    }
    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
    public void addEntity(Entity entity)throws EntityAlreadyExistsException{
        boolean entityExists = false;
        for(int i=0; i<entityList.size(); i++){
            if(entity.equals(entityList.get(i))){
                entityExists = true;
                throw new EntityAlreadyExistsException();
            }
        }
        if(entityExists==false){entityList.add(entity);}
    }
    public void removeEntity(Entity entity){
        entityList.remove(entity);
    }
    public void insertDonator(Donator donator)throws DonatorAlreadyExistsException {
        if(donatorList.isEmpty()){donatorList.add(donator);}
        else{
            boolean donatorExists = false;
            for(int i=0; i<donatorList.size(); i++){
                if(donator==donatorList.get(i)){
                    donatorExists = true;
                    throw new DonatorAlreadyExistsException();
                }
            }
            if(donatorExists==false){donatorList.add(donator);}
        }
    }
    public void removeDonator(Donator donator){
        donatorList.remove(donator);
    }
    public void insertBeneficiary(Beneficiary beneficiary)throws BeneficiaryAlreadyExistsException{
        if(beneficiaryList.isEmpty()){beneficiaryList.add(beneficiary);}
        else{
            boolean benExists = false;
            for(int i=0; i<beneficiaryList.size(); i++){
                if(beneficiary==beneficiaryList.get(i)){
                    benExists = true;
                    throw new BeneficiaryAlreadyExistsException();
                }
            }
            if(benExists==false){beneficiaryList.add(beneficiary);}
        }
    }
    public void removeBeneficiary(Beneficiary beneficiary){
        beneficiaryList.remove(beneficiary);
    }
    public void listEntities(){
        System.out.println("    Υλικά");
        System.out.println("---------------");
        for(int i=0; i<entityList.size(); i++){
            if(entityList.get(i).getIsMaterial()==true){
                System.out.println(entityList.get(i).toString());
            }
        }
        System.out.println();
        System.out.println("   Services");
        System.out.println("---------------");
        for(int i=0; i<entityList.size(); i++){
            if(entityList.get(i).getIsMaterial()==false){
                System.out.println(entityList.get(i).toString());
            }
        }
    }
    public void listBeneficiaries(){
        for(int i=0; i<beneficiaryList.size(); i++){
            System.out.println(beneficiaryList.get(i).getName() + ":");
            beneficiaryList.get(i).getReceivedList().listMaterialRdEntities();
            beneficiaryList.get(i).getReceivedList().listServiceRdEntities();
        }
    }
    public void listDonators(){
        for (Donator don:donatorList)
            System.out.println(don.getName()+"\n");
    }
    //proairetikes wrappers methodoi gia tin current donations
}