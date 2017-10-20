import java.util.Random;
import java.util.Scanner;

public class ChallengerMd extends Mastermind {

    public void printEndChallengerMd(int nbTry, int nbToFind){
        if (nbTry == 0){
            System.out.println("Vous avez perdu ! La solution était : " + nbToFind + "\n");
        }else {
            System.out.println("C'est gagné ! Bien joué !\n");
        }
    }

    public void startChallenger(){
        Random random = new Random();
        int nbToFind = random.nextInt(9999 - 1000 + 1) + 1000;
        Scanner sc = new Scanner(System.in);
        int nbUser = 0;
        int nbTry = Property.nbTryMd;
        boolean endOfGame = false;

        System.out.println("Votre objectif est de trouver la bonne combinaison, en 12 coups maximum\n");

        if (Property.devMode.equals("true")){
            System.out.println("** Dev mode ** le nombre a trouvé est : " + nbToFind);
        }

        do {
            nbUser = getNbEntry(sc);
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
