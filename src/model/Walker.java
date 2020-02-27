package model;

public class Walker extends Zombie {
    private final int MAX_BASE_HP = 30;
    private final int MIN_BASE_HP = 15;
    private final int MAX_SPEED = 10;
    private final int MIN_SPEED = 6;
    private final int MAX_HIT_ROLL = 20;
    private final int MIN_HIT_ROLL = 9;
    private final int CRITICAL_POINT = 20;
    private final String HIT_DICE = "3d6";


    public Walker(int arms, int legs, int baseHp, double speedFtPerAct) {
        super(arms, legs, baseHp, speedFtPerAct);
    }

    public Walker() {}

    @Override
    public String toString() {
        return String.format(super.toString() + "%s", "}");
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
