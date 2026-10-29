import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

// вспомогательный класс с методами для работы с файлами,
// используется потоками Thread1 и Thread2
public class Filee {

    // записывает один символ в конец файла
    public static void writeChar(java.io.File file, char c) {
        try (FileOutputStream fos = new FileOutputStream(file, true)) {
            fos.write(c);
        } catch (IOException e) {
            System.out.println("Ошибка записи символа: " + e.getMessage());
        }
    }

    // записывает строку в конец файла
    public static void writeString(java.io.File file, String s) {
        try (FileOutputStream fos = new FileOutputStream(file, true)) {
            fos.write(s.getBytes());
        } catch (IOException e) {
            System.out.println("Ошибка записи строки: " + e.getMessage());
        }
    }

    // возвращает текущий размер файла в байтах
    public static long getFileSize(java.io.File file) {
        return file.length();
    }

    // текущая дата и время для записи в файл, формат ГГГГММДД ЧЧММСС
    public static String getCurrentDateTime() {
        Calendar c = Calendar.getInstance();
        return String.format("%04d-%02d-%02d %02d:%02d:%02d",
                c.get(Calendar.YEAR),
                c.get(Calendar.MONTH) + 1,
                c.get(Calendar.DAY_OF_MONTH),
                c.get(Calendar.HOUR_OF_DAY),
                c.get(Calendar.MINUTE),
                c.get(Calendar.SECOND));
    }

    // текущая дата и время для имени файла, формат ГГГГММДД_ЧЧММСС
    public static String getCurrentDateTimeForName() {
        Calendar c = Calendar.getInstance();
        return String.format("%04d%02d%02d_%02d%02d%02d",
                c.get(Calendar.YEAR),
                c.get(Calendar.MONTH) + 1,
                c.get(Calendar.DAY_OF_MONTH),
                c.get(Calendar.HOUR_OF_DAY),
                c.get(Calendar.MINUTE),
                c.get(Calendar.SECOND));
    }

    // переименовывает файл, при неудаче выводит сообщение
    public static void renameFile(java.io.File oldFile, java.io.File newFile) {
        if (!oldFile.renameTo(newFile)) {
            System.out.println("Не удалось переименовать файл");
        }
    }

    // обнуляет содержимое файла
    public static void resetFile(java.io.File file) {
        try (FileOutputStream fos = new FileOutputStream(file, false)) {
            fos.write(new byte[0]);
        } catch (IOException e) {
            System.out.println("Ошибка очистки файла: " + e.getMessage());
        }
    }
}