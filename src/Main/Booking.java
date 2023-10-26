package Main;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Booking {
    private LocalDateTime bookingTime;
    private LocalDateTime bookingDate;
    private String customerName;
    private String customerMail;
    private ArrayList<Booking> bookings;
    final String filename = "BookingFile.txt";
    Scanner scanner = new Scanner(System.in);


    Booking(String customerName, String customerMail, LocalDateTime bookingDate, LocalDateTime bookingTime){
        this.customerName = customerName;
        this.customerMail = customerMail;
        this.bookingDate = bookingDate;
        this.bookingTime = bookingTime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerMail() {
        return customerMail;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerMail(String customerMail) {
        this.customerMail = customerMail;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    public void createBooking(){
        System.out.println("Enter customer name: " );
        customerName = scanner.next();


        LocalDate today = LocalDate.now();
        System.out.println("Current date today " + today);

        System.out.println("Choose Date: ");



        for (int i = 0; true; i ++) {

        }
    }

    public void deleteBooking(){
    }

    public void saveFileAfterDeleteBooking(){
    }

    public void saveFile(){
    }

    public void readFile(){

    }


}


