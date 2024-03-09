public abstract class User
{
 private String name;
 private String phone_number;

 User(String name,String phone_number)
 {
     this.name=name;
     this.phone_number=phone_number;
 }
 public String getName(){
     return name;
 }
 public String getPhone_number(){
     return phone_number;
 }
}
    