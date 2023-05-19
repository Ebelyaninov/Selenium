package List;

import org.testng.annotations.Test;

import java.util.*;

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
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        System.out.println(numbers);
    }

    @Test(description = "Objects comparing")
    void objectsComparing() {
        List<Person> people = new ArrayList<>();
        people.add(new Person(3, "Bob"));
        people.add(new Person(2, "Katy"));
        people.add(new Person(1, "Mike"));

        Collections.sort(people, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                if (o1.getId() > o2.getId()) {
                    return 1;
                } else if (o1.getId() < o2.getId()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        System.out.println(people);
    }

    @Test(description = "Comparable interface")
    void comparable() {
        List<Person> peopleList = new ArrayList<>();
        Set<Person> peopleSet = new TreeSet<>();

        addElements(peopleList);
        addElements(peopleSet);
        Collections.sort(peopleList);

        System.out.println(peopleList);
        System.out.println(peopleSet);
    }

    private static void addElements(Collection collections) {
        collections.add(new Person(5, "Bob"));
        collections.add(new Person(3, "Tom"));
        collections.add(new Person(2, "Katy"));
        collections.add(new Person(4, "George"));
        collections.add(new Person(1, "Testing"));
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
            return -1;
        } else {
            return 0;
        }
    }
}

class Person implements Comparable<Person> {
    private int id;
    private String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (id != person.id) return false;
        return Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(Person o) {
        if (this.name.length() > o.getName().length()) {
            return 1;
        } else if (this.name.length() < o.getName().length()) {
            return -1;
        } else {
            return 0;
        }
    }
}