public class Modifier {
    String name;
    double speed;
    double coverage;
    double happiness; 
    double salary;

    public Modifier(String name, double speed, double coverage, double happiness, double salary) {
        this.coverage = coverage;
        this.happiness = happiness;
        this.salary = salary;
        this.speed = speed;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getSpeed() {
        return speed;
    }

    public double getCoverage() {
        return coverage;
    }

    public double getHappiness() {
        return happiness;
    }

    public double getSalary(){
        return salary;
    }
}
