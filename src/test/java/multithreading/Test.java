package multithreading;

import lombok.SneakyThrows;

import java.util.Scanner;

public class Test {
    @SneakyThrows
    public static void main(String[] args) {
//        MyThread myThread = new MyThread();
//        myThread.start();

//        Thread thread = new Thread(new Runner());
//        thread.start();
//        System.out.println("Hello from main thread");

//        RunnerWithWhile myThread = new RunnerWithWhile();
//        myThread.start();
//
//        Scanner scanner = new Scanner(System.in);
//        scanner.next();
//
//        myThread.shutDown();

//        Synchronize synchronize = new Synchronize();
//        synchronize.doWork();

        Worker worker = new Worker();
        worker.run();
    }
}
