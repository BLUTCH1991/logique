import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class MoreOrLess extends Game{

    public boolean checkNb(int nbUser, int nbToFind){
        boolean isCorrect = true;
        String newNbToFind = String.valueOf(nbToFind);
        String newNbUser = String.valueOf(nbUser);

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < newNbUser.length();i++){
            if (newNbToFind.charAt(i) == newNbUser.charAt(i)){
                result.append("=");
            }
            if (newNbToFind.charAt(i) > newNbUser.charAt(i)){
                result.append("+");
                isCorrect = false;
            }
            if (newNbToFind.charAt(i) < newNbUser.charAt(i)){
                result.append("-");
                isCorrect = false;
            }
        }

        System.out.println("Proposition : " + nbUser + " -> Réponse : " + result);
        return (isCorrect);
    }

    public int getNbEntry(Scanner sc){
        int nbUser = 0;
        boolean isNb = false;

        do {
            System.out.println("Entrez une combinaison à 4 chiffres (de 1000 à 9999) :\n");
            try {
                nbUser = sc.nextInt();
                if (nbUser <= 9999 && nbUser >= 1000){
                    isNb = true;
                }
            } catch (InputMismatchException e){
                sc.next();
            }
        } while(!isNb);

        return (nbUser);
    }

    public void defender(){

    }

    public void challenger(){
        Random random = new Random();
        int nbToFind = random.nextInt(9999 - 1000 + 1) + 1000;
        Scanner sc = new Scanner(System.in);
        int nbUser = 0;
        int nbTry = 10;
        boolean endOfGame = false;

        System.out.println("Trouve la combinaison\n");

        do {
            nbUser = getNbEntry(sc);
            endOfGame = this.checkNb(nbUser,nbToFind);
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

    public void initMoreOrLess(int modeChose){
        switch (modeChose){
            case 1:
                System.out.println("Bienvenue dans le jeu plus ou moins en mode Challenger !");
                this.challenger();
                break;
            case 2:
                System.out.println("Bienvenue dans le jeu plus ou moins en mode Défenseur !");
                this.defender();
                break;
            case 3:
                System.out.println("Bienvenue dans le jeu plus ou moins en mode Duel !");
                break;
        }
    }
}
