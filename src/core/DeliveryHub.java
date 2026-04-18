package core;

import java.util.ArrayList;
import people.MembershipType;
import java.util.Collections;

import people.Courier;
import people.Customer;
import people.HubPerson;

public class DeliveryHub {
    private String hubName;
    private ArrayList<HubPerson> people;
    private ArrayList<DeliveryOrder> orders;

    public DeliveryHub(String hubName) {
        this.hubName = hubName;
        this.people = new ArrayList<>();
        this.orders = new ArrayList<>();
    }

    public String getHubName() {
        return hubName;
    }

    // addPerson(HubPerson p)
    public void addPerson(HubPerson p) throws DeliveryHubException {
        for (HubPerson person : people) {
            if (person.getPersonId().equals(p.getPersonId())) {
                throw new DeliveryHubException("Person with ID " + p.getPersonId() + " already exists.");
            }
        } 
        people.add(p);
    }

    // addOrder(DeliveryOrder o)
    public void addOrder(DeliveryOrder o) throws DeliveryHubException {
        for (DeliveryOrder order : orders) {
            if (order.getOrderCode().equals(o.getOrderCode())) {
                throw new DeliveryHubException("Order with code " + o.getOrderCode() + " already exists.");
            }
            boolean isExistingActive = order.getStatus() != DeliveryStatus.DELIVERED && 
                                   order.getStatus() != DeliveryStatus.CANCELLED && 
                                   order.getStatus() != DeliveryStatus.RETURNED;
            if (isExistingActive && order.getParcel().getTrackingNumber() == o.getParcel().getTrackingNumber()){
                throw new DeliveryHubException("Tracking number " + o.getParcel().getTrackingNumber() + " is already active.");
            }
        }
        orders.add(o);
    }

    // findPersonById(String personId)
    public HubPerson findPersonById(String personId) {
        for (HubPerson p : people) {
            if (p.getPersonId().equals(personId)) {
                return p;
            }
        }
        return null;
    }

    // findOrderByCode(String orderCode)
    public DeliveryOrder findOrderByCode(String ordercode) {
        for ( DeliveryOrder o : orders) {
            if (o.getOrderCode().equals(ordercode)) {
                return o;
            }
        }
        return null;
    }
    
    // assignCourier(String orderCode, Courier c)
    public void assignCourier(String orderCode, Courier c) throws DeliveryHubException {
        DeliveryOrder order = findOrderByCode(orderCode);
        if(order == null){
            throw new DeliveryHubException("Order with code " + orderCode + " not found.");
        }
        if (c.getCurrentActiveOrder() >= c.getMaxActiveOrders()){
            throw new DeliveryHubException("Courier " + c.getName() + " has reached maximum active orders.");
        }
        order.assignCourier(c);
        c.increaseCurrentActiveOrder();
    }


    // Count customers
    public int countCustomers() {
        int count = 0; 
        for (HubPerson p : people) {
            if (p instanceof Customer) {
                count++;
            }
        }
        return count;
    }

    // Count couriers
    public int countCouriers() {
        int count = 0;
        for (HubPerson p : people) {
            if (p instanceof Courier) {
                count++;
            }
        }
        return count;
    }

    // Count active orders 
    public int countActiveOrders() {
        int count = 0;
        for (DeliveryOrder o : orders) {
            if (o.getStatus() != DeliveryStatus.DELIVERED && o.getStatus() != DeliveryStatus.CANCELLED && o.getStatus() != DeliveryStatus.RETURNED) {
                count++;
            }
        }
        return count;
    }

    // Open Revenue
    public double estimateOpenRevenue() {
        double total = 0;
        for (DeliveryOrder o : orders) {
            // Only calculate for open orders
            if (o.getStatus() != DeliveryStatus.DELIVERED && 
                o.getStatus() != DeliveryStatus.CANCELLED && 
                o.getStatus() != DeliveryStatus.RETURNED) {
                
                // If it has a final fee set, use it. Otherwise, estimate it from the parcel.
                if (o.getFinalFee() > 0) {
                    total += o.getFinalFee();
                } else if (o.getParcel() instanceof parcel.Chargeable) {
                    total += ((parcel.Chargeable) o.getParcel()).estimateDeliveryFee();
                }
            }
        }
        return total;
    }

    // Print All People
    public void printAllPeople() {
        for(HubPerson p : people) {
            System.out.println(p);
        }
    }

