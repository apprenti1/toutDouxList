package betterConsoleScanner;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ConsoleScanner {
    private Scanner sc = new Scanner(System.in);
    public int nextInt(String message){
        while (!this.sc.hasNextInt()){
            System.out.println(message);
            this.sc.nextLine();
        }
        int result = this.sc.nextInt();
        this.sc.nextLine();
        return result;
    }
    public Double nextDouble(String message){
        while (!this.sc.hasNextDouble()){
            System.out.println(message);
            this.sc.nextLine();
        }
        Double result = this.sc.nextDouble();
        this.sc.nextLine();
        return result;
    }
    public String nextLine(){
        return sc.nextLine();
    }
    public int choixInt(int nb1, int nb2, String message){
        while (true) {
            int choix = this.nextInt(message);
            if (choix>=nb1 && choix<=nb2){
                return choix;
            }
            else{
                System.out.println(message);
            }
        }
    }
    public int choixInt(int nb1, int nb2, int defaut, String message){
        int choix = this.nextInt(message);
        if (choix>=nb1 && choix<=nb2){
            return choix;
        }
        else{
            return defaut;
        }
    }
    public String choixString(String[] options, String message){
        while (true) {
            String choix = this.nextLine();
            if (this.indexOf(choix, options) != -1){
                return choix;
            }
            else{
                System.out.println(message);
            }
        }
    }
    public String choixString(String[] options, int defaut){
        while (true) {
            String choix = this.nextLine();
            if (this.indexOf(choix, options) != -1){
                return choix;
            }
            else {
                return options[defaut];
            }
        }
    }
    private int indexOf(String value, String[] table){
        int result = -1;
        int increment = 0;
        for (String i : table) {
            if (value == i){
                result = increment;
            }
            result++;
        }
        return result;
    }

}
