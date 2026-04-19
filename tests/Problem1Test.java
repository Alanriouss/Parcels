package tests;

import people.Customer;
import people.Courier;
import people.MembershipType;

public class Problem1Test {
    public static void main(String[] args) {
        System.out.println("=== PROBLEM 1: CORE PEOPLE HIERARCHY ===");
        
        // 1. Create 2 customers
        Customer c1 = new Customer("C-01", "Alice Smith", 123456789, MembershipType.Standard);
        Customer c2 = new Customer("C-02", "Bob Jones", 987654321, MembershipType.Premium);
        
        // 2. Create 2 couriers
        Courier cr1 = new Courier("CR-01", "Charlie Bike", 111222333, "Bicycle", 3, 2);
        Courier cr2 = new Courier("CR-02", "Diana Van", 444555666, "Van", 5, 5);

        // 3. Print them and show getRole()
        System.out.println(c1.toString() + " | Role: " + c1.getRole());
        System.out.println(c2.toString() + " | Role: " + c2.getRole());
        System.out.println(cr1.toString() + " | Role: " + cr1.getRole());
        System.out.println(cr2.toString() + " | Role: " + cr2.getRole());
    }
}