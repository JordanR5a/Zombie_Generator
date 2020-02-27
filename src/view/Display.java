package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Display {
    private boolean replay;
    private int inputNum;

    public String getUserInput() {
        BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
        String input = null;
        do {
            try {
                input = br.readLine();
                if(input.isEmpty() || input == null){
                    throw new IllegalStateException("User input unacceptable");
                }
                setReplay(false);
            } catch (IOException | IllegalStateException exception) {
                sendRequest("Please try again.");
                setReplay(true);
            }
        } while (isReplay());
        return input;
    }

    public int getUserInputNum(){
        return Integer.parseInt(getUserInput());
    }

    public void askForUserNumInput(){
        do {
            try {
                setInputNum(getUserInputNum());
                setReplay(false);
            } catch (IllegalStateException exception) {
                sendRequest("Please try again.");
                setReplay(true);
            }
        } while (isReplay());
    }

    public void displayInputtedGroupOfObjsByClass(List group, List classes){
        do {
            try {
                displayGroupOfObjsByClass(group, classes);
                setReplay(false);
            } catch (NumberFormatException | IllegalStateException exception) {
                sendRequest("Please try again.");
                setReplay(true);
            }
        } while (isReplay());
    }

    private void displayGroupOfObjs(List group, String contents){
        sendRequest(String.format("%s:", contents));
        for(Object obj : group){
            sendRequest(obj.toString());
        }
        sendRequest("");
    }

    public void displayGroupOfObjsByClass(List group, List classes){
        sendRequest(String.format("%ss(%d)\n",
                classes.get(0).getClass().getSuperclass().getSimpleName(),
                group.size()));
        for (int i = 0; i < classes.size(); i++) {
            if(group.contains(classes.get(i))){
                List matches = new ArrayList<>();
                for(Object obj : group){
                    if(obj.equals(classes.get(i))){
                        matches.add(obj);
                    }
                }
                displayGroupOfObjs(matches, String.format("%ss(%d)",
                        classes.get(i).getClass().getSimpleName(),
                        matches.size()));
            }
        }
    }

    public void displayObj(Object obj){
        sendRequest(obj.toString());
    }

    public void sendRequest(String request){
        System.out.println(request);
    }

    public boolean isReplay() {
        return replay;
    }

    public void setReplay(boolean replay) {
        this.replay = replay;
    }

    public int getInputNum() {
        return inputNum;
    }

    public void setInputNum(int inputNum) {
        if(inputNum < 0){
            throw new IllegalStateException("'inputNum' cannot be negative");
        }
        this.inputNum = inputNum;
    }
}
