package List;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CollectionsListTests {

    @Test(description = "test")
    void arrayListTest() {
//        int [] x = new int[3];
//        for (int i = 0; i < 3; i++) {
//            x[i] = 1;
//        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            arrayList.add(i);
        }
        System.out.println(arrayList);
        System.out.println(arrayList.get(0));
        System.out.println(arrayList.get(9));
        //For Loop
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println(arrayList.get(i));
        }
        //For Each Loop
        for (Integer x : arrayList)
            System.out.println(arrayList.get(x));
        //Удаление не эфективно из середины и начала
        arrayList.remove(3); //[1,2,3,4,5] -> [1,2, ,4,5] -> [1,2,4,5]
        System.out.println(arrayList);
        //Лучше ссылать объекты класса на интерфейс который этот класс реализует
        List<Integer> list = new ArrayList<>();
        //Проводим много удалений из листа и ссылаем на LinkedList
        list = new LinkedList<>();
        list.remove(2);
    }

    @Test(description = "LinkedList")
    void linkedListTest() {
        List<Integer> linkedList = new LinkedList<>();
        // head ->  [1] -> [2] -> [3] -> [4]
        List<Integer> arrayList = new ArrayList<>();
        measureTimeForGet(linkedList);
        measureTimeForGet(arrayList);
    }

    @Test(description = "LinkedList")
    void testMyList() {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.add(1);
        myLinkedList.add(2);
        myLinkedList.add(10);

        System.out.println(myLinkedList);
        System.out.println(myLinkedList.get(0));
        System.out.println(myLinkedList.get(1));
        System.out.println(myLinkedList.get(2));
        myLinkedList.remove(1);
        System.out.println(myLinkedList);
        myLinkedList.remove(1);
        System.out.println(myLinkedList);
        myLinkedList.remove(0);
        System.out.println(myLinkedList);
    }

    private static void measureTimeForAdd(List<Integer> list) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            list.add(i);
        }
        // [] -> [0] -> [0],[1] -> [0],[1],[2]
        //Добавление в начало листа ситуация будет обратной
        //list.add(0; i);
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - start);
    }

    private static void measureTimeForGet(List<Integer> list) {
        for (int i = 0; i < 100000; i++) {
            list.add(i);
        }
        long start = System.currentTimeMillis();
        for (Integer x : list)
            list.get(x);
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - start);
    }

}
