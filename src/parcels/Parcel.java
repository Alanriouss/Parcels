abstract class Parcel {
    private int trackingNumber;
    private String senderName;
    private String receiverName;
    private double weight;
    private String destinationZone;
    // ConStructor
    public Parcel(int trackingNumber,String senderName,String receiverName,double weight,String destinationZone){
        this.trackingNumber = trackingNumber;
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.weight = weight;
        this.destinationZone = destinationZone;
    }
    // Getter & Setter
    public int getTrackingNumber(){return trackingNumber;}
    public void setTrackingNumber(int trackingNumber){
        this.trackingNumber = trackingNumber;
    }
    public String getSenderName(){return senderName;}
    public void setSenderName(String senderName){
        this.senderName = senderName;
    }
    public String getReceiverName(){
        return receiverName;
    }
    public void setReceiverName(String receiverName){
        this.receiverName = receiverName;
    }
    public double getWeight(){
        return weight;
    }
    public void setWeight(double weight){
        this.weight = weight;
    }
    public String getDestinationZone(){
        return destinationZone;
    }
    public void setDestinationZone(String destinationZone){
        this.destinationZone = destinationZone;
    }
    // abstract and Override
    public abstract String getParcelType();
    @Override
    public String toString(){
        return "Parcel with number: " + trackingNumber + " sent by " + senderName + " to " + receiverName + " .Parcel weight: " + weight +" ,Send to : " + destinationZone+ " .";
    }
}
