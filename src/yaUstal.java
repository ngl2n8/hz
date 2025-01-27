import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

class Student {
    private String name;
    private String grade;

    public Student(String name, String grade) {
        this.name = name;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return name + ". " + grade;
    }
}

public class yaUstal {
    private String filePath = "C:\\Users\\karic\\IdeaProjects\\name\\src\\students.txt";

    public List<Student> readStudents() {
        List<Student> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\. ");
                if (parts.length == 2) {
                    students.add(new Student(parts[0], parts[1]));
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
        return students;
    }

    public void writeStudents(List<Student> students) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Student student : students) {
                writer.write(student.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

    public void addStudent(String name, String grade) {
        List<Student> students = readStudents();
        students.add(new Student(name, grade));
        writeStudents(students);
        System.out.println("Студент добавлен.");
    }

    public void deleteStudent(String name) {
        List<Student> students = readStudents();
        students.removeIf(student -> student.getName().equals(name));
        writeStudents(students);
        System.out.println("Студент удален.");
    }

    public void updateStudent(String name, String newGrade) {
        List<Student> students = readStudents();
        for (Student student : students) {
            if (student.getName().equals(name)) {
                student.setGrade(newGrade);
                break;
            }
        }
        writeStudents(students);
        System.out.println("Информация о студенте обновлена.");
    }

    public void printStudents() {
        List<Student> students = readStudents();
        if (students.isEmpty()) {
            System.out.println("Список студентов пуст.");
        } else {
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    public static void main(String[] args) {
        yaUstal manager = new yaUstal();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nВыберите действие:");
            System.out.println("1. Добавить студента");
            System.out.println("2. Удалить студента");
            System.out.println("3. Изменить информацию о студенте");
            System.out.println("4. Показать всех студентов");
            System.out.println("5. Выйти");
            System.out.print("Ваш выбор: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Введите имя студента: ");
                    String name = scanner.nextLine();
                    System.out.print("Введите оценку студента: ");
                    String grade = scanner.nextLine();
                    manager.addStudent(name, grade);
                    Main.LOGGER.log(Level.INFO,"студенту"+ " "+ name + "добавлена оценка" + grade);
                    break;
                case 2:
                    System.out.print("Введите имя студента для удаления: ");
                    String deleteName = scanner.nextLine();
                    manager.deleteStudent(deleteName);
                    Main.LOGGER.log(Level.WARNING,"удален студент"+deleteName+"(возможна ошибка)");
                    break;
                case 3:
                    System.out.print("Введите имя студента для изменения: ");
                    String updateName = scanner.nextLine();
                    System.out.print("Введите новую оценку: ");
                    String newGrade = scanner.nextLine();
                    manager.updateStudent(updateName, newGrade);
                    Main.LOGGER.log(Level.INFO,"студент "+updateName +" изменил свою оценку на "+newGrade );
                    break;
                case 4:
                    manager.printStudents();
                    Main.LOGGER.log(Level.INFO,"пользователь вывел всех студентов");
                    break;
                case 5:
                    System.out.println("Выход из программы.");
                    Main.LOGGER.log(Level.INFO,"выход");
                    return;
                default:
                    Main.LOGGER.log(Level.WARNING ,"произошла ошибка,метод ретурнул дефолт");
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }
}