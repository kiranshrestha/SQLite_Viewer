import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner s = new Scanner(System.in);

        String inputDate = s.nextLine();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            System.out.println(new SimpleDateFormat("MM/dd/yyyy").format(sdf.parse(inputDate)));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}