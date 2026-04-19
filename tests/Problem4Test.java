package tests;

import core.DeliveryHub;
import core.DeliveryOrder;
import core.DeliveryHubException;
import people.Customer;
import people.Courier;
import people.MembershipType;
import parcel.StandardParcel;

public class Problem4Test {
    public static void main(String[] args) {
        System.out.println("=== PROBLEM 4: DELIVERY HUB MANAGEMENT ===");
        
        // 1. Create one delivery hub
        DeliveryHub hub = new DeliveryHub("Central Station Hub");

        Customer cust = new Customer("C-01", "Alice", 123456789, MembershipType.Standard);
        Courier cour = new Courier("CR-01", "Charlie", 111111111, "Van", 3, 2);
        StandardParcel parcel = new StandardParcel(101, "Alice", "Eve", 2.0, "Zone A", false);

        try {
            // 2. Add people
            hub.addPerson(cust);
            hub.addPerson(cour);
            System.out.println("People added successfully.");

            // 3. Add orders
            DeliveryOrder order = new DeliveryOrder("ORD-001", cust, parcel);
            hub.addOrder(order);
            System.out.println("Order added successfully.");

            // 4. Assign couriers
            hub.assignCourier("ORD-001", cour);
            System.out.println("Courier assigned successfully.");

            // 5. Print the hub summary
            System.out.println("\n--- Hub Summary ---");
            System.out.println(hub.toString());
            
        } catch (DeliveryHubException e) {
            System.out.println("Hub Error: " + e.getMessage());
        }
    }
}