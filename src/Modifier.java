public class Modifier {
    String name;
    double speed;
    double coverage;
    double happiness; 

    public Modifier(double coverage, double happiness, String name, double speed) {
        this.coverage = coverage;
        this.happiness = happiness;
        this.name = name;
        this.speed = speed;
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
}
