import java.util.Scanner;

public class ChallengerMd extends Mastermind {

    public void printEndChallengerMd(int nbTry, int nbToFind){
        if (nbTry == 0){
            System.out.println("Vous avez perdu ! La solution était : " + nbToFind + "\n");
        }else {
            System.out.println("C'est gagné ! Bien joué !\n");
        }
    }

    public void startChallenger(){
        int nbMaxRand = getMaxForRand(Property.nbSizeMd,usableNbs.length - 1);
        int nbToFind = getCustomRandom();
        String nbToFindStr = String.valueOf(nbToFind);
        Scanner sc = new Scanner(System.in);
        int nbUser = 0;
        int nbTry = Property.nbTryMd;
        boolean endOfGame = false;

        if (nbToFindStr.length() < Property.nbSizeMd){
            nbToFindStr = fillOfZero(nbToFindStr,2);
        }

        System.out.println("Votre objectif est de trouver la bonne combinaison, en " + nbTry + " coups maximum\n");

        if (Property.devMode.equals("true")){
            System.out.println("** Dev mode ** le nombre a trouver est : " + nbToFindStr + "\n");
        }

        do {
            nbUser = getNbEntryMd(sc,nbMaxRand,usableNbs);
            endOfGame = checkNb(nbUser,nbToFind,1,1);
            nbTry -= 1;
            if (nbTry == 0){
                endOfGame = true;
            }
        } while(!endOfGame);

        printEndChallengerMd(nbTry,nbToFind);
        endOfGame(2,1,sc);
    }
}
