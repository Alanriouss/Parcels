public class StandardParcel extends Parcel implements Chargeable {
    private boolean isFragile;
    public StandardParcel(int trackingNumber,String senderName,String receiverName,double weight,String destinationZone,boolean isFragile){
        super(trackingNumber, senderName, receiverName, weight, destinationZone);
        this.isFragile = isFragile;
    }
    public boolean getFragile(){
        return isFragile;
    }
    public void setFragile(boolean isFragile){
        this.isFragile = isFragile;
    }
    public double estimateDeliveryFee(){
        double baseFeePerKg = 5.0;
        double fee = getWeight() * baseFeePerKg;
        if (isFragile){
            fee += 15;
        }
        return fee;
    }
    @Override
    public String getParcelType(){
        return "Standard";
    }
    public String toString(){
        return super.toString() + "Parcel type: " + getParcelType() + " with the estimate fee of: " + estimateDeliveryFee();
    }
}
