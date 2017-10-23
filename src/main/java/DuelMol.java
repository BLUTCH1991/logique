import java.util.Random;
import java.util.Scanner;

public class DuelMol extends MoreOrLess {

    public void printEndDuelMol(int nbTry, int nbToFind, boolean endOfGameComputer){
        if (nbTry == 0){
            System.out.println("Vous avez perdu et l'ordinateur aussi ! La solution était : " + nbToFind + "\n");
        }else{
            if (endOfGameComputer){
                System.out.println("L'ordinateur a été plus rapide, dommage !\n");
            }else{
                System.out.println("Vous avez battu l'ordinateur, bien joué !\n");
            }
        }
    }

    public void startDuel(){
        int nbMaxRand = getMaxForRand(Property.nbSizeMol,9);
        int nbMinRand = getMinForRand(Property.nbSizeMol);
        Random random = new Random();
        int nbToFind = random.nextInt(nbMaxRand - nbMinRand + 1) + nbMinRand;
        Scanner sc = new Scanner(System.in);
        int nbUser = 0;
        int nbTry = Property.nbTryMol;
        int nbComputer = random.nextInt(nbMaxRand - nbMinRand + 1) + nbMinRand;
        boolean endOfGameUser = false;
        boolean endOfGameComputer = false;

        System.out.println("Votre objectif est de trouver la bonne combinaison avant l'ordinateur !\n");

        if (Property.devMode.equals("true")){
            System.out.println("** Dev mode ** le nombre a trouver est : " + nbToFind);
        }

        do {
            nbUser = getNbEntry(sc,1, nbMaxRand);
            endOfGameUser = checkNb(nbUser,nbToFind,1);
            if (!endOfGameUser){
                if (nbTry == Property.nbTryMol){
                    nbComputer = random.nextInt(nbMaxRand - nbMinRand + 1) + nbMinRand;
                }else{
                    nbComputer = getComputerNb(nbToFind,nbComputer);
                }
                endOfGameComputer = checkNb(nbComputer,nbToFind,3);
            }
            nbTry -= 1;
            if (nbTry == 0){
                endOfGameUser = true;
                endOfGameComputer = true;
            }
        } while(!endOfGameUser && !endOfGameComputer);

        printEndDuelMol(nbTry,nbToFind,endOfGameComputer);
        endOfGame(1,3,sc);
    }
}
