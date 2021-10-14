class Info {

    public static void printCurrentThreadInfo() {
        Thread t = Thread.currentThread();
        System.out.printf("name: %s\n" +
                "priority: %d\n", t.getName(), t.getPriority());
    }
}