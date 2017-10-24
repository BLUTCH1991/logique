import java.util.Random;
import java.util.Scanner;

public class DuelMd extends Mastermind {

    private int nbSizeMd = getNbSizeMd();
    private int nbTryMd = getNbTryMd();
    private int[] usableNbs = getUsableNbs();

    public void printEndDuel(int nbToFind, boolean endOfGameComputer){
        if (nbTryMd == 0){
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
        int nbMaxRand = getMaxForRand(nbSizeMd,usableNbs.length - 1);
        int nbMinRand = getMinForRand(nbSizeMd);
        Random random = new Random();
        int nbToFind = random.nextInt(nbMaxRand - nbMinRand + 1) + nbMinRand;
        Scanner sc = new Scanner(System.in);
        int nbUser = 0;
        int nbComputer = 0;
        boolean endOfGameUser = false;
        boolean endOfGameComputer = false;
        String nbToFindStr = String.valueOf(nbToFind);
        String nbComputerStr = String.valueOf(nbComputer);
        int nbPresentComputer = 0;

        System.out.println("******  Votre objectif est de trouver la bonne combinaison avant l'ordinateur ******\n");
        if (super.getDevMode().equals("true")){
            System.out.println("** Dev mode ** le nombre a trouver est : " + nbToFind);
        }

        do {
            nbUser = getNbEntry(sc,2, nbMaxRand);
            endOfGameUser = checkNb(nbUser,nbToFind,1,1);
            if (!endOfGameUser){
                nbPresentComputer = howManyPresentComputer(nbComputer,nbToFind);
                if (nbPresentComputer == 4){
                    nbComputerStr = generateNumber(nbToFindStr,Integer.toString(nbComputer));
                    nbComputer = Integer.parseInt(nbComputerStr);
                    endOfGameComputer = checkNb(nbComputer,nbToFind,3,2);
                }else{
                    nbComputer = getComputerNb(nbComputer,nbToFind,3);
                    endOfGameComputer = checkNb(nbComputer,nbToFind,3,2);
                }
            }
            nbTryMd -= 1;
            if (nbTryMd == 0){
                endOfGameUser = true;
                endOfGameComputer = true;
            }
        } while(!endOfGameUser && !endOfGameComputer);

        printEndDuel(nbToFind,endOfGameComputer);
        endOfGame(2,3,sc);
    }
}
