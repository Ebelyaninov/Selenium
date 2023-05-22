package multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyThread extends Thread {
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Hello from MyThread " + i);
        }
    }
}

class Runner implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Hello from MyThread " + i);
        }
    }
}

class RunnerWithWhile extends Thread {
    //volatile не будет кэшироватся и (убераем проблему когерантности кэшей)
    //Если 2 потока пишут в переменную. то ключевое слово не сработает
    private volatile boolean running = true;
    int i = 0;
    public void run() {
        while (running == true || i == 30) {
            System.out.println("Hello!");
            try {
                Thread.sleep(1000 );
                i++;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void shutDown() {
        this.running = false;
    }
}


class Synchronize {
    private int counter;
    public void doWork() throws InterruptedException {

        /*
            1: 100 -> 101 -> 101 _> 102 -> 102 -> 103 -> 103
            2: 100 -> 101 -> 101 -> 101
         */
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++)
                    increment();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++)
                    //counter= counter + 1; // counter++
                    increment();
            }
        });

        thread.start();
        thread2.start();

        thread.join();
        thread2.join();

        System.out.println(counter);
    }

    //Синхронизируемся на объекте
    //Synchronize synchronize = new Synchronize();
    public synchronized void increment() {
        counter++;
    }

    //Аналогичный increment
    public void incrementExample() {
        synchronized (this) {
            counter++;
        }
    }
}

class Worker {
    private Random random = new Random();
    private Object lock1 = new Object();
    private Object lock2 = new Object();
    private List<Integer> list = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();

    public void addTolist() {
        synchronized (lock1) {
            list.add(random.nextInt(100));
        }
    }

    public void addTolist2() {
        synchronized (lock2) {
            list2.add(random.nextInt(100));
        }
    }

    public void work() {
        for (int i = 0; i < 1000; i++) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            addTolist();
            addTolist2();
        }
    }

    public List<Integer> returnList() {
        return this.list;
    }

    public List<Integer> returnList2() {
        return this.list2;
    }

    public void run() {
        long bofore = System.currentTimeMillis();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                work();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                work();
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

        long after = System.currentTimeMillis();
        System.out.println("Program tool " + (after - bofore) + " ms to run");

        System.out.println("List1 : " + list.size());
        System.out.println("List2 : " + list2.size());
    }
}
