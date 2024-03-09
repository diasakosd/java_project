public class Material extends Entity 
{
    private double level1; //posotita pou dikaioutai 1 atomo
    private double level2; //posotita pou dikaioutai 2-4 atoma
    private double level3; //posotita pou dikaioutai 5+ atoma

    Material(String name, String description, int id,double level1,double level2,double level3)
    {
        super(name, description, id);
        this.level1=level1;//posotita pou dikaioutai 1 atomo 
        this.level2=level2;//posotita pou dikaiountai 2-4 atoma 
        this.level3=level3;//posostita pou dikaiountai 5+ atoma
        this.isMaterial = true; 
    }
    public String getDetails()
    {
        return "\nΤο " + getName() + " ανήκει στην κατηγορία των υλικών.\n" + "Το 1 άτομο δικαιούται " + level1 + " ποσότητα/ες από το υλικό αυτό.\nΤα 2-4 άτομα δικαιούνται " + level2 + " ποσότητα/ες από το υλικό αυτό.\nΤα 5 άτομα δικαιούνται " + level3 + " ποσότητα/ες από το υλικό αυτό.";
    }
    public double getLevel1(){
        return level1;
    }
    public double getLevel2(){
        return level2;
    }
    public double getLevel3(){
        return level3;
    }
}
