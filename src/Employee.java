public class Employee {
    String name;
    String jobtitle;
    int speed;
    int coverage;
    int fulfilment;
    int salary;

    public Employee(String name, String jobtitle, int speed, int coverage, int fulfilment, int salary) {
        this.name = name;
        this.jobtitle = jobtitle;
        this.speed = speed;
        this.coverage = coverage;
        this.fulfilment = fulfilment;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public String getJobTitle() {
        return jobtitle;
    }

    public int getSpeed() {
        return speed;
    }

    public int getCoverage() {
        return coverage;
    }

    public int getFulfilment() {
        return fulfilment;
    }

    public int getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Employee{");
        sb.append("name=").append(name);
        sb.append(", jobtitle=").append(jobtitle);
        sb.append(", speed=").append(speed);
        sb.append(", coverage=").append(coverage);
        sb.append(", fulfilment=").append(fulfilment);
        sb.append(", salary=").append(salary);
        sb.append('}');
        return sb.toString();
    }
}