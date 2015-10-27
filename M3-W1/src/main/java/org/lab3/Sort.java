/*
Sort list or array by count of vowels in string
*/
package org.lab3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Created by Alesia Kuptsova on 10/25/2015.
 */
public class Sort {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter string:");
        String str = sc.nextLine();
        sc.close();

        //TODO Where i list (or array) of strings?
        //TODO How can user specify order of sorting?

        String[] n = str.split(" ");
        Arrays.sort(n, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return countVo(o1) - countVo(o2);
            }
        });
        for (int i = 0; i < n.length; ++i) {
            System.out.println(n[i]);
        }
    }

    private static int countVo(String s) {
        String vowels = "aeiouAEIOU";
        int m = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (vowels.indexOf(s.charAt(i)) != -1) {
                m += 1; //TODO Use simple increment (m++)
            }
        }
        return m;
    }
}
