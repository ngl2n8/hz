import java.io.*;
import java.util.*;

public class IHate4Th {

    private static final String FILE_PATH = "students.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Добро пожаловать в систему управления студентами!");
        while (true) {

            System.out.println("Выберите действие:");
            System.out.println("1. Добавить студента");
            System.out.println("2. Вывести всех студентов");
            System.out.println("3. Найти студента");
            System.out.println("4. Удалить студента");
            System.out.println("5. Выйти");
            System.out.print("Ваш выбор: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера

            switch (choice) {
                case 1:
                    addStudent(scanner);
                    break;
                case 2:
                    printAllStudents();
                    break;
                case 3:
                    findStudent(scanner);
                    break;
                case 4:
                    deleteStudent(scanner);
                    break;
                case 5:
                    System.out.println("Выход из программы.");
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private static void addStudent(Scanner scanner) {
        System.out.print("Введите фамилию и имя студента: ");
        String name = scanner.nextLine();
        System.out.print("Введите оценки по предметам (например, Математика: 5, Физика: 4): ");
        String grades = scanner.nextLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(name + " " + grades);
            writer.newLine();
            System.out.println("Студент " + name + " добавлен.");
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

    private static void printAllStudents() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            System.out.println("Все студенты:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }

    private static void findStudent(Scanner scanner) {
        System.out.print("Введите фамилию студента для поиска: ");
        String surname = scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(surname)) {
                    System.out.println("Найден студент: " + line);
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Студент не найден.");
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }

    private static void deleteStudent(Scanner scanner) {
        System.out.print("Введите фамилию студента для удаления: ");
        String surname = scanner.nextLine();

        List<String> students = new ArrayList<>();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith(surname)) {
                    students.add(line);
                } else {
                    found = true;
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
            return;
        }

        if (!found) {
            System.out.println("Студент не найден.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (String student : students) {
                writer.write(student);
                writer.newLine();
            }
            System.out.println("Студент " + surname + " удален.");
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }
}