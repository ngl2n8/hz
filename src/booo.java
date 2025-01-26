//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class booo {
//    String filePath = "src/students.txt"; // Путь к файлу
//
//    // Считываем данные из файла в список
//    List<String> lines = new ArrayList<>();
//
//        try (
//    BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
//        String line;
//        while ((line = reader.readLine()) != null) {
//            lines.add(line); // Добавляем строку в список
//        }
//    } catch (
//    IOException e) {
//        System.out.println("Ошибка при чтении файла: " + e.getMessage());
//    }
//        lines.set(5, "New1231 text");
//}
