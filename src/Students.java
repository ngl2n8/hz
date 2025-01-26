import java.io.*;
import java.util.Scanner;

public class Students {
    public static void Students() {
        String filePath = "C:\\Users\\karic\\IdeaProjects\\name\\src\\students.txt";
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(new File(filePath)));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Файл не найден: " + e.getMessage());
        }

            try (Scanner scanner = new Scanner(in)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    int dotIndex = line.indexOf(".");
                    if (dotIndex != -1 && dotIndex + 1 < line.length()) {
                        String result = line.substring(dotIndex + 1).trim();
                        System.out.println(result);
                    } else {
                        System.out.println("Точка не найдена или после точки нет текста.");
                    }
                }
            } catch (RuntimeException e) {
                System.out.println("Ошибка при чтении файла: " + e.getMessage());
            }
        }

    public static void main(String[] args) {
        Students();
    }
}