/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.toies.jpuyo.toiespassgenerator.app.data;

import android.test.AndroidTestCase;

import com.toies.jpuyo.toiespassgenerator.app.PlayerPassword;

import java.text.Normalizer;
import java.util.Random;

public class TestRandomPassword extends AndroidTestCase {

    public void setUp() {}

    public void testGetLastWord(){

        String playerName = "Alexis Sanchez";
        String lastWord = getLastWord(playerName);

        assertTrue("Error: Test failed to get the last word of Alexis Sanchez",
                lastWord.equals("Sanchez"));

        playerName = "Sanchez";
        lastWord = getLastWord(playerName);

        assertTrue("Error: Test failed to get the last word of Sanchez",
                lastWord.equals("Sanchez"));

        playerName = "";
        lastWord =  getLastWord(playerName);

        assertTrue("Error: Test failed to get the last word of empty string",
                lastWord.equals(""));
    }

    public void testGetRandomNumber(){

        Random randomGenerator = new Random();
        for (int idx = 1; idx <= 100; ++idx){
            int randomInt = randomGenerator.nextInt(9999);
            assertTrue("Error: Test failed to get Random Number beetwen 0 and 9999",
                    randomInt >= 0 && randomInt <= 9999);
        }
    }

    public void testRemoveAccents() {

        String playerName ="Áléxis Sánchéz";
        String playerNameWithoutAccents = removeAccents(playerName);

        assertTrue("Error: Test failed to remove accents for " + playerName +". The result is "+playerNameWithoutAccents,
                playerNameWithoutAccents.equals("Alexis Sanchez"));
    }

    public void testGetPassword() {

        String playerName = "Alexis Sánchez";
        PlayerPassword playerPassword = new PlayerPassword("Alexis Sánchez");
        String password = playerPassword.generatePassword();

        assertTrue("Error: Test failed to get Password for Alexis Sanchez",
                password.equals("Sanchez"+playerPassword.getRandomNumber()));
    }

    private String getLastWord(String playerName){
       return playerName.substring(playerName.lastIndexOf(" ")+1);
    }

    private String removeAccents(String text) {
        return text == null ? null :
                Normalizer.normalize(text, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }
}
