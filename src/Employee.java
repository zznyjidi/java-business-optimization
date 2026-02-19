public class Employee{
    String name;
    String jobtitle;
    int speed;
    int coverage;
    int fulfilment;
    int salary;

    public Employee(String name, String jobtitle, int speed, int coverage, int fulfilment, int salary){
        this.name = name;
        this.jobtitle = jobtitle;
        this.speed = speed;
        this.coverage = coverage;
        this.fulfilment = fulfilment;
        this.salary = salary;
    }

    public String getName(){
        return name;
    }

    public String getJobTitle(){
        return jobtitle;
    }

    public int getSpeed(){
        return speed;
    }

    public int getCoverage(){
        return coverage;
    }

    public int getFulfilment(){
        return fulfilment;
    }

    public int getSalary(){
        return salary;
    }
}