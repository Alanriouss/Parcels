public class Problem3Test {
    public static void main(String[] args) {

        System.out.println("=== PROBLEM 3 TEST ===");

        Customer customer1 = new Customer("CUS-101", "Nguyen Van A", 123456, "Premium");
        Courier courier1 = new Courier("COR-2001", "Nguyen Van C", 1357924, "Motorcycle", 3, 1);

        StandardParcel standard1 = new StandardParcel(1001, "Alice Smith", "Bob Jones", 2.5, "Zone A", true);
        StandardParcel standard2 = new StandardParcel(1002, "Charlie Brown", "Diana Prince", 5.0, "Zone B", false);
        ExpressParcel express1 = new ExpressParcel(2001, "Eve Davis", "Frank Miller", 1.2, "Zone A", 1);

        DeliveryOrder order1 = null;
        DeliveryOrder order2 = null;
        DeliveryOrder order3 = null;

        // =====================================================
        // ✔ Requirement 1: Create at least 3 orders
        // =====================================================
        try {
            order1 = new DeliveryOrder("ORD-001", customer1, standard1);
            order2 = new DeliveryOrder("ORD-002", customer1, standard2);
            order3 = new DeliveryOrder("ORD-003", customer1, express1);

            System.out.println("✅ Requirement 1 Passed: 3 orders created");

        } catch (DeliveryHubException e) {
            System.out.println("❌ Failed to create orders: " + e.getMessage());
            return;
        }

        // =====================================================
        // ✔ Requirement 2: Valid status change
        // =====================================================
        try {
            order1.assignCourier(courier1);
            order1.startDelivery();
            order1.markAsDelivered();

            System.out.println("✅ Requirement 2 Passed: Valid status flow");

        } catch (DeliveryHubException e) {
            System.out.println("❌ Valid flow failed: " + e.getMessage());
        }

        // =====================================================
        // ✔ Requirement 3 & 4: Invalid + caught exception
        // =====================================================
        try {
            order2.startDelivery(); // chưa assign courier → sai
            System.out.println("❌ FAIL: Invalid transition allowed");

        } catch (DeliveryHubException e) {
            System.out.println("✅ Requirement 3 & 4 Passed: " + e.getMessage());
        }

        System.out.println("\n=== TEST COMPLETED ===");
    }
}