import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Zad3 {

    public static void main(String[] args) {
        String[] fileNames = {"part1.txt", "part2.txt", "part3.txt"};
        List<String> students = new ArrayList<>();

        for (String fileName : fileNames) {
            File file = new File(fileName);

            if (!file.exists()) {
                System.out.println("Файл " + fileName + " не найден. Создаем новый файл.");
                try {
                    if (file.createNewFile()) {
                        System.out.println("Файл " + fileName + " успешно создан.");
                    } else {
                        System.out.println("Не удалось создать файл " + fileName + ".");
                    }
                } catch (IOException e) {
                    System.out.println("Ошибка при создании файла " + fileName + ": " + e.getMessage());
                    continue;
                } catch (SecurityException e) {
                    System.out.println("Нет прав доступа для создания файла " + fileName + ".");
                    continue;
                }
            }

            if (!file.canRead()) {
                System.out.println("Нет прав доступа для чтения файла " + fileName + ".");
                continue;
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (!line.isEmpty()) {
                        students.add(line);
                    }
                }
            } catch (IOException e) {
                System.out.println("Ошибка при чтении файла: " + fileName);
                e.printStackTrace();
            }
        }

        Collections.sort(students);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("full_list.txt"))) {
            for (String student : students) {
                writer.write(student);
                writer.newLine();
            }
            System.out.println("Данные успешно объединены и записаны в full_list.txt");
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл full_list.txt");
            e.printStackTrace();
        } catch (SecurityException e) {
            System.out.println("Нет прав доступа для записи в файл full_list.txt.");
        }
    }
}