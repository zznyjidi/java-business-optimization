import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadFile {
    final static String CSV_DELIMITER = ",|\n|\r\n";

    public static List<Employee> readEmployeeFile(File file) throws FileNotFoundException {
        List<Employee> employees = new ArrayList<>();

        try (Scanner fileReader = new Scanner(file)) {
            fileReader.nextLine();

            fileReader.useDelimiter(CSV_DELIMITER);
            while (fileReader.hasNextLine())
                employees.add(new Employee(
                        fileReader.next(),
                        fileReader.next(),
                        Integer.parseInt(fileReader.next()),
                        Integer.parseInt(fileReader.next()),
                        Integer.parseInt(fileReader.next()),
                        Integer.parseInt(fileReader.next())));
        }

        return employees;
    }

    public List<Modifier> readTrainFile(File file) throws FileNotFoundException{
        List<Modifier> modifiers = new ArrayList<>();

        try (Scanner trainEmployee = new Scanner(file)){
            trainEmployee.nextLine();

            trainEmployee.useDelimiter(CSV_DELIMITER);
            while (trainEmployee.hasNextLine())
                modifiers.add(new Modifier(
                    trainEmployee.next(),
                    Double.parseDouble(trainEmployee.next()),
                    Double.parseDouble(trainEmployee.next()),
                    Double.parseDouble(trainEmployee.next()),
                    Double.parseDouble(trainEmployee.next())));
        }
        return modifiers;
    }
}
