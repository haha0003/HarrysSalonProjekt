package Main;

import java.util.ArrayList;
import java.util.Scanner;

public class Service {

    private String name;
    private int price;
    private ArrayList <Service> services = new ArrayList<>();
    private ArrayList<Service> chosenServiceList = new ArrayList<>();
    private ArrayList<Service> chosenServiceListNoPrice = new ArrayList<>();

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
        services.add(new Service("Bleach asshole", 1000));
        services.add(new Service("Bikini wax", 500));
        services.add(new Service("Manicure", 400));
    }

    public void viewServicesNoPrice(){
        for (int i = 0; i < services.size(); i++){
            System.out.println("#" + i + ". " + services.get(i).getName());
        }
    }

    public void chooseServiceNoPrice(){
        boolean start = true;
        boolean addAnotherService = true;

    while (start){
        System.out.println("Do you want to add a service? (Y/N)");
        String ans = scanner.nextLine();
        if (ans.equalsIgnoreCase("y")){
        while (addAnotherService) {
            viewServicesNoPrice();
            System.out.println("Enter the number of the wanted service");
            int choiceOfService = scanner.nextInt();
            scanner.nextLine();
            if (choiceOfService >= 0 && choiceOfService < services.size()) {
                Service service = services.get(choiceOfService);
                int locationOfService = services.indexOf(service);
                System.out.printf("\n#%-3d %-20s %n", locationOfService, service.getName());
                chosenServiceListNoPrice.add(service);
            } else {
                System.out.println("INVALID!!!");
            }
            System.out.println("Would you like to add another service? (Y/N)");
            String addServiceChoice = scanner.nextLine();
            if (addServiceChoice.equalsIgnoreCase("N")) {
                addAnotherService = false;
                start = false;
                viewSelectedServicesNoPrice();
            } else if (!addServiceChoice.equalsIgnoreCase("Y")){
                System.out.println("INVALID!!!");
            }
            }
            }
        else if (ans.equalsIgnoreCase("n")){
            start = false;
        }
        else {
            System.out.println("INVALID!!!");
        }
    }
    }

    public void viewSelectedServicesNoPrice(){
        System.out.println("Selected services: ");
        for (int i = 0; i < chosenServiceListNoPrice.size(); i++){
            Service service = chosenServiceListNoPrice.get(i);
            System.out.printf("#%-3d %-20s %n", i, service.getName());
        }
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
