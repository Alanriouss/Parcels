
abstract class HubPerson{
    private String personId;
    private String name;
    private int phoneNumber;
    // Contructor
    public HubPerson(String personId , String name, int phoneNumber){
        this.personId = personId;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
    // Getter & Setter
    public String getPersonId(){return personId;}
    public void setPersonId(String personId){
        this.personId = personId;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getPhoneNumber(){
        return phoneNumber;
    }
    public void setPhoneNumber(int phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    // Get Role & Override
    public abstract String getRole();
    @Override
    public String toString(){
        return "Name: "+ name +" ,Id: " + personId+" ,Phone number: "+ phoneNumber+ " ";
    }

}