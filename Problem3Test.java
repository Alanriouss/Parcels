public class Problem3Test {
    public static void main(String[] args) {
        System.out.println("--- PROBLEM 3: STRICT ASSIGNMENT TEST ---");

        try {

            Customer customer1 = new Customer("CUS-101", "Nguyen Van A", 123456, "Premium");
            Courier courier1 = new Courier("COR-2001", "Nguyen Van C", 1357924, "Motorcycle", 3, 1);
            StandardParcel standard1 = new StandardParcel(1001, "Alice Smith", "Bob Jones", 2.5, "Zone A", true);
            StandardParcel standard2 = new StandardParcel(1002, "Charlie Brown", "Diana Prince", 5.0, "Zone B", false);
            ExpressParcel express1 = new ExpressParcel(2001, "Eve Davis", "Frank Miller", 1.2, "Zone A", 1);
            
            // Requirement 1: Create at least 3 orders
            DeliveryOrder order1 = new DeliveryOrder("ORD-001", customer1, standard1);
            DeliveryOrder order2 = new DeliveryOrder("ORD-002", customer1, standard2);
            DeliveryOrder order3 = new DeliveryOrder("ORD-003", customer1, express1);
            System.out.println("✅ Requirement 1 Passed: 3 Orders created successfully.");

            // Requirement 2: Demonstrate valid status changes (Sự thay đổi trạng thái hợp lệ)
            order1.assignCourier(courier1);      
            order1.startDelivery();               
            order1.markAsDelivered();             
            System.out.println("✅ Requirement 2 Passed: Order 1 completed a valid workflow.");

            // Requirement 3 & 4: Demonstrate one invalid status change & one caught exception
            System.out.println("\nTesting Invalid Transition ...");
            
            // Attempting to move Order 2 to OUT_FOR_DELIVERY without assigning a courier first
            // We use startDelivery() here because it throws an error if it's not ASSIGNED yet.
            order2.startDelivery(); 
            
            // If the code reaches here, your validation failed
            System.out.println("❌ FAIL: System allowed an invalid transition!");

        } catch (DeliveryHubException e) {
            System.out.println("✅ Requirement 3 & 4 Passed: Caught Exception -> " + e.getMessage());
        }
    }
}