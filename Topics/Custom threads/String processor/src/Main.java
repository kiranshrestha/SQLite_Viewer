import java.util.Locale;
import java.util.Scanner;

class StringProcessor extends Thread {

    final Scanner scanner = new Scanner(System.in); // use it to read string from the standard input

    @Override
    public void run() {
        // implement this method
        while (true) {
            String word = scanner.nextLine();
            if (word.equals(word.toUpperCase(Locale.ROOT))) {
                System.out.println("FINISHED");
                break;
            } else {
                System.out.println(word.toUpperCase(Locale.ROOT));
            }
        }
    }
}