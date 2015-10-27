/*
Transpose a matrix.
*/
package org.lab2;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Alesia Kuptsova on 10/25/2015.
 */

public class Matrix {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter rows and columns:");  //TODO Message is not clear for understanding
        int n = in.nextInt();
        int m = in.nextInt();
        in.close();
        Random randomGenerator = new Random();
        int[][] mat = new int[n][m];
        for (int i = 0; i < mat.length; ++i) {  //TODO You known length of arrays
            for (int j = 0; j < mat[i].length; ++j) {   //TODO Why do you use pre increment?
                mat[i][j] = randomGenerator.nextInt(10);    //TODO It will be better to use number provided by user
            }
        }
        for (int i = 0; i < mat.length; ++i) {
            for (int j = 0; j < mat[i].length; ++j) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("\n");

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                System.out.print(mat[j][i] + " ");
            }
            System.out.println();
        }

        //TODO Where is transposed  matrix?
    }
}