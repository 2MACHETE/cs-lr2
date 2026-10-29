import java.util.Scanner;
import java.util.concurrent.Semaphore;

// запускает оба потока и завершает их по нажатию Enter
public class Main {

    public static void main() {
        java.io.File file = new java.io.File("currentdata.txt");
        // семафор с одним разрешением работает как мьютекс
        Semaphore semaphore = new Semaphore(1);

        Thread1 first = new Thread1(semaphore, file);
        Thread2 second = new Thread2(semaphore, file);

        first.start();
        second.start();

        System.out.println("Нажмите enter для завершения потоков");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        // поднятие потоков из сна, вызов исключений
        first.interrupt();
        second.interrupt();

        try {
            first.join();
            second.join();
        } catch (InterruptedException e) {
            System.out.println("Главный поток прерван: " + e.getMessage());
        }

        scanner.close();
        System.out.println("Программа завершена");
    }
}