import java.io.FileNotFoundException;
import java.util.Scanner;

public class CommandLineRunner {
    private Scanner in = new Scanner(System.in);
    Processor processor = new Processor();

    public void workingprocess() {
        while (true) {
            processor.proceed(in.nextLine());
        }
    }
}
