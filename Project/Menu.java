import java.util.*;
public class Menu
{
    Scanner sc = new Scanner(System.in);
    public void Login(Organization o) 
    {
        do
        {
        String phone_number;
        System.out.println("Σύνδεση...");
        System.out.print("Δώστε το κινητό σας τηλέφωνο: ");
        phone_number = sc.nextLine();

        try
        { 
            Long.parseLong(phone_number);

            boolean isSignedUp = false;
            if(phone_number.equals(o.getAdmin().getPhone_number())){
           Login_Admin(o);
           isSignedUp = true;
        }
        for(Donator don : o.getDonatorList()){
            if(phone_number.equals(don.getPhone_number())){
                Login_Donator(don, o);
                isSignedUp = true;
                break;
            }
        }
        for(Beneficiary ben : o.getBeneficiaryList()){
            if(phone_number.equals(ben.getPhone_number())){
                Login_Beneficiary(ben, o);
                isSignedUp = true;
                break;
            }
        }

        if(isSignedUp==false){
            SignUp(o, phone_number);
        }
        }
        catch(NumberFormatException e)
        {
            System.out.println("\nΔώσατε άκυρους χαρακτήρες. Δώστε το τηλέφωνό σας πάλι.\n");
        }
        }while(true);
    }
    public void SignUp(Organization o, String phone_number){
        do
        {
            System.out.println("\nΩς τι είδους χρήστη επιθυμείτε να εγγραφείτε;");
            System.out.println("Επιλέξτε παρακάτω:");
            System.out.println("1: Δωριτής");
            System.out.println("2: Επωφελούμενος");
            System.out.println("3: Back");
            String choice1 = null;

            System.out.print("\nΕπιλογή: ");
            try {
                choice1 = sc.nextLine();
                int choice=Integer.parseInt(choice1);


            switch(choice){
                case 1:
                    System.out.println("\n0: Back ");

                    System.out.print("\nΠαρακαλώ εισάγετε το όνομα σας: ");
                    String name = sc.nextLine();
                    if(name.equals("0"))
                    {
                        SignUp(o, phone_number);
                    }
                    else
                    {
                        Donator donator = new Donator(name, phone_number);

                        try
                        {
                            o.insertDonator(donator);

                            System.out.println();

                            Login(o);
                        }
                        catch (DonatorAlreadyExistsException e){e.print();}
                    }
                    break;

                case 2:
                    System.out.println("\n0: Back ");
                    System.out.print("\nΠαρακαλώ εισάγετε το όνομα σας: ");
                    String name1 = sc.nextLine();
                    if(name1.equals("0"))
                    {
                        SignUp(o ,phone_number);
                    }
                    else
                    {
                        String sNoPersons=null;
                        int noPersons = 0;
                        do
                        {
                            try
                            {
                                System.out.print("\nΠαρακαλώ εισάγετε τον αριθμό των μελών της οικογένεια σας: ");
                                sNoPersons = sc.nextLine();
                                noPersons = Integer.parseInt(sNoPersons);

                                if(noPersons<=0)
                                {
                                    System.out.println("\nΟ αριθμός πρέπει να είναι μεγαλύτερος του 0.");
                                }
                            }
                            catch(NumberFormatException e)
                            {
                                System.out.println("\nΛάθος χαρακτήρες.Ξαναδώστε τον αριθμό.");
                            }
                        }while(noPersons<=0);
                        Beneficiary beneficiary = new Beneficiary(name1, phone_number, noPersons);
                        try
                        {
                            o.insertBeneficiary(beneficiary);

                            System.out.println();

                            Login(o);
                        }
                        catch (BeneficiaryAlreadyExistsException e){e.print();}
                    }
                    break;

                case 3:
                    System.out.println();
                    Login(o);
                    break;
                default:
                    System.out.println("\nΛάθος επιλογή. Παρακαλώ επιλέξτε μία απο τις υπάρχουσες επιλογές");
            }
        }
            catch (NumberFormatException e){
                System.out.println("\nΜΗΝ ΔΙΝΕΤΕ ΑΚΥΡΟΥΣ ΧΑΡΑΚΤΗΡΕΣ\n");
                SignUp(o ,phone_number);
            }
        }

        while(true);
    }
    
    public void Login_Donator(Donator donator,Organization o) 
    {
        System.out.println("\nΚαλωσορίσατε δωρητή.\n\nΤα στοιχεία σας.\n--------------\nΌνομα: " + donator.getName() + "\nΤηλέφωνο: " + donator.getPhone_number() + "\nΟργανισμός: " + o.getName() + "\nΌνομα Admin: " + o.getAdmin().getName());
        
        do
        {
        String epilogh="";

        System.out.println();
        System.out.println("1: Add offer");
        System.out.println("2: Show offers");
        System.out.println("3: Commit");
        System.out.println("4: Logout");
        System.out.println("5: Exit");

        System.out.print("\nΔώστε τον αντίστοιχο αριθμό για την επιλογή σας: ");
     
        epilogh=sc.nextLine();

        switch(epilogh)
        {
            case "1":
            Add_Offer(donator,o);
            break;

            case "2":
            Show_Offers(donator,o);
            break;

            case "3":
            Donator_Commit(donator,o);
            break;

            case "4":
            System.out.println();
            Logout(o);
            break;

            case "5":
            Exit();
            break;

            default:
            System.out.println("\nΔώσατε λάθος επιλογή.Ξαναδώστε την επιλογή σας.");
            break;
        }  
    }while(true);
    }

