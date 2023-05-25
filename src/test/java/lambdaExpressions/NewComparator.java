package lambdaExpressions;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class NewComparator {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        list.add("Hello!");
        list.add("Goodbye");
        list.add("a");
        list.add("ab");

        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() > o2.length()) {
                    return 1;
                } else if (o1.length() < o2.length()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        System.out.println(list);

        list.sort((s1, s2) -> {
//            if (s1.length() > s2.length()) {
//                return 1;
//            } else if (s1.length() < s2.length()) {
//                return -1;
//            } else {
//                return 0;
//            }

            if (s1.length() < s2.length()) return 1;
            else if (s1.length() > s2.length()) return -1;
            else return 0;
        });
        System.out.println(list);
        //Лямбда выражения можно записывать в переменную
        Comparator<String> comparator = (s1, s2) -> {
            if (s1.length() > s2.length()) return 1;
            else if (s1.length() < s2.length()) return -1;
            else return 0;
        };
        list.sort(comparator);
        System.out.println(list);
    }
}
