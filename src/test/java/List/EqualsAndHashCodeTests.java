package List;

import org.testng.annotations.Test;

import java.util.*;

public class EqualsAndHashCodeTests {

    @Test(description = "equals")
    void equalsTest() {
        Map<Person, String> hashMap = new HashMap<>();
        Set<Person> hashSet = new HashSet<>();
        Set<String> setString = new HashSet<>();

        Person personeOne = new Person(1, "Mike");
        Person personeTwo = new Person(2, "Katty");
        Person personeThree = new Person(1, "Mike");

        hashMap.put(personeOne, "one");
        hashMap.put(personeTwo, "two");
        hashMap.put(personeThree, "three");

        hashSet.add(personeOne);
        hashSet.add(personeTwo);
        hashSet.add(personeThree);

        System.out.println(hashMap);
        System.out.println(hashSet);

    }

    class Person {
        private int id;
        private String name;

        public Person(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }

        //Добавили equals для сравнения наших объектов(сравниваем id и name), Иначе сравниваем ссылки на объекты и получаем разные
        // Из-за этого перезапишем данные
        // {Person{id=1, name='Mike'}=three, Person{id=2, name='Katty'}=two}
        // [Person{id=1, name='Mike'}, Person{id=2, name='Katty'}]
        // Он боьшой и сравнивает весь объект
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Person person = (Person) o;

            if (id != person.id) return false;
            return Objects.equals(name, person.name);
        }

        // {object} произвольной длинны конвертируем в число фиксированной длинны -> {int} и сравниваем целые числа
        @Override
        public int hashCode() {
            int result = id;
            result = 31 * result + (name != null ? name.hashCode() : 0);
            return result;
        }

        /*
            Контракт hashCode() equals()
            1) У двух проверяемых объектов вызываем метод hashCode()
            eсли получили 2 разных числа, то они разные и закончили работу

            2) Если хэш == то -> equals()

            3) equals() -> выдает ответ
         */
    }
}
