package Main;

import java.util.Scanner;

public class Customer {
    private String customerName;
    private String customerMail;

    Customer(String customerName, String customerMail) {
        this.customerName = customerName;
        this.customerMail = customerMail;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerMail() {
        return customerMail;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerMail(String customerMail) {
        if (isValidMail(customerMail) && isValidDot(customerMail)) {
            this.customerMail = customerMail;
        } else {
            System.out.println("INVALID!!! (must contain '@' and '.com' or '.dk')");
        }
    }

    public boolean isValidMail(String customerMail) {
        return customerMail.contains("@");
    }

    public boolean isValidDot(String customerMail){
        return customerMail.contains(".");
    }

}
