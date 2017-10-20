import java.util.Random;
import java.util.Scanner;

public class DuelMd extends Mastermind {

    private int nbTry = Property.nbTryMd;

    public void printEndDuel(int nbToFind, boolean endOfGameComputer){
        if (this.nbTry == 0){
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
        Random random = new Random();
        int nbToFind = random.nextInt(9999 - 1000 + 1) + 1000;
        Scanner sc = new Scanner(System.in);
        int nbUser = 0;
        int nbComputer = 0;
        boolean endOfGameUser = false;
        boolean endOfGameComputer = false;
        String nbToFindStr = String.valueOf(nbToFind);
        String nbComputerStr = String.valueOf(nbComputer);
        int nbPresentComputer = 0;

        System.out.println("******  Votre objectif est de trouver la bonne combinaison avant l'ordinateur ******\n");

        if (Property.devMode.equals("true")){
            System.out.println("** Dev mode ** le nombre a trouvé est : " + nbToFind);
        }

        do {
            nbUser = getNbEntry(sc);
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
            this.nbTry -= 1;
            if (this.nbTry == 0){
                endOfGameUser = true;
                endOfGameComputer = true;
            }
        } while(!endOfGameUser && !endOfGameComputer);

        printEndDuel(nbToFind,endOfGameComputer);
        endOfGame(2,3,sc);
    }
}
