package Main;

import java.io.File;
import java.util.Scanner;

public class Finance {
    private String password;
    private File transactionsFile;
    private boolean passwordValidated = false;
    Scanner in = new Scanner(System.in);


    public void financeRun(){
        System.out.println("Type in password:");
        this.password = in.nextLine();
        validatePassword(password);
    }

    public void validatePassword(String password) {
        if (!password.equals("HairyHarry")) {
            System.out.println("Incorrect password. Try again.");
        }else{
            System.out.println("Login successful");
            passwordValidated = true;
        }

    }

}
