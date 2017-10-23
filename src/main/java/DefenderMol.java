import java.util.Random;
import java.util.Scanner;

public class DefenderMol extends MoreOrLess {

    public void printEndDefenderMol(int nbTry){
        if (nbTry == 0){
            System.out.println("L'ordinateur n'a pas réussi à trouver la combinaison !\n");
        }else{
            System.out.println("L'ordinateur a trouvé la combinaison !\n");
        }
    }

    public void startDefender(){
        System.out.println("****** But du jeu : L'ordinateur ne doit pas trouver votre combinaison ******\n");
        int nbMaxRand = getMaxForRand(Property.nbSizeMol);
        int nbMinRand = getMinForRand(Property.nbSizeMol);
        Scanner sc = new Scanner(System.in);
        int nbUser = getNbEntry(sc,1, nbMaxRand);
        Random random = new Random();
        int nbComputer = random.nextInt(nbMaxRand - nbMinRand + 1) + nbMinRand;
        boolean endOfGame;
        int nbTry = Property.nbTryMol;
        String nbUserStr = Integer.toString(nbUser);

        if (nbUserStr.length() < Property.nbSizeMol){
            nbUserStr = fillOfZero(nbUserStr,1);
        }

        System.out.println("Vous avez choisi : " + nbUserStr + "\n");

        do {
            if (nbTry == Property.nbTryMol){
                nbComputer = random.nextInt(nbMaxRand - nbMinRand + 1) + nbMinRand;
            }else{
                nbComputer = getComputerNb(nbUser,nbComputer);
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
