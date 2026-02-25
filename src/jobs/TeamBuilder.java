package jobs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeamBuilder {
    Map<Title, List<Employee>> employeeMap = new HashMap<>();
    int budget = 0;

    List<EmployeeTreeNode> rootNodes = new ArrayList<>();

    public TeamBuilder(List<Employee> employees, int budget) {
        this.budget = budget;
        for (Title title : Title.values()) {
            employeeMap.put(title, new ArrayList<>());
        }
        for (Employee employee : employees)
            if (employee.getSalary() < budget)
                employeeMap.get(employee.jobTitle).add(employee);
    }

    public int buildTree(Title[] titles, EmployeeTreeNode node) {
        // Check for budget
        if (node.currentBudgetLeft < 1)
            return -1;

        if (titles.length < 1)
            return -1;
        Title[] nextTitles = Arrays.copyOfRange(titles, 1, titles.length);

        // Build tree
        List<Integer> result = new ArrayList<>();
        for (Employee employee : employeeMap.get(titles[0])) {
            // Check if has enough budget
            if (node.currentBudgetLeft >= employee.salary) {
                // Check this node
                EmployeeTreeNode newNode = new EmployeeTreeNode(employee, (node.currentBudgetLeft - employee.salary));
                node.subNodes.add(newNode);
                // Check if has next layer
                if (titles.length > 0)
                    result.add(buildTree(nextTitles, newNode));
                else
                    newNode.isLastLayer = true;
            }
        }

        if (titles.length > 0) {
            // Find min valid budget & max budget
            int min = Integer.MAX_VALUE;
            int max = -1;
            for (int budget : result) {
                if (budget > max)
                    max = budget;
                if (budget < min && budget > 0)
                    min = budget;
            }

            if (node.employee != null)
                node.lowestBudget = node.employee.salary + min;

            // Check if is dead end
            if (max == -1) {
                node.isDeadEnd = true;
                return -1;
            }
            return min;
        } else {
            node.lowestBudget = node.employee.salary;
            return node.lowestBudget;
        }
    }

    public int buildTree() {
        return buildTree(Title.values(), new EmployeeTreeNode(this.rootNodes, budget));
    }

    public List<EmployeeTreeNode> getRootNodes() {
        return rootNodes;
    }
}
