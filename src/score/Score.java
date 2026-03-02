package score;

import jobs.Employee;

public class Score {
    public static int SPEED_MULTIPLIER = 10000;
    public static int COVERAGE_MULTIPLIER = SPEED_MULTIPLIER * 100;

    int coverage = 0;
    int speed = 0;
    int fulfilment = 0;

    public Score(int coverage, int speed, int fulfilment) {
        this.coverage = coverage;
        this.speed = speed;
        this.fulfilment = fulfilment;
        ensureScore();
    }

    public void addScore(int coverage, int speed, int fulfilment) {
        coverage += coverage;
        speed += speed;
        fulfilment += fulfilment;
        ensureScore();
    }

    public void addScore(Employee employee) {
        addScore(employee.getCoverage(), employee.getSpeed(), employee.getFulfilment());
    }

    public void ensureScore() {
        if (coverage > 100)
            coverage = 100;
        if (speed > 100)
            speed = 100;
    }

    public int getScore() {
        return fulfilment
                + ((speed - 1) * SPEED_MULTIPLIER)
                + ((coverage - 1) * COVERAGE_MULTIPLIER);
    }

    public static Score fromScore(int score) {
        int remainder;

        int coverage = (score / COVERAGE_MULTIPLIER) + 1;
        remainder = score % COVERAGE_MULTIPLIER;

        int speed = (remainder / SPEED_MULTIPLIER) + 1;
        remainder = remainder % SPEED_MULTIPLIER;

        return new Score(coverage, speed, remainder);
    }

    public Score copy() {
        return new Score(coverage, speed, fulfilment);
    }
}
