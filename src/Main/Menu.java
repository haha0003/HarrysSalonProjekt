package Main;

import java.util.Scanner;

public class Menu {
    private String menuHead;
    private String [] menuLog;

    Menu(String menuHead, String[] menuLog){
        this.menuHead = menuHead;
        this.menuLog = menuLog;
    }


    public void printMenu(){
        System.out.println(menuHead);
        for (int i = 0; i < menuLog.length;i++){
            System.out.println(menuLog[i]);
        }
        System.out.println();
    }

    public int readChoice(){
        Scanner scanner = new Scanner(System.in);
        boolean valid = false;
        int choice = 0;

        while (! valid){
            if (scanner.hasNextInt()){
                choice = scanner.nextInt();
                valid = true;
                System.out.println();
            } else {
                scanner.nextLine();
            }
        }
        return choice;
    }

}
