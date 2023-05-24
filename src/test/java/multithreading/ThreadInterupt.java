package multithreading;

import java.util.Random;

public class ThreadInterupt {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                for (int i = 0; i < 100_000_000; i++) {
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("Thread was interrupted");
                        break;
                    }
                    Math.sin(random.nextDouble());
                }
            }
        });
        System.out.println("Starting thread");

        thread.start();
        Thread.sleep(3000);

        thread.interrupt();

        thread.join();
        System.out.println("Thread has finished");
    }
}
