import java.util.Random;
import java.util.Scanner;

public class DuelMd extends Mastermind {

    private int nbSizeMd = getNbSizeMd();
    private int nbTryMd = getNbTryMd();
    private long[] usableNbs = getUsableNbs();

    public void startDuel(){
        System.out.println("usableNbs.lenght = " + usableNbs.length);
        long nbMaxRand = getMaxForRand(nbSizeMd,usableNbs.length - 1);
        System.out.println("nbMaRand = " + nbMaxRand);
        long nbMinRand = getMinForRand(this.nbSizeMd);
        Random random = new Random();
        long nbToFind = nbMinRand + (long)(random.nextDouble()*(nbMaxRand - nbMinRand));
        Scanner sc = new Scanner(System.in);
        long nbUser = 0;
        long nbComputer = 0;
        boolean endOfGameUser = false;
        boolean endOfGameComputer = false;
        String nbToFindStr = String.valueOf(nbToFind);
        String nbComputerStr = String.valueOf(nbComputer);
        int nbPresentComputer = 0;
        int nbTry = this.nbTryMd;

        System.out.println("******  Votre objectif est de trouver la bonne combinaison avant l'ordinateur ******\n");
        if (super.getDevMode().equals("true")){
            System.out.println("** Dev mode ** le nombre a trouver est : " + nbToFind);
        }

        do {
            nbUser = getNbEntry(sc,2, nbMaxRand);
            endOfGameUser = checkNb(nbUser,nbToFind,1,1);
            if (!endOfGameUser){
                nbPresentComputer = countNbPresComputer(nbComputer,nbToFind);
                if (nbPresentComputer == 4){
                    nbComputerStr = generateNumber(nbToFindStr,Long.toString(nbComputer));
                    nbComputer = Long.parseLong(nbComputerStr);
                    endOfGameComputer = checkNb(nbComputer,nbToFind,3,2);
                }else{
                    nbComputer = getComputerNb(nbComputer,nbToFind,3);
                    endOfGameComputer = checkNb(nbComputer,nbToFind,3,2);
                }
            }
            nbTry -= 1;
            if (nbTry== 0){
                endOfGameUser = true;
                endOfGameComputer = true;
            }
        } while(!endOfGameUser && !endOfGameComputer);

        printEndDuel(nbToFind,endOfGameComputer,nbTry);
        endOfGame(2,3,sc);
    }
}
