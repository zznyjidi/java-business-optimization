package jobs;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class GetTeam {

    public static ArrayList<Employee> all = new ArrayList<>();

    static String[] titles = {
            "SoftwareEngineer",
            "MarketingManager",
            "SalesRepresentative",
            "GraphicDesigner",
            "FinancialAnalyst",
            "HRManager",
            "OperationsManager",
            "DataScientist",
            "CustomerSupportSpecialist",
            "ProjectManager"
    };

    static Team best = null;

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int budget = sc.nextInt();
            try {
                all.addAll(ReadFile.readEmployeeFile(new File("people.csv")));
            } catch (FileNotFoundException e) {
                System.out.println("not found");
                return;
            }

            ArrayList<Employee>[] groups = groupByTitle(all);

            // try all
            build(0, groups, new ArrayList<>(), 0, 0, 0, 0, budget);

            if (best == null) {
                System.out.println("Not enough budget");
                return;
            }

            System.out.println("Best team:");
            for (Employee p : best.people) {
                System.out.println(p.getName() + " | " + p.getJobTitle() + " | $" + p.getSalary());
            }
            System.out.println("Cost=" + best.cost + " Coverage=" + best.coverage
                    + " Speed=" + best.speed + " Fulfilment=" + best.fulfilment);
        }
    }

    private static void build(int index,
            ArrayList<Employee>[] groups,
            ArrayList<Employee> current,
            int cost, int cov, int spd, int ful,
            int budget) {

        if (index == titles.length) {
            if (cost <= budget) {
                Team now = new Team(current, cost, cov, spd, ful);
                if (best == null || now.betterThan(best))
                    best = now;
            }
            return;
        }

        for (Employee person : groups[index]) {
            current.add(person);

            build(index + 1, groups, current,
                    cost + person.getSalary(),
                    cov + person.getCoverage(),
                    spd + person.getSpeed(),
                    ful + person.getFulfilment(),
                    budget);

            current.remove(current.size() - 1);
        }
    }

    private static ArrayList<Employee>[] groupByTitle(ArrayList<Employee> allPeople) {
        @SuppressWarnings("unchecked")
        ArrayList<Employee>[] groups = new ArrayList[titles.length];
        for (int i = 0; i < titles.length; i++)
            groups[i] = new ArrayList<>();

        for (Employee person : allPeople) {
            for (int i = 0; i < titles.length; i++) {
                if (person.getJobTitle().equals(titles[i])) {
                    groups[i].add(person);
                    break;
                }
            }
        }
        return groups;
    }

    static class Team {
        ArrayList<Employee> people;
        int cost, coverage, speed, fulfilment;

        Team(ArrayList<Employee> people, int cost, int coverage, int speed, int fulfilment) {
            this.people = new ArrayList<>(people);
            this.cost = cost;
            this.coverage = coverage;
            this.speed = speed;
            this.fulfilment = fulfilment;
        }

        private int stage() {
            if (coverage < 100)
                return 0;
            if (speed < 100)
                return 1;
            return 2;
        }

        boolean betterThan(Team other) {
            int s1 = this.stage();
            int s2 = other.stage();
            if (s1 != s2)
                return s1 > s2;

            if (s1 == 0) {
                if (coverage != other.coverage)
                    return coverage > other.coverage;
                if (speed != other.speed)
                    return speed > other.speed;
                if (fulfilment != other.fulfilment)
                    return fulfilment > other.fulfilment;
                return cost < other.cost;
            }

            if (s1 == 1) {
                if (speed != other.speed)
                    return speed > other.speed;
                if (coverage != other.coverage)
                    return coverage > other.coverage;
                if (fulfilment != other.fulfilment)
                    return fulfilment > other.fulfilment;
                return cost < other.cost;
            }

            if (fulfilment != other.fulfilment)
                return fulfilment > other.fulfilment;
            if (speed != other.speed)
                return speed > other.speed;
            if (coverage != other.coverage)
                return coverage > other.coverage;
            return cost < other.cost;
        }
    }
}
