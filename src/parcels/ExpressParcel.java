public class ExpressParcel extends Parcel implements Chargeable {
    private int priorityLevel;
    // Contructor
    public ExpressParcel(int trackingNumber,String senderName,String receiverName,double weight,String destinationZone,int priorityLevel){
        super(trackingNumber, senderName, receiverName, weight, destinationZone);
        this.priorityLevel =  priorityLevel; // 1 =Urgern , 2 = Very Urgent , 3 = Critical
    }
    // Getter & Setter
    public int getPriorityLevel(){return priorityLevel;}
    public void setPriorityLevel(int priorityLevel){
        this.priorityLevel = priorityLevel;
    }
    public double estimateDeliveryFee(){
        double basePerKg = 15;
        double fee = getWeight() * basePerKg + (15 * priorityLevel);
        return fee;
    }
    @Override
    public String getParcelType(){
        return "Express";
    }
    public String toString(){
        return super.toString() + "Parcel type: " + getParcelType() + " with the estimate fee of: " + estimateDeliveryFee();
    }
}
