package org.lab1;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Created by Alesia Kuptsova on 10/25/2015.
 */
public class Calc {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            while (true) {
                System.out.println("Enter first number:");
                double n1 = sc.nextInt();
                System.out.println("Enter a sign of operation:");
                char op = sc.next().charAt(0);
                System.out.println("Enter second number:");
                double n2 = sc.nextInt();
                try {
                    System.out.println("Result:" + Calc.metodClac(n1, op, n2));
                } catch (RuntimeException e){
                    System.out.println(e.getMessage());
                }
            }
        } catch (NoSuchElementException e) {

        }
        sc.close();
        System.out.println("exit");
    }

    private static double metodClac(double n1, char op, double n2) {
        {
            switch (op) {
                case '+':
                    return n1 + n2;
                case '-':
                    return n1 - n2;
                case '*':
                    return n1 * n2;
                case '/':
                    if(n2==0.0){
                      throw new RuntimeException("Division by zero");
                    }
                    return n1 / n2;
                default:
                    throw new RuntimeException("Invalid operation");
            }
        }
    }
}