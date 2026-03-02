import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import employee.Employee;
import employee.ReadFile;
import filters.FilterReplacements;
import jobs.TeamBuilder;

public class App {
    public static void main(String[] args) throws FileNotFoundException {
        List<Employee> employees = ReadFile.readEmployeeFile(new File("people.csv"));
        TeamBuilder builder = new TeamBuilder(employees, 1000000);
        builder.applyFilter(new FilterReplacements());
        IO.println(builder.buildTree());
        IO.println(builder.getRootNodes());
    }
}
