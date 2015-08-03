package com.toies.jpuyo.toiespassgenerator.app;

import java.text.Normalizer;
import java.util.Random;

public class PlayerPassword {

    private String playerName;
    private String singleWordWithoutAccents;
    private int randomNumber;


    public PlayerPassword(String playerName){
        this.playerName = playerName;
    }

    public int getRandomNumber(){
        return randomNumber;
    }

    public String generatePassword(){
        setSingleNormalizedWord();
        setRandomNumber();
        return this.singleWordWithoutAccents + String.valueOf(this.randomNumber);
    }

    private void setSingleNormalizedWord() {
        String lastWord = playerName.substring(playerName.lastIndexOf(" ")+1);
        this.singleWordWithoutAccents = Normalizer.normalize(lastWord, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

    private void setRandomNumber() {
        this.randomNumber = new Random().nextInt(9999);
    }
}
