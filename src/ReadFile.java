import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadFile {
    public static List<Employee> readEmployeeFile(File file) throws FileNotFoundException {
        List<Employee> employees = new ArrayList<>();

        try (Scanner fileReader = new Scanner(file)) {
            fileReader.nextLine();

            fileReader.useDelimiter(",|\n|\r\n");
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
}
