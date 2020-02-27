package model;

public class Runner extends Zombie {
    private int climbSpeed;
    private final int MAX_BASE_HP = 22;
    private final int MIN_BASE_HP = 10;
    private final int MAX_SPEED = 25;
    private final int MIN_SPEED = 15;
    private final int MAX_HIT_ROLL = 20;
    private final int MIN_HIT_ROLL = 5;
    private final int CRITICAL_POINT = 19;
    private final String HIT_DICE = "2d8";

    public Runner(int arms, int legs, int baseHp, double speedFtPerAct) {
        super(arms, legs, baseHp, speedFtPerAct);
        this.setSpeedFtPerAct(speedFtPerAct);
    }

    public Runner() {}

    @Override
    public String toString() {
        return String.format("%s, climbSpeed=%d%s", super.toString(), this.getClimbSpeed(), "}");
    }

    protected int getMAX_HIT_ROLL() {
        return MAX_HIT_ROLL;
    }

    protected int getMIN_HIT_ROLL() {
        return MIN_HIT_ROLL;
    }

    protected int getCRITICAL_POINT() {
        return CRITICAL_POINT;
    }

    @Override
    public void setSpeedFtPerAct(double speedFtPerAct) {
        super.setSpeedFtPerAct(speedFtPerAct);
        this.climbSpeed = (int)(getSpeedFtPerAct()/3.0);
    }

    public int getClimbSpeed() {
        return climbSpeed;
    }

    public int getMinBaseHp() {
        return MIN_BASE_HP;
    }

    public int getMaxBaseHp() {
        return MAX_BASE_HP;
    }

    public int getMinSpeed() {
        return MIN_SPEED;
    }

    public int getMaxSpeed() {
        return MAX_SPEED;
    }

    public String getHIT_DICE() {
        return HIT_DICE;
    }
}
