package Main;

import java.io.File;
import java.sql.Array;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Booking {
    private Customer customer;
    private ArrayList<String> bookings = new ArrayList<String>();
    private ArrayList<String> availableBookings = new ArrayList<String>();
    final String filename = "BookingFile.txt";

    Scanner scanner = new Scanner(System.in);
    Random random = new Random();

    Booking(String name, String mail, ArrayList<String> bookings, String date) {
        this.customer = new Customer("", "");
        this.customer.setCustomerName(name);
        this.customer.setCustomerMail(mail);
        this.bookings = bookings;
        this.bookings.add(date);
    }

    public Booking() {
    }

    public void createCustomer(){
        customer = new Customer("", "");
        System.out.println("CREATE BOOKING");
        System.out.println("Enter customer name: ");
        String name = scanner.nextLine();
        customer.setCustomerName(name);
        do {
            System.out.println("Enter customer mail: ");
            String mail = scanner.nextLine();
            customer.setCustomerMail(mail);
        } while (!customer.isValidMail(customer.getCustomerMail()));
    }

    public void createBooking(){
        createCustomer();
        Service service = new Service();
        service.chooseServiceNoPrice();
        System.out.println();
        availableDates();
        boolean validChoice = false;
        while (!validChoice) {
            System.out.println("Choose a date: ");
            int ans = scanner.nextInt();
            scanner.nextLine();
            if (ans >= 0 && ans < availableBookings.size()) {
                String selectedDate = availableBookings.get(ans);
                availableBookings.remove(ans);
                selectedDate = selectedDate.replaceAll("#\\d+\\. ", "");
                System.out.println("Selected date: " + selectedDate);

                Booking booking = new Booking(customer.getCustomerName(), customer.getCustomerMail(), bookings, selectedDate);

                System.out.println("\n\nBOOKING CREATED");
                System.out.println("Customer name: " + customer.getCustomerName());
                System.out.println("Customer mail: " + customer.getCustomerMail());
                service.viewSelectedServicesNoPrice();
                viewBooking();

                validChoice = true;
            } else {
                System.out.println("INVALID!!!");
            }
        }
    }

    public void viewBooking(){
        System.out.printf("Booking date: ");
        for (int i = 0; i < bookings.size(); i++) {
            System.out.println(bookings.get(i));
        }
    }

    public void availableDates(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd EEEE HH:mm", Locale.ENGLISH);
        for (int i = 0; i < 5; i++) {
            LocalDateTime dateTime = randomDay();
            String formatDateTime = "#" + i + ". " + dateTime.format(formatter);
            availableBookings.add(formatDateTime);
            System.out.println(formatDateTime);
        }
    }

    public LocalDateTime randomDay(){
        LocalDateTime randomDateTime;
        do { randomDateTime = randomTime();
        } while (randomDateTime.getDayOfWeek() == DayOfWeek.SATURDAY || randomDateTime.getDayOfWeek() == DayOfWeek.SUNDAY);
        return randomDateTime;
    }

    public LocalDateTime randomTime() {
        LocalDateTime start = LocalDateTime.of(2023, 01, 01, 23, 59);
        LocalDateTime end = LocalDateTime.of(2024, 12, 31, 23, 59);
        long days = ChronoUnit.DAYS.between(start, end);
        long randomDays = ThreadLocalRandom.current().nextLong(days + 1);
        int randomHour = random.nextInt(10) + 8;
        int randomMinute;
        if (random.nextInt() <= 30) {randomMinute = 30;
        } else {randomMinute = 0;}
        return start.plusDays(randomDays)
                .withHour(randomHour)
                .withMinute(randomMinute);
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


