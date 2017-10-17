import java.util.*;

public class MoreOrLess extends Game{

    private Integer tabEntries[][] = new Integer[4][10];

    public boolean checkNb(int nbUser, int nbToFind, int mode){
        boolean isCorrect = true;
        String newNbToFind = String.valueOf(nbToFind);
        String newNbUser = String.valueOf(nbUser);
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < newNbUser.length();i++){
            if (newNbToFind.charAt(i) == newNbUser.charAt(i)){
                result.append("=");
            }
            if (newNbToFind.charAt(i) > newNbUser.charAt(i)){
                result.append("+");
                isCorrect = false;
            }
            if (newNbToFind.charAt(i) < newNbUser.charAt(i)){
                result.append("-");
                isCorrect = false;
            }
        }
        switch (mode){
            case 1:
                System.out.println("Votre proposition : " + nbUser + " -> Indice : " + result);
                break;
            case 2:
                System.out.println("Proposition de l'ordinateur : " + nbUser + " -> Indice : " + result + "\n");
                break;
            case 3:
                System.out.println("L'ordinateur a joué\n");
        }
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
        int digitComputerChoice = 0;
        String nbUserStr = String.valueOf(nbUser);
        String nbComputerStr = String.valueOf(nbComputer);
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < nbUserStr.length();i++){
            digitComputerChoice = 0;
            digit = 0;
            if (nbUserStr.charAt(i) != '\0' && nbComputerStr.charAt(i) != '\0' && (nbUserStr.charAt(i) > nbComputerStr.charAt(i))){
                digit = Character.getNumericValue(nbComputerStr.charAt(i));
                digitComputerChoice = this.getNewDigit(i,digit,1);
                result.append(digitComputerChoice);
            }
            if (nbUserStr.charAt(i) < nbComputerStr.charAt(i)){
                digit = Character.getNumericValue(nbComputerStr.charAt(i));
                digitComputerChoice = this.getNewDigit(i,digit,2);
                result.append(digitComputerChoice);
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
                System.out.println("Bienvenue dans le jeu plus ou moins en mode Challenger !");
                ChallengerMol challenger = new ChallengerMol();
                challenger.startChallenger();
                break;
            case 2:
                System.out.println("Bienvenue dans le jeu plus ou moins en mode Défenseur !");
                DefenderMol defender = new DefenderMol();
                defender.startDefender();
                break;
            case 3:
                System.out.println("Bienvenue dans le jeu plus ou moins en mode Duel !");
                DuelMol duel = new DuelMol();
                duel.startDuel();
                break;
        }
    }
}
