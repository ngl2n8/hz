import java.io.FileInputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
public class Main {

    static Logger LOGGER;
    static {
        try(FileInputStream ins = new FileInputStream("C:\\Users\\karic\\IdeaProjects\\name\\onlyForLogger\\logger.config")){
            LogManager.getLogManager().readConfiguration(ins);
            LOGGER = Logger.getLogger(Main.class.getName());
        }catch (Exception ignore){
            ignore.printStackTrace();
        }
    }
    public static void main(String[] args) {
        try {
            LOGGER.log(Level.INFO,"Начало main, создаем лист с типизацией Integers");
            List<Integer> ints = new ArrayList<Integer>();
            LOGGER.log(Level.INFO,"присваиваем лист Integers листу без типипзации");
            List empty = ints;
            LOGGER.log(Level.INFO,"присваиваем лист без типипзации листу строк");
            List<String> string = empty;


            LOGGER.log(Level.INFO,"выводим все элементы листа с типизацией Integers в консоль");
            for (Object anInt : ints) {
                System.out.println(anInt);
            }

            LOGGER.log(Level.INFO,"Размер равен " + ints.size());
            LOGGER.log(Level.INFO,"Получим первый элемент");
            Integer integer = ints.get(0);
            LOGGER.log(Level.INFO,"выведем его в консоль");
            System.out.println(integer);

        }catch (Exception e){
            LOGGER.log(Level.WARNING,"что-то пошло не так" , e);
        }

    }
}