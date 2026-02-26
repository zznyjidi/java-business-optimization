package filters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import jobs.Employee;
import jobs.Title;

public class FilterReplacements implements EmployeeFilter {
    public Map<Title, List<Employee>> filterEmployee(Map<Title, List<Employee>> employeeMap) {
        Map<Title, List<Employee>> filteredEmployeeMap = new HashMap<>();

        for (Entry<Title, List<Employee>> titleEntry : employeeMap.entrySet()) {
            List<Employee> filterEmployees = new ArrayList<>();

            for (Employee employee : titleEntry.getValue()) {
                boolean isReplaceable = false;
                for (Employee comparingEmployee : titleEntry.getValue()) {
                    if (employee.isCompletelyWorse(comparingEmployee)) {
                        isReplaceable = true;
                        break;
                    }
                }

                if (!isReplaceable)
                    filterEmployees.add(employee);
            }

            filteredEmployeeMap.put(titleEntry.getKey(), filterEmployees);
        }

        return filteredEmployeeMap;
    }
}
