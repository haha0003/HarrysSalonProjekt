package Main;

import java.util.ArrayList;
import java.util.Scanner;

public class Service {

    private String name;
    private int price;
    private ArrayList <Service> services = new ArrayList<>();
    private ArrayList<Service> chosenServiceList = new ArrayList<>();

    Scanner scanner = new Scanner(System.in);

    Service(String name, int price){
        this.name = name;
        this.price = price;
    }

    public Service() {
        serviceAdd();
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }


    public void serviceAdd(){
        services.add(new Service("Haircut", 200));
        services.add(new Service("Shave",150));
        services.add(new Service("Hair coloring", 400));
        services.add(new Service("Perm", 300));
        services.add(new Service("Beard trim", 100 ));
    }

    public void viewServices(){
        for (int i = 0; i < services.size(); i++){
            System.out.printf("#%-3d %-20s $%d%n", i, services.get(i).getName(), services.get(i).getPrice());
        }
    }

    public void chooseService(){
        boolean addAnotherService = true;

            while (addAnotherService) {
                viewServices();
                System.out.println("Enter the number of the wanted service");
                int choiceOfService = scanner.nextInt();
                scanner.nextLine();
                if (choiceOfService >= 0 && choiceOfService < services.size()) {
                    Service service = services.get(choiceOfService);
                    int locationOfService = services.indexOf(service);
                    System.out.printf("#%-3d %-20s $%d%n", locationOfService, service.getName(), service.getPrice());
                    chosenServiceList.add(service);
                } else {
                    System.out.println("INVALID!!!");
                }

                System.out.println("\nWould you like to add another service? (Y/N)");
                String addServiceChoice = scanner.nextLine();
                if (addServiceChoice.equalsIgnoreCase("N")) {
                    addAnotherService = false;
                    viewSelectedServices();
                } else if (!addServiceChoice.equalsIgnoreCase("Y")){
                    System.out.println("INVALID!!!");
                }
            }
        }

        public void viewSelectedServices(){
            System.out.println("Selected services: ");
            for (int i = 0; i < chosenServiceList.size(); i++){
                Service service = chosenServiceList.get(i);
                System.out.printf("#%-3d %-20s $%d%n", i, service.getName(), service.getPrice());
            }
        }
}
