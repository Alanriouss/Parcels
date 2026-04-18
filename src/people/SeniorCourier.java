package people;
public class SeniorCourier extends Courier {
    private double bonusRate;
    public SeniorCourier(String personId , String name, int phoneNumber, String vehicleType,int maxActiveOrders,int yearsOfService, double bonusRate){
        super(personId, name, phoneNumber, vehicleType, maxActiveOrders, yearsOfService);
        this.bonusRate = bonusRate;
        this.setMaxActiveOrders(5);
    }
    // Getter & Setters
    public double getBonusRate(){return bonusRate;}
    public void setBonusRate(double bonusRate){
        this.bonusRate = bonusRate;
    }
    // Override
    @Override
    public String getRole(){
        return "Senior Courier";
    }
    @Override
    public String toString(){
        return super.toString() + " ,Bonus Rate: " + bonusRate + " ,Role: " + getRole();
    }
}