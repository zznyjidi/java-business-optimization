package filters;

import java.util.List;
import java.util.Map;

import employee.Employee;
import employee.Title;

public interface EmployeeFilter {
    public Map<Title, List<Employee>> filterEmployee(Map<Title, List<Employee>> employeeMap);
}
