class Starter {

    public static void startRunnables(Runnable[] runnables) {
        // implement the method
        for (Runnable run :
                runnables) {
            new Thread(run).start();
        }
    }
}