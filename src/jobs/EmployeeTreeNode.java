package jobs;

import java.util.ArrayList;
import java.util.List;

public class EmployeeTreeNode {
    Employee employee;

    int lowestBudget;
    int currentBudgetLeft;
    boolean isLastLayer = false;
    boolean isDeadEnd = false;

    List<EmployeeTreeNode> subNodes = new ArrayList<>();

    public EmployeeTreeNode(Employee employee, int currentBudgetLeft) {
        this.employee = employee;
        this.currentBudgetLeft = currentBudgetLeft;
    }

    public EmployeeTreeNode(List<EmployeeTreeNode> subNodes, int currentBudgetLeft) {
        this.subNodes = subNodes;
        this.currentBudgetLeft = currentBudgetLeft;
    }

    @Override
    public String toString() {
        return "{employee:" + employee + ", isDeadEnd:" + isDeadEnd + ", subNodes:" + subNodes + "}";
    }
}
