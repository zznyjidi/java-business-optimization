package jobs;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class GetTeam {

    public static ArrayList<Employee> all = new ArrayList<>();
    static Title[] titles = Title.values();
    static Team best = null;

    static int[] minSalRemain, maxCovRemain, maxSpdRemain, maxFulRemain;

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            if (!sc.hasNextInt()) return;
            int budget = sc.nextInt();
            
            try {
                all.addAll(ReadFile.readEmployeeFile(new File("people2.csv")));
            } catch (FileNotFoundException e) {
                System.out.println("not found");
                return;
            }

            ArrayList<Employee>[] groups = groupByTitle(all);
            for (int i = 0; i < groups.length; i++) {
                groups[i] = pruneDominatedEmployees(groups[i]);
                // Sort by Salary to ensure the fastest budget pruning
                groups[i].sort(Comparator.comparingInt(Employee::getSalary));
            }

            precomputeBounds(groups);
            build(0, groups, new ArrayList<>(), 0, 0, 0, 0, budget);

            printResult();
        }
    }

    private static void precomputeBounds(ArrayList<Employee>[] groups) {
        int n = titles.length;
        minSalRemain = new int[n + 1];
        maxCovRemain = new int[n + 1];
        maxSpdRemain = new int[n + 1];
        maxFulRemain = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            int minS = Integer.MAX_VALUE, maxC = 0, maxS = 0, maxF = 0;
            for (Employee e : groups[i]) {
                minS = Math.min(minS, e.getSalary());
                maxC = Math.max(maxC, e.getCoverage());
                maxS = Math.max(maxS, e.getSpeed());
                maxF = Math.max(maxF, e.getFulfilment());
            }
            minSalRemain[i] = minSalRemain[i+1] + (minS == Integer.MAX_VALUE ? 0 : minS);
            maxCovRemain[i] = maxCovRemain[i+1] + maxC;
            maxSpdRemain[i] = maxSpdRemain[i+1] + maxS;
            maxFulRemain[i] = maxFulRemain[i+1] + maxF;
        }
    }

    private static void build(int idx, ArrayList<Employee>[] groups, ArrayList<Employee> current,
                             int cost, int cov, int spd, int ful, int budget) {
        
        // 1. Hard Budget Pruning
        if (cost + minSalRemain[idx] > budget) return;

        // 2. Safe Metric Pruning
        if (best != null) {
            // Only prune if this branch CANNOT reach the same 'capped' stage as best
            int bestCapCov = Math.min(best.coverage, 100);
            int bestCapSpd = Math.min(best.speed, 100);

            if (cov + maxCovRemain[idx] < bestCapCov) return;
            if (bestCapCov == 100 && (spd + maxSpdRemain[idx] < bestCapSpd)) return;
            
            // If we are both at 100/100, prune if we can't beat Fulfillment
            if (bestCapCov == 100 && bestCapSpd == 100) {
                if (ful + maxFulRemain[idx] < best.fulfilment) return;
            }
        }

        if (idx == titles.length) {
            if (best == null || isBetter(cov, spd, ful, cost)) {
                best = new Team(current, cost, cov, spd, ful);
            }
            return;
        }

        for (Employee p : groups[idx]) {
            int nCost = cost + p.getSalary();
            if (nCost + minSalRemain[idx + 1] > budget) break; 

            current.add(p);
            build(idx + 1, groups, current, nCost, 
                  cov + p.getCoverage(), spd + p.getSpeed(), ful + p.getFulfilment(), budget);
            current.remove(current.size() - 1);
        }
    }

    private static boolean isBetter(int cov, int spd, int ful, int cost) {
        int c1 = Math.min(cov, 100), c2 = Math.min(best.coverage, 100);
        if (c1 != c2) return c1 > c2;

        int s1 = Math.min(spd, 100), s2 = Math.min(best.speed, 100);
        if (s1 != s2) return s1 > s2;

        if (ful != best.fulfilment) return ful > best.fulfilment;
        if (cost != best.cost) return cost < best.cost;
        if (cov != best.coverage) return cov > best.coverage;
        return spd > best.speed;
    }

    private static ArrayList<Employee> pruneDominatedEmployees(ArrayList<Employee> group) {
        ArrayList<Employee> pruned = new ArrayList<>();
        for (Employee a : group) {
            boolean dominated = false;
            for (Employee b : group) {
                if (a == b) continue;
                if (b.getSalary() <= a.getSalary() && b.getCoverage() >= a.getCoverage() && 
                    b.getSpeed() >= a.getSpeed() && b.getFulfilment() >= a.getFulfilment()) {
                    if (b.getSalary() < a.getSalary() || b.getCoverage() > a.getCoverage() || 
                        b.getSpeed() > a.getSpeed() || b.getFulfilment() > a.getFulfilment()) {
                        dominated = true; break;
                    }
                }
            }
            if (!dominated) pruned.add(a);
        }
        return pruned;
    }

    private static ArrayList<Employee>[] groupByTitle(ArrayList<Employee> allPeople) {
        ArrayList<Employee>[] groups = new ArrayList[titles.length];
        for (int i = 0; i < titles.length; i++) groups[i] = new ArrayList<>();
        for (Employee p : allPeople) groups[p.getJobTitle().ordinal()].add(p);
        return groups;
    }

    private static void printResult() {
        if (best == null) { System.out.println("Not enough budget"); return; }
        System.out.println("Best team:");
        for(Employee p : best.people) System.out.println(p.getName() + " | " + p.getJobTitle() + " | $" + p.getSalary());
        System.out.println("Cost=" + best.cost + " Coverage=" + best.coverage + " Speed=" + best.speed + " Fulfilment=" + best.fulfilment);
    }

    static class Team {
        ArrayList<Employee> people;
        int cost, coverage, speed, fulfilment;
        Team(ArrayList<Employee> p, int c, int cv, int s, int f) {
            this.people = new ArrayList<>(p); this.cost = c; this.coverage = cv; this.speed = s; this.fulfilment = f;
        }
    }
}