package multithreading;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerPattern {
    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    produce();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    consumer();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        thread.start();
        thread2.start();
        thread.join();
        thread2.join();
    }

    private static void produce() throws InterruptedException {
        Random random = new Random();
        while (true) {
            queue.put(random.nextInt(100));
        }
    }

    private static void consumer() throws InterruptedException {
        int i = 0;
        while (true ||  i == 40) {
            Random random = new Random();
            Thread.sleep(100);

            if (random.nextInt(10) == 5) {
                System.out.println(queue.take());
                System.out.println("Queue size is " + queue.size());
            }
            i++;
        }
    }
}
