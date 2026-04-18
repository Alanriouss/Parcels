package people;
public class Customer extends HubPerson{
    private MembershipType membershipType;
    
    public Customer(String personId , String name, int phoneNumber, MembershipType membershipType){
        super(personId, name, phoneNumber);
        this.membershipType = membershipType;
    }
    // Getter & Setter
    public MembershipType getMembershipType(){return membershipType;}
    public void setMembershipType(MembershipType membershipType){
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
