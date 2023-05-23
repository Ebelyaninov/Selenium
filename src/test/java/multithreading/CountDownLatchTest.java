package multithreading;

import lombok.SneakyThrows;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yevheniibelianinov
 */
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++)
            executorService.submit(new Processor(i, countDownLatch));
        executorService.shutdown();
        for (int i = 0; i < 3; i++) {
            Thread.sleep(1000);
            countDownLatch.countDown();
        }

    }
}

class Processor implements Runnable {
    private CountDownLatch countDownLatch;
    private int id;

    public Processor(int id, CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
        this.id = id;
    }

    @SneakyThrows
    @Override
    public void run() {
        Thread.sleep(3000);
        countDownLatch.await();
        System.out.println("Thread with id " + id + " proceeded");
    }
}
