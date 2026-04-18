import ExpressParcel;
import StandardParcel;
import core.DeliveryHubException;
import core.DeliveryOrder;
import people.Customer;

public class Problem3Test {
    public static void main(String[] args) {
        System.out.println("=== PROBLEM 3: DELIVERY ORDER STATE MANAGEMENT TEST ===\n");
        Customer customer = new Customer("CUS-01", "Alice", 123456789, "Standard");
        Courier courier = new Courier("COR-01", "Bob", 987654321, "Van", 5, 2);
        
        StandardParcel parcel1 = new StandardParcel(101, "Sender A", "Alice", 2.0, "Zone 1", false);
        ExpressParcel parcel2 = new ExpressParcel(102, "Sender B", "Alice", 1.5, "Zone 1", 1);
        StandardParcel parcel3 = new StandardParcel(103, "Sender C", "Alice", 5.0, "Zone 2", true);

        try {
            System.out.println("--- 1. Creating 3 Orders ---");
            DeliveryOrder order1 = new DeliveryOrder("ORD-001", customer, parcel1);
            DeliveryOrder order2 = new DeliveryOrder("ORD-002", customer, parcel2);
            DeliveryOrder order3 = new DeliveryOrder("ORD-003", customer, parcel3);
            
            System.out.println("Order 1 Status: " + order1.getStatus());
            System.out.println("Order 2 Status: " + order2.getStatus());
            System.out.println("Order 3 Status: " + order3.getStatus());

            System.out.println("\n--- 2. Demonstrating Valid Status Changes (Order 1) ---");
            System.out.println("Initial Status: " + order1.getStatus());
            
            order1.assignCourier(courier);
            System.out.println("After assignCourier(): " + order1.getStatus());
            
            order1.startDelivery();
            System.out.println("After startDelivery(): " + order1.getStatus());
            
            order1.markAsDelivered();
            System.out.println("After markAsDelivered(): " + order1.getStatus());
            
            System.out.println("Result: Valid status changes successful!");

            System.out.println("\n--- 3. Demonstrating Invalid Status Change & Caught Exception (Order 2) ---");
            System.out.println("Order 2 Current Status: " + order2.getStatus());
            
            try {
                System.out.println("Attempting to start delivery without assigning a courier...");
                order2.startDelivery(); 
                
                System.out.println("FAIL: Exception was not thrown!"); 
            } catch (DeliveryHubException e) {
                System.out.println("Result -> Caught Expected Exception: " + e.getMessage());
            }

            System.out.println("\n--- 4. Additional Invalid Transition Test (Order 1) ---");
            try {
                System.out.println("Attempting to cancel an order that is already DELIVERED...");
                order1.cancel();
            } catch (DeliveryHubException e) {
                System.out.println("Result -> Caught Expected Exception: " + e.getMessage());
            }

        } catch (DeliveryHubException e) {
            System.out.println("Unexpected Error during setup: " + e.getMessage());
        }

        System.out.println("\n=== PROBLEM 3 TEST COMPLETED ===");
    }
}