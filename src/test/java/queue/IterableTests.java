package queue;

import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class IterableTests {

    @Test(description = "Iterable")
    void iterableTest() {
        List<Integer> list = Arrays.asList(1, 2, 3);
        // Java 5
        for (int x : list)
            System.out.println(x);
        //То по чему можно проходить или итерироватся
        //Например есть класс библиотека и мы храним книги и нужно пройтись по этим книгам
        //Before 5 java проходились так for each
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) System.out.println(iterator.next());

//        for (int x : list) {
//            System.out.println(x);
//            //нельзя изменять лист когда мы по нему проходимся for each, но с итератором возможно
//            list.remove(x);
//        }
        int idx = 0;
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            if (idx == 1) {
                iterator.remove();
            }
            idx++;
        }
        System.out.println(list);
    }
}
