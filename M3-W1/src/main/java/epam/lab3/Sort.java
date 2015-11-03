/*
Sort list or array by count of vowels in string
*/
package epam.lab3;

import java.util.Comparator;
import java.util.Scanner;

/*
  Created by Alesia Kuptsova on 10/25/2015.
 */
public class Sort {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        try {
            final boolean ascending;
            while (true) {
                System.out.print("Enter sort order ascending/descending [a/d]: ");
                String orderStr = sc.nextLine();
                if (orderStr.length() > 1 ||
                        (!orderStr.isEmpty() && !(orderStr.charAt(0) == 'a' || orderStr.charAt(0) == 'd'))) {
                    System.out.println("Invalid input");
                } else {
                    if (orderStr.isEmpty() || orderStr.charAt(0) == 'a')
                        ascending = true;
                    else
                        ascending = false;
                    break;
                }
            }
            System.out.println("Enter string count:");
            int count = sc.nextInt();
            sc.nextLine(); // move to next line
            String[] strings = new String[count];
            System.out.println("Enter strings:");
            for (int i = 0; i < strings.length; i++) {
                strings[i] = sc.nextLine();
            }
            sort(strings, new Comparator<String>() {
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
        } catch (Exception exc) {
            System.out.println("Execution error");
        }
        sc.close();
    }

    private static int countVo(String s) {
        String vowels = "aeiouAEIOU";
        int m = 0;
        for (int i = 0; i < s.length(); i++) {
            if (vowels.indexOf(s.charAt(i)) != -1) {
                m++;
            }
        }
        return m;
    }

    private static void sort(String[] strings, Comparator<String> comparator) {
        for (int i = 0; i < strings.length; i++) {
            int minIdx = i;
            for (int j = i + 1; j < strings.length; j++) {
                if (comparator.compare(strings[j], strings[minIdx]) < 0) {
                    minIdx = j;
                }
            }
            if (minIdx != i) {
                String t = strings[i];
                strings[i] = strings[minIdx];
                strings[minIdx] = t;
            }
        }
    }
}