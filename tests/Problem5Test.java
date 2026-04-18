import ExpressParcel;
import StandardParcel;
import core.DeliveryHub;
import core.DeliveryHubException;
import core.DeliveryOrder;
import people.Customer;

public class Problem5Test {
    public static void main(String[] args) {
        System.out.println("=== PROBLEM 5: REPORTS AND SORTING TEST ===");

        try {
            DeliveryHub hub = new DeliveryHub("Smart Delivery");

            // Setup Data
            Customer cust = new Customer("CUS-99", "Phong", 123456, "Standard");
            Courier cour1 = new Courier("COR-01", "Bao", 111, "Van", 5, 2);
            Courier cour2 = new Courier("COR-02", "Cuong", 222, "Motorcycle", 5, 3);
            Courier cour3 = new Courier("COR-03", "Anh", 333, "Bike", 5, 1);
            
            hub.addPerson(cust);
            hub.addPerson(cour1);
            hub.addPerson(cour2);
            hub.addPerson(cour3);

            StandardParcel stdParcel1 = new StandardParcel(11, "X", "Y", 2.0, "Z1", false);
            ExpressParcel expParcel = new ExpressParcel(22, "X", "Z", 1.0, "Z2", 1);
            StandardParcel stdParcel2 = new StandardParcel(33, "A", "B", 5.0, "Z3", true);

            DeliveryOrder ord1 = new DeliveryOrder("O-1", cust, stdParcel1);
            ord1.setFinalFee(10.0);
            DeliveryOrder ord2 = new DeliveryOrder("O-2", cust, expParcel);
            ord2.setFinalFee(25.0);
            DeliveryOrder ord3 = new DeliveryOrder("O-3", cust, stdParcel2);
            ord3.setFinalFee(15.0);

            hub.addOrder(ord1);
            hub.addOrder(ord2);
            hub.addOrder(ord3);

            // Assign couriers to simulate workload
            hub.assignCourier("O-1", cour1);
            hub.assignCourier("O-2", cour1);
            hub.assignCourier("O-3", cour3);

            // CALL THE INTEGRATED REPORT GENERATOR
            hub.generateManagementReport();

        } catch (DeliveryHubException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}