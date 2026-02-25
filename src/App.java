import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import jobs.Employee;
import jobs.ReadFile;
import jobs.TeamBuilder;

public class App {
    public static void main(String[] args) throws FileNotFoundException {
        List<Employee> employees = ReadFile.readEmployeeFile(new File("people.csv"));
        TeamBuilder builder = new TeamBuilder(employees, 1000000000);
        IO.println(builder.buildTree());
        IO.println(builder.getRootNodes());
    }
}
