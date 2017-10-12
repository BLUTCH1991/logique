import java.util.Random;
import java.util.Scanner;

public class DuelMol extends MoreOrLess {

    public void startDuel(){
        Random random = new Random();
        int nbToFind = random.nextInt(9999 - 1000 + 1) + 1000;
        Scanner sc = new Scanner(System.in);
        int nbUser = 0;
        int nbTry = 10;
        int nbComputer = random.nextInt(9999 - 1000 + 1) + 1000;
        boolean endOfGameUser = false;
        boolean endOfGameComputer = false;

        System.out.println("Votre objectif est de trouver la bonne combinaison avant l'ordinateur !\n");

        do {
            nbUser = getNbEntry(sc);
            endOfGameUser = this.checkNb(nbUser,nbToFind,1);
            if (!endOfGameUser){
                if (nbTry == 10){
                    nbComputer = random.nextInt(9999 - 1000 + 1) + 1000;
                }else{
                    nbComputer = getComputerNb(random,nbToFind,nbComputer);
                }
                endOfGameComputer = this.checkNb(nbComputer,nbToFind,3);
            }
            nbTry -= 1;
            if (nbTry == 0){
                endOfGameUser = true;
                endOfGameComputer = true;
            }
        } while(!endOfGameUser && !endOfGameComputer);

        if (nbTry == 0){
            System.out.println("Vous avez perdu et l'ordinateur aussi ! La solution était : " + nbToFind + "\n");
        }else{
            if (endOfGameComputer){
                System.out.println("L'ordinateur a été plus rapide, dommage !\n");
            }else{
                System.out.println("TVous avez battu l'ordinateur, bien joué !\n");
            }
        }
        endOfGame(1,3,sc);
    }
}
