package multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/*
Использываем когда у нас есть ресурс и много потоков использует этот ресурс и мы хотим ограничить доступ к ресурсу
Например: У нас есть сервер и 10 потоков пишут в этот сервер (сервер ресурс и само соеденение - ресурс)
С помощью Semaphor можем делить этот ресурс
 */
public class SemaphoreTest {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(3);

//        semaphore.acquire(); //используем для начала взаемодействия
//        semaphore.release(); //используем для завершения взаемодействия
//        semaphore.availablePermits(); //возвращает допустимые соеденения

        Connection connection = Connection.getConnection();
        ExecutorService executorService = Executors.newFixedThreadPool(200);
        for (int i = 0; i < 200; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        connection.work();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(20, TimeUnit.SECONDS);
    }
}

//SingleTone
class Connection {
    private static Connection connection = new Connection();
    private int connectionsCount;
    private Semaphore semaphore = new Semaphore(10);

    private Connection() {

    }

    public static Connection getConnection() {
        return connection;
    }

    public void work() throws InterruptedException {
        semaphore.acquire();
        try {
            doWork();
        } finally {
            semaphore.release();
        }
    }

    private void doWork() throws InterruptedException {
        synchronized (this) {
            connectionsCount++;
            System.out.println(connectionsCount);
        }
        Thread.sleep(3000);

        synchronized (this) {
            connectionsCount--;
        }
    }
}
