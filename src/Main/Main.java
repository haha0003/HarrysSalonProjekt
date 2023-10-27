package Main;

public class Main {
    public static void main(String[] args) {
       new Main().run();
    }

    private void run() {

        Menu menu = new Menu("\nPlease enter number: ", new String[]{
                "1. Create a booking",
                "2. Delete a booking",
                "3. Serve customer",
                "4. View financials",
                "5. View services",
                "6. Choose service",
                "9. End program"
        });

        Booking booking = new Booking();
        Service service = new Service();

        boolean running = true;
        System.out.println("Welcome!");
        while (running) {
        menu.printMenu();
        int userInput = menu.readChoice();
            switch (userInput) {
                case 1 -> booking.createBooking();
                case 2 -> booking.deleteBooking();
                case 3 -> System.out.println();
                case 4 -> System.out.println();
                case 5 -> service.viewServices();
                case 6 -> service.chooseService();
                case 9 -> running = false;
                default -> System.out.println("INVALID!!!");
            }
        }
    }


}
