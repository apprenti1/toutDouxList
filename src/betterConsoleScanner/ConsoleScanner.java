package betterConsoleScanner;

import java.util.Scanner;

public class ConsoleScanner {
    private Scanner sc = new Scanner(System.in);
    public int nextInt(String message){
        while (!this.sc.hasNextInt()){
            System.out.print(message);
            this.nextLine();
        }
        int result = this.sc.nextInt();
        this.nextLine();
        return result;
    }
    public Double nextDouble(String message){
        while (!this.sc.hasNextDouble()){
            System.out.print(message);
            this.nextLine();
        }
        Double result = this.sc.nextDouble();
        this.nextLine();
        return result;
    }
    public String nextLine(){
            return sc.nextLine();
    }
    public String nextLine(String formatErrorMessage){
        String result = sc.nextLine();
        while (!this.verifStringFormat(result)){
            System.out.print(formatErrorMessage);
            result = sc.nextLine();
        }
            return result;
    }
    public int choixInt(int nb1, int nb2, String message){
        while (true) {
            int choix = this.nextInt(message);
            if (choix>=nb1 && choix<=nb2){
                return choix;
            }
            else{
                System.out.print(message);
            }
        }
    }
    public int choixInt(int nb1, int nb2, int defaut){
        String choixStr = this.nextLine();
        try {
            int choix = Integer.parseInt(choixStr);
            if (choix >= nb1 && choix <= nb2) {
                return choix;
            } else {
                return defaut;
            }
        }catch (NumberFormatException e){
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
                System.out.print(message);
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

    public String[] stringForm(String beforeText, String[] formValues, String afterText, String formatErrorMessage){
        String[] result = new String[formValues.length];
        int increment = 0;
        for (String i:formValues) {
            System.out.print(beforeText+i+afterText);
            result[increment] = this.nextLine(formatErrorMessage);
            increment++;
        }
        return result;
    }
    public String[] stringForm(String beforeText, String[] formValues, String afterText){
        String[] result = new String[formValues.length];
        int increment = 0;
        for (String i:formValues) {
            System.out.print(beforeText+i+afterText);
            result[increment] = this.nextLine();
            increment++;
        }
        return result;
    }

    public int[] intForm(String beforeText, String[] formValues, String afterText, String formatErrorMessage){
        int[] result = new int[formValues.length];
        int increment = 0;
        for (String i:formValues) {
            System.out.print(beforeText+i+afterText);
            result[increment] = this.nextInt(formatErrorMessage);
            increment++;
        }
        return result;
    }
    public double[] doubleForm(String beforeText, String[] formValues, String afterText, String formatErrorMessage){
        double[] result = new double[formValues.length];
        int increment = 0;
        for (String i:formValues) {
            System.out.print(beforeText+i+afterText);
            result[increment] = this.nextDouble(formatErrorMessage);
            increment++;
        }
        return result;
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
    public boolean verifStringFormat(String text){
        if ((   text.indexOf('"')+
                text.indexOf("'")+
                text.indexOf('\n')+
                text.indexOf('\r')+
                text.indexOf(' ')+
                text.indexOf('\t')+
                text.indexOf('(')+
                text.indexOf(')'))==-8){
            return true;
        } else{ return false;}}

}

