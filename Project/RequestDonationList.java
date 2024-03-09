import java.util.*;

public class RequestDonationList
{
    private ArrayList <RequestDonation> rdEntities=new ArrayList<RequestDonation>();
    
    public ArrayList <RequestDonation> getRdEntities(){
        return rdEntities;
    }
    public RequestDonation get(int id){
        RequestDonation object = null;
        for(int i=0; i<rdEntities.size(); i++)
            if(id==rdEntities.get(i).getId()) {
                object = rdEntities.get(i);
                break;
            }
        return object;
    }
    public void add(Organization o,RequestDonation r)throws EntityDoesNotExistException, EntityAmountDoesNotExistException, InvalidRequestException{
        boolean flag1=false;
        for(Entity ent:o.getEntityList()){
            if(ent.getId()==r.getId()){
                flag1=true;
            }
        }
        //an to entity pou bazw den yparzei sto entityList tou organismou throw exception
        if(flag1==false){   
            throw new EntityDoesNotExistException();
        }
        //an to entity pou bazw yparxei sthn entityList tou organismou
        if(flag1==true)
        {
            boolean flag2=false;
            for (RequestDonation rd:rdEntities){ //diatrexw tin rdEntities
                //an to RequestDonation pou prosthetw yparxei stin rdEntities kanw update to quantity
                if(r.getId()==rd.getId()){
                    rd.setQuantity(rd.getQuantity() + r.getQuantity());
                    flag2=true;
                }
            }
            //an to Request Donation pou prosthetw den yparxei stin rdEntities to prosthetw
            if(flag2==false){
                rdEntities.add(r);
            }
        }
    }
    public void remove(RequestDonation rd){
        try{
                for(RequestDonation r:rdEntities){
                if(r==rd){
                    rdEntities.remove(r);
                }
            }
        }
        catch(ConcurrentModificationException e){}
    }
    public void modify(RequestDonation rd,double quantity){
        for(RequestDonation r:rdEntities){
            if(r.getId()==rd.getId())
            {
                r.setQuantity(quantity);
            }
        }
    }
    public void monitor(){
        for(RequestDonation rd:rdEntities){
            System.out.println("Όνομα είδους: " + rd.getEntity().getName());
            System.out.println("Ποσότητα/Ώρες: " + rd.getQuantity());
        }
        System.out.println();
    }
    public void reset(){
        try{rdEntities.clear();}
        catch(ConcurrentModificationException e){}
    }
    public ArrayList<RequestDonation> listMaterialRdEntities(){ //diatrexei thn lista rdentities kai typwnei tisplirofories kai thn posotita twn material 
        ArrayList<RequestDonation> matList = new ArrayList<RequestDonation>();
        int counter=0;
        for(int i=0; i<rdEntities.size(); i++){
            if(rdEntities.get(i).getEntity().getIsMaterial()==true){
                counter++;
                matList.add(rdEntities.get(i));
                System.out.print(counter + ": " + rdEntities.get(i).getEntity().getName() + "\n");
            }
        }
        return matList;
    }
    public ArrayList<RequestDonation> listServiceRdEntities(){ //diatrexei thn lista rdentities kai typwnei tis plirofories twn service
        ArrayList<RequestDonation> servList = new ArrayList<RequestDonation>();
        int counter=0;
        for(int i=0; i<rdEntities.size(); i++){
            if(rdEntities.get(i).getEntity().getIsMaterial()==false){
                counter++;
                servList.add(rdEntities.get(i));
                System.out.print(counter + ": " + rdEntities.get(i).getEntity().getName() + "\n");
            }
        }
        return servList;
    }
     public ArrayList<RequestDonation> getMaterialRdEntities() {
        ArrayList<RequestDonation> matList = new ArrayList<RequestDonation>();
        for (int i = 0; i < rdEntities.size(); i++) {
            if (rdEntities.get(i).getEntity().getIsMaterial() == true) {
                matList.add(rdEntities.get(i));
            }
        }
        return matList;
    }
    public ArrayList<RequestDonation> getServiceRdEntities(){
        ArrayList<RequestDonation> servList = new ArrayList<RequestDonation>();
        for (int i = 0; i < rdEntities.size(); i++) {
            if (rdEntities.get(i).getEntity().getIsMaterial() == false) {
                servList.add(rdEntities.get(i));
            }
        }
        return servList;
    }
}