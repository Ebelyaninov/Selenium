package multithreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    public static void main(String[] args) throws InterruptedException {
        Task task = new Task();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                task.firstThread();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                task.secondThread();
            }
        });

        thread.start();
        thread2.start();
        thread.join();
        thread2.join();
        task.showCounter();
    }
}

class Task {
    private int counter;
    private Lock lock = new ReentrantLock();

    private void increament() {
        for (int i = 0; i < 10000; i++) {
            counter++;
        }
    }

    public void firstThread() {
        lock.lock();
        increament();
        lock.unlock();
    }

    public void secondThread() {
        lock.lock();
        increament();
        lock.unlock();
    }

    public void showCounter() {
        System.out.println(counter);
    }
}