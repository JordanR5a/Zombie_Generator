package model;

public class Tank extends Zombie {
    private int damageModifier;
    private final int MAX_DAMAGE_MODIFIER = 20;
    private final int MIN_DAMAGE_MODIFIER = 10;
    private final int MAX_BASE_HP = 70;
    private final int MIN_BASE_HP = 45;
    private final int MAX_SPEED = 8;
    private final int MIN_SPEED = 4;
    private final int MAX_HIT_ROLL = 20;
    private final int MIN_HIT_ROLL = 10;
    private final int CRITICAL_POINT = 20;
    private final int CRITICAL_RATE = 3;
    private final String HIT_DICE = "3d6";

    public Tank(int arms, int legs, int baseHp, double speedFtPerAct, int damageModifier) {
        super(arms, legs, baseHp, speedFtPerAct);
        this.setDamageModifier(damageModifier);

    }

    public Tank() {}

    @Override
    public String toString() {
        return String.format("%s%s", super.toString(), "}");
    }

    @Override
    public int attack() {
        int dmg = super.attack();
        if(dmg != 0){
           dmg += getDamageModifier();
        }
        return dmg;
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
    protected int getCRITICAL_RATE(){
        return CRITICAL_RATE;
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

    public int getDamageModifier() {
        return damageModifier;
    }

    public int getMAX_DAMAGE_MODIFIER() {
        return MAX_DAMAGE_MODIFIER;
    }

    public int getMIN_DAMAGE_MODIFIER() {
        return MIN_DAMAGE_MODIFIER;
    }

    public void setDamageModifier(int damageModifier) {
        if(damageModifier < 0){
            throw new IllegalStateException("'damageModifier' is unacceptable");
        }
        this.damageModifier = damageModifier;
    }

    public String getHIT_DICE() {
        return HIT_DICE;
    }
}