    public void Add_Offer(Donator donator,Organization o) 
    {
        String epilogh="";

        System.out.println("\n1: Materials\n2: Services\n3: Back");
        int counter1=0;
        int counter2=0;

        System.out.print("\nΔώστε τον αντίστοιχο αριθμό για την επιλογή σας: ");
        epilogh=sc.nextLine();

        switch(epilogh)
        {
            case "1":
            do
            {
                counter1=0;

                System.out.println("\nΠαρεχόμενα είδη από τον οργανισμό:");
                for(var k: o.getEntityList())
                {
                    if(k.getIsMaterial()==true)
                    {
                        counter1++;
                        System.out.println(counter1 + ".Είδος: " + k.getName() + " Id: " + k.getId());
                    }
                }

                counter1=0;

                System.out.println("\nΤα τωρινά donations για υλικά:");

                for(var l: o.getCurrentDonations().getRdEntities())
                {
                    if(l.getEntity().getIsMaterial()==true)
                    {
                        counter1++;
                        System.out.println(counter1 + ".Είδος: " + l.getEntity().getName() + " Id: " + l.getEntity().getId() + " Ποσότητα: " + l.getQuantity());
                    }
                }

                String epilogh_material="";
                String epilogh_material_donate="";

                System.out.print("\nΔώστε το id του υλικού που θέλετε να δείτε τις πληροφορίες και να κάνετε donate,αλλιώς γράψε back: ");
                epilogh_material=sc.nextLine();

                if(epilogh_material.equals("back"))
                {
                    Add_Offer(donator, o);
                }

                boolean flag1=false;
                boolean flag1_lathous=false;

                RequestDonation rd1= new RequestDonation(null,0);

                try
                {
                for(var h: o.getEntityList())
                {
                    if(Integer.parseInt(epilogh_material)==h.getId() && h.getIsMaterial()==true)
                    {
                        flag1=true;
                        rd1.setEntity(h);
                    }
                }

                for(var x:o.getCurrentDonations().getRdEntities())
                {
                    if(Integer.parseInt(epilogh_material)==x.getEntity().getId() && x.getEntity().getIsMaterial()==true)
                    {
                        flag1=true;
                        rd1.setEntity(x.getEntity());
                        rd1.setQuantity(x.getQuantity());
                    }
                }
                }
                catch(NumberFormatException e)
                {
                    flag1_lathous=true;

                    System.out.println("\nΔώσατε άκυρους χαρακτήρες.Ξαναδώστε το id.");
                }

                if(flag1==true)
                {
                    System.out.println("\n" + rd1.getEntity().toString());
                    System.out.println("Η τρέχουσα ποσότητα για το υλικό είναι: " + rd1.getQuantity());

                    do
                    {
                        System.out.print("\nΘέλετε να κάνετε donate το υλικό " + rd1.getEntity().getName() + ";(y/n): ");
                        epilogh_material_donate=sc.nextLine();

                        if(epilogh_material_donate.equals("y"))
                        {
                            String posothta;

                            do
                            {
                            posothta="0";

                            System.out.print("\nΔώστε την ποσότητα που θέλετε να κάνετε donate, αλλιώς γράψτε back: ");
                            posothta=sc.nextLine();

                            try
                            {
                                if(posothta.equals("back"))
                                {
                                    break;
                                }

                                if(Double.parseDouble(posothta)<=0)
                                {
                                    System.out.println("\nΗ ποσότητα που κάνετε donate θα πρέπει να είναι μεγαλύτερη του 0.");

                                    posothta="0";

                                    continue;
                                }

                                rd1.setQuantity(Double.parseDouble(posothta));

                                donator.add(o,rd1);

                                System.out.println("\nΤο donation έγινε με επιτυχία.");
                            }
                            catch(NumberFormatException e)
                            {
                                posothta="0";

                                System.err.println("\nΓράψατε λάθος χαρακτήρες.Ξαναδώστε την επιλογή σας.");
                            }
                            }while(posothta.equals("0"));
                        }

                        else if (epilogh_material_donate.equals("y")==false && epilogh_material_donate.equals("n")==false)
                        {
                            System.out.println("\nΔώσατε λάθος επιλογή.Ξαναδώστε την επιλογή σας.");
                        }
                    }while(epilogh_material_donate.equals("y")==false && epilogh_material_donate.equals("n")==false);
                }

                else if(flag1==false && flag1_lathous==false)
                {
                    try
                    {
                    throw new EntityDoesNotExistException();
                    }
                    catch(EntityDoesNotExistException e)
                    {
                        e.print();
                    }
                }
            }while(true);

            case "2":
            do
            {
                counter2=0;

                System.out.println("\nΠαρεχόμενες υπηρεσίες από τον οργανισμό:");
                for(var p: o.getEntityList())
                {
                    if(p.getIsMaterial()==false)
                    {
                        counter2++;
                        System.out.println(counter2 + ".Υπηρεσία: " + p.getName() + " Id: " + p.getId());
                    }
                }

                counter2=0;

                System.out.println("\nΤα τωρινά donations για υπηρεσίες:");

                for(var v: o.getCurrentDonations().getRdEntities())
                {
                    if(v.getEntity().getIsMaterial()==false)
                    {
                        counter2++;
                        System.out.println(counter2 + ".Υπηρεσία: " + v.getEntity().getName() + " Id: " + v.getEntity().getId() + " Ώρες: " + v.getQuantity());
                    }
                }

                String epilogh_service="";
                String epilogh_service_donate="";

                System.out.print("\nΔώστε το id της υπηρεσίας που θέλετε να δείτε τις πληροφορίες και να κάνετε donate,αλλιώς γράψε back: ");
                epilogh_service=sc.nextLine();

                if(epilogh_service.equals("back"))
                {
                    Add_Offer(donator, o);
                }

                boolean flag2=false;
                boolean flag2_lathous=false;

                RequestDonation rd2= new RequestDonation(null,0);

                try
                {
                for(var h: o.getEntityList())
                {
                    if(Integer.parseInt(epilogh_service)==h.getId() && h.getIsMaterial()==false)
                    {
                        flag2=true;
                        rd2.setEntity(h);
                    }
                }
                
                for(var x:o.getCurrentDonations().getRdEntities())
                {
                    if(Integer.parseInt(epilogh_service)==x.getEntity().getId() && x.getEntity().getIsMaterial()==false)
                    {
                        flag2=true;
                        rd2.setEntity(x.getEntity());
                        rd2.setQuantity(x.getQuantity());
                    }
                }
                }
                catch(NumberFormatException e)
                {
                    flag2_lathous=true;

                    System.out.println("\nΔώσατε άκυρους χαρακτήρες.Ξαναδώστε το id.");
                }

                if(flag2==true)
                {
                    System.out.println("\n" + rd2.getEntity().toString());
                    System.out.println("Οι τρέχουσες ώρες για την υπηρεσία είναι: " + rd2.getQuantity());

                    do
                    {
                        System.out.print("\nΘέλετε να κάνετε donate την υπηρεσία " + rd2.getEntity().getName() + ";(y/n): ");
                        epilogh_service_donate=sc.nextLine();

                        if(epilogh_service_donate.equals("y"))
                        {
                            String hours;

                            do
                            {
                            hours="0";

                            System.out.print("\nΔώστε τις ώρες που θέλετε να κάνετε donate, αλλιώς γράψτε back: ");
                            hours=sc.nextLine();

                            try
                            {
                                if(hours.equals("back"))
                                {
                                    break;
                                }

                                if(Double.parseDouble(hours)<=0)
                                {
                                    System.out.println("\nΤο πλήθος των ωρών που κάνετε donate θα πρέπει να είναι μεγαλύτερο του 0.");

                                    hours="0";

                                    continue;
                                }

                                rd2.setQuantity(Double.parseDouble(hours));

                                donator.add(o,rd2);


                                System.out.println("\nΤο donation έγινε με επιτυχία.");
                            }
                            catch(NumberFormatException e)
                            {
                                hours="0";

                                System.err.println("\nΓράψατε λάθος χαρακτήρες.Ξαναδώστε την επιλογή σας.");
                            }
                            }while(hours.equals("0"));
                        }

                        else if (epilogh_service_donate.equals("y")==false && epilogh_service_donate.equals("n")==false)
                        {
                            System.out.println("\nΔώσατε λάθος επιλογή.Ξαναδώστε την επιλογή σας.");
                        }
                    }while(epilogh_service_donate.equals("y")==false && epilogh_service_donate.equals("n")==false);
                }

                else if(flag2==false && flag2_lathous==false)
                {
                    try
                    {
                    throw new EntityDoesNotExistException();
                    }
                    catch(EntityDoesNotExistException e)
                    {
                        e.print();
                    }
                }
            }while(true);

            case "3":
                Login_Donator(donator,o);
                break;

            default:
                System.out.println("\nΔώσατε λάθος επιλογή. Ξαναδώστε τον αριθμό για την επιλογή σας.");
                Add_Offer(donator, o);
                break;
        }
    }

    public void Show_Offers(Donator donator,Organization o)
    {
        do
        {
            if(donator.getOffersList().getRdEntities().isEmpty())
            {
                System.out.println("\nΔεν προσφέρετε κανένα υλικό ή υπηρεσία αυτή την στιγμή.");
                Login_Donator(donator, o);
            }
            else
            {
                System.out.println("\nΤα υλικά και υπηρεσίες που παρέχετε:");
                for(int i=0; i<donator.getOffersList().getRdEntities().size(); i++)
                {
                    System.out.println(i + "." + donator.getOffersList().getRdEntities().get(i).getEntity().toString());
                    System.out.println("Η/Οι ποσότητα/ώρες του/της υλικού/υπηρεσίας είναι: " + donator.getOffersList().getRdEntities().get(i).getQuantity() + "\n");
                }
                System.out.println("1. Διαγραφή συγκεκριμένης παροχής");
                System.out.println("2. Τροποποίηση ποσότητας παροχής");
                System.out.println("3. Καθαρισμός όλων των παροχών");
                System.out.println("4. Commit");
                System.out.println("5. Back");

                String epilogh="";

                try
                {
                    System.out.print("\nΔώστε την επιλογή σας: ");
                    epilogh=sc.nextLine();
                }
                catch(InputMismatchException e){}

                switch(epilogh)
                {
                    case "1":
                        boolean exists_diagrafhs=false;

                        do
                        {
                            String epilogh_diagrafhs="";

                            System.out.print("\nΔώστε το id του συγκεκριμένου υλικού ή υπηρεσίας για διαγραφή της παροχής αυτής, αλλιώς γράψτε back: ");
                            epilogh_diagrafhs=sc.nextLine();

                            if(epilogh_diagrafhs.equals("back"))
                            {
                                Show_Offers(donator, o);
                            }

                            try
                            {
                                for(var x: donator.getOffersList().getRdEntities())
                                {
                                    if(Integer.parseInt(epilogh_diagrafhs)==x.getEntity().getId())
                                    {
                                        exists_diagrafhs=true;

                                        donator.removeUpdate(o,x);

                                        System.out.println("\nΗ διαγραφή της παροχής έγινε με επιτυχία.");

                                        break;
                                    }
                                }

                                if(exists_diagrafhs==false)
                                {
                                    throw new OfferDoesNotExistException();
                                }
                            }
                            catch(NumberFormatException e)
                            {
                                System.err.println("\nΔώσατε άκυρους χαρακτήρες.Ξαναδώστε το id.");
                            }
                            catch(OfferDoesNotExistException e)
                            {
                                System.err.println();
                                e.print();
                                System.err.println("Ξαναδώστε το id.");
                            }
                        }while(exists_diagrafhs==false);
                        break;

                    case "2":
                        boolean exists_tropopoihshs=false;

                        do
                        {
                            String epilogh_tropopoihshs="";

                            System.out.print("\nΔώστε το id του συγκεκριμένου υλικού ή υπηρεσίας που θέλετε να τροποποιηθεί, αλλιώς γράψτε back: ");
                            epilogh_tropopoihshs=sc.nextLine();

                            if(epilogh_tropopoihshs.equals("back"))
                            {
                                Show_Offers(donator, o);
                            }

                            try
                            {
                                for(var y: donator.getOffersList().getRdEntities())
                                {
                                    if(Integer.parseInt(epilogh_tropopoihshs)==y.getEntity().getId())
                                    {
                                        exists_tropopoihshs=true;
                                    }
                                }

                                String posothta_tropopoihshs="";

                                if(exists_tropopoihshs==true)
                                {
                                    do
                                    {
                                        posothta_tropopoihshs="";

                                        System.out.print("\nΔώστε την καινούργια ποσότητα της παροχής, αλλιώς γράψτε back: ");
                                        posothta_tropopoihshs=sc.nextLine();

                                        try
                                        {
                                            if(posothta_tropopoihshs.equals("back"))
                                            {
                                                exists_tropopoihshs=false;

                                                break;
                                            }
                                            
                                            if(Double.parseDouble(posothta_tropopoihshs)<=0)
                                            {
                                                System.out.println("\nΗ ποσότητα που τροποποιείτε θα πρέπει να είναι μεγαλύτερη του 0.");

                                                continue;
                                            }

                                            else
                                            {

                                                for(var y: donator.getOffersList().getRdEntities())
                                                {
                                                    if(Integer.parseInt(epilogh_tropopoihshs)==y.getEntity().getId())
                                                    {
                                                        donator.modifyUpdate(y, Double.parseDouble(posothta_tropopoihshs));

                                                        break;
                                                    }
                                                }

                                                System.out.println("\nΗ τροποποίηση της παροχής έγινε με επιτυχία.");

                                                break;
                                            }
                                        }
                                        catch(NumberFormatException e)
                                        {
                                            System.err.println("\nΔώσατε λάθος χαρακτήρες.Ξαναδώστε την επιλογή σας.");
                                        }
                                    }while(true);
                                }

                                if(exists_tropopoihshs==false && posothta_tropopoihshs.equals("back")==false)
                                {
                                    throw new OfferDoesNotExistException();
                                }
                            }
                            catch(NumberFormatException e)
                            {
                                System.err.println("\nΔώσατε λάθος χαρακτήρες.Ξαναδώστε το id.");
                            }
                            catch(OfferDoesNotExistException e)
                            {
                                System.err.println();
                                e.print();
                                System.err.println("Ξαναδώστε το id.");
                            }
                        }while(exists_tropopoihshs==false);
                        break;

                    case "3":
                        donator.resetUpdate();
                        System.out.println("\nΌλες οι παροχές σας διαγράφτηκαν με επιτυχία.");
                        Login_Donator(donator, o);
                        break;

                    case "4":
                        donator.commitUpdate(o);
                        System.out.println("\nΤο commit έγινε με επιτυχία.");
                        Login_Donator(donator, o);
                        break;

                    case "5":
                        Login_Donator(donator, o);
                        break;

                    default:
                        System.out.println("\nΔώσατε λάθος αριθμό ή χαρακτήρα.Ξαναδώστε την επιλογή σας.");
                        break;
                }
            }
        }while(true);
    }

