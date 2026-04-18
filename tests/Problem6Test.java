package tests;

import core.*;
import people.*;
import parcel.*;

public class Problem6Test {
    public static void main(String[] args) {
        DeliveryHub hub = new DeliveryHub("Central City Hub");

        System.out.println("=== 1. Valid Person Creation ===");
        Customer standardCust = new Customer("C001", "Alice", 123456789, MembershipType.Standard);
        Customer premiumCust = new Customer("C002", "Bob", 987654321, MembershipType.Premium);
        Courier normalCourier = new Courier("CR01", "Charlie", 111222333, "Bike", 3, 2);
        SeniorCourier seniorCourier = new SeniorCourier("CR02", "Diana", 444555666, "Van", 5, 10, 1.5);

        try {
            hub.addPerson(standardCust);
            hub.addPerson(premiumCust);
            hub.addPerson(normalCourier);
            hub.addPerson(seniorCourier);
            System.out.println("People added successfully.");
        } catch (DeliveryHubException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== 2. Duplicate ID Rejection ===");
        try {
            Customer duplicateCust = new Customer("C001", "Alice Duplicate", 000000000, MembershipType.Standard);
            hub.addPerson(duplicateCust);
        } catch (DeliveryHubException e) {
            System.out.println("SUCCESS - Caught expected exception: " + e.getMessage());
        }

        System.out.println("\n=== 3. Valid Order Creation ===");
        StandardParcel p1 = new StandardParcel(1001, "Alice", "Eve", 2.5, "Zone A", false); // Base Fee: 12.5
        ExpressParcel p2 = new ExpressParcel(1002, "Bob", "Frank", 1.0, "Zone B", 1); // Base Fee: 15 + 15 = 30.0
        StandardParcel p3 = new StandardParcel(1003, "Alice", "George", 5.0, "Zone C", true); // Base Fee: 25 + 15 = 40.0
        StandardParcel p4 = new StandardParcel(1004, "Bob", "Hank", 1.0, "Zone D", false);

        DeliveryOrder order1 = null, order2 = null, order3 = null, order4 = null;
        try {
            order1 = new DeliveryOrder("O-001", standardCust, p1);
            order2 = new DeliveryOrder("O-002", premiumCust, p2);
            order3 = new DeliveryOrder("O-003", standardCust, p3);
            order4 = new DeliveryOrder("O-004", premiumCust, p4);

            hub.addOrder(order1);
            hub.addOrder(order2);
            hub.addOrder(order3);
            hub.addOrder(order4);
            System.out.println("Orders created and added successfully.");
        } catch (DeliveryHubException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== 4. Duplicate Order / Tracking Rejection ===");
        try {
            DeliveryOrder duplicateOrder = new DeliveryOrder("O-001", standardCust, p1);
            hub.addOrder(duplicateOrder);
        } catch (DeliveryHubException e) {
            System.out.println("SUCCESS - Caught expected exception: " + e.getMessage());
        }

        System.out.println("\n=== 5. Valid Courier Assignment ===");
        try {
            hub.assignCourier("O-001", normalCourier);
            hub.assignCourier("O-002", normalCourier);
            hub.assignCourier("O-003", normalCourier);
            System.out.println("Couriers assigned successfully.");
        } catch (DeliveryHubException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== 6. Courier-Capacity Rejection ===");
        try {
            // Normal courier has max 3 active orders, assigning a 4th should fail
            hub.assignCourier("O-004", normalCourier);
        } catch (DeliveryHubException e) {
            System.out.println("SUCCESS - Caught expected exception: " + e.getMessage());
        }

        System.out.println("\n=== 7. Invalid Status Transition Rejection ===");
        try {
            // Trying to mark an order as delivered before it is "OUT_FOR_DELIVERY"
            order4.markAsDelivered();
        } catch (DeliveryHubException e) {
            System.out.println("SUCCESS - Caught expected exception: " + e.getMessage());
        }

        System.out.println("\n=== 8. Premium-Customer Discount Behavior & Valid Delivery Flow ===");
        try {
            // Process Order 1 (Standard Customer)
            double fee1 = ((Chargeable) order1.getParcel()).estimateDeliveryFee();
            order1.setFinalFee(fee1); // Set fee FIRST
            order1.startDelivery();   // Then start delivery
            order1.markAsDelivered();
            System.out.println("Standard Customer Final Fee: $" + order1.getFinalFee() + " (Expected $12.5)");

            // Process Order 2 (Premium Customer)
            double fee2 = ((Chargeable) order2.getParcel()).estimateDeliveryFee(); 
            order2.setFinalFee(fee2); // Set fee FIRST (Should automatically apply 10% discount)
            order2.startDelivery();   // Then start delivery
            order2.markAsDelivered();
            System.out.println("Premium Customer Final Fee: $" + order2.getFinalFee() + " (Expected $27.0 from $30.0 base)");

            // Process Order 3
            double fee3 = ((Chargeable) order3.getParcel()).estimateDeliveryFee(); 
            order3.setFinalFee(fee3); 
            order3.startDelivery();

            // Assign Order 4 to the Senior Courier so it isn't orphaned
            hub.assignCourier("O-004", seniorCourier);
            
        } catch (DeliveryHubException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== 9. Generated Reports (Sorted lists, Revenue, etc.) ===");
        // This will print the updated report you rewrote previously
        hub.generateManagementReport();
    }
}