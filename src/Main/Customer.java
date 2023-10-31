package Main;

public class Customer {
    private String customerName;
    private String customerMail;

    Customer(String customerName, String customerMail){
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
        this.customerMail = customerMail;
        if (isValidMail(customerMail)) {
            this.customerMail = customerMail;
        } else {
            System.out.println("Invalid Mail. Please enter email again with " + "@." );
        }

    }

    private boolean isValidMail(String customerMail) {
       return customerMail.contains("@");
        }
}
