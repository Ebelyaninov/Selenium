package multithreading;

public class Test {
    public static void main(String[] args) {
//        MyThread myThread = new MyThread();
//        myThread.start();

        Thread thread = new Thread(new Runner());
        thread.start();
        System.out.println("Hello from main thread");
    }
}