    public void Donator_Commit(Donator donator,Organization o)
    {
        if(donator.getOffersList().getRdEntities().isEmpty()){
            System.out.println("\nΔεν προσφέρετε κανένα είδος για να γίνει commit.");
        }
        else{
            donator.commitUpdate(o);
            System.out.println("\nΤο commit έγινε με επιτυχία.");
        }
    }
    
    public void Login_Admin(Organization o)
    {



            System.out.println("Καλωσορίσατε διαχειριστή.\n\nΤα στοιχεία σας.\n--------------\nΌνομα: " + o.getAdmin().getName() + "\nΤηλέφωνο: " + o.getAdmin().getPhone_number() + "\n");
            System.out.println("");
            System.out.println("1. View");
            System.out.println("2. Monitor Organization");
            System.out.println("3. Logout");
            System.out.println("4. Exit");
            System.out.print("Που θες να πλοηγηθείς; Δώσε τον αριθμό που αντιστοιχεί στην κάθε επιλογή: ");

        String epilogi1_a = null;
        try {
            epilogi1_a = sc.nextLine();


            switch (Integer.parseInt(epilogi1_a)) {
                case 1:
                    Admin_Case1(o);
                    break;
                case 2:
                    Admin_Case2(o);
                    break;
                case 3:
                    Login(o);
                    break;
                case 4:
                    Exit();
                    break;
                default:
                    System.out.println("\nΕΔΩΣΕΣ ΛΑΘΟΣ ΕΠΙΛΟΓΗ!! ΞΑΝΑΠΡΟΣΠΑΘΗΣΕ");
                    System.out.println();
                    Login_Admin(o);

            }
        }
        catch (NumberFormatException e){
            System.out.println("\nΜΗΝ ΔΙΝΕΤΕ ΑΚΥΡΟΥΣ ΧΑΡΑΚΤΗΡΕΣ\n");
            System.out.println();
            Login_Admin(o);
        }



    }
    public void Admin_Case1(Organization o)
    {


        System.out.println();
        System.out.println("------------------");
        System.out.println("|      View      |");
        System.out.println("------------------");
        System.out.println("1. Material");
        System.out.println("2. Services");
        System.out.println("3. Back");
        System.out.println("4. Logout");
        System.out.println("5. Exit");
        System.out.print("Δώσε την τιμή που αντιστοιχει στην πράξη σου: ");

        String epilogi1_1_a = null;
        //amyntikos programmatismos
        do{

            try {
                epilogi1_1_a = sc.nextLine();
            
            

        if(Integer.parseInt(epilogi1_1_a)==1) {

            System.out.println();

            if (o.getCurrentDonations().getMaterialRdEntities().isEmpty()) {
                System.out.println("Δεν υπάρχουν τωρινές προσφορές!\n");
                Admin_Case1(o);
            } else {
                for (int i = 0; i < o.getCurrentDonations().getMaterialRdEntities().size(); i++) {
                    System.out.print((i + 1) + ". " + o.getCurrentDonations().getMaterialRdEntities().get(i).getEntity().getName());
                    System.out.println("(" + o.getCurrentDonations().getMaterialRdEntities().get(i).getQuantity() + ")");

                }
                System.out.println(o.getCurrentDonations().getMaterialRdEntities().size()+1+". Back");
                System.out.println();

                System.out.print("Δωσε τον αντιστοίχο αριθμό για το Material που θες να δείς ή κανε back: ");
                String epilogi_Material = null;
                do{
                try {
                    epilogi_Material = sc.nextLine();


                    for (int i = 0; i < o.getCurrentDonations().getMaterialRdEntities().size(); i++) {
                        if (Integer.parseInt(epilogi_Material) == (i + 1)) {
                            o.getCurrentDonations().getMaterialRdEntities().get(i).getEntity().toString();
                            System.out.println(o.getCurrentDonations().getMaterialRdEntities().get(i).getEntity().getDetails());
                            System.out.println("Η τρέχουσα ποσότητα στον οργανισμό: " + o.getCurrentDonations().getMaterialRdEntities().get(i).getQuantity());
                            System.out.println();
                            break;

                        }
                    }
                    if (Integer.parseInt(epilogi_Material) > o.getCurrentDonations().getMaterialRdEntities().size() + 1 || Integer.parseInt(epilogi_Material) < 0) {
                        System.out.println("ΔΩΣΑΤΕ ΛΑΘΟΣ ΕΠΙΛΟΓΗ");
                        System.out.print("Δώστε ξανά την επιλογή σας: ");

                    }
                    if (Integer.parseInt(epilogi_Material) == o.getCurrentDonations().getMaterialRdEntities().size() + 1)
                        Admin_Case1(o);
                }
                catch (NumberFormatException e){
                    System.out.println("\nΜΗΝ ΔΙΝΕΤΕ ΑΚΥΡΟΥΣ ΧΑΡΑΚΤΗΡΕΣ\n");
                    System.out.println();
                    Admin_Case1(o);
                }

                }while(Integer.parseInt(epilogi_Material)>o.getCurrentDonations().getMaterialRdEntities().size()+1||Integer.parseInt(epilogi_Material)<0);

                    System.out.println("1. Back");
                    System.out.println("2. Logout");
                    System.out.println("3. Exit");
                String x6 = null;
                do {
                    System.out.print("Δώσε την τιμή που θές: ");

                    try {
                        x6 = sc.nextLine();



                        if (Integer.parseInt(x6) == 1)
                            Admin_Case1(o);
                        if (Integer.parseInt(x6) == 2)
                            Login(o);
                        if (Integer.parseInt(x6) == 3)
                            Exit();
                        if (Integer.parseInt(x6) != 1 && Integer.parseInt(x6) != 2 && Integer.parseInt(x6) != 3) {
                            System.out.println("ΔΩΣΑΤΕ ΛΑΘΟΣ ΕΠΙΛΟΓΗ - ΘΑ ΠΡΕΠΕΙ ΝΑ ΔΩΣΕΤΕ ΞΑΝΑ ΜΙΑ ΤΙΜΗ");

                        }
                    }
                    catch (NumberFormatException e){
                        System.out.println("\nΜΗΝ ΔΙΝΕΤΕ ΑΚΥΡΟΥΣ ΧΑΡΑΚΤΗΡΕΣ\n");
                        System.out.println();
                        Admin_Case1(o);
                    }
                }while (Integer.parseInt(x6)!=1&& Integer.parseInt(x6)!=2 && Integer.parseInt(x6)!=3);
            }
        }

            if (Integer.parseInt(epilogi1_1_a)== 2) {

                System.out.println();

                if (o.getCurrentDonations().getServiceRdEntities().isEmpty()) {
                    System.out.println("Δεν υπάρχουν τωρινές προσφορές!\n");
                    Admin_Case1(o);

                } else {
                    for (int i = 0; i < o.getCurrentDonations().getServiceRdEntities().size(); i++) {
                        System.out.print((i + 1) + ". " + o.getCurrentDonations().getServiceRdEntities().get(i).getEntity().getName());
                        System.out.println("(" + o.getCurrentDonations().getServiceRdEntities().get(i).getQuantity() + ")");


                    }
                    System.out.println(o.getCurrentDonations().getServiceRdEntities().size()+1+". Back");
                    System.out.println();
                    System.out.print("Δωσε τον αντιστοίχο αριθμό για το Service που θες να δείς: ");
                    String epilogi_Service = null;
                    do{

                    try {
                        epilogi_Service = sc.nextLine();


                        for (int i = 0; i < o.getCurrentDonations().getServiceRdEntities().size(); i++) {
                            if (Integer.parseInt(epilogi_Service) == (i + 1)) {
                                System.out.println();
                                System.out.print(o.getCurrentDonations().getServiceRdEntities().get(i).getEntity().getEntityInfo());
                                System.out.println(o.getCurrentDonations().getServiceRdEntities().get(i).getEntity().getDetails());
                                System.out.println("Η τρέχουσα ποσότητα στον οργανισμό: " + o.getCurrentDonations().getServiceRdEntities().get(i).getQuantity());
                                System.out.println();
                                break;

                            }
                        }
                        if (Integer.parseInt(epilogi_Service) > o.getCurrentDonations().getServiceRdEntities().size() + 1 || Integer.parseInt(epilogi_Service) < 0) {
                            System.out.println("ΔΩΣΑΤΕ ΛΑΘΟΣ ΕΠΙΛΟΓΗ");
                            System.out.print("Δώστε ξανά την επιλογή σας: ");

                        }
                        if (Integer.parseInt(epilogi_Service) == o.getCurrentDonations().getServiceRdEntities().size() + 1)
                            Admin_Case1(o);

                    }
                    catch (NumberFormatException e){
                        System.out.println("\nΜΗΝ ΔΙΝΕΤΕ ΑΚΥΡΟΥΣ ΧΑΡΑΚΤΗΡΕΣ\n");
                        System.out.println();
                        Admin_Case1(o);
                    }

                }while (Integer.parseInt(epilogi_Service)>o.getCurrentDonations().getServiceRdEntities().size()+1||Integer.parseInt(epilogi_Service)<0);
                }
                System.out.println("1. Back");
                System.out.println("2. Logout");
                System.out.println("3. Exit");
                String  x7 = null;
                do {
                    System.out.print("Δώσε την τιμή που θές: ");

                    try {
                        x7 = sc.nextLine();


                    if (Integer.parseInt(x7) == 1)
                        Admin_Case1(o);
                    if (Integer.parseInt(x7) == 2)
                        Login(o);
                    if (Integer.parseInt(x7) == 3)
                        Exit();
                    if (Integer.parseInt(x7) != 1 && Integer.parseInt(x7) != 2 && Integer.parseInt(x7) != 3) {
                        System.out.println("ΔΩΣΑΤΕ ΛΑΘΟΣ ΕΠΙΛΟΓΗ - ΘΑ ΠΡΕΠΕΙ ΝΑ ΞΑΝΑΔΩΣΕΤΕ ΜΙΑ ΤΙΜΗ");

                    }
                }
                    catch (NumberFormatException e){
                        System.out.println("\nΜΗΝ ΔΙΝΕΤΕ ΑΚΥΡΟΥΣ ΧΑΡΑΚΤΗΡΕΣ\n");
                        System.out.println();
                        Admin_Case1(o);
                    }

                }while (Integer.parseInt(x7)!=1&& Integer.parseInt(x7)!=2 && Integer.parseInt(x7)!=3);
            }
            if (Integer.parseInt(epilogi1_1_a) == 3)
                Login_Admin(o);

            if (Integer.parseInt(epilogi1_1_a) == 4)
                Login(o);

            if (Integer.parseInt(epilogi1_1_a) == 5)
                Exit();

                if (Integer.parseInt(epilogi1_1_a)!=1&&Integer.parseInt(epilogi1_1_a)!=2&&Integer.parseInt(epilogi1_1_a)!=3&&Integer.parseInt(epilogi1_1_a)!=4&&Integer.parseInt(epilogi1_1_a)!=5) {
                    System.out.println("\nΕΔΩΣΕΣ ΛΑΘΟΣ ΕΠΙΛΟΓΗ!! ΞΑΝΑΠΡΟΣΠΑΘΗΣΕ");
                    System.out.print("Δώστε ξανά την επιλογή σας: ");


                }
            }
            catch (NumberFormatException e)
            {
                System.out.println("\nΜΗΝ ΔΙΝΕΤΕ ΑΚΥΡΟΥΣ ΧΑΡΑΚΤΗΡΕΣ\n");
                Admin_Case1(o);
            }
        }while(Integer.parseInt(epilogi1_1_a)!=1&&Integer.parseInt(epilogi1_1_a)!=2&&Integer.parseInt(epilogi1_1_a)!=3&&Integer.parseInt(epilogi1_1_a)!=4&&Integer.parseInt(epilogi1_1_a)!=5);
    }


