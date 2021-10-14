public class Main {

    public static void main(String[] args) {

        // create threads and start them using the class RunnableWorker
        new Thread(new RunnableWorker(), "worker-hello").start();
        new Thread(new RunnableWorker(), "worker-test").start();
        new Thread(new RunnableWorker(), "worker-run").start();
    }
}

// Don't change the code below       
class RunnableWorker implements Runnable {

    @Override
    public void run() {
        final String name = Thread.currentThread().getName();

        if (name.startsWith("worker-")) {
            System.out.println("too hard calculations...");
        } else {
            return;
        }
    }
}