package model;

import java.util.Random;

abstract public class Zombie {
    private final int DEFAULT_CRITICAL_RATE = 2;
    private int arms;
    private int legs;
    private int baseHp;
    private double speedFtPerAct;

    public Zombie(int arms, int legs, int baseHp, double speedFtPerAct) {
        this.setArms(arms);
        this.setLegs(legs);
        this.setBaseHp(baseHp);
        this.setSpeedFtPerAct(speedFtPerAct);
    }

    public Zombie() {}

    @Override
    public String toString() {
        return String.format("%s{arms=%d, legs=%d, baseHp=%d, speedFtPerAct=%.2f", getClass().getSimpleName(),
                arms, legs, baseHp, speedFtPerAct);
    }

    @Override
    public boolean equals(Object o) {
        return this.getClass() == o.getClass();
    }

    public int attack(){
        int damageDealt = 0;
        String context = "missing and dealing no damage.";
        int chance = this.roll(getHIT_DICE());
        if(chance >= getMIN_HIT_ROLL() && chance < getCRITICAL_POINT()){
            damageDealt = this.roll(getHIT_DICE());
            context = String.format("hitting and dealing %d normal damage.", damageDealt);
        } else if(chance >= getCRITICAL_POINT() && chance <= getMAX_HIT_ROLL()){
            damageDealt = this.roll(getHIT_DICE())*getCRITICAL_RATE();
            context = String.format("hitting and dealing %d critical damage.", damageDealt);
        }
        System.out.println(String.format("%s rolls %d, %s", this.getClass().getSimpleName(), chance, context));
        return damageDealt;
    }

    protected abstract int getMAX_HIT_ROLL();
    protected abstract int getMIN_HIT_ROLL();
    protected abstract int getCRITICAL_POINT();
    protected abstract String getHIT_DICE();
    protected int getCRITICAL_RATE(){
        return DEFAULT_CRITICAL_RATE;
    }




    public int roll(String diceStr){
        if(diceStr.isEmpty() || diceStr == null || !diceStr.contains("d") || diceStr.length() < 2){
            throw new IllegalStateException("'diceStr' is unacceptable");
        }
        int diceSize = Integer.parseInt(diceStr.substring(diceStr.indexOf("d") + 1));
        if(diceStr.startsWith("d")){
            return randomNum(1, diceSize);
        } else {
            int diceAmount = Integer.parseInt(diceStr.substring(0, diceStr.indexOf("d")));
            int result = 0;
            for (int i = 0; i < diceAmount; i++) {
                result += randomNum(1, diceSize);
            }
            return result;
        }
    }

    private int randomNum(int min, int max){
        if(min >= max){
            throw new IllegalStateException("min can never be higher than max");
        }
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

    public int getArms() {
        return arms;
    }

    public void setArms(int arms) {
        if(arms < 0 || arms > 2){
            throw new IllegalStateException("'arms' is unacceptable");
        }
        this.arms = arms;
    }

    public int getLegs() {
        return legs;
    }

    public void setLegs(int legs) {
        if(legs < 0 || legs > 2){
            throw new IllegalStateException("'legs' is unacceptable");
        }
        this.legs = legs;
    }

    public int getBaseHp() {
        return baseHp;
    }

    public void setBaseHp(int baseHp) {
        if(baseHp < getMinBaseHp() || baseHp > getMaxBaseHp()){
            throw new IllegalStateException("'baseHp' is unacceptable");
        }
        this.baseHp = baseHp;
    }

    abstract public int getMinBaseHp();
    abstract public int getMaxBaseHp();

    public double getSpeedFtPerAct() {
        return speedFtPerAct;
    }

    public void setSpeedFtPerAct(double speedFtPerAct) {
        if(speedFtPerAct < getMinSpeed() || speedFtPerAct > getMaxSpeed()){
            throw new IllegalStateException("'speedFtPerAct' is unacceptable");
        }
        this.speedFtPerAct = speedFtPerAct;
    }

    abstract public int getMinSpeed();
    abstract public int getMaxSpeed();
}
