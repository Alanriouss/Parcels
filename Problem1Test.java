public class Problem1Test {

    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("       TESTING CORE PEOPLE HIERARCHY     ");
        System.out.println("=========================================\n");

        // 1. Create at least 2 customers [cite: 110]
        // Assuming Constructor: Customer(personId, name, phoneNumber, membershipType)
        Customer customer1 = new Customer("CUS-101", "Nguyen Van A", 0123456, "Premium");
        Customer customer2 = new Customer("CUS-102", "Nguyen Van b", 1234567, "Standard");

        // 2. Create at least 2 couriers [cite: 111]
        // Assuming Constructor: Courier(personId, name, phoneNumber, vehicleType, maxActiveOrders, yearsOfService)
        Courier courier1 = new Courier("COR-2001", "Nguyen Van C", 1357924, "Motorcycle", 3, 1);
        Courier courier2 = new Courier("COR-2002", "Nguyen Van D", 2468101, "Van", 5, 4);

        // 3. Print them and show that getRole() works correctly 
        System.out.println("--- Testing Customer Objects ---");
        System.out.println("Expected Role: Customer | Actual Role: " + customer1.getRole());
        System.out.println(customer1.toString());
        System.out.println();
        
        System.out.println("Expected Role: Customer | Actual Role: " + customer2.getRole());
        System.out.println(customer2.toString());
        System.out.println("\n");

        System.out.println("--- Testing Courier Objects ---");
        System.out.println("Expected Role: Courier | Actual Role: " + courier1.getRole());
        System.out.println(courier1.toString());
        System.out.println();

        System.out.println("Expected Role: Courier | Actual Role: " + courier2.getRole());
        System.out.println(courier2.toString());
        System.out.println("\n");
    }
}