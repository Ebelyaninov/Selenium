package queue;

import org.testng.annotations.Test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class QueueTests {

    @Test(description = "Queue tests")
    void queueTests() {
        Queue<LinkedList> queue = new LinkedList<>();
        Person person1 = new Person(1);
        Person person2 = new Person(2);
        Person person3 = new Person(3);
        Person person4 = new Person(4);
        Person person5 = new Person(5);

        Queue<Person> people = new LinkedList<>();
        people.add(person3);
        people.add(person2);
        people.add(person4);
        people.add(person1);
        people.add(person5);

        for (Person person : people)
            System.out.println(person);

        //Новый элемент не сможем добавить в очередь
        Queue<Person> queeWithLimit = new ArrayBlockingQueue<>(3);
        System.out.println(queeWithLimit.offer(person3));
        System.out.println(queeWithLimit.offer(person2));
        System.out.println(queeWithLimit.offer(person4));
        System.out.println(queeWithLimit.offer(person1));
        System.out.println(queeWithLimit.offer(person5));
        System.out.println(" ");

        System.out.println(queeWithLimit.remove());
        System.out.println(queeWithLimit.peek());
        System.out.println(" ");
        System.out.println(queeWithLimit);
    }
}

class Person {
    private int id;

    public Person(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                '}';
    }
}