    public void Admin_Case2(Organization o)
    {
        System.out.println();
        System.out.println("------------------------");
        System.out.println("| Monitor Organization |");
        System.out.println("------------------------");
        System.out.println("1. List Beneficiaries");
        System.out.println("2. List Donators");
        System.out.println("3. Reset Beneficiaries Lists");
        System.out.println("4. Back");
        System.out.println("5. Logout");
        System.out.println("6. Exit");
        System.out.print("Επίλεξε ποια λίστα θες να επεξεργαστείς: ");//επίλεξε οχι επέλεξε
        String epilogi2_a =null;
        do {
            try {
                epilogi2_a = sc.nextLine();


                if (Integer.parseInt(epilogi2_a) == 1) {
                    if (o.getBeneficiaryList().isEmpty()) {
                        System.out.println("\nΔΕΝ ΥΠΑΡΧΟΥΝ Επωφελούμενοι!");
                        Admin_Case2(o);
                    } else {
                        System.out.println();
                        for (int i = 0; i < o.getBeneficiaryList().size(); i++)
                            System.out.println((i + 1) + ". " + o.getBeneficiaryList().get(i).getName());


                        System.out.println();
                        System.out.println("1. Δυνατότητα να γίνει καθαρισμός όλως των ειδών που έχει λάβει ο Επωφελούμενος της επιλογής σου.");
                        System.out.println("2. Δίνεται η δυνατότητα να διαγραφεί ο Επωφελούμενος της επιλογής σου. ");
                        System.out.println("3. Back");
                        System.out.println("4. Logout");

                        System.out.println("5. Exit");
                        String x = null;
                        do {

                            System.out.print("\nΔώσε την τιμή που θές: ");


                            try {
                                x = sc.nextLine();


                                if (Integer.parseInt(x) == 1) {
                                    System.out.println();


                                    for (int i = 0; i < o.getBeneficiaryList().size(); i++)
                                        System.out.println((i + 1) + ". " + o.getBeneficiaryList().get(i).getName());
                                    System.out.println(o.getBeneficiaryList().size() + 1 + ". Back");
                                    System.out.println();

                                    System.out.print("Διάλεξε σε ποιον θες να κάνεις καθαρισμό ή κάνε back: ");
                                    String x1 = null;
                                    do {
                                        try {
                                            x1 = sc.nextLine();


                                            for (int i = 0; i < o.getBeneficiaryList().size(); i++) {
                                                if (Integer.parseInt(x1) == (i + 1)) {
                                                    if (o.getBeneficiaryList().get(i).getReceivedList().getRdEntities().isEmpty())
                                                        System.out.println("\nΔεν έχει λάβει κανένα είδος!\n");
                                                    else if (!o.getBeneficiaryList().get(i).getReceivedList().getRdEntities().isEmpty()) {
                                                        System.out.println("Είχε: \n");

                                                        for (int j = 0; j < o.getBeneficiaryList().get(i).getReceivedList().getRdEntities().size(); j++) {
                                                            System.out.print((j + 1) + "." + o.getBeneficiaryList().get(i).getReceivedList().getRdEntities().get(j).getEntity().getName());
                                                            System.out.println("(" + o.getBeneficiaryList().get(i).getReceivedList().getRdEntities().get(j).getQuantity() + ")");

                                                        }
                                                        System.out.println();

                                                        o.getAdmin().reset(o.getBeneficiaryList().get(i));
                                                        System.out.println("Εγινε διαγραφή των ειδών που έχει λάβει ο Επωφελούμενος: " + o.getBeneficiaryList().get(i).getName() + " ✓");
                                                        break;
                                                    }
                                                }
                                            }
                                            if (Integer.parseInt(x1) == o.getBeneficiaryList().size() + 1)
                                                Admin_Case2(o);

                                            if (Integer.parseInt(x1) > o.getBeneficiaryList().size() + 1 || Integer.parseInt(x1) < 0) {
                                                System.out.println("ΔΩΣΑΤΕ ΛΑΘΟΣ ΕΠΙΛΟΓΗ");
                                                System.out.print("Δώστε ξανά την επιλογή σας: ");

                                            }
                                        }
                                        catch (NumberFormatException e)
                                        {
                                            System.out.println("\nΜΗΝ ΔΙΝΕΤΕ ΑΚΥΡΟΥΣ ΧΑΡΑΚΤΗΡΕΣ\n");
                                            System.out.println();
                                            Admin_Case2(o);
                                        }
                                    } while (Integer.parseInt(x1) > o.getBeneficiaryList().size() + 1 || Integer.parseInt(x1) < 0);
                                    System.out.println();
                                    System.out.println("1. Back");
                                    System.out.println("2. Logout");
                                    System.out.println("3. Exit");
                                    String x8 = null;
                                    do {
                                        System.out.print("Δώσε την τιμή που θές: ");
                                        try {
                                            x8 = sc.nextLine();



                                            if (Integer.parseInt(x8) == 1)
                                                Admin_Case2(o);
                                            if (Integer.parseInt(x8) == 2)
                                                Login(o);
                                            if (Integer.parseInt(x8) == 3)
                                                Exit();
                                            if (Integer.parseInt(x8) != 1 && Integer.parseInt(x8) != 2 && Integer.parseInt(x8) != 3) {
                                                System.out.println("ΔΩΣΑΤΕ ΛΑΘΟΣ ΕΠΙΛΟΓΗ - Θα πρέπει να ξαναδώσετ μια τιμή");


                                            }
                                        }
                                        catch (NumberFormatException e)
                                        {
                                            System.out.println("\nΜΗΝ ΔΙΝΕΤΕ ΑΚΥΡΟΥΣ ΧΑΡΑΚΤΗΡΕΣ\n");
                                            System.out.println();
                                            Admin_Case2(o);
                                        }
                                    } while (Integer.parseInt(x8) != 1 && Integer.parseInt(x8) != 2 && Integer.parseInt(x8) != 3);

                                }

                                if (Integer.parseInt(x) == 2) {
                                    System.out.println();


                                    for (int i = 0; i < o.getBeneficiaryList().size(); i++)
                                        System.out.println((i + 1) + ". " + o.getBeneficiaryList().get(i).getName());
                                    System.out.println(o.getBeneficiaryList().size() + 1 + ". Back");

                                    System.out.print("Δώσε ποιον Επωφελούμενο θες να διαγράψεις ή κάνε back: ");
                                    String x2 = null;
                                    do {
                                        try {

                                            x2 = sc.nextLine();


                                            for (int i = 0; i < o.getBeneficiaryList().size(); i++) {
                                                if (Integer.parseInt(x2) == (i + 1)) {
                                                    System.out.println("\nO Επωφελούμενος: " + o.getBeneficiaryList().get(i).getName() + " διαγραφτηκε! ✓");
                                                    o.removeBeneficiary(o.getBeneficiaryList().get(i));
                                                    Admin_Case2(o);
                                                }


                                            }
                                            if (Integer.parseInt(x2) == o.getBeneficiaryList().size() + 1)
                                                Admin_Case2(o);

                                            if (Integer.parseInt(x2) < 0 || Integer.parseInt(x2) > o.getBeneficiaryList().size() + 1) {
                                                System.out.println("ΔΩΣΑΤΕ ΛΑΘΟΣ ΕΠΙΛΟΓΗ");
                                                System.out.print("Δώστε ξανά την επιλογή σας: ");


                                            }
                                        }
                                        catch (NumberFormatException e)
                                        {
                                            System.out.println("\nΜΗΝ ΔΙΝΕΤΕ ΑΚΥΡΟΥΣ ΧΑΡΑΚΤΗΡΕΣ\n");
                                            System.out.println();
                                            Admin_Case2(o);
                                        }
                                    } while (Integer.parseInt(x2) < 0 || Integer.parseInt(x2) > o.getBeneficiaryList().size() + 1);

                                }
                                if (Integer.parseInt(x) == 3)
                                    Admin_Case2(o);
                                if (Integer.parseInt(x) == 4)
                                    Login(o);
                                if (Integer.parseInt(x) == 5)
                                    Exit();
                                if (Integer.parseInt(x) != 1 && Integer.parseInt(x) != 2 && Integer.parseInt(x) != 3 && Integer.parseInt(x) != 4 && Integer.parseInt(x) != 5) {
                                    System.out.print("ΔΩΣΑΤΕ ΛΑΘΟΣ ΕΠΙΛΟΓΗ - ΘΑ ΠΡΕΠΕΙ ΝΑ ΞΑΝΑΔΩΣΕΤΕ ΜΙΑ ΤΙΜΗ");


                                }
                            }
                            catch (NumberFormatException e)
                            {
                                System.out.println("\nΜΗΝ ΔΙΝΕΤΕ ΑΚΥΡΟΥΣ ΧΑΡΑΚΤΗΡΕΣ\n");
                                System.out.println();
                                Admin_Case2(o);
                            }
                        } while (Integer.parseInt(x) != 1 && Integer.parseInt(x) != 2 && Integer.parseInt(x) != 3 && Integer.parseInt(x) != 4 && Integer.parseInt(x) != 5);

                    }
                }
                if (Integer.parseInt(epilogi2_a) == 2) {
                    if (o.getDonatorList().isEmpty()) {
                        System.out.println("\nΔΕΝ ΥΠΑΡΧΟΥΝ Δωρητές!");
                        Admin_Case2(o);
                    } else {
                        System.out.println();
                        for (int i = 0; i < o.getDonatorList().size(); i++)
                            System.out.println((i + 1) + "." + o.getDonatorList().get(i).getName());

                        System.out.println();
                        System.out.println("1. Δυνατότητα να εμφανιστούν όλες οι τρέχουσες παροχές που πρροτίθεται να παρέχει ο Δωριτής της επιλογής σου.");
                        System.out.println("2. Δίνεται η δυνατότητα να διαγραφεί ο Δωριτής της επιλογής σου. ");
                        System.out.println("3. Back");
                        System.out.println("4. Logout");
                        System.out.println("5. Exit");
                        System.out.print("Δώσε την τιμή που θές: ");
                        String x3 = null;
                        do {
                            try {
                                x3 = sc.nextLine();


                                if (Integer.parseInt(x3) == 1) {
                                    System.out.println();
                                    for (int i = 0; i < o.getDonatorList().size(); i++)
                                        System.out.println((i + 1) + ". " + o.getDonatorList().get(i).getName());
                                    System.out.println(o.getDonatorList().size() + 1 + ". Back");


                                    System.out.print("Δωσε ποιου Δωριτή τις παροχές θες να δεις ή κάνε back: ");
                                    String x4 = null;
                                    do {
                                        try {
                                            x4 = sc.nextLine();

                                            for (int i = 0; i < o.getDonatorList().size(); i++) {
                                                if (Integer.parseInt(x4) == (i + 1)) {
                                                    System.out.println("\nO Δωριτής: " + o.getDonatorList().get(i).getName() + " πρροτίθεται να παρέχει: ");
                                                    o.getDonatorList().get(i).monitor();
                                                    break;
                                                }
                                            }
                                            if (Integer.parseInt(x4) == o.getDonatorList().size() + 1)
                                                Admin_Case2(o);

                                            if (Integer.parseInt(x4) < 0 || Integer.parseInt(x4) > o.getDonatorList().size() + 1) {
                                                System.out.println("ΔΩΣΑΤΕ ΛΑΘΟΣ ΕΠΙΛΟΓΗ");
                                                System.out.print("Δώστε ξανά την επιλογή σας: ");


                                            }
                                        }
                                        catch (NumberFormatException e)
                                        {
                                            System.out.println("\nΜΗΝ ΔΙΝΕΤΕ ΑΚΥΡΟΥΣ ΧΑΡΑΚΤΗΡΕΣ\n");
                                            System.out.println();
                                            Admin_Case2(o);
                                        }
                                    } while (Integer.parseInt(x4) < 0 || Integer.parseInt(x4) > o.getDonatorList().size() + 1);
                                    System.out.println();
                                    System.out.println("1. Back");
                                    System.out.println("2. Logout");
                                    System.out.println("3. Exit");
                                    System.out.print("Δώσε την τιμή που θές: ");
                                    String x10 = null;
                                    do {
                                        try {
                                            x10 = sc.nextLine();


                                            if (Integer.parseInt(x10) == 1)
                                                Admin_Case2(o);
                                            if (Integer.parseInt(x10) == 2)
                                                Login(o);
                                            if (Integer.parseInt(x10) == 3)
                                                Exit();
                                            if (Integer.parseInt(x10) != 1 && Integer.parseInt(x10) != 2 && Integer.parseInt(x10) != 3) {
                                                System.out.println("ΔΩΣΑΤΕ ΛΑΘΟΣ ΕΠΙΛΟΓΗ");
                                                System.out.print("Δώστε ξανά την επιλογή σας: ");

                                            }
                                        }
                                        catch (NumberFormatException e)
                                        {
                                            System.out.println("\nΜΗΝ ΔΙΝΕΤΕ ΑΚΥΡΟΥΣ ΧΑΡΑΚΤΗΡΕΣ\n");
                                            System.out.println();
                                            Admin_Case2(o);
                                        }
                                    } while (Integer.parseInt(x10) != 1 && Integer.parseInt(x10) != 2 && Integer.parseInt(x10) != 3);
                                }
                                if (Integer.parseInt(x3) == 2) {


                                    for (int i = 0; i < o.getDonatorList().size(); i++)
                                        System.out.println((i + 1) + ". " + o.getDonatorList().get(i).getName());
                                    System.out.println(o.getDonatorList().size() + 1 + ". Back");


                                    System.out.print("Δωσε ποιον Δωριτή θες να διαγράψεις ή κάνε back: ");
                                    String x5 = null;
                                    do {
                                        try {
                                            x5 = sc.nextLine();

                                            for (int i = 0; i < o.getDonatorList().size(); i++) {
                                                if (Integer.parseInt(x5) == i + 1) {
                                                    System.out.println("\nΟ Δωριτής: " + o.getDonatorList().get(i).getName() + " διαγραφτηκε! ✓");
                                                    o.removeDonator(o.getDonatorList().get(i));
                                                    Admin_Case2(o);
                                                }
                                                if (Integer.parseInt(x5) == o.getDonatorList().size() + 1)
                                                    Admin_Case2(o);
                                                if (Integer.parseInt(x5) < 0 || Integer.parseInt(x5) > o.getDonatorList().size() + 1) {
                                                    System.out.println("ΔΩΣΑΤΕ ΛΑΘΟΣ ΕΠΙΛΟΓΗ");
                                                    System.out.print("Δώστε ξανά την επιλογή σας: ");

                                                }

                                            }
                                        }
                                        catch (NumberFormatException e)
                                        {
                                            System.out.println("\nΜΗΝ ΔΙΝΕΤΕ ΑΚΥΡΟΥΣ ΧΑΡΑΚΤΗΡΕΣ\n");
                                            System.out.println();
                                            Admin_Case2(o);
                                        }
                                    } while (Integer.parseInt(x5) < 0 || Integer.parseInt(x5) > o.getDonatorList().size() + 1);

                                }
                                if (Integer.parseInt(x3) == 3)
                                    Admin_Case2(o);
                                if (Integer.parseInt(x3) == 4)
                                    Login(o);
                                if (Integer.parseInt(x3) == 5)
                                    Exit();

                                if (Integer.parseInt(x3) != 1 && Integer.parseInt(x3) != 2 && Integer.parseInt(x3) != 3 && Integer.parseInt(x3) != 4 && Integer.parseInt(x3) != 5) {
                                    System.out.println("ΔΩΣΑΤΕ ΛΑΘΟΣ ΕΠΙΛΟΓΗ");
                                    System.out.print("Δώστε ξανά την επιλογή σας: ");

                                }
                            }
                            catch (NumberFormatException e)
                            {
                                System.out.println("\nΜΗΝ ΔΙΝΕΤΕ ΑΚΥΡΟΥΣ ΧΑΡΑΚΤΗΡΕΣ\n");
                                Admin_Case2(o);
                            }
                        } while (Integer.parseInt(x3) != 1 && Integer.parseInt(x3) != 2 && Integer.parseInt(x3) != 3 && Integer.parseInt(x3) != 4 && Integer.parseInt(x3) != 5);
                    }
                }
                if (Integer.parseInt(epilogi2_a) == 3) {
                    if (o.getBeneficiaryList().isEmpty()) {
                        System.out.println("\nΔΕΝ ΥΠΑΡΧΟΥΝ επωφελούμενοι!");
                        Admin_Case2(o);
                    } else {
                        for (int i = 0; i < o.getBeneficiaryList().size(); i++) {
                            o.getAdmin().reset(o.getBeneficiaryList().get(i));
                        }
                        System.out.println("\nΈγινε διαγραφή των ειδών που έχουν λάβει όλοι οι Επωφελούμενοι. ✓\n");
                        System.out.println("1. Back");
                        System.out.println("2. Logout");
                        System.out.println("3. Exit");
                        System.out.print("Δώσε την τιμή που θές: ");
                        String x12 = null;
                        do {
                            try {
                                x12 = sc.nextLine();


                                if (Integer.parseInt(x12) == 1)
                                    Admin_Case2(o);
                                if (Integer.parseInt(x12) == 2)
                                    Login(o);
                                if (Integer.parseInt(x12) == 3)
                                    Exit();
                                if (Integer.parseInt(x12) != 1 && Integer.parseInt(x12) != 2 && Integer.parseInt(x12) != 3) {
                                    System.out.println("ΔΩΣΑΤΕ ΛΑΘΟΣ ΕΠΙΛΟΓΗ");
                                    System.out.print("Δώστε ξανά την επιλογή σας: ");

                                }
                            }
                            catch (NumberFormatException e)
                            {
                                System.out.println("\nΜΗΝ ΔΙΝΕΤΕ ΑΚΥΡΟΥΣ ΧΑΡΑΚΤΗΡΕΣ\n");
                                Admin_Case2(o);
                            }
                        } while (Integer.parseInt(x12) != 1 && Integer.parseInt(x12) != 2 && Integer.parseInt(x12) != 3);
                    }
                }
                if (Integer.parseInt(epilogi2_a) == 4)
                    Login_Admin(o);
                if (Integer.parseInt(epilogi2_a) == 5)
                    Login(o);
                if (Integer.parseInt(epilogi2_a) == 6)
                    Exit();
                if (Integer.parseInt(epilogi2_a) != 1 && Integer.parseInt(epilogi2_a) != 2 && Integer.parseInt(epilogi2_a) != 3 && Integer.parseInt(epilogi2_a) != 4 && Integer.parseInt(epilogi2_a) != 5 && Integer.parseInt(epilogi2_a) != 6) {
                    System.out.println("\nΕΔΩΣΕΣ ΛΑΘΟΣ ΕΠΙΛΟΓΗ!! ΞΑΝΑΠΡΟΣΠΑΘΗΣΕ");
                    System.out.print("Δώστε ξανά την επιλογή σας: ");
                }
            }
            catch (NumberFormatException e){
                System.out.println("\nΜΗΝ ΔΙΝΕΤΕ ΑΚΥΡΟΥΣ ΧΑΡΑΚΤΗΡΕΣ\n");
                System.out.println();
                Admin_Case2(o);
            }
        }while (Integer.parseInt(epilogi2_a) != 1 && Integer.parseInt(epilogi2_a) != 2 && Integer.parseInt(epilogi2_a) != 3 && Integer.parseInt(epilogi2_a) != 4 && Integer.parseInt(epilogi2_a) != 5 && Integer.parseInt(epilogi2_a) != 6);
    }
    
