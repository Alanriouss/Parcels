public class Problem4Test {
    public static void main(String[] args) {
        System.out.println("--- PROBLEM 4: STRICT ASSIGNMENT TEST ---");

        try {
            // Requirement 1: Create one delivery hub
            DeliveryHub vungTauHub = new DeliveryHub("Vung Tau Smart Logistics");
            System.out.println("✅ Hub Created!");

            // Setup Data 
            Customer premiumCust = new Customer("CUS-01", "Dung", 1234567, "Premium");
            Customer standardCust = new Customer("CUS-02", "Minh", 7654321, "Standard");
            Courier courier1 = new Courier("COR-01", "Bao", 999888, "Van", 3, 2);
            
            // Using anonymous classes for abstract Parcel 
            Parcel parcel1 = new Parcel(101, "Sender A", "Receiver B", 2.0, "Zone 1") {
                @Override public String getParcelType() { return "Standard"; }
            };
            Parcel parcel2 = new Parcel(102, "Sender C", "Receiver D", 1.5, "Zone 2") {
                @Override public String getParcelType() { return "Express"; }
            };

            // Requirement 2: Add people and orders 
            vungTauHub.addPerson(premiumCust);
            vungTauHub.addPerson(standardCust);
            vungTauHub.addPerson(courier1);

            DeliveryOrder order1 = new DeliveryOrder("ORD-001", premiumCust, parcel1);
            order1.setFinalFee(50.5);
            DeliveryOrder order2 = new DeliveryOrder("ORD-002", standardCust, parcel2);
            order2.setFinalFee(30.5);
            
            vungTauHub.addOrder(order1);
            vungTauHub.addOrder(order2);
            System.out.println("✅ People and Orders added successfully.");

            // Requirement 3: Assign couriers (
            vungTauHub.assignCourier("ORD-001", courier1);
            System.out.println("✅ Courier assigned to ORD-001.");

            // Requirement 4: Print the hub summary 
            System.out.println("\n===== HUB SUMMARY =====");
            
            System.out.println("Total Couriers: " + vungTauHub.countCouriers());
            System.out.println("Active Orders: " + vungTauHub.countActiveOrders());
            System.out.println("Estimated Open Revenue: $" + vungTauHub.estimateOpenRevenue());
            
            System.out.println("\n--- ALL PEOPLE IN HUB ---");
            vungTauHub.printAllPeople();
            
            System.out.println("\n--- ALL ORDERS IN HUB ---");
            vungTauHub.printAllOrders();

        } catch (DeliveryHubException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}