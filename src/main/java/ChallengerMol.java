import java.util.Random;
import java.util.Scanner;

public class ChallengerMol extends MoreOrLess {

    private int nbSizeMol = getNbSizeMol();
    private int nbTryMol = getNbTryMol();

    public void startChallenger(){
        long nbMaxRand = getMaxForRand(this.nbSizeMol,9);
        long nbMinRand = getMinForRand(this.nbSizeMol);
        Random random = new Random();
        long nbToFind = (nbMaxRand +(long)(random.nextDouble()*(nbMaxRand - nbMinRand))) / 10;
        Scanner sc = new Scanner(System.in);
        long nbUser;
        int nbTry = this.nbTryMol;
        boolean endOfGame;

        System.out.println("Votre objectif est de trouver la bonne combinaison, en " + nbTry + " coups maximum\n");

        if (super.getDevMode().equals("true")){
            System.out.println("** Dev mode ** le nombre à trouver est : " + nbToFind);
        }

        do {
            nbUser = getNbEntry(sc,1,nbMaxRand);
            endOfGame = checkNb(nbUser,nbToFind,1);
            nbTry -= 1;
            if (nbTry == 0){
                endOfGame = true;
            }
        } while(!endOfGame);

        printEndChallenger(nbTry,nbToFind);
        endOfGame(1,1,sc);
    }
}
