import java.util.Random;
import java.util.Scanner;

public class DuelMol extends MoreOrLess {

    private int nbSizeMol = getNbSizeMol();
    private int nbTryMol = getNbTryMol();

    public void startDuel(){
        long nbMaxRand = getMaxForRand(this.nbSizeMol,9);
        long nbMinRand = getMinForRand(this.nbSizeMol);
        Random random = new Random();
        long nbToFind = nbMinRand + (long)(random.nextDouble()*(nbMaxRand - nbMinRand));
        Scanner sc = new Scanner(System.in);
        long nbUser = 0;
        long nbComputer = 0;
        int nbTry = this.nbTryMol;
        boolean endOfGameUser = false;
        boolean endOfGameComputer = false;

        System.out.println("Votre objectif est de trouver la bonne combinaison avant l'ordinateur !\n");

        if (super.getDevMode().equals("true")){
            System.out.println("** Dev mode ** le nombre a trouver est : " + nbToFind);
        }

        do {
            nbUser = getNbEntry(sc,1, nbMaxRand);
            endOfGameUser = checkNb(nbUser,nbToFind,1);
            if (!endOfGameUser){
                nbComputer = (nbTry == this.nbTryMol) ? nbMinRand + (long)(random.nextDouble()*(nbMaxRand - nbMinRand)) : getComputerNb(nbToFind,nbComputer);
                if (nbTry == this.nbTryMol){
                    initTestedNbs();
                    putFirstTestedNb(Long.toString(nbComputer));
                }
                endOfGameComputer = checkNbComputer(nbComputer,nbToFind,3);
            }
            nbTry -= 1;
            if (nbTry == 0){
                endOfGameUser = true;
                endOfGameComputer = true;
            }
        } while(!endOfGameUser && !endOfGameComputer);

        printEndDuel(nbToFind,endOfGameComputer,nbTry);
        endOfGame(1,3,sc);
    }
}
