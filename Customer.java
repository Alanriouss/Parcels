public class Customer extends HubPerson{
    private String membershipType;
    
    public Customer(String personId , String name, int phoneNumber, String membershipType){
        super(personId, name, phoneNumber);
        this.membershipType = membershipType;
    }
    // Getter & Setter
    public String getMembershipType(){return membershipType;}
    public void setMembershipType(String membershipType){
        this.membershipType = membershipType;
    }
    // Get Role & To String
    @Override
    public String getRole(){
        return "Customer";
    }
    public String toString(){
        return super.toString() + ",Membership Type: " + membershipType + " ,Role: " +getRole();
    }
}
