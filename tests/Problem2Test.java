package tests;

import parcel.StandardParcel;
import parcel.ExpressParcel;
import parcel.Chargeable;

public class Problem2Test {
    public static void main(String[] args) {
        System.out.println("=== PROBLEM 2: PARCEL HIERARCHY & FEE BEHAVIOR ===");
        
        // 1. Create one standard and one express parcel
        StandardParcel standard = new StandardParcel(1001, "Alice", "Eve", 2.0, "Zone A", true);
        ExpressParcel express = new ExpressParcel(1002, "Bob", "Frank", 1.5, "Zone B", 2);

        // 2. Print them and show their estimated fees using the Chargeable interface
        System.out.println(standard.toString());
        System.out.println("Standard Parcel Fee (via interface): $" + ((Chargeable) standard).estimateDeliveryFee());
        
        System.out.println();
        
        System.out.println(express.toString());
        System.out.println("Express Parcel Fee (via interface): $" + ((Chargeable) express).estimateDeliveryFee());
    }
}