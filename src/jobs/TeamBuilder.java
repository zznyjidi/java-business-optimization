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

        // Title Array for next layer of tree
        Title[] nextTitles = null;
        if (titles.length > 1)
            nextTitles = Arrays.copyOfRange(titles, 1, titles.length);

        // Build tree
        List<Integer> result = new ArrayList<>();
        for (Employee employee : employeeMap.get(titles[0])) {
            // Check if has enough budget
            if (node.currentBudgetLeft >= employee.salary) {
                // Create node for valid branch in next layer
                EmployeeTreeNode newNode = new EmployeeTreeNode(employee, (node.currentBudgetLeft - employee.salary));
                node.subNodes.add(newNode);
                // Check if next layer is the last layer
                if (titles.length > 1)
                    result.add(buildTree(nextTitles, newNode));
                else {
                    newNode.isLastLayer = true;
                    newNode.lowestBudget = employee.salary;
                    result.add(newNode.lowestBudget);
                }
            }
        }

        // Find min valid cost
        boolean hasValid = false;
        int min = Integer.MAX_VALUE;
        for (int cost : result) {
            if (cost > 0) {
                hasValid = true;
                if (cost < min)
                    min = cost;
            }
        }

        // Calculate lowest budget in branches
        if (node.employee != null)
            node.lowestBudget = node.employee.salary + min;

        // Check if is dead end
        if (!hasValid) {
            node.isDeadEnd = true;
            return -1;
        }
        return min;
    }

    public int buildTree() {
        return buildTree(Title.values(), new EmployeeTreeNode(this.rootNodes, budget));
    }

    public List<EmployeeTreeNode> getRootNodes() {
        return rootNodes;
    }
}
