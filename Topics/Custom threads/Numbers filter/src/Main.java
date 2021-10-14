import java.util.Scanner;

class NumbersFilter extends Thread {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void run() {
        while (true) {
            int num = scanner.nextInt();
            scanner.nextLine();
            if (num == 0) {
                break;
            } else {
                if (num % 2 == 0) {
                    System.out.println(num);
                }
            }
        }
    }
}