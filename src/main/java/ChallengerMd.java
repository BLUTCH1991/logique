import java.util.Scanner;

public class ChallengerMd extends Mastermind {

    private int nbSizeMd = getNbSizeMd();
    private int nbTryMd = getNbTryMd();
    private int[] usableNbs = getUsableNbs();

    public void printEndChallengerMd(int nbTry, int nbToFind){
        if (nbTry == 0){
            System.out.println("Vous avez perdu ! La solution était : " + nbToFind + "\n");
        }else {
            System.out.println("C'est gagné ! Bien joué !\n");
        }
    }

    public void startChallenger(){
        int nbMaxRand = getMaxForRand(this.nbSizeMd,this.usableNbs.length - 1);
        int nbToFind = getCustomRandom();
        String nbToFindStr = String.valueOf(nbToFind);
        Scanner sc = new Scanner(System.in);
        int nbUser = 0;
        int nbTry = this.nbTryMd;
        boolean endOfGame = false;

        if (nbToFindStr.length() < this.nbSizeMd){
            nbToFindStr = fillOfZero(nbToFindStr,2);
        }

        System.out.println("Votre objectif est de trouver la bonne combinaison, en " + nbTry + " coups maximum\n");
        if (super.getDevMode().equals("true")){
            System.out.println("** Dev mode ** le nombre a trouver est : " + nbToFindStr + "\n");
        }

        do {
            nbUser = getNbEntryMd(sc,nbMaxRand,this.usableNbs);
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
