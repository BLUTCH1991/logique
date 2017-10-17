import java.util.Random;
import java.util.Scanner;

public class DefenderMol extends MoreOrLess {

    public void startDefender(){
        Scanner sc = new Scanner(System.in);
        int nbUser = getNbEntry(sc);
        Random random = new Random();
        int nbComputer = random.nextInt(9999 - 1000 + 1) + 1000;
        boolean endOfGame = false;
        int nbTry = 10;

        System.out.println("Vous avez choisi : " + nbUser);

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

        if (nbTry == 0){
            System.out.println("L'ordinateur n'a pas réussi à trouver la combinaison !\n");
        }else{
            System.out.println("L'ordinateur a trouvé la combinaison !\n");
        }
        endOfGame(1,2,sc);
    }
}
