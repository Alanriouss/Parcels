package people;
public class Courier extends HubPerson implements Comparable<Courier>{
    private String vehicleType;
    private int maxActiveOrders;
    private int yearsOfService;
    private int currentActiveOrder;
    public Courier(String personId , String name, int phoneNumber, String vehicleType,int maxActiveOrders,int yearsOfService){
        super(personId, name, phoneNumber);
        this.vehicleType = vehicleType;
        this.maxActiveOrders = 3;
        this.yearsOfService = yearsOfService;
        this.currentActiveOrder = 0;
    }
    // Getter & Setters
    public String getVehicleType(){return vehicleType;}
    public void setVehicleType(String vehicleType){
        this.vehicleType = vehicleType;
    }
    public int getMaxActiveOrders(){return maxActiveOrders;}
    public void setMaxActiveOrders(int maxActiveOrders){
        this.maxActiveOrders = maxActiveOrders;
    }
    public int getYearsOfService(){return yearsOfService;}
    public void setYearsOfService(int yearsOfService){
        this.yearsOfService = yearsOfService;
    }
    public int getCurrentActiveOrder(){return currentActiveOrder;}
    public void increaseCurrentActiveOrder(){
        this.currentActiveOrder++;
    }
    public void decreaseCurrentActiveOrder(){
        if (this.currentActiveOrder > 0){
            this.currentActiveOrder--; 
        }
    }
    // Override
    @Override
    public String getRole(){
        return "Courier";
    }
    public String toString(){
        return super.toString() + ",Vehicle Type: " + vehicleType + " ,Active Orders: " + maxActiveOrders + " ,Years of Service: " + yearsOfService + " ,Role: " + getRole();
    }
    // Compare Courier
    @Override
    public int compareTo(Courier other){
        int workloadCompare = Integer.compare(this.currentActiveOrder, other.currentActiveOrder);
        if (workloadCompare == 0){
            return this.getName().compareTo(other.getName());
        }
        return workloadCompare;
    }
}
