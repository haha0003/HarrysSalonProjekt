package Main;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Booking {
    private LocalDateTime bookingTime;
    private LocalDateTime bookingDate;
    private String customerName;
    private String customerMail;
    private ArrayList<Booking> bookings;
    final String filename = "BookingFile.txt";
    Scanner scanner = new Scanner(System.in);
    private ServicePriceList servicePriceList;



    Booking(String customerName, String customerMail, LocalDateTime bookingTime){
        this.customerName = customerName;
        this.customerMail = customerMail;
        this.bookingTime = bookingTime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerMail() {
        return customerMail;
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

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    public void createBooking() {

        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = startDate.plusDays(4);

        List<LocalDateTime> availableTimesInInterval = new ArrayList<>();

        for (LocalDateTime time = startDate; !time.isAfter(endDate); time = time.plusHours(1)) {
            if (availableTimes.contains(time) && !existingBooking.contains(time)) {
                availableTimesInInterval.add(time);
            }
        }

        if (availableTimesInInterval.isEmpty()) {
            System.out.println("No available times in the next four days. ");
            return;
        }

        System.out.println("Available Times for the next four days:");
        for (int i = 0; i < availableTimesInInterval.size(); i++) {
            System.out.println(i + ". " + availableTimesInInterval.get(i));
        }


        System.out.println("Create booking for customer:");
        System.out.println("Enter customer name: ");
        customerName = scanner.nextLine();
        System.out.println("Enter customer mail: ");
        customerMail = scanner.nextLine();

        servicePriceList = getServicePriceList();

        System.out.println("Choose the type of booking you would want: ");
        for (int i = 0; i < servicePriceList.size(); i++) {
            System.out.println(i + ". " + servicePriceList.get(i));
        }

        int serviceChoice = scanner.nextInt();

        bookingTime = availableTimesInInterval.get(0);

        Booking booking = new Booking(customerName, customerMail, bookingTime, servicePriceList.get(serviceChoice));

        System.out.println("Booking created for customer");

        exsistingBookings.add(bookingTime);
        availableTimes.remove(bookingTime);


        System.out.println("Booking created for: " + bookingTime);
        System.out.println("Customer: " + customerName);
        System.out.println("Customer Mail: " + customerMail);
        System.out.println("Service: " + servicePriceList);

        scanner.close();

    }

    public void deleteBooking(){
    }

    public void saveFileAfterDeleteBooking(){
    }

    public void saveFile(){
    }


}