    public void Login_Beneficiary(Beneficiary b, Organization o){
        System.out.println();
        System.out.println("Οργανισμός: " + o.getName());
        System.out.println("Καλωσορίσατε επωφελούμενε\n\nΤα στοιχεία σας:\n----------------\nΌνομα: " + b.getName() + "\nΤηλέφωνο: " + b.getPhone_number());
        System.out.println("Αριθμός μελών οικογένειας: " + b.getNoPersons() +"\n");
        System.out.println("1: Add Request");
        System.out.println("2: Show Requests");
        System.out.println("3: Commit");
        System.out.println("4: Back");
        System.out.println("5: Logout");
        System.out.println("6: Exit");
        try
        {
        String sChoice; 
        int choice = -1;
        do{
            System.out.println();
            System.out.print("Δώστε την επιλογή σας: ");
            //amintikos programmatismos gia tin antimetopisi number format exception 
            sChoice = sc.nextLine();
            choice = Integer.parseInt(sChoice); 
            switch(choice){
                case 1: AddRequest(b, o); break;
                case 2: ShowRequests(b, o); break;
                case 3: LoginBen_Commit(b, o); break;
                case 4: Logout(o); break;
                case 5: Logout(o); break;
                case 6: Exit(); break;
                default: System.out.println("Λάθος επιλογή...");
                }
        }
        while(choice<1 || choice>6);
        }
        //apotrepei ton xristi apo to na dinei grammata
        catch(NumberFormatException e){
            System.out.println("\nΜΗΝ ΔΙΝΕΤΕ ΑΚΥΡΟΥΣ ΧΑΡΑΚΤΗΡΕΣ\n");
            LoginBen_Back(b, o);
        }
    }
    public void AddRequest(Beneficiary b, Organization o){
        if(o.getCurrentDonations().getRdEntities().isEmpty()){
            System.out.println("Δεν προσφέρεται κανένα είδος αυτή την στιγμή");
            LoginBen_Back(b, o);
        }
        else{
            //diaxeirisi NumberFormatException gia na apotrepw ton xrisrti na plhktrologei strings i megalous aritmous ekei pou den prepei 
            try
            {
                System.out.print("1. Material");
                int matCounter=0;
                //μετράει τα υλικά απο την λίστα με τα προσφερώμενα entities toy organismou
                for(RequestDonation rd : o.getCurrentDonations().getRdEntities()){
                    if(rd.getEntity().getIsMaterial()){matCounter++;}
                }
                System.out.print(" (ο οργανισμός παρέχει " + matCounter + " υλικά)\n");
                System.out.print("2. Services");
                int servCounter=0;
                //μετράει τις υπηρεσίες απο την λιστα με τα προσφερώμενα entities του organismou
                for(RequestDonation rd : o.getCurrentDonations().getRdEntities()){
                    if(rd.getEntity().getIsMaterial()==false){servCounter++;}
                }
                System.out.print(" (ο οργανισμός παρέχει " + servCounter + " υπηρεσίες)\n");
                int choice = -1;
                do{
                    System.out.println("3: Back");
                    System.out.print("\nΠαρακαλώ επιλέξτε μία κατηγορία: ");
                    String sChoice = sc.nextLine();
                    choice = Integer.parseInt(sChoice);
                    if(choice==1){
                        ArrayList<RequestDonation> matList;
                        matList = o.getCurrentDonations().listMaterialRdEntities();//epistrefei lista me ola ta material kai parallila ta typwnei gia na ta dei o xristis
                        if(matList.isEmpty()){
                            System.out.println("\nΔεν παρέχεται κανένα υλικό αυτή την στιγμή.\n");
                            AddRequest(b, o);
                        }
                        int matChoice = -1;
                        do{
                            System.out.println("0: Back");
                            System.out.print("\nΕπιλέξτε ποιο υλικό επιθυμείτε: ");
                            String sMatChoice = sc.nextLine();
                            matChoice = Integer.parseInt(sMatChoice);
                            if(matChoice==0){AddRequest(b, o);}
                            else if(matChoice>0 && matChoice<=matList.size()) {
                                RequestDonation chosenRd = (RequestDonation)matList.get(matChoice-1).clone();// dimiourgw clone tou RequestDonation pou dialekse
                                RequestDonation requestedRd = null;
                                //diatrexv thn requests list gia na dw an exei zitisei to yliko idi 
                                for(RequestDonation rd : b.getRequestsList().getRdEntities()){
                                    if(rd.getId()==chosenRd.getId()){
                                        requestedRd = rd;
                                    }
                                }
                                //diatrexw tin current donations gia na dw poso exei lavei idi apo to iliko
                                RequestDonation receivedRd = null;
                                for(RequestDonation rd : b.getReceivedList().getRdEntities()){
                                    if(rd.getId()==chosenRd.getId()){
                                        receivedRd = rd;
                                    }
                                }
                                System.out.println(matChoice + ". " + chosenRd.getEntity().getName() + ": ");
                                System.out.println(chosenRd.getEntity().toString());
                                if(requestedRd != null){
                                    System.out.println("Ποσότητα που έχετε ζητήσει: " + requestedRd.getQuantity());
                                }
                                if(receivedRd != null){
                                    System.out.println("Ποσότητα που έχετε λάβει: " + receivedRd.getQuantity());
                                }
                                System.out.println("Διαθέσιμη ποσότητα στον οργανισμό: " + chosenRd.getQuantity());
                                System.out.println("Ποσοτητα που δικαιούστε: " + b.getQqf(chosenRd));
                                String sQuantity;
                                double quantity = -1;
                                do{
                                    System.out.println("0: Back\n");
                                    System.out.print("Δώστε την ποσότητα που επιθυμείτε ή 0 για να πάτε πίσω: ");
                                    sQuantity = sc.nextLine();
                                    quantity = Double.parseDouble(sQuantity);
                                    if(quantity==0){AddRequest(b, o);}
                                    else if(requestedRd != null && receivedRd == null){
                                        chosenRd.setQuantity(requestedRd.getQuantity() + quantity);    
                                    }
                                    else if(receivedRd != null && requestedRd == null){
                                        chosenRd.setQuantity(receivedRd.getQuantity() + quantity); 
                                    }
                                    else if(requestedRd != null && receivedRd != null){
                                        chosenRd.setQuantity(requestedRd.getQuantity() + receivedRd.getQuantity() + quantity);
                                    } 
                                    else{chosenRd.setQuantity(quantity);}
                                }
                                while(quantity<0);
                                String YorN;
                                do{
                                    System.out.println("\nΕίστε σίγουροι οτι θέλετε να κάνετε αίτημα για το συγκεκριμένο υλικό;");
                                    System.out.print("Αν είστε πατήστε 'y' αν δεν είστε πατήστε 'n': ");
                                    YorN = sc.nextLine();
                                    switch(YorN){
                                        case "y": 
                                            b.addToRequestsList(o, chosenRd); //prosthiki stin lista twn requests tou beneficiary
                                        break;  
                                        case "n": AddRequest(b, o); break;
                                        default: System.out.println("Λάθος επιλογή...");
                                    }
                                }
                                while(!YorN.equals("y") && !YorN.equals("n"));
                                break;
                            }
                            else{System.out.println("Λάθος επιλογή...");}
                        }
                        while(matChoice<0 || matChoice>matList.size());
                        break;
                    }
                    else if(choice==2){
                        ArrayList<RequestDonation> servList; 
                        servList = o.getCurrentDonations().listServiceRdEntities();
                        if(servList.isEmpty()){
                            System.out.println("\nΔεν παρέχεται καμία υπηρεσία αυτή την στιγμή.\n");
                            AddRequest(b, o);
                        } 
                        int servChoice = -1;
                        do{
                            System.out.println("0: Back");
                            System.out.print("\nΕπιλέξτε ποια υπηρεσία επιθυμείτε: ");
                            String sServChoice = sc.nextLine();
                            servChoice = Integer.parseInt(sServChoice);
                            if(servChoice==0){AddRequest(b, o);}
                            else if(servChoice>0 && servChoice<=servList.size()){
                                RequestDonation chosenRd = (RequestDonation)servList.get(servChoice-1).clone();//anitigrafo tou RequestDonation pou dialekse
                                //diatrexv tin requests list gia na dw an exei zitisei to service idi 
                                RequestDonation requestedRd = null;
                                for(RequestDonation rd : b.getRequestsList().getRdEntities()){
                                    if(rd.getId()==chosenRd.getId()){
                                        requestedRd = rd;
                                    }
                                }
                                //diatrexw tin current donations gia na dw poso exei lavei idi apo to service
                                RequestDonation receivedRd = null;
                                for(RequestDonation rd : b.getReceivedList().getRdEntities()){
                                    if(rd.getId()==chosenRd.getId()){
                                        receivedRd = rd;
                                    }
                                }
                                System.out.println(servChoice + ". " + chosenRd.getEntity().getName() + ": ");
                                System.out.println(chosenRd.getEntity().toString());
                                double quantity = -1;
                                if(requestedRd != null){
                                    System.out.println("Ώρες που έχετε ζητήσει: " + requestedRd.getQuantity());
                                }
                                if(receivedRd != null){
                                    System.out.println("Ώρες που έχετε λάβει: " + receivedRd.getQuantity());
                                }
                                System.out.println("Διαθέσιμες ώρες στον οργανισμό: " + chosenRd.getQuantity());
                                chosenRd.setQuantity(0);
                                do{
                                    System.out.println("0: Back\n");
                                    System.out.print("Δώστε τις ώρες που επιθυμείτε ή 0 για να πάτε πίσω: ");
                                    String sQuantity = sc.nextLine();
                                    quantity = Double.parseDouble(sQuantity);
                                    if(quantity==0){AddRequest(b, o);}
                                    else if(requestedRd != null && receivedRd == null){
                                        chosenRd.setQuantity(requestedRd.getQuantity() + quantity);    
                                    }
                                    else if(receivedRd != null && requestedRd == null){
                                        chosenRd.setQuantity(receivedRd.getQuantity() + quantity); 
                                    }
                                    else if(requestedRd != null && receivedRd != null){
                                        chosenRd.setQuantity(requestedRd.getQuantity() + receivedRd.getQuantity() + quantity);
                                    } 
                                    else{chosenRd.setQuantity(quantity);}                                  
                                }
                                while(quantity<0);
                                String YorN;
                                do{
                                    System.out.println("\nΕίστε σίγουροι οτι θέλετε να κάνετε αίτημα για την συγκεκριμένη υπηρεσία;");
                                    System.out.print("Πατήστε 'y' ή 'n' αν είστε ή όχι: ");
                                    YorN = sc.nextLine();
                                    switch(YorN){
                                        case "y": 
                                            b.addToRequestsList(o, chosenRd); //prosthiki stin lista twn requests tou beneficiary 
                                            break;
                                        case "n": AddRequest(b, o); break;
                                        default: System.out.println("Λάθος επιλογή...");
                                    }
                                }
                                while(!YorN.equals("y") && !YorN.equals("n"));
                            }
                            else{System.out.println("Λάθος επιλογή...");}
                        }
                        while(servChoice<1 || servChoice>servList.size()); 
                        break;
                    }
                    else if(choice==3){
                        LoginBen_Back(b, o);
                        break;
                    }
                    else{System.out.println("Λάθος επιλογή...");}
                }
                while(choice!=1 && choice!=2 && choice!=3);
                LoginBen_Back(b, o);
            }
            catch(NumberFormatException e){
                System.out.println("\nΜΗΝ ΔΙΝΕΤΕ ΑΚΥΡΟΥΣ ΧΑΡΑΚΤΗΡΕΣ\n");
                AddRequest(b, o);
            }
        }
    }
    public void ShowRequests(Beneficiary b, Organization o){
        ArrayList <RequestDonation> requestsList = b.getRequestsList().getRdEntities();
        System.out.println("\nΤα είδη που έχετε ζητήσει: ");
        System.out.println();
        b.listRequests();
        if(requestsList.isEmpty()){LoginBen_Back(b, o);}
        else{
            //diaxeirisi NumberFormatException gia na apotrepw ton xrisrti na plhktrologei strings i megalous aritmous ekei pou den prepei 
            try
            {
                System.out.println();
                System.out.println("1: Επιλογή ενός αιτήματος για διαγραφή ή τροποποίηση");
                System.out.println("2: Καθαρισμός όλων των αιτημάτων");
                System.out.println("3: Commit");
                System.out.println("4: Back");
                int choice = -1;
                do{
                    System.out.print("\nΕπιλέξτε ένα απο τα παραπάνω: ");
                    String sChoice = sc.nextLine();
                    choice = Integer.parseInt(sChoice);
                    if(choice==1){
                        int requestChoice = -1;
                        RequestDonation chosenRequest = null;
                        RequestDonation chosenOffer = null;
                        do{
                            System.out.print("Παρακαλώ επιλέξτε ένα αίτημα: ");
                            String sRequestChoice = sc.nextLine();
                            requestChoice = Integer.parseInt(sRequestChoice);
                            if(requestChoice>=1 && requestChoice<=requestsList.size()){
                                chosenRequest = requestsList.get(requestChoice-1);
                                //vriskw to antistoixw offer stin currentDonations tou Organismou gia na dw posi posotita exei minei
                                for(RequestDonation rd: o.getCurrentDonations().getRdEntities()){
                                    if(rd.getId()==chosenRequest.getId()){
                                        chosenOffer = (RequestDonation)rd.clone();
                                    }
                                }
                                System.out.println(requestChoice + ". " + chosenRequest.getEntity().getName() + ":");
                                System.out.println(chosenRequest.getEntity().toString());
                                if(chosenRequest.getEntity().getIsMaterial()){
                                    System.out.println("Ποσότητα που έχετε ζητήσει: " + chosenRequest.getQuantity());   
                                    System.out.println("Διαθέσιμη ποσότητα στον οργανισμό: " +chosenOffer.getQuantity());
                                    System.out.println("Ποσοτητα που δικαιούστε: " + b.getQqf(chosenRequest));
                                }
                                else{
                                    System.out.println("Ώρες που έχετε ζητήσει: " + chosenRequest.getQuantity());
                                    System.out.println("Διαθέσιμες ώρες στον οργανισμό: " + chosenOffer.getQuantity());
                                }            
                            }
                            else{System.out.println("Λάθος επιλογή...");}
                        }
                        while(requestChoice<1 || requestChoice>b.getRequestsList().getRdEntities().size());
                        System.out.println("\n0: Back");
                        System.out.println("1: Διαγραφή του επιλεγμένου αιτήματος");
                        System.out.println("2: Αλλαγή της ποσότητας του επιλεγμένου αιτήματος");
                        int choice1 = -1;
                        do{
                            System.out.print("Επιλέξτε ένα απο τα παραπάνω: ");
                            String sChoice1 = sc.nextLine();
                            choice1 = Integer.parseInt(sChoice1);
                            if(choice1==1){
                                System.out.println("Γίνεται διαγραφή του αιτήματος: " + requestChoice);
                                b.removeRequest(chosenRequest);
                                break;
                            }
                            else if(choice1==2){
                                double newQuant = -1;
                                do{
                                    System.out.print("Γράψτε την νέα ποσότητα/ώρα που επιθυμείται: ");
                                    String sNewQuant = sc.nextLine();
                                    newQuant = Double.parseDouble(sNewQuant);
                                }
                                while(newQuant<0);
                                b.modifyRequest(o, chosenRequest, newQuant); 
                                break;
                            }
                            else if(choice1==0){ShowRequests(b, o); break;}
                            else{System.out.println("Λάθος επιλογή...");}
                        }
                        while(choice1!=1 && choice1!=2 && choice1!=0);
                        break;
                    }
                    else if(choice==2){
                        System.out.println("Είστε σίγουρος/η οτι θέλετε να διαγράψεται όλα τα αιτήματα σας;");
                        String yn;
                        do{
                            System.out.print("Πατήστε 'y' αν είστε και 'n' αν δεν είστε: "); 
                            yn = sc.nextLine(); 
                            if(yn.equals("y")){
                                b.resetRequests();
                                break;
                            }
                            else if(yn.equals("n")){
                                ShowRequests(b, o);
                                break;
                            }
                            else{System.out.println("Λάθος επιλογή...");}
                        }
                        while(!yn.equals("y") && !yn.equals("n"));
                        break;
                    }
                    else if(choice==3){
                        LoginBen_Commit(b, o);
                        break;
                    }
                    else if(choice==4){
                        LoginBen_Back(b, o);
                        break;
                    }
                    else{System.out.println("Λάθος επιλογή...");}
                }
                while(choice<1 || choice>4);
                LoginBen_Back(b, o);
            }
            catch(NumberFormatException e){
                System.out.println("\nΜΗΝ ΔΙΝΕΤΕ ΑΚΥΡΟΥΣ ΧΑΡΑΚΤΗΡΕΣ\n");
                ShowRequests(b, o);
            }
        }
    }
    public void LoginBen_Commit( Beneficiary b, Organization o){
        System.out.println();
        System.out.println("\nΟλοκλήρωση καταχώρησης αιτημάτων...\n");
        RequestDonationList requestsNotReceived;
        requestsNotReceived = b.commit(o);
        System.out.println("Αιτήματα που ικανοποιήθηκαν επιτυχώς: ");
        b.monitor();
        if(!requestsNotReceived.getRdEntities().isEmpty()){
            System.out.println("Αιτήματα που δεν μπορούν να ικανοποιηθουν:");
            requestsNotReceived.monitor();    
        }
        LoginBen_Back(b, o);
    }
    public void LoginBen_Back(Beneficiary b, Organization o){
        System.out.println();
        System.out.println("1: Add Request");
        System.out.println("2: Show Requests");
        System.out.println("3: Commit");
        System.out.println("4: Back");
        System.out.println("5: Logout");
        System.out.println("6: Exit");
        try
        {
            String sChoice;
            int choice = -1;
            do{
                System.out.println();
                System.out.print("Δώστε την επιλογή σας: ");
                //amintikos programmatismos gia tin antimetopisi number format exception 
                sChoice = sc.nextLine();
                choice = Integer.parseInt(sChoice);
                switch(choice){
                    case 1: AddRequest(b, o); break;
                    case 2: ShowRequests(b, o); break;
                    case 3: LoginBen_Commit(b, o); break;
                    case 4: Logout(o);
                    case 5: Logout(o);
                    case 6: Exit(); break;
                    default: System.out.println("Λάθος επιλογή...");
                    }
            }
            while(choice<1 || choice>6);
        }
        //apotrepei ton xristi apo to na dinei grammata
        catch(NumberFormatException e){
            System.out.println("\nΜΗΝ ΔΙΝΕΤΕ ΑΚΥΡΟΥΣ ΧΑΡΑΚΤΗΡΕΣ\n");
            LoginBen_Back(b, o);
        }
    }
    public void Logout(Organization o)
    {
        Login(o);
    }
    public void Exit()
    {
        sc.close();
        System.exit(0);
    }
}