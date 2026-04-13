public class Problem2Test {

    public static void main(String[] args) {

        StandardParcel standard1 = new StandardParcel(1001, "Alice Smith", "Bob Jones", 2.5, "Zone A", true);
        StandardParcel standard2 = new StandardParcel(1002, "Charlie Brown", "Diana Prince", 5.0, "Zone B", false);

        ExpressParcel express1 = new ExpressParcel(2001, "Eve Davis", "Frank Miller", 1.2, "Zone A", 1); // High priority
        ExpressParcel express2 = new ExpressParcel(2002, "Grace Hopper", "Alan Turing", 8.0, "Zone C", 3); // Critical priority

        System.out.println("--- Testing Standard Parcels ---");
        System.out.println(standard1.toString());
        System.out.println("Type: " + standard1.getParcelType());
        System.out.println("Estimated Fee: $" + standard1.estimateDeliveryFee());
        System.out.println();

        System.out.println(standard2.toString());
        System.out.println("Type: " + standard2.getParcelType());
        System.out.println("Estimated Fee: $" + standard2.estimateDeliveryFee());
        System.out.println("\n");

        System.out.println("--- Testing Express Parcels ---");
        System.out.println(express1.toString());
        System.out.println("Type: " + express1.getParcelType());
        System.out.println("Estimated Fee: $" + express1.estimateDeliveryFee());
        System.out.println();

        System.out.println(express2.toString());
        System.out.println("Type: " + express2.getParcelType());
        System.out.println("Estimated Fee: $" + express2.estimateDeliveryFee());
        System.out.println("\n");
    }
}