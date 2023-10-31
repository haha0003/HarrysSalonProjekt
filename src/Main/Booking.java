package Main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Booking {
    private String customerName;
    private String customerMail;
    private LocalDateTime bookingTime;
    private LocalDateTime bookingDate; // var blevet fjernet??
    private ArrayList<Booking> bookings;
    private List<LocalDateTime> availableTimes = generateAvailableTimes();
    private List<LocalDateTime> existingBookings = new ArrayList<>();

    private ServicePriceList servicePriceList;

    final String filename = "BookingFile.txt";
    private Scanner scanner = new Scanner(System.in);

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
        bookings = new ArrayList<Booking>();
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

    public LocalDateTime getBookingDate() {
      return bookingDate;
    }

    public void setBookingDate (LocalDateTime bookingDate){
     this.bookingDate = bookingDate;
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
            if (availableTimes.contains(time) && !existingBookings.contains(time)) {
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


        System.out.println("Choose the type of booking you would want: ");
        for (int i = 0; i < servicePriceList.size(); i++) {
            System.out.println(i + ". " + servicePriceList.get(i));
        }

        int serviceChoice = scanner.nextInt();

        bookingTime = availableTimesInInterval.get(0);

        Booking booking = new Booking(customerName, customerMail, bookingTime, servicePriceList.get(serviceChoice));

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

    public void deleteBooking() {
        int index = 1;
        int chosenIndex;
        System.out.println("which booking do you want to delete? Type number.");
        for (Booking i : bookings) {
            System.out.println(index + ". " + i.getBookingDate() + " - " + i.getBookingTime());
            index++;
        }
        do {
            chosenIndex = scanner.nextInt();
            if (chosenIndex < 1 || chosenIndex > index - 1) {
                System.out.println("Invalid option. Please try again.");
            }
        } while (chosenIndex < 1 || chosenIndex > index - 1);

        // Removes the booking at the chosen index
        bookings.remove(chosenIndex - 1);
        System.out.println("The booking is removed.");
    }

    public void saveFileAfterDeleteBooking() { // vi behøver ikke!! hear me out.
    }

    public void saveFile() {
        try {
            File myFile = new File("BookingFile.txt");
            if (myFile.createNewFile()) {
                System.out.println("File created: " + myFile.getName());
            } else {
                System.out.println("A file already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
        try {
            FileWriter myWriter = new FileWriter("BookingFile.txt");
            for (Booking i : bookings) {
                myWriter.write("%s - %s\nCustomer name:%s\nMail:%s\n_______________________".formatted(i.getBookingDate(), i.getBookingTime(), i.getCustomerName(), i.getCustomerMail()));
                myWriter.write(System.lineSeparator());
            }
                myWriter.close();
                System.out.println("The file has been saved");
            } catch(IOException e){
                System.out.println("An error occurred.");
            }


            public void readFile() {

            }











    }
}