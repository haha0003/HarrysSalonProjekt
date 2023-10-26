package Main;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Booking {
    private LocalDateTime bookingTime;
    private LocalDateTime bookingDate;
    private String customerName;
    private String customerMail;
    private ArrayList<Booking> bookings;
    final String filename = "BookingFile.txt";


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


