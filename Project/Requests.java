public class Requests extends RequestDonationList //synolo twn eidwn poy kanei request o Beneficiary
{
    public void add(Organization o,RequestDonation r, Beneficiary b)throws EntityAmountDoesNotExistException, InvalidRequestException, EntityDoesNotExistException{
        boolean validAmount = false;
        boolean valid = false;
        boolean rdFound = false;
        boolean isService = false;
        //diatrexw tin lista me ta prosferomena RequestDonations apo Dwrites an vrw service to kanw add kateuthian
        for(RequestDonation rd : o.getCurrentDonations().getRdEntities()){
            if(rd.getId()==r.getId()){rdFound = true;}
            if(r.getEntity().getIsMaterial()==false){isService = true;}
            if(rdFound && r.getQuantity()<=rd.getQuantity()){validAmount = true;}
            if(rdFound && isService && validAmount){super.add(o, r); break;}
            else if(rdFound && isService){
                if(validAmount==false){throw new EntityAmountDoesNotExistException();}
            }
        }
        rdFound = false;
        validAmount = false;
        //diatrexw tin lista me ta prosferomena RequestDonations apo Dwrites kai kanw add mono otan tyrountai ta A, B tis ekfwnisis
        if(isService==false){
            for(RequestDonation rd : o.getCurrentDonations().getRdEntities()){
                if(rd.getId()==r.getId()){rdFound = true;}
                if(rdFound && r.getQuantity()<=rd.getQuantity()){validAmount = true;}
                if(validRequestDonation(r, b)){valid = true;}
                //an isxoyoun ta A, B apo tin ekfwnisi
                if(rdFound && validAmount && valid){
                    super.add(o, r);
                    break;
                }
                //alliws exceptions
                else if(rdFound){
                    if(validAmount==false){throw new EntityAmountDoesNotExistException();}
                    if(valid==false){throw new InvalidRequestException();}    
                }
            }
        }
    }
    private boolean validRequestDonation(RequestDonation r, Beneficiary ben){
        double requestedQuant = r.getQuantity(); //requested quantity
        int benLevel = ben.getLevel(); //to level tou beneficiary 
        double qqf = 0; //quantity qualified for. Dhladi posotita pou dikaioutai o beneficiary analoga me to level tou
        if(r.getEntity().getIsMaterial()){
            switch(benLevel){
            case 1: qqf = r.getEntity().getLevel1(); break;
            case 2: qqf = r.getEntity().getLevel2(); break;
            case 3: qqf = r.getEntity().getLevel3(); break;
            }
            boolean flag = false;
            boolean matRequested = false;
            RequestDonation rdReceived = null;
            //diatrexw tin receivedList gia na dw poso exei lavei idi o beneficiary apo to requested material
            for(RequestDonation rd : ben.getReceivedList().getRdEntities()){
                if(rd.getId()==r.getId()){
                    matRequested = true;  
                    rdReceived = rd;
                }
            } 
            //an den exei lavei tpt o beneficiary epistrefw true 
            if(ben.getReceivedList().getRdEntities().isEmpty()){
                if(requestedQuant<=qqf){flag = true;}
            }
            //an exei lavei to requested material kai i posotita pou zitaei + tin posotita po exei parei den kseperna to qqf return true
            else if(matRequested == true){
                if(rdReceived.getQuantity() + requestedQuant <= qqf){
                    flag = true;
                }
                else{flag = false;}
            }
            //an exei lavei kati alla oxi auto pou zitaei twra 
            else if(matRequested == false){
                if(requestedQuant <= qqf){flag = true;}
            }
            if(flag == true){return true;}
            else{return false;}
        }
        //an einai service
        else{return true;}
    }
    public void modify(Organization o,RequestDonation r, Beneficiary b, double quantity)throws EntityAmountDoesNotExistException, InvalidRequestException, EntityDoesNotExistException{
        boolean validAmount = false;
        boolean valid = false;
        boolean rdFound = false;
        boolean isService = false;
        //diatrexw tin lista me ta prosferomena RequestDonations apo Dwrites an vrw service to kanw modify kateuthian
        for(RequestDonation rd : o.getCurrentDonations().getRdEntities()){
            if(rd.getId()==r.getId()){rdFound = true;}
            if(r.getEntity().getIsMaterial()==false){isService = true;}
            if(rdFound && quantity<=rd.getQuantity()){validAmount = true;}
            if(rdFound && isService && validAmount){super.modify(r, quantity); break;}
            else if(rdFound && isService){
                if(validAmount==false){throw new EntityAmountDoesNotExistException();}
            }
        }
        rdFound = false;
        validAmount = false;
        //diatrexw tin lista me ta prosferomena RequestDonations apo Dwrites kai kanw modify mono otan tyrountai ta A, B tis ekfwnisis
        if(isService==false){
            for(RequestDonation rd : o.getCurrentDonations().getRdEntities()){
                if(rd.getId()==r.getId()){rdFound = true;}
                if(rdFound && quantity<=rd.getQuantity()){validAmount = true;}
                if(validRequestDonation(r, b, quantity)){valid = true;}
                //an isxoyoun ta A, B apo tin ekfwnisi
                if(rdFound && validAmount && valid){
                    super.modify(r, quantity);
                    break;
                }
                //alliws exceptions
                else if(rdFound && !isService){
                    if(validAmount==false){throw new EntityAmountDoesNotExistException();}
                    if(valid==false){throw new InvalidRequestException();}    
                }
            }
        }
    }
    private boolean validRequestDonation(RequestDonation r, Beneficiary ben, double quantity){
        double requestedQuant = quantity;
        int benLevel = ben.getLevel(); //to level tou beneficiary 
        double qqf = 0; //quantity qualified for. Dhladi posotita pou dikaioutai o beneficiary analoga me to level tou
        if(r.getEntity().getIsMaterial()){
            switch(benLevel){
            case 1: qqf = r.getEntity().getLevel1(); break; 
            case 2: qqf = r.getEntity().getLevel2(); break;
            case 3: qqf = r.getEntity().getLevel3(); break;
            }
            boolean flag = false;
            boolean matRequested = false;
            RequestDonation rdReceived = null;
            //diatrexw tin receivedList gia na dw poso exei lavei idi o beneficiary apo to requested material
            for(RequestDonation rd : ben.getReceivedList().getRdEntities()){
                if(rd.getId()==r.getId()){
                    matRequested = true;  
                    rdReceived = rd;
                }
            } 
            //an den exei lavei tpt o beneficiary epistrefw true 
            if(ben.getReceivedList().getRdEntities().isEmpty()){
                if(requestedQuant<=qqf){flag = true;}
            }
            //an exei lavei to requested material kai i posotita pou zitaei + tin posotita po exei parei den kseperna to qqf return true
            else if(matRequested == true){
                if(rdReceived.getQuantity() + requestedQuant <= qqf){
                    flag = true;
                }
                else{flag = false;}
            }
            //an exei lavei kati alla oxi auto pou zitaei twra 
            else if(matRequested == false){
                if(requestedQuant <= qqf){flag = true;}
            }
            if(flag == true){return true;}
            else{return false;}
        }
        //an einai service
        else{return true;}
    }
    public RequestDonationList commit(Organization o, Beneficiary b){//throws EntityAmountDoesNotExistException,InvalidRequestException{
        boolean validAmount = false;
        boolean valid = false;
        boolean rdFound = false;
        boolean nonExistantAmount = false;
        boolean invalidReq = false;
        RequestDonationList requestsNotReceived = new RequestDonationList();
        //gia kathe Request tis requestsList diatrexw tin currentDonations gia na dw an yparxei
        for(RequestDonation r : b.getRequestsList().getRdEntities()){
            if(r.getEntity().getIsMaterial()==false){
                for(RequestDonation rd : o.getCurrentDonations().getRdEntities()){
                    if(r.getId()==rd.getId()){
                        rdFound = true;
                        if(r.getQuantity()<=rd.getQuantity()){validAmount = true;}
                    }
                    if(rdFound && validAmount){
                        double newQuant = rd.getQuantity() - r.getQuantity();
                        o.getCurrentDonations().modify(rd, newQuant); //afairw tin posotita pou phra apo to rd tou currentDonations 
                        b.addToReceivedList(o,r); //vazw to service stin received list 
                        break;
                    }
                    //alliws exceptions kai prosthiki antigrafou tou r stin lista requestsNotReceived 
                    else if(rdFound){
                        if(validAmount==false){
                            try{requestsNotReceived.add(o, (RequestDonation)r.clone());}
                            catch(EntityDoesNotExistException e){e.print();}
                            catch(EntityAmountDoesNotExistException e){e.print();}
                            catch(InvalidRequestException e){e.print();}
                            nonExistantAmount = true;
                        }
                        if(valid==false){
                            try{requestsNotReceived.add(o, (RequestDonation)r.clone());}
                            catch(EntityDoesNotExistException e){e.print();}
                            catch(EntityAmountDoesNotExistException e){e.print();}
                            catch(InvalidRequestException e){e.print();}
                            invalidReq = true;
                        }    
                    }
                    validAmount = false;
                    valid = false;
                    rdFound = false;
                }
            }
            validAmount = false;
            valid = false;
            rdFound = false;
            if(r.getEntity().getIsMaterial()){
                for(RequestDonation rd : o.getCurrentDonations().getRdEntities()){
                    if(rd.getId()==r.getId()){
                        rdFound = true;
                        if(r.getQuantity()<=rd.getQuantity()){validAmount = true;}
                        if(validRequestDonation(r, b)){valid = true;}
                    }
                    //an isxoyoun ta A, B apo tin ekfwnisi
                    if(rdFound && validAmount && valid){
                        double newQuant = rd.getQuantity() - r.getQuantity();
                        o.getCurrentDonations().modify(rd, newQuant); //afairw tin posotita pou phra apo to rd tou currentDonations 
                        b.addToReceivedList(o,r); //vazw to mat stin received list 
                        break;
                    }
                    //alliws exceptions kai prosthiki antigrafou tou r stin lista requestsNotReceived 
                    else if(rdFound){
                        if(validAmount==false){
                            try{
                                requestsNotReceived.add(o, (RequestDonation)r.clone());
                            }
                            catch(EntityDoesNotExistException e){e.print();}
                            catch(EntityAmountDoesNotExistException e){e.print();}
                            catch(InvalidRequestException e){e.print();}
                            nonExistantAmount = true;
                        }
                        if(valid==false){
                            try{requestsNotReceived.add(o, (RequestDonation)r.clone());}
                            catch(EntityDoesNotExistException e){e.print();}
                            catch(EntityAmountDoesNotExistException e){e.print();}
                            catch(InvalidRequestException e){e.print();}
                            invalidReq = true;
                        }    
                    }
                    rdFound = false;
                    valid = false;
                    validAmount = false;
                }
            }
            validAmount = false;
            valid = false;
            rdFound = false;
        }
        b.getRequestsList().reset(); //ta vgazw ola apo tin requests list
        if(nonExistantAmount){
            try{
                throw new EntityAmountDoesNotExistException();
            }
            catch(EntityAmountDoesNotExistException e){
                e.print();
                return requestsNotReceived;
            }
        }
        else if(invalidReq){
            try{
                throw new InvalidRequestException();
            }
            catch(InvalidRequestException e){
                e.print();
                return requestsNotReceived;
            }
        }
        else{
            return requestsNotReceived;
        }
    }
}