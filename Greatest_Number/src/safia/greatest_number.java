package safia;

import java.util.Scanner;

public class greatest_number {

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        System.out.println("enter the 1st number : ");
        int num1 = sc.nextInt();

        System.out.println("enter the 2nd number : ");
        int num2 = sc.nextInt();

        System.out.println("enter the 3rd number : ");
        int num3 = sc.nextInt();

        if(num1 >= num2) {
            if(num1 >= num3)
                System.out.println(num1 + " is the greatest number.");
            else
                System.out.println(num3 + " is the greatest number.");
        } else {
            if(num2 >= num3)
                System.out.println(num2 + " is the greatest number.");
            else
                System.out.println(num3 + " is the greatest number.");
        }

    }
}
