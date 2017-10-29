import java.util.Random;
import java.util.Scanner;

public class DefenderMol extends MoreOrLess {

    private int nbSizeMol = getNbSizeMol();
    private int nbTryMol = getNbTryMol();

    public void startDefender(){
        System.out.println("****** But du jeu : L'ordinateur ne doit pas trouver votre combinaison ******\n");
        long nbMaxRand = getMaxForRand(this.nbSizeMol,9);
        long nbMinRand = getMinForRand(this.nbSizeMol);
        Scanner sc = new Scanner(System.in);
        long nbUser = getNbEntry(sc,1, nbMaxRand);
        Random random = new Random();
        long nbComputer = 0;
        boolean endOfGame;
        int nbTry = this.nbTryMol;
        String nbUserStr = Long.toString(nbUser);
        long rand = 0;

        if (nbUserStr.length() < this.nbSizeMol){
            nbUserStr = fillOfZero(nbUserStr,1);
        }

        System.out.println("Vous avez choisi : " + nbUserStr + "\n");

        do {
            rand = nbMinRand + (long)(random.nextDouble()*(nbMaxRand - nbMinRand));
            nbComputer = (nbTry == this.nbTryMol) ? rand : getComputerNb(nbUser,nbComputer);
            if (nbTry == this.nbTryMol){
                initTestedNbs();
                putFirstTestedNb(Long.toString(nbComputer));
            }
            endOfGame = checkNbComputer(nbComputer,nbUser,2);
            nbTry -= 1;
            if (nbTry == 0){
                endOfGame = true;
            }
        }while (!endOfGame);

        printEndDefender(nbTry);
        endOfGame(1,2,sc);
    }
}
