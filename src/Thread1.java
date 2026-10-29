import java.util.concurrent.Semaphore;

// первый поток: посимвольно пишет строку, затем время, потом спит 1 секунду
public class Thread1 extends Thread {

    private final Semaphore semaphore;
    private final java.io.File file;

    public Thread1(Semaphore semaphore, java.io.File file) {
        this.semaphore = semaphore;
        this.file = file;
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                // захватываем семафор на время всей записи строки и времени,
                // чтобы второй поток не переименовал файл в середине записи
                semaphore.acquire();
                try {
                    String text = "the first thread writes the time:";
                    for (int i = 0; i < text.length(); i++) {
                        Filee.writeChar(file, text.charAt(i));
                        Thread.sleep(200);
                    }
                    Filee.writeString(file, Filee.getCurrentDateTime() + System.lineSeparator());
                } finally {
                    semaphore.release();
                }
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Первый поток завершён");
        }
    }
}