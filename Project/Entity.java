public abstract class Entity
{
    private String name;
    private String description;
    private int id;
    protected boolean isMaterial=false;

    Entity(String name, String description, int id)
    {
        this.name = name;
        this.description = description;
        this.id = id;
    }
    public String getEntityInfo()
    {
        return "Id: "+id+" ,Περιγραφή: "+description+" ,Όνομα: "+name;
    }
    abstract String getDetails();
    public String toString()
    {
        String s = this.getEntityInfo();
        String s1 = this.getDetails();
        return s.concat(s1);
    }
    public boolean getIsMaterial(){
        return isMaterial;
    }
    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }
    public int getId(){
        return id;
    }
    abstract double getLevel1();
    abstract double getLevel2();
    abstract double getLevel3();
}