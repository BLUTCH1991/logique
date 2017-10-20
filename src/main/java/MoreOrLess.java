import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.*;

public class MoreOrLess extends Game{

    private Integer tabEntries[][] = new Integer[Property.nbSizeMol][10];

    public void printClue(int mode, String nbUser, String result){
        switch (mode){
            case 1:
                System.out.println("Votre proposition : " + nbUser + " -> Indice : " + result);
                break;
            case 2:
                System.out.println("Proposition de l'ordinateur : " + nbUser + " -> Indice : " + result);
                break;
            case 3:
                System.out.println("L'ordinateur a joué\n");
                break;
        }
    }

    public boolean checkNb(int nbUser, int nbToFind, int mode){
        boolean isCorrect = true;
        String nbToFindStr = String.valueOf(nbToFind);
        String nbUserStr = String.valueOf(nbUser);
        StringBuilder result = new StringBuilder();

        if (nbToFindStr.length() < Property.nbSizeMol){
            nbToFindStr = fillOfZero(nbToFindStr,1);
        }
        if (nbUserStr.length() < Property.nbSizeMol){
            nbUserStr = fillOfZero(nbUserStr,1);
        }

        for (int i = 0; i < nbUserStr.length();i++){
            if (nbToFindStr.charAt(i) == nbUserStr.charAt(i)){
                result.append("=");
            }
            if (nbToFindStr.charAt(i) > nbUserStr.charAt(i)){
                result.append("+");
                isCorrect = false;
            }
            if (nbToFindStr.charAt(i) < nbUserStr.charAt(i)){
                result.append("-");
                isCorrect = false;
            }
        }
        printClue(mode,nbUserStr,result.toString());
        return (isCorrect);
    }

    public void initTabEntries(){
        for (int i = 0; i < this.tabEntries.length; i++){
            for (int j = 0; j < this.tabEntries[i].length; j++){
                this.tabEntries[i][j] = 10;
            }
        }
    }

    private int lastNbPosition(int i){
        int j = 0;

        while (this.tabEntries[i][j] != null && this.tabEntries[i][j] != 10){
            j += 1;
        }
        return (j);
    }

    public int getNewDigit(int i, int digit, int way){
        int sizeLine = lastNbPosition(i);
        int randomNb = 0;
        boolean isOk = false;
        Random rand = new Random();

        if (way == 1){
            while (!isOk){
                isOk = true;
                randomNb = rand.nextInt((9 - digit) + 1) + digit;
                for (int j = 0; j < this.tabEntries[i].length; j++){
                    if (this.tabEntries[i][j] != null && randomNb == this.tabEntries[i][j]){
                        isOk = false;
                    }
                }
            }
        }else{
            while (!isOk){
                isOk = true;
                randomNb = rand.nextInt(digit);
                for (int j = 0; j < this.tabEntries[i].length; j++){
                    if (this.tabEntries[i][j] != null && randomNb == this.tabEntries[i][j]){
                        isOk = false;
                    }
                }
            }
        }
        this.tabEntries[i][sizeLine] = randomNb;
        return (randomNb);
    }

    public int getComputerNb(Random random, int nbUser, int nbComputer){
        int digit = 0;
        String nbUserStr = String.valueOf(nbUser);
        String nbComputerStr = String.valueOf(nbComputer);
        StringBuilder result = new StringBuilder();


        if (nbUserStr.length() < Property.nbSizeMol){
            nbUserStr = fillOfZero(nbUserStr,1);
        }
        if (nbComputerStr.length() < Property.nbSizeMol){
            nbComputerStr = fillOfZero(nbComputerStr,1);
        }

        System.out.println("nb user = " + nbUserStr + " et nbcomputer = " + nbComputerStr);

        for (int i = 0; i < nbUserStr.length(); i++){
            if (nbUserStr.charAt(i) > nbComputerStr.charAt(i)){
                digit = Character.getNumericValue(nbComputerStr.charAt(i));
                result.append(getNewDigit(i,digit,1));
            }
            if (nbUserStr.charAt(i) < nbComputerStr.charAt(i)){
                digit = Character.getNumericValue(nbComputerStr.charAt(i));
                result.append(getNewDigit(i,digit,2));
            }
            if (nbUserStr.charAt(i) == nbComputerStr.charAt(i)){
                result.append(nbUserStr.charAt(i));
            }
        }
        return (Integer.parseInt(result.toString()));
    }

    public void initMoreOrLess(int modeChose){
        switch (modeChose){
            case 1:
                System.out.println("Bienvenue dans le jeu plus ou moins en mode Challenger !\n");
                ChallengerMol challenger = new ChallengerMol();
                challenger.startChallenger();
                break;
            case 2:
                System.out.println("Bienvenue dans le jeu plus ou moins en mode Défenseur !\n");
                DefenderMol defender = new DefenderMol();
                defender.startDefender();
                break;
            case 3:
                System.out.println("Bienvenue dans le jeu plus ou moins en mode Duel !\n");
                DuelMol duel = new DuelMol();
                duel.startDuel();
                break;
        }
    }
}
