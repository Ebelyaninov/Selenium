package List;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class CollectionsSetTests {

    @Test(description = "HashMap")
    void hashMapTests() {
        // 1 -> [.. .. ..]
        Map<Integer, String> hashMap = new HashMap<>();
        hashMap.put(1, "One");
        hashMap.put(2, "Two");
        hashMap.put(3, "Three");

        System.out.println(hashMap);
        hashMap.put(3, "New value");
        System.out.println(hashMap);
        System.out.println(hashMap.get(1));
        System.out.println(hashMap.get(10));
        //HashMap не гарантирует вывод элементов в нужном порядке, он более быстрый при этом
        for (Map.Entry<Integer, String> entry : hashMap.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    @Test(description = "linkedHashMap")
    void linkedHashMapTests() {
        Map<Integer, String> hashMap = new HashMap<>();  //Внутри не герантируется порядок
        Map<Integer, String> linkedHashMap = new LinkedHashMap<>(); // Гарантируется порядок добавления
        Map<Integer, String> treeMap = new TreeMap<>(); // Гарантирует сортировку по ключу (задаем порядок сортировки)

        testMap(hashMap);
        testMap(linkedHashMap);
        testMap(treeMap);
    }

    public static void testMap(Map<Integer, String> map) {
        map.put(39, "Bob");
        map.put(12, "Mike");
        map.put(78, "Tom");
        map.put(0, "Tim");
        map.put(1500, "Lewis");
        map.put(7, "Bob");

        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