    // Print All Orders
    public void printAllOrders() {
        for(DeliveryOrder o : orders) {
            System.out.println(o);
        }
    }
    //Problem 5
    public void sortCourierbyWorkload(){
        ArrayList<Courier> couriers = new ArrayList<>();
        for (HubPerson p : people){
            if(p instanceof Courier){
                couriers.add((Courier) p);
            }
        }
        Collections.sort(couriers);
        for (Courier c : couriers){
            System.out.println(c);
        }
    }
    // Method returning the courier with the most active orders
    public Courier getTopWorkloadCourier() {
        Courier topCourier = null;
        int maxOrders = -1;
        
        for (HubPerson p : people) {
            if (p instanceof Courier) {
                Courier c = (Courier) p;
                if (c.getCurrentActiveOrder() > maxOrders) {
                    maxOrders = c.getCurrentActiveOrder();
                    topCourier = c;
                }
            }
        }
        return topCourier;
    }
    public void sortOrdersByUrgencyAndDate() {
    ArrayList<DeliveryOrder> sortedOrders = new ArrayList<>(this.orders);
    sortedOrders.sort((o1, o2) -> {
        String type1 = o1.getParcel().getParcelType();
        String type2 = o2.getParcel().getParcelType();

        // If o1 is Express and o2 is Standard, o1 comes FIRST
        if (type1.equals("Express") && type2.equals("Standard")) {
            return -1; 
        }
        else if (type1.equals("Standard") && type2.equals("Express")) {
            return 1; 
        }
        else {
            return o1.getCreatedDate().compareTo(o2.getCreatedDate());
        }
    });
    System.out.println("--- Orders Sorted by Urgency and Date ---");
    for (DeliveryOrder order : sortedOrders) {
        System.out.println(order);
    }
}
    public void printActiveOrders(){
        for (DeliveryOrder o : orders){
            // Compare using the DeliveryStatus enum, not Strings!
            if (o.getStatus() != DeliveryStatus.DELIVERED && 
                o.getStatus() != DeliveryStatus.CANCELLED && 
                o.getStatus() != DeliveryStatus.RETURNED ){
                
                System.out.println(o);
            }
        }
    }
    public void printCompletedOrders(){
        for (DeliveryOrder o : orders){
            if (o.getStatus() == DeliveryStatus.DELIVERED){
                System.out.println(o);
            }
        }
    }
    public void calculateTotalCompletedRevenue(){
        double sum = 0;
        for (DeliveryOrder o : orders){
            if (o.getStatus() == DeliveryStatus.DELIVERED){
                sum += o.getFinalFee();
            }
        }
        System.out.println("Total complete Revenue: " + sum);
    }
    public void printPremiumCustomerRevenue(){
        double sum = 0;
        for (DeliveryOrder o : orders){
            if (o.getStatus() == DeliveryStatus.DELIVERED && 
                o.getCustomer().getMembershipType() == MembershipType.Premium){
                sum += o.getFinalFee();
            }
        }
        System.out.println("Total Revenue from Premium Customers: " + sum);
    }
// Integrated method to generate the full management report
    // Integrated method to generate the full management report
    public void generateManagementReport() {
        System.out.println("          DELIVERY HUB MANAGEMENT REPORT          ");
        System.out.println("==================================================");
        
        // 1. Active and Completed Orders
        System.out.println("\n[1] ALL ACTIVE ORDERS");
        printActiveOrders();
        
        System.out.println("\n[2] ALL COMPLETED ORDERS");
        printCompletedOrders();
        
        // 3. Top Workload Courier
        System.out.println("\n[3] TOP WORKLOAD COURIER");
        Courier topCourier = getTopWorkloadCourier();
        if (topCourier != null) {
            System.out.println("Top Courier: " + topCourier.getName() + 
                               " | Active Orders: " + topCourier.getCurrentActiveOrder());
        } else {
            System.out.println("No couriers available or assigned.");
        }
        
        // 4. Sorted Orders
        System.out.println("\n[4] ORDERS BY URGENCY AND DATE");
        sortOrdersByUrgencyAndDate(); 
        
        // 5. Sorted Couriers
        System.out.println("\n[5] COURIERS BY ACTIVE WORKLOAD");
        sortCourierbyWorkload();

        // 6. Revenue Summary
        System.out.println("\n[6] REVENUE SUMMARY");
        System.out.println("Estimated Open Revenue: $" + estimateOpenRevenue());
        calculateTotalCompletedRevenue();
        
        // 7. Custom Management Report
        System.out.println("\n[7] CUSTOM REPORT: PREMIUM TIER INSIGHTS");
        printPremiumCustomerRevenue();
        
        System.out.println("\n==================================================");
    }
    @Override
    public String toString() {
        return "\n--------------------------------------------------------------------------------" +
               "\nDeliveryHub: " + hubName +
               "\nCustomers: " + countCustomers() +
               "\nCouriers: " + countCouriers() +
               "\nActive Orders: " + countActiveOrders() +
               "\nEstimated Revenue: " + estimateOpenRevenue() +
               "\n--------------------------------------------------------------------------------";
            } 
}
