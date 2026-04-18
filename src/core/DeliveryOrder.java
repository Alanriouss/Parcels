package core;

import people.Customer;
import people.Courier;
import parcel.Parcel;
import java.time.LocalDate;
public class DeliveryOrder   {
    private final String orderCode;
    private final Customer customer;
    private final Parcel parcel;
    private Courier assignedCourier;
    private DeliveryStatus status;
    private final LocalDate createdDate;
    private int deliveryAttempts;
    private double finalFee;

    public DeliveryOrder(String orderCode, Customer customer, Parcel parcel ) throws DeliveryHubException {
        if (orderCode == null || orderCode.isEmpty()) {
            throw new DeliveryHubException("Order code cannot be empty");
        }
        if (customer == null || parcel == null) {
            throw new DeliveryHubException("Customer or Parcel cannot be null");
        }

        this.orderCode = orderCode;
        this.customer = customer;
        this.parcel = parcel;
        this.createdDate = LocalDate.now();
        this.status = DeliveryStatus.CREATED;
        this.deliveryAttempts = 0;
        this.finalFee = 0;
        this.assignedCourier = null;
    }

    // GETTERS 
    public String getOrderCode() { return orderCode; }
    public Customer getCustomer() { return customer; }
    public Parcel getParcel() { return parcel; }
    public LocalDate getCreatedDate() { return createdDate; }
    public DeliveryStatus getStatus() { return status; }
    public Courier getAssignedCourier() { return assignedCourier; }
    public int getDeliveryAttempts() { return deliveryAttempts; }
    public double getFinalFee() { return finalFee; }

  
    private boolean isFinalState() {
        return status == DeliveryStatus.DELIVERED || status == DeliveryStatus.CANCELLED || status == DeliveryStatus.RETURNED;
    }

    public void assignCourier(Courier courier) throws DeliveryHubException {
        if (courier == null) {
            throw new DeliveryHubException("Courier cannot be null");
        }
        if (isFinalState()) {
            throw new DeliveryHubException("Cannot assign courier in final state");
        }
        if (this.assignedCourier != null) {
            throw new DeliveryHubException("Courier already assigned");
        }
        this.assignedCourier = courier;
        this.status = DeliveryStatus.ASSIGNED;
    }

    public void startDelivery() throws DeliveryHubException {
        if (status != DeliveryStatus.ASSIGNED) {
            throw new DeliveryHubException("Order must be ASSIGNED before delivery");
        }
        this.status = DeliveryStatus.OUT_FOR_DELIVERY;
    }

    public void markAsDelivered() throws DeliveryHubException {
        if (status != DeliveryStatus.OUT_FOR_DELIVERY) {
            throw new DeliveryHubException("Order must be OUT_FOR_DELIVERY before marking delivered");
        }
        this.status = DeliveryStatus.DELIVERED;
        if (this.assignedCourier != null){
            this.assignedCourier.decreaseCurrentActiveOrder();
        }
    }

    public void cancel() throws DeliveryHubException {
        if (isFinalState()) {
            throw new DeliveryHubException("Order already finalized");
        }
        this.status = DeliveryStatus.CANCELLED;
        if (this.assignedCourier != null){
            this.assignedCourier.decreaseCurrentActiveOrder();
        }
    }

    public void increaseDeliveryAttempts() throws DeliveryHubException {
        if (isFinalState()) {
            throw new DeliveryHubException("Cannot update attempts in final state");
        }
        this.deliveryAttempts++;
    }

    public void setFinalFee(double fee) throws DeliveryHubException {
        if (fee < 0) {
            throw new DeliveryHubException("Final fee cannot be negative");
        }
        if (status == DeliveryStatus.OUT_FOR_DELIVERY || isFinalState()) {
            throw new DeliveryHubException("Cannot change fee during or after delivery");
        }
        if(customer.getMembershipType() == people.MembershipType.Premium){
            fee *= 0.9; // 10% discount for GOLD members
        }
        this.finalFee = fee;
    }

    @Override
    public String toString() {
        return  "\n--------------------------------------------------------------------------------" +
                "\nOderCode: " + orderCode +
                "\nCustomer: " + customer.getName() +
                "\nParcel: " + parcel.getParcelType() +
                "\nStatus: " + status +
                "\nCreated Date: " + createdDate +
                "\nDelivery Attempts: " + deliveryAttempts +
                "\nFinal Fee: " + finalFee +
                "\nAssigned Courier: " + (assignedCourier != null ? assignedCourier.getName() : "Unassigned") + 
                "\n--------------------------------------------------------------------------------";
            }
}