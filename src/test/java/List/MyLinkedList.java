package List;

import java.util.Arrays;

public class MyLinkedList {
    private Node head;
    private int size;

    public void add(int value) {
        //Если это первое добавление в список
        if (head == null) {
            //то здесь будет какая-то логика
            this.head = new Node(value);
        } else {
            Node temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            // [1] -> [2] -> [3] Хэд не трогаем и создаем новую ссылку на хэд, прошлись по листу и нашли последний элемент и завершим цикл
            // и сейчас сетим в конец спика значение
            temp.setNext(new Node(value)); // [1] -> [2] -> [3] -> [4]
        }
        size++;
    }

    public String toString() {
        //создать массив который = size нашего списка
        int[] result = new int[size];
        int idx = 0;
        Node temp = head;
        // [1] -> [2] -> [3]  берем первый элемент и записываем в резалт
        while (temp != null) {
            //Продвигаемся по списку и каждый раз в массив добавляем новое значение узла
            result[idx++] = temp.getValue();
            temp = temp.getNext();
        }
        return Arrays.toString(result);
    }

    public int get(int index) {
        int currnetIndex = 0;
        Node temp = head;
        // [1] -> [2] -> [3]  указали 1
        // currnetIndex != 1 -> else -> currnetIndex == 1
        // currnetIndex == 1 -> return 2
        while (temp != null) {
            if (currnetIndex == index) {
                return temp.getValue();
            } else {
                temp = temp.getNext();
                currnetIndex++;
            }
        }

        throw new IllegalArgumentException();
    }

    public void remove(int index) {
        if (index == 0) {
            head = head.getNext();
            size--;
            return;
        }

        int currnetIndex = 0;
        Node temp = head;
        // [1] -> [2] -> [3]  указали 1  нам нужно дойти не до двойки, а до элемента 1
        // currnetIndex != 1 -> else -> currnetIndex == 1
        // currnetIndex == 1 -> return 2
        while (temp != null) {
            //Or
            //((currnetIndex +1) == index)
            if (currnetIndex == index - 1) {
                //Текущему элементу назначаем новую ссылку
                // [1] -> [2] -> [3] получаем 3 элемент
                temp.setNext(temp.getNext().getNext());
                size--;
                return;
            } else {
                temp = temp.getNext();
                currnetIndex++;
            }
        }
    }

    private static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
