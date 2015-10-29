/*
Transpose a matrix.
*/
package org.lab2;

import java.util.Scanner;

/**
 * Created by Alesia Kuptsova on 10/25/2015.
 */

public class Matrix {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the number of rows  of matrix:");
        int n = in.nextInt();
        System.out.println("Enter the number of columns of matrix:");
        int m = in.nextInt();

        int matrix[][] = new int[n][m];

        System.out.println("Enter the elements of matrix:");

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                matrix[i][j] = in.nextInt();

        int transpose[][] = new int[m][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++)
                transpose[j][i] = matrix[i][j];
        }

        System.out.println("Transpose of entered matrix:");

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++)
                System.out.print(transpose[i][j] + "\t");
            System.out.print("\n");
        }
    }
}


