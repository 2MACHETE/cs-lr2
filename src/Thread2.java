import java.util.concurrent.Semaphore;

// второй поток: раз в 10 секунд проверяет размер файла и при превышении
// 200 байт сохраняет его под уникальным именем и обнуляет исходный
public class Thread2 extends Thread {

    private final Semaphore semaphore;
    private final java.io.File file;

    public Thread2(Semaphore semaphore, java.io.File file) {
        this.semaphore = semaphore;
        this.file = file;
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                Thread.sleep(10000);
                // захватываем семафор перед проверкой и переименованием,
                // чтобы не мешать первому потоку во время записи
                semaphore.acquire();
                try {
                    if (Filee.getFileSize(file) > 200) {
                        java.io.File newFile = new java.io.File("currentdata_" + Filee.getCurrentDateTimeForName() + ".txt");
                        Filee.renameFile(file, newFile);
                        Filee.resetFile(file);
                        System.out.println("Файл сохранён как: " + newFile.getName());
                    }
                } finally {
                    semaphore.release();
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Второй поток завершён");
        }
    }
}