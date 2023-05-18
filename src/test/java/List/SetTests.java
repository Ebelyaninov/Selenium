package List;

import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetTests {

    @Test(description = "Set")
    void setTests() {
        Set<String> hashSet = new HashSet<>();
        Set<String> linkedHashSet = new LinkedHashSet<>();
        Set<String> treeSet = new TreeSet<>();
        // Лексико графический порядок сортировки строк a < b < c & aa < ab
        hashSet.add("Katty");
        hashSet.add("Tom");
        hashSet.add("George");
        hashSet.add("Donald");
        hashSet.add("Mike");

        for (String name : hashSet) {
            System.out.println(name);
        }

        System.out.println(" ");
        linkedHashSet.add("Katty");
        linkedHashSet.add("Tom");
        linkedHashSet.add("George");
        linkedHashSet.add("Donald");
        linkedHashSet.add("Mike");

        for (String name : linkedHashSet) {
            System.out.println(name);
        }

        System.out.println(" ");
        treeSet.add("Zorro");
        treeSet.add("Katty");
        treeSet.add("Tom");
        treeSet.add("George");
        treeSet.add("Donald");
        treeSet.add("Mike");

        for (String name : treeSet) {
            System.out.println(name);
        }
        System.out.println(" ");

        System.out.println(hashSet.contains("Tom"));
        System.out.println(hashSet.contains("Tim"));
        System.out.println(" ");

        hashSet.add("Tom");
        hashSet.add("Tom");

        for (String name : hashSet) {
            System.out.println(name);
        }
    }
}
