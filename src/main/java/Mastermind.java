import java.lang.reflect.Array;
import java.util.Arrays;

public class Mastermind extends Game {

    public int balanceNbPresent(String nbUserStr, String nbToFindStr, int nbPresent){
        int count = 9;
        int nbInUser = 0;
        int nbInFind = 0;

        while (count >= 0){
            nbInFind = 0;
            nbInUser = 0;
            for (int z = 0; z < nbUserStr.length(); z++){
                if (Character.getNumericValue(nbUserStr.charAt(z)) == count){
                    nbInUser += 1;
                }
            }
            for (int e = 0; e < nbToFindStr.length(); e++){
                if (Character.getNumericValue(nbToFindStr.charAt(e)) == count){
                    nbInFind += 1;
                }
            }
            if (nbInFind > 0 && nbInUser > 0) {
                nbPresent -= (nbInUser - nbInFind);
            }
            count -= 1;
        }
        return (nbPresent);
    }

    public boolean checkIfNbExist(int[] nbFound, int nb){
        boolean isExist = false;

        for (int k = 0; k < nbFound.length; k++) {
            if (nb == nbFound[k]) {
                isExist = true;
            }
        }
        return (isExist);
    }

    public boolean checkNb(int nbUser, int nbToFind, int gameMode){
        String nbToFindStr = String.valueOf(nbToFind);
        String nbUserStr = String.valueOf(nbUser);
        int nbPresent = 0;
        int nbPut = 0;
        int[] nbFound = new int[nbToFindStr.length()];
        boolean isExist = false;

        //Fill string of 0 if it's not enough length, for example 11 -> 0011
        if (nbUserStr.length() < 4){
           nbUserStr = fillOfZero(nbUserStr);
        }

        for (int i = 0; i < nbUserStr.length(); i++){
            if (nbUserStr.charAt(i) == nbToFindStr.charAt(i)){
                nbPut += 1;
            }
            //TODO find a good way to empty this tab
            for (int k = 0; k < nbFound.length; k++){
                nbFound[k] = 42;
            }
            for (int j = 0; j < nbToFindStr.length(); j++){
                if (nbToFindStr.charAt(j) != '\0' && nbToFindStr.charAt(j) == nbUserStr.charAt(i)){
                    isExist = checkIfNbExist(nbFound,nbToFindStr.charAt(j));
                    if (!isExist){
                        nbFound[j] = nbToFindStr.charAt(j);
                        nbPresent += 1;
                    }
                }
            }
        }

        nbPresent = balanceNbPresent(nbUserStr,nbToFindStr,nbPresent);

        //TODO masquer cette info
        System.out.println("Nombre à trouver : " + nbToFind);
        System.out.println("Votre proposition : " + nbUserStr + " -> indice : " + nbPresent + " présents et " + nbPut + " bien placés\n");

        return (nbPut == 4);
    }

    public void initMastermind(int modeChose){
        switch (modeChose){
            case 1:
                System.out.println("Bienvenue sur Mastermind en mode Challenger !");
                ChallengerMd challenger = new ChallengerMd();
                challenger.startChallenger();
                break;
            case 2 :
                System.out.println("Bienvenue sur Mastermind en mode Défenseur !");
                break;
            case 3:
                System.out.println("Bienvenue sur Mastermind en mode Duel !");
                break;
        }
    }
}
