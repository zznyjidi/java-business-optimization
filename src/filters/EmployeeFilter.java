package filters;

import java.util.List;
import java.util.Map;

import jobs.Employee;
import jobs.Title;

public interface EmployeeFilter {
    public Map<Title, List<Employee>> filterEmployee(Map<Title, List<Employee>> employeeMap);
}
