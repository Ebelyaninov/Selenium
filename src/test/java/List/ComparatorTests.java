package List;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorTests {

    @Test(description = "comparator")
    void comparatorTest() {
        List<String> animals = new ArrayList<>();
        animals.add("asdfq");
        animals.add("dog");
        animals.add("cat");
        animals.add("ab");
        animals.add("frog");
        animals.add("bird");
        animals.add("a");

        Collections.sort(animals);
        System.out.println(animals);

        List<Integer> numbers = new ArrayList<>();
        numbers.add(5);
        numbers.add(0);
        numbers.add(500);
        numbers.add(100);

        Collections.sort(numbers);
        System.out.println(numbers);

        Collections.sort(animals, new StringLenghtComparator());
        System.out.println(animals);

        Collections.sort(numbers, new BackwardsIntegerComparator());
        System.out.println(numbers);

        //Если нужно использовать один раз, то создаем анонимный класс
        Collections.sort(numbers, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 < o2) {
                    return 1;
                } else if (o1 > o2) {
                    return  -1;
                } else {
                    return 0;
                }
            }
        });
        System.out.println(numbers);
    }
 }

 class StringLenghtComparator implements Comparator<String> {

     @Override
     public int compare(String o1, String o2) {
         /*
            o1 > o2 -> 1;
            o1 < o2 -> -1;
            o1 == 02 -> 0;

            compare(2, 1) -> 1;
            compare(1, 2) -> -1;
            compare(3, 3) -> 0;
          */

         if (o1.length() > o2.length()) {
             return 1;
         } else if (o1.length() < o2.length()) {
             return -1;
         } else {
             return 0;
         }
     }
 }

 class BackwardsIntegerComparator implements Comparator<Integer> {
     @Override
     public int compare(Integer o1, Integer o2) {
         if (o1 < o2) {
             return 1;
         } else if (o1 > o2) {
             return  -1;
         } else {
             return 0;
         }
     }
 }
