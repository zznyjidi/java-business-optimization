package test;

import jobs.Employee;

public class filterTest {
    public static void main(String[] args) {
        Employee employee1 = new Employee(
                "Levi Morris",
                "Sales Representative",
                7, 6, 72,
                82000);
        Employee employee2 = new Employee(
                "Samuel Rivera",
                "Sales Representative",
                7, 6, 71,
                83000);
        IO.println(employee1.isCompletelyWorse(employee2));
        IO.println(employee2.isCompletelyWorse(employee1));
    }
}
