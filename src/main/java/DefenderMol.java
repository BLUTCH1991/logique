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
        Scanner sc = new Scanner(System.in);
        int nbUser = getNbEntry(sc);
        Random random = new Random();
        int nbComputer = random.nextInt(9999 - 1000 + 1) + 1000;
        boolean endOfGame = false;
        int nbTry = Property.nbTryMol;
        String nbUserStr = Integer.toString(nbUser);

        if (nbUserStr.length() < Property.nbSizeMol){
            nbUserStr = fillOfZero(nbUserStr);
        }

        System.out.println("Vous avez choisi : " + nbUserStr + "\n");

        do {
            if (nbTry == 10){
                nbComputer = random.nextInt(9999 - 1000 + 1) + 1000;
            }else{
                nbComputer = getComputerNb(random,nbUser,nbComputer);
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
