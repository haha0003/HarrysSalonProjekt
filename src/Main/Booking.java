package Main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Booking {
    private Customer customer;
    private String selectedDate;
    private ArrayList<String> availableBookings = new ArrayList<String>();
    private ArrayList<Booking> bookings = new ArrayList<>();
    final String filename = "BookingFileNew.txt";

    Scanner scanner = new Scanner(System.in);
    Random random = new Random();

    Booking(String name, String mail, String selectedDate) {
        this.customer = new Customer("", "");
        this.customer.setCustomerName(name);
        this.customer.setCustomerMail(mail);
        this.selectedDate = selectedDate;
    }

    public Booking() {
    }

    public void setSelectedDate(String selectedDate) {
        this.selectedDate = selectedDate;
    }
    public String getSelectedDate() {
        return selectedDate;
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
        } while (!customer.isValidMail(customer.getCustomerMail()) && !customer.isValidDot(customer.getCustomerMail()));
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
                setSelectedDate (availableBookings.get(ans));
                availableBookings.remove(ans);
                setSelectedDate (selectedDate.replaceAll("\\d+\\. ", ""));
                System.out.println("Selected date: " + getSelectedDate());

                Booking booking = new Booking(customer.getCustomerName(), customer.getCustomerMail(), getSelectedDate());

                bookings.add(booking);

                System.out.println("\n\nBOOKING CREATED");
                System.out.println("Customer name: " + customer.getCustomerName());
                System.out.println("Customer mail: " + customer.getCustomerMail());
                System.out.println("Booking date: " + getSelectedDate());
                service.viewSelectedServicesNoPrice();

                saveFile(booking);

                validChoice = true;
            } else {
                System.out.println("INVALID!!!");
            }
        }
    }

    public void availableDates(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd EEEE HH:mm", Locale.ENGLISH);
        for (int i = 0; i < 5; i++) {
            LocalDateTime dateTime = randomDay();
            String formatDateTime = i + ". " + dateTime.format(formatter);
            Collections.sort(availableBookings, Comparator.naturalOrder());
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
        int randomHour = random.nextInt(8) + 10;
        int randomMinute;
        if (random.nextInt() <= 30) {randomMinute = 30;
        } else {randomMinute = 0;}
        return start.plusDays(randomDays)
                .withHour(randomHour)
                .withMinute(randomMinute);
    }

    public void viewBookings(){
        for (int i = 0; i < bookings.size(); i++){
            Booking booking = bookings.get(i);
            System.out.println(i + ". Customer\n" + booking.getSelectedDate() + "\n" + booking.customer.getCustomerName() + "\n" + booking.customer.getCustomerMail() + "\n");
        }
    }

    public void deleteBooking() {
        viewBookings();
        System.out.println("Enter number of booking wanted deleted");
        int ans = scanner.nextInt();
        scanner.nextLine();

        if (ans >= 0 && ans < bookings.size()){
            Booking deletedBooking = bookings.remove(ans);
            saveFileAfterDeleteBooking(deletedBooking);
            System.out.println("Booking deleted");
        } else {
            System.out.println("INVALID!!!");
        }
    }

    public void saveFileAfterDeleteBooking(Booking deletedBooking) {
        try {
            PrintWriter print = new PrintWriter(new FileWriter(filename));
            for (int i = 0; i < bookings.size(); i++) {
                Booking booking = bookings.get(i);
                print.println(booking.getSelectedDate() + "\n" + booking.customer.getCustomerName() + "\n" + booking.customer.getCustomerMail());
            }
            print.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void saveFile(Booking booking) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(filename, true));
            writer.println("Date: " + booking.selectedDate);
            writer.println("Name: " + booking.customer.getCustomerName());
            writer.println("Mail: " + booking.customer.getCustomerMail());
            writer.close();
            System.out.println("The file has been saved");
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    public void readFile() {
        try {
            File file = new File(filename);
            if (!file.exists()) {
                System.out.println("File 'BookingFile.txt' does not exist.");
                return;
            }
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String date = sc.nextLine();
                String name = sc.nextLine();
                String mail = sc.nextLine();

                Booking booking = new Booking();
                booking.customer = new Customer("","");

                booking.setSelectedDate(date);
                booking.customer.setCustomerName(name);
                booking.customer.setCustomerMail(mail);
                bookings.add(booking);
                } sc.close();
        } catch (IOException e){
            System.out.println("Error occured");
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return  bookings.indexOf(this) + ". " +"\n" +
                customer.getCustomerName() + "\n" +
                customer.getCustomerMail() + "\n" +
                selectedDate;
    }
}