import java.util.Random;
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
        int nbMaxRand = getMaxForRand(Property.nbSizeMd);
        int nbMinRand = getMinForRand(Property.nbSizeMd);
        Random random = new Random();
        int nbToFind = random.nextInt(nbMaxRand - nbMinRand + 1) + nbMinRand;
        Scanner sc = new Scanner(System.in);
        int nbUser;
        int nbTry = Property.nbTryMd;
        boolean endOfGame = false;

        System.out.println("Votre objectif est de trouver la bonne combinaison, en " + nbTry + " coups maximum\n");

        if (Property.devMode.equals("true")){
            System.out.println("** Dev mode ** le nombre a trouvé est : " + nbToFind);
        }

        do {
            nbUser = getNbEntry(sc,2, nbMaxRand);
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
