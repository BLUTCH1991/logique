import java.util.Scanner;

public class DefenderMd extends Mastermind {

    public void printEndDefenderMd(int nbTry){
        if (nbTry == 0){
            System.out.println("L'ordinateur n'a pas réussi à trouver la combinaison !\n");
        }else{
            System.out.println("L'ordinateur a trouvé la combinaison !\n");
        }
    }

    public void startDefenderMd(){
        int nbMaxRand = getMaxForRand(Property.nbSizeMd);
        Scanner sc = new Scanner(System.in);
        int nbToFind = getNbEntry(sc,2, nbMaxRand);
        int nbTry = Property.nbTryMd;
        int nbComputer = 0;
        boolean endOfGame;

        System.out.println("Vous avez choisi la combinaison : " + nbToFind);

        do {
            nbComputer = getComputerNb(nbComputer,nbToFind,2);
            endOfGame = checkNb(nbComputer,nbToFind,2,2);
            nbTry -= 1;
            if (nbTry == 0){
                endOfGame = true;
            }
        } while(!endOfGame);

        printEndDefenderMd(nbTry);
        endOfGame(2,2,sc);
    }
}
