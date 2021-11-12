package blackJack;

import java.util.Scanner;

public class IO {

    Scanner sc = new Scanner(System.in);

    public String getString(){
        return sc.nextLine();
    }

    public <T>void printAnything(T t){
        System.out.println(t);
    }

    public void exit(){
        System.exit(0);
    }
    public void pauseCode(){
        printAnything("Press enter to continue");
        sc.nextLine();
    }
}
