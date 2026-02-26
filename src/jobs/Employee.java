package jobs;

public class Employee {
    String name;
    Title jobTitle;
    int speed;
    int coverage;
    int fulfilment;
    int salary;

    public Employee(String name, String jobTitle, int speed, int coverage, int fulfilment, int salary) {
        this.name = name;
        this.speed = speed;
        this.coverage = coverage;
        this.fulfilment = fulfilment;
        this.salary = salary;

        this.jobTitle = switch (jobTitle) {
            case "Software Engineer" -> Title.SoftwareEngineer;
            case "Marketing Manager" -> Title.MarketingManager;
            case "Sales Representative" -> Title.SalesRepresentative;
            case "Graphic Designer" -> Title.GraphicDesigner;
            case "Financial Analyst" -> Title.FinancialAnalyst;
            case "HR Manager" -> Title.HRManager;
            case "Operations Manager" -> Title.OperationsManager;
            case "Data Scientist" -> Title.DataScientist;
            case "Customer Support Specialist" -> Title.CustomerSupportSpecialist;
            case "Project Manager" -> Title.ProjectManager;
            default -> throw new IllegalArgumentException(jobTitle);
        };
    }

    public String getName() {
        return name;
    }

    public Title getJobTitle() {
        return jobTitle;
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
        sb.append("{");
        sb.append("name:").append(name);
        sb.append(", jobTitle:").append(jobTitle);
        sb.append(", speed:").append(speed);
        sb.append(", coverage:").append(coverage);
        sb.append(", fulfilment:").append(fulfilment);
        sb.append(", salary:").append(salary);
        sb.append('}');
        return sb.toString();
    }
}
