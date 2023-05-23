package multithreading;

import lombok.SneakyThrows;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumerPatternTwo {
    public static void main(String[] args) {
        ProducerConsumer pc = new ProducerConsumer();

        Thread thread1 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                pc.producer();
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                pc.consumer();
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}

class ProducerConsumer {
    private Queue<Integer> queue = new LinkedList<>();
    private final int LIMIT = 10;
    private final Object lock = new Object();

    public void producer() throws InterruptedException {
        int value = 0;

        while (true) {
            synchronized (lock) {
                while (queue.size() == LIMIT) {
                    lock.wait();
                }
                queue.offer(value++);
                lock.notify();
            }
        }
    }

    public void consumer() throws InterruptedException {

        synchronized (this) {
            while (true) {
                synchronized (lock) {
                    while (queue.size() == 0) {
                        lock.wait();
                    }

                    int value = queue.poll();
                    System.out.println(value);
                    System.out.println("Queue size is " + queue.size());
                    lock.notify();
                }

                Thread.sleep(1000);
            }
        }
    }
}
