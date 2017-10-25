import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class MoreOrLess extends Game{

    private Property prop = super.getProp();
    private int nbSizeMol = prop.getNbSizeMol();
    private int nbTryMol = prop.getNbTryMol();
    private String devMode = prop.getDevMode();
    private static final Logger logger = LogManager.getLogger(MoreOrLess.class);
    private Integer testedNbs[][] = new Integer[nbSizeMol][10];

    /*************** GETTERS *********************/

    public int getNbSizeMol() {
        return this.nbSizeMol;
    }

    public int getNbTryMol() {
        return this.nbTryMol;
    }

    public String getDevMode(){
        return this.devMode;
    }

    /**************** METHODS *********************/

    public void putFirstTestedNb(String nbToAdd){
        for (int i = 0; i < nbToAdd.length(); i++){
            this.testedNbs[i][0] = Character.getNumericValue(nbToAdd.charAt(i));
        }
    }

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

        //Example : get 131 but nb size = 4 so generate 0131
        if (nbToFindStr.length() < this.nbSizeMol){
            nbToFindStr = fillOfZero(nbToFindStr,1);
        }
        if (nbUserStr.length() < this.nbSizeMol){
            nbUserStr = fillOfZero(nbUserStr,1);
        }

        //Generate clue
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

    public int lastNbPosition(int i){
        int j = 0;

        while (this.testedNbs[i][j] != null){
            j += 1;
        }
        return (j);
    }

    public int getNewDigit(int i, int digit, int min, int max){
        int sizeLine = lastNbPosition(i);
        int randomNb = 0;
        boolean isOk = false;
        Random rand = new Random();

        while (!isOk){
            isOk = true;
            //randomNb = rand.nextInt(9 - (digit+1) + 1) + (digit+1);
            randomNb = rand.nextInt(max - min + 1) + min;
            //Look is digit already tested
            for (int j = 0; j < this.testedNbs[i].length; j++){
                if (this.testedNbs[i][j] != null && randomNb == this.testedNbs[i][j]){
                    isOk = false;
                }
            }
        }
        //Add the new digit in tested numbers array
        this.testedNbs[i][sizeLine] = randomNb;
        return (randomNb);
    }

    public int getComputerNb(int nbUser, int nbComputer){
        int digit;
        String nbUserStr = String.valueOf(nbUser);
        String nbComputerStr = String.valueOf(nbComputer);
        StringBuilder result = new StringBuilder();

        //Example : get 131 but nb size = 4 so generate 0131
        if (nbUserStr.length() < this.nbSizeMol){
            nbUserStr = fillOfZero(nbUserStr,1);
        }
        if (nbComputerStr.length() < this.nbSizeMol){
            nbComputerStr = fillOfZero(nbComputerStr,1);
        }

        //Generate a new digit when necessary and add it to string result
        for (int i = 0; i < nbUserStr.length(); i++){
            if (nbUserStr.charAt(i) > nbComputerStr.charAt(i)){
                digit = Character.getNumericValue(nbComputerStr.charAt(i));
                result.append(getNewDigit(i,digit,digit+1,9));
            }
            if (nbUserStr.charAt(i) < nbComputerStr.charAt(i)){
                digit = Character.getNumericValue(nbComputerStr.charAt(i));
                result.append(getNewDigit(i,digit,0,digit));
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
                logger.info("Jeu choisi : Plus ou Moins");
                logger.info("Mode de jeu choisi : Challenger");
                System.out.println("Bienvenue dans le jeu plus ou moins en mode Challenger !\n");
                ChallengerMol challenger = new ChallengerMol();
                challenger.startChallenger();
                break;
            case 2:
                logger.info("Jeu choisi : Plus ou Moins");
                logger.info("Mode de jeu choisi : Défenseur");
                System.out.println("Bienvenue dans le jeu plus ou moins en mode Défenseur !\n");
                DefenderMol defender = new DefenderMol();
                defender.startDefender();
                break;
            case 3:
                logger.info("Jeu choisi : Plus ou Moins");
                logger.info("Mode de jeu choisi : Duel");
                System.out.println("Bienvenue dans le jeu plus ou moins en mode Duel !\n");
                DuelMol duel = new DuelMol();
                duel.startDuel();
                break;
        }
    }
}
