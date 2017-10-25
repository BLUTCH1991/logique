import java.util.Random;
import java.util.Scanner;

public class DefenderMol extends MoreOrLess {

    private int nbSizeMol = getNbSizeMol();
    private int nbTryMol = getNbTryMol();

    public void printEndDefenderMol(int nbTry){
        if (nbTry == 0){
            System.out.println("L'ordinateur n'a pas réussi à trouver la combinaison !\n");
        }else{
            System.out.println("\nL'ordinateur a trouvé la combinaison en " + (nbTryMol-nbTry) + " coups !\n");
        }
    }

    public void startDefender(){
        System.out.println("****** But du jeu : L'ordinateur ne doit pas trouver votre combinaison ******\n");
        int nbMaxRand = getMaxForRand(this.nbSizeMol,9);
        int nbMinRand = getMinForRand(this.nbSizeMol);
        Scanner sc = new Scanner(System.in);
        int nbUser = getNbEntry(sc,1, nbMaxRand);
        Random random = new Random();
        int nbComputer = 0;
        boolean endOfGame;
        int nbTry = this.nbTryMol;
        String nbUserStr = Integer.toString(nbUser);

        if (nbUserStr.length() < this.nbSizeMol){
            nbUserStr = fillOfZero(nbUserStr,1);
        }

        System.out.println("Vous avez choisi : " + nbUserStr + "\n");

        do {
            nbComputer = (nbTry == this.nbTryMol) ? random.nextInt(nbMaxRand - nbMinRand + 1) + nbMinRand : getComputerNb(nbUser,nbComputer);
            if (nbTry == this.nbTryMol){
                putFirstTestedNb(Integer.toString(nbComputer));
            }
            endOfGame = checkNb(nbComputer,nbUser,2);
            nbTry -= 1;
            if (nbTry == 0){
                endOfGame = true;
            }
        }while (!endOfGame);

        printEndDefenderMol(nbTry);
        endOfGame(1,2,sc);
    }
}
