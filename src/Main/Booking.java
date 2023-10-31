package Main;

import java.io.File;
import java.sql.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Booking {
    private Customer customer;
    private LocalDateTime bookingTime;

    private List<LocalDateTime> availableTimes = generateAvailableTimes();
    private List<LocalDateTime> existingBookings = new ArrayList<>();


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
        LocalDateTime endDate = now.plusDays(5);

        System.out.println("Available Times for the next four days");
        for (LocalDateTime time = now; !time.isAfter(endDate); time = time.plusHours(1)) {
            if (availableTimes.contains(time) && !existingBookings.contains(time)) {
                System.out.println(time);
            }
        }

        if (availableTimes.isEmpty()) {
            System.out.println("No available times in the next four days");
        }
    }


    public void createBooking() {

        System.out.println("Show available bookings: ");
        for (int i = 0; i < availableTimes.size(); i++) {
            System.out.println(i + ". " + availableTimes.get(i));
        }


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

        customer = new Customer("", "");
        System.out.println("Create booking for customer:");
        scanner.nextLine();
        System.out.println("Enter customer name: ");
        String name = scanner.nextLine();
        customer.setCustomerName(name);

        do {
            System.out.println("Enter customer mail: ");
            String mail = scanner.nextLine();
            customer.setCustomerMail(mail);
        } while (customer.getCustomerMail() == null);


        Service service = new Service();
        System.out.println("Choose service: ");
        service.chooseServiceNoPrice();


        Booking booking = new Booking(customer.getCustomerName(), customer.getCustomerMail(), bookingTime);


        existingBookings.add(bookingTime);
        availableTimes.remove(bookingTime);


        System.out.println("Booking created for: " + customer.getCustomerName() + ", " + bookingTime);
        System.out.println("Customer: " + customer.getCustomerName());
        System.out.println("Customer Mail: " + customer.getCustomerMail());
        System.out.println();
        service.viewSelectedServicesNoPrice();

        saveFile();

        scanner.close();

    }



    public void deleteBooking() {
    }

    public void saveFileAfterDeleteBooking() {
    }

    public void saveFile() {
    }

    public void readFile() {

    }


}


