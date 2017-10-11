import java.util.Random;
import java.util.Scanner;

public class ChallengerMol extends MoreOrLess {

    public void startChallenger(){
        Random random = new Random();
        int nbToFind = random.nextInt(9999 - 1000 + 1) + 1000;
        Scanner sc = new Scanner(System.in);
        int nbUser = 0;
        int nbTry = 10;
        boolean endOfGame = false;

        System.out.println("Ton objectif est de trouver la bonne combinaison, en 10 coups maximum\n");

        do {
            nbUser = getNbEntry(sc);
            endOfGame = this.checkNb(nbUser,nbToFind,1);
            nbTry -= 1;
            if (nbTry == 0){
                endOfGame = true;
            }
        } while(!endOfGame);
        if (nbTry == 0){
            System.out.println("Looser !\n");
        }else{
            System.out.println("Good game !\n");
        }
        endOfGame(1,1,sc);
    }
}
