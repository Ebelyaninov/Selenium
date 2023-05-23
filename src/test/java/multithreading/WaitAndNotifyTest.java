package multithreading;

import lombok.SneakyThrows;

import java.util.Scanner;

public class WaitAndNotifyTest {
    public static void main(String[] args) {
        ProducerConsumer wn = new ProducerConsumer();

        Thread thread1 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                wn.producer();
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                wn.consumer();
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

class WaitAndNotify {
    public void produce() throws InterruptedException {
        synchronized (this) {
            System.out.println("Producer thread was started... ");
            //Аналогично produce, но запись правильная ниже
            //this.wait();
            wait();  //1 - отдаем instrisic lock, и другие могу забрать ключ и синхронизироваться на даном объекте
            // ждем пока будет вызван notify().
            System.out.println("Producer thread was resumed... ");
        }
    }

    public void consumer() throws InterruptedException {
        Thread.sleep(2000);
        Scanner scanner = new Scanner(System.in);
        synchronized (this) {
            System.out.println("Waiting for return key pressed: ");
            scanner.nextLine();
            notify(); //notifyAll пробуждаем все потоки
            Thread.sleep(5000);
        }
    }
}
