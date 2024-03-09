public class Service extends Entity
{
    Service(String name, String description, int id){
        super(name, description, id);
        this.isMaterial = false;
    }
    
    public String getDetails(){
        return "\n" + getName() + " ανήκει στην κατηγορία των υπηρεσιών.";
    }
    //prepei na ginei ylopoihsh tous epeidi einai orismenenes ws abstract stin Entity
    public double getLevel1(){return 0;}
    public double getLevel2(){return 0;}
    public double getLevel3(){return 0;}
}
