package tests;

import core.DeliveryOrder;
import core.DeliveryHubException;
import people.Customer;
import people.Courier;
import people.MembershipType;
import parcel.StandardParcel;
import parcel.ExpressParcel;

public class Problem3Test {
    public static void main(String[] args) {
        System.out.println("=== PROBLEM 3: DELIVERY ORDER STATE MANAGEMENT ===");
        
        Customer customer = new Customer("C-01", "Alice", 123456789, MembershipType.Standard);
        Courier courier = new Courier("CR-01", "Charlie", 111111111, "Van", 3, 2);
        
        StandardParcel p1 = new StandardParcel(101, "Alice", "Eve", 2.0, "Zone A", false);
        ExpressParcel p2 = new ExpressParcel(102, "Alice", "Frank", 1.0, "Zone B", 1);
        StandardParcel p3 = new StandardParcel(103, "Alice", "George", 5.0, "Zone C", true);

        try {
            // 1. Create 3 orders
            DeliveryOrder order1 = new DeliveryOrder("ORD-001", customer, p1);
            DeliveryOrder order2 = new DeliveryOrder("ORD-002", customer, p2);
            DeliveryOrder order3 = new DeliveryOrder("ORD-003", customer, p3);

            // 2. Demonstrate valid status changes
            System.out.println("\n--- Valid Transitions (Order 1) ---");
            System.out.println("Status after creation: " + order1.getStatus());
            order1.assignCourier(courier);
            System.out.println("Status after assignment: " + order1.getStatus());
            order1.setFinalFee(p1.estimateDeliveryFee());
            order1.startDelivery();
            System.out.println("Status during delivery: " + order1.getStatus());
            order1.markAsDelivered();
            System.out.println("Status after completion: " + order1.getStatus());

            // 3. Demonstrate invalid status change & caught exception
            System.out.println("\n--- Invalid Transition (Order 2) ---");
            try {
                System.out.println("Attempting to mark order as delivered before assigning a courier...");
                order2.markAsDelivered(); // Should fail
            } catch (DeliveryHubException e) {
                System.out.println("SUCCESS - Caught exception: " + e.getMessage());
            }

        } catch (DeliveryHubException e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }
}