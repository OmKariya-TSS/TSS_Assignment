import com.tss.controller.CustomerController;

public class Main {
    public static void main(String[] args) {
        CustomerController customerController = new CustomerController();
        customerController.readAllCustomers();
    }
}
