import java.util.Scanner;

public class DefenderMd extends Mastermind {

    private int nbSizeMd = getNbSizeMd();
    private int nbTryMd = getNbTryMd();
    private long[] usableNbs = getUsableNbs();

    public void startDefenderMd(){
        long nbMaxRand = getMaxForRand(this.nbSizeMd,this.usableNbs.length - 1);
        Scanner sc = new Scanner(System.in);
        long nbToFind = getNbEntryMd(sc,nbMaxRand,this.usableNbs);
        String nbToFindStr = String.valueOf(nbToFind);
        int nbTry = this.nbTryMd;
        long nbComputer = 0;
        boolean endOfGame;

        if (nbToFindStr.length() < this.nbSizeMd){
            nbToFindStr = fillOfZero(nbToFindStr,2);
        }

        System.out.println("Vous avez choisi la combinaison : " + nbToFindStr);

        do {
            nbComputer = getComputerNb(nbComputer,nbToFind,2);
            endOfGame = checkNb(nbComputer,nbToFind,2,2);
            nbTry -= 1;
            if (nbTry == 0){
                endOfGame = true;
            }
        } while(!endOfGame);

        printEndDefender(nbTry);
        endOfGame(2,2,sc);
    }
}
