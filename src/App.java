import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import employee.Employee;
import employee.ReadFile;
import filters.FilterReplacements;
import jobs.TeamBuilder;

public class App {
    public static void main(String[] args) throws FileNotFoundException {
        List<Employee> employees = ReadFile.readEmployeeFile(new File("people2.csv"));
        TeamBuilder builder = new TeamBuilder(employees, Integer.parseInt(IO.readln("Budget> ")));
        builder.applyFilter(new FilterReplacements());
        IO.println(builder.buildTeam());
    }
}
