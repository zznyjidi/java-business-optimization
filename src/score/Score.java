package score;

import employee.Employee;

public class Score {
    public static int COVERAGE_CAP = 100;
    public static int SPEED_CAP = 100;
    public static int FULFILMENT_CAP = 10000;

    public static int SPEED_MULTIPLIER = FULFILMENT_CAP;
    public static int COVERAGE_MULTIPLIER = SPEED_MULTIPLIER * SPEED_CAP;

    int coverage = 0;
    int speed = 0;
    int fulfilment = 0;

    public Score() {
    }

    public Score(int coverage, int speed, int fulfilment) {
        this.coverage = coverage;
        this.speed = speed;
        this.fulfilment = fulfilment;
        ensureScore();
    }

    public Score addScore(int coverage, int speed, int fulfilment) {
        this.coverage += coverage;
        this.speed += speed;
        this.fulfilment += fulfilment;
        ensureScore();
        return this;
    }

    public Score addScore(Employee employee) {
        return addScore(employee.getCoverage(), employee.getSpeed(), employee.getFulfilment());
    }

    public void ensureScore() {
        if (coverage > 100)
            coverage = 100;
        if (speed > 100)
            speed = 100;
    }

    public int getScore() {
        return (fulfilment - 1)
                + ((speed - 1) * SPEED_MULTIPLIER)
                + ((coverage - 1) * COVERAGE_MULTIPLIER);
    }

    public static Score fromScore(int score) {
        int remainder;

        int coverage = (score / COVERAGE_MULTIPLIER) + 1;
        remainder = score % COVERAGE_MULTIPLIER;

        int speed = (remainder / SPEED_MULTIPLIER) + 1;
        remainder = (remainder % SPEED_MULTIPLIER) + 1;

        return new Score(coverage, speed, remainder);
    }

    public Score copy() {
        return new Score(coverage, speed, fulfilment);
    }

    public boolean isBetter(Score other) {
        return getScore() > other.getScore();
    }

    public boolean isZero() {
        return (coverage == 0) && (speed == 0) && (fulfilment == 0);
    }

    @Override
    public String toString() {
        return "Score [coverage=" + coverage + ", speed=" + speed + ", fulfilment=" + fulfilment + "]";
    }
}
