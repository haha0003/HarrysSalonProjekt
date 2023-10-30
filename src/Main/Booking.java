package Main;

import java.io.File;
import java.sql.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Booking {
    private String customerName;
    private String customerMail;
    private LocalDateTime bookingTime;

    private List<LocalDateTime> availableTimes = generateAvailableTimes();
    private List<LocalDateTime> existingBookings = new ArrayList<>();

    private Service servicePriceList;

    final String filename = "BookingFile.txt";
    private Scanner scanner = new Scanner(System.in);

    public Booking() {

    }

    public List<LocalDateTime> generateAvailableTimes() {
        List<LocalDateTime> availableTimes = new ArrayList<>();
        LocalDateTime currentDateTime = LocalDateTime.now();

        while (currentDateTime.getHour() < 18) {
            availableTimes.add(currentDateTime);
            currentDateTime = currentDateTime.plusHours(1);

        }
        return availableTimes;
    }


    public Booking(String customerName, String customerMail, LocalDateTime bookingTime) {
        this.customerName = customerName;
        this.customerMail = customerMail;
        this.bookingTime = bookingTime;
    }


    public LocalDateTime getBookingTime() {
        return bookingTime;
    }


    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    public void availableBookings() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime endDate = now.plusDays(4);

        List<LocalDateTime> availableTimesInInterval = getAvailableTimes(now, endDate);

        if (availableTimesInInterval.isEmpty()) {
            System.out.println("No available times in the next four days. ");
        } else {
            System.out.println("Available Times for the next four days");
            printAvailableTimes(availableTimesInInterval);
        }
    }

    private List<LocalDateTime> getAvailableTimes(LocalDateTime start, LocalDateTime end) {
        List<LocalDateTime> availableTimesInInterval = new ArrayList<>();
        for (LocalDateTime time = start; !time.isAfter(end); time = time.plusHours(1)) {
            if (isTimeAvailable(time)){
                availableTimesInInterval.add(time);
            }
        }
        return availableTimesInInterval;
    }

    private boolean isTimeAvailable(LocalDateTime time) {
        return availableTimes.contains(time) && !existingBookings.contains(time);
    }

        private void printAvailableTimes (List<LocalDateTime> times) {
            for (LocalDateTime time : times) {
                System.out.println(time);
            }
        }

        

    public void createBooking() {

        System.out.println("Show available bookings: ");
            printAvailableTimes(availableTimes);

        System.out.println("Choose the number of the selected date");
        int chooseNumber = scanner.nextInt();

        if (chooseNumber >= 0 && chooseNumber < availableTimes.size()) {
            LocalDateTime chosenDate = availableTimes.get(chooseNumber);
            existingBookings.add(chosenDate);
        } else {
            System.out.println("Invalid number. Please choose a valid booking date");
            scanner.close();
            return;
        }

        System.out.println("Create booking for customer:");
        scanner.nextLine();
        System.out.println("Enter customer name: ");
        customerName = scanner.nextLine();
        System.out.println("Enter customer mail: ");
        customerMail = scanner.nextLine();

        Booking booking = new Booking(customerName, customerMail, bookingTime);

        System.out.println("Booking created for customer");

        existingBookings.add(bookingTime);
        availableTimes.remove(bookingTime);


        System.out.println("Booking created for: " + bookingTime);
        System.out.println("Customer: " + customerName);
        System.out.println("Customer Mail: " + customerMail);
        System.out.println("Service: " + servicePriceList);

        saveFile();

        scanner.close();

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


