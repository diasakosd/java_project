public class RequestDonation implements Cloneable //gia na apofugoume CloneNotSupportedException
{
    private Entity entity; //to id(entity.getId()) tou entity einai "kleidi" na ginei xrisi getter gia access
    private double quantity;
    
    RequestDonation(Entity entity, double quantity){
        this.entity = entity;
        this.quantity = quantity;
    }
    public int getId(){
        return entity.getId();
    }
    public Entity getEntity(){
        return entity;
    }
    public double getQuantity(){
        return quantity;
    }
    public void setEntity(Entity ent){
        this.entity = ent;
    }
    public void setQuantity(double quant){
        if(quant>=0){
            this.quantity = quant;
        }
    }
    public Object clone(){
        Object obj = null;
        try{obj = super.clone();}
        catch(CloneNotSupportedException e){}
        return obj;
    }
}
