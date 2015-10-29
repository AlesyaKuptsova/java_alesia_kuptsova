/*
Sort list or array by count of vowels in string
*/
package org.lab3;

import java.util.Comparator;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

/*
  Created by Alesia Kuptsova on 10/25/2015.
 */
public class Sort {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter sort order Ascending/descending [A/d]: ");
        String orderStr = sc.nextLine();
        final boolean ascending = orderStr.isEmpty() || orderStr.charAt(0) != 'd';
        System.out.println("Enter strings:");
        ArrayList<String> strings = new ArrayList<String>();
        try {
            while (true) {
                String str = sc.nextLine();
                strings.add(str);
            }
        } catch (Exception exc) {
        }
        sc.close();

        Collections.sort(strings, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (ascending)
                    return countVo(o1) - countVo(o2);
                else
                    return countVo(o2) - countVo(o1);
            }
        });
        System.out.println("Sorted:");
        for (String str : strings) {
            System.out.println(str);
        }
    }

    private static int countVo(String s) {
        String vowels = "aeiouAEIOU";
        int m = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (vowels.indexOf(s.charAt(i)) != -1) {
                m++;
            }
        }
        return m;
    }
}