import java.util.Scanner;

public class ChallengerMd extends Mastermind {

    private int nbSizeMd = getNbSizeMd();
    private int nbTryMd = getNbTryMd();
    private long[] usableNbs = getUsableNbs();

    public void startChallenger(){
        long nbMaxRand = getMaxForRand(this.nbSizeMd,this.usableNbs.length - 1);
        long nbToFind = getCustomRandom();
        String nbToFindStr = String.valueOf(nbToFind);
        Scanner sc = new Scanner(System.in);
        long nbUser = 0;
        int nbTry = this.nbTryMd;
        boolean endOfGame = false;

        if (nbToFindStr.length() < this.nbSizeMd){
            nbToFindStr = fillOfZero(nbToFindStr,2);
        }

        System.out.println("Votre objectif est de trouver la bonne combinaison, en " + nbTry + " coups maximum\n");
        if (super.getDevMode().equals("true")){
            System.out.println("** Dev mode ** le nombre à trouver est : " + nbToFindStr + "\n");
        }

        do {
            nbUser = getNbEntryMd(sc,nbMaxRand,this.usableNbs);
            endOfGame = checkNb(nbUser,nbToFind,1,1);
            nbTry -= 1;
            if (nbTry == 0){
                endOfGame = true;
            }
        } while(!endOfGame);

        printEndChallenger(nbTry,nbToFind);
        endOfGame(2,1,sc);
    }
}
