package controller;

import model.Runner;
import model.Tank;
import model.Walker;
import model.Zombie;
import view.Display;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ZedGenerator {
    private List<Zombie> activeZombies = new ArrayList<>();

    public static void main(String[] args){
        ZedGenerator zedGenerator = new ZedGenerator();
        zedGenerator.activeZombies.add(new Walker());
        zedGenerator.activeZombies.add(new Runner());
        zedGenerator.activeZombies.add(new Tank());
        zedGenerator.run(zedGenerator.activeZombies);
    }

    private void run(List activeZombies){
        Display display = new Display();
        display.setReplay(false);
        while(!(display.getInputNum() == 4)){
            try {
                display.sendRequest("\n1: Generate zombie\n" +
                        "2: Generate a group of zombies\n" +
                        "3: Custom generation\n" +
                        "4: Exit\n");
                display.askForUserNumInput();
                if (display.getInputNum() == 1) {
                    display.displayObj(randomZombie());
                } else if (display.getInputNum() == 2) {
                    display.displayGroupOfObjsByClass(randomAmountOfZombies(1, 10), activeZombies);
                } else if (display.getInputNum() == 3) {
                    display.sendRequest("Please input the amount.");
                    display.displayInputtedGroupOfObjsByClass(randomGroupOfZombies(display.getUserInputNum()), activeZombies);
                }
            } catch (NumberFormatException exception){
                display.sendRequest("Please try again.");
            }
        }
    }

    private int randomNum(int min, int max){
        if(min >= max){
            throw new IllegalStateException("min can never be higher than max");
        }
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

    private Zombie randomZombie(){
        Zombie randZed = null;
        int chance = randomNum(0, 2);
        switch (chance){
            case 0:
                randZed = new Walker();
                break;
            case 1:
                randZed = new Runner();
                break;
            case 2:
                randZed = new Tank();
                ((Tank)randZed).setDamageModifier(randomNum(
                        ((Tank) randZed).getMIN_DAMAGE_MODIFIER(), ((Tank) randZed).getMAX_DAMAGE_MODIFIER()));
                break;
        }
        randZed.setArms(randomNum(0, 2));
        randZed.setLegs(randomNum(0, 2));
        randZed.setBaseHp(randomNum(randZed.getMinBaseHp(), randZed.getMaxBaseHp()));
        randZed.setSpeedFtPerAct(randomNum(randZed.getMinSpeed(), randZed.getMaxSpeed()));
        return randZed;
    }

    private List<Zombie> randomAmountOfZombies(int min, int max){
        List<Zombie> zombies = new ArrayList<>();
        for (int i = 0; i < randomNum(min, max); i++) {
            zombies.add(randomZombie());
        }
        return zombies;
    }

    private List<Zombie> randomGroupOfZombies(int amount){
        List<Zombie> zombies = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            zombies.add(randomZombie());
        }
        return zombies;
    }
}
