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
        boolean endOfGame = false;

        System.out.println("Ton objectif est de trouver la bonne combinaison avant l'ordinateur !\n");

        do {
            nbUser = getNbEntry(sc);
            endOfGame = this.checkNb(nbUser,nbToFind,1);
            if (!endOfGame){
                if (nbTry == 10){
                    nbComputer = random.nextInt(9999 - 1000 + 1) + 1000;
                }else{
                    nbComputer = getComputerNb(random,nbUser,nbComputer);
                }
                endOfGame = this.checkNb(nbComputer,nbUser,2);
            }
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
