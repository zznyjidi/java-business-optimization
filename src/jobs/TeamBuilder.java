package jobs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import employee.Employee;
import employee.Title;
import filters.EmployeeFilter;
import score.Score;

public class TeamBuilder {
    Map<Title, List<Employee>> employeeMap = new HashMap<>();
    int budget = 0;

    public TeamBuilder(List<Employee> employees, int budget) {
        this.budget = budget;
        for (Title title : Title.values()) {
            employeeMap.put(title, new ArrayList<>());
        }
        for (Employee employee : employees)
            if (employee.getSalary() < budget)
                employeeMap.get(employee.getJobTitle()).add(employee);
    }

    public void applyFilter(EmployeeFilter filter) {
        employeeMap = filter.filterEmployee(employeeMap);
    }

    public Score buildTeam(Title[] titles, int budgetLeft, Score lastLayerScore) {
        // Check for budget
        if (budgetLeft < 1)
            return new Score();

        Score bestScore = new Score();
        if (titles.length == 1) {
            // Last Layer
            for (Employee employee : employeeMap.get(titles[0])) {
                if (budgetLeft > employee.getSalary()) {
                    Score nodeScore = lastLayerScore.copy().addScore(employee);
                    if (nodeScore.isBetter(bestScore))
                        bestScore = nodeScore;
                }
            }
        } else {
            // Not Last Layer
            Title[] nextTitles = Arrays.copyOfRange(titles, 1, titles.length);

            for (Employee employee : employeeMap.get(titles[0])) {
                // Check if has enough budget
                if (budgetLeft >= employee.getSalary()) {
                    Score nodeScore = buildTeam(nextTitles,
                            budgetLeft - employee.getSalary(),
                            lastLayerScore.copy().addScore(employee));
                    if (nodeScore.isBetter(bestScore))
                        bestScore = nodeScore;
                }
            }
        }
        return bestScore;
    }

    public Score buildTeam() {
        return buildTeam(Title.values(), budget, new Score());
    }
}
