import java.util.Random;
import java.util.Scanner;

public class ChallengerMol extends MoreOrLess {

    public void printEndChallengerMol(int nbTry, int nbToFind){
        if (nbTry == 0){
            System.out.println("Vous avez perdu ! La solution était : " + nbToFind + "\n");
        }else{
            System.out.println("C'est gagné ! Bien joué !\n");
        }
    }

    public void startChallenger(){
        int nbMaxRand = getMaxForRand(Property.nbSizeMol);
        int nbMinRand = getMinForRand(Property.nbSizeMol);
        Random random = new Random();
        int nbToFind = random.nextInt(nbMaxRand - nbMinRand + 1) + nbMinRand;
        Scanner sc = new Scanner(System.in);
        int nbUser = 0;
        int nbTry = Property.nbTryMol;
        boolean endOfGame = false;

        System.out.println("Votre objectif est de trouver la bonne combinaison, en " + nbTry + " coups maximum\n");

        if (Property.devMode.equals("true")){
            System.out.println("** Dev mode ** le nombre a trouvé est : " + nbToFind);
        }

        do {
            nbUser = getNbEntry(sc,1,nbMaxRand);
            endOfGame = checkNb(nbUser,nbToFind,1);
            nbTry -= 1;
            if (nbTry == 0){
                endOfGame = true;
            }
        } while(!endOfGame);

        printEndChallengerMol(nbTry,nbToFind);
        endOfGame(1,1,sc);
    }
}
