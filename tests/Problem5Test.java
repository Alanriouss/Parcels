package tests;

import core.DeliveryHub;
import core.DeliveryOrder;
import core.DeliveryHubException;
import people.Customer;
import people.Courier;
import people.MembershipType;
import parcel.StandardParcel;
import parcel.ExpressParcel;

public class Problem5Test {
    public static void main(String[] args) {
        System.out.println("=== PROBLEM 5: REPORTS, SORTING, AND CAPACITY ===");
        
        DeliveryHub hub = new DeliveryHub("Central Station Hub");
        
        Customer c1 = new Customer("C-01", "Alice", 123456789, MembershipType.Standard);
        Courier cr1 = new Courier("CR-01", "Bob (Bike)", 111, "Bike", 3, 1);
        Courier cr2 = new Courier("CR-02", "Charlie (Van)", 222, "Van", 5, 2);

        StandardParcel p1 = new StandardParcel(101, "Alice", "Eve", 2.0, "Zone A", false);
        ExpressParcel p2 = new ExpressParcel(102, "Alice", "Frank", 1.0, "Zone B", 1);
        
        try {
            hub.addPerson(c1);
            hub.addPerson(cr1);
            hub.addPerson(cr2);

            DeliveryOrder order1 = new DeliveryOrder("ORD-01-STD", c1, p1);
            DeliveryOrder order2 = new DeliveryOrder("ORD-02-EXP", c1, p2);
            
            hub.addOrder(order1);
            hub.addOrder(order2);

            // Assign courier 2 to both orders so he has the highest workload
            hub.assignCourier("ORD-01-STD", cr2);
            hub.assignCourier("ORD-02-EXP", cr2);

            // 1. Sorted order list (Express should come before Standard)
            System.out.println("\n--- Testing Order Sorting ---");
            hub.sortOrdersByUrgencyAndDate();

            // 2. Sorted courier list (Bob should come before Charlie because Charlie has 2 orders and Bob has 0)
            System.out.println("\n--- Testing Courier Workload Sorting ---");
            hub.sortCourierbyWorkload();

            // 3. Workload summary / Top courier
            System.out.println("\n--- Testing Top Workload Courier ---");
            Courier top = hub.getTopWorkloadCourier();
            if (top != null) {
                System.out.println("Top Courier is: " + top.getName() + " with " + top.getCurrentActiveOrder() + " active orders.");
            }

        } catch (DeliveryHubException e) {
            System.out.println("Hub Error: " + e.getMessage());
        }
    }
}