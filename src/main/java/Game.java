import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {

    private int gameChose = 0;
    private int modeChose = 0;


    public void initGame(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Bienvenue !\n");
        gameChoice(sc);
        gameModeChoice(sc);
        redirectToGame();
    }

    private void gameChoice(Scanner sc){
        boolean isNb = false;

        do {
            System.out.println("Sélectionnez un jeu :\n");
            System.out.println("(1) Plus ou moins\n(2) Mastermind\n");
            try {
                this.gameChose = sc.nextInt();
                if (this.gameChose == 1 || this.gameChose == 2){
                    isNb = true;
                }
            } catch (InputMismatchException e) {
                sc.next();
                isNb = false;
            }
        } while (!isNb);
    }

    private void gameModeChoice(Scanner sc){
        boolean isNb = false;

        do {
            System.out.println("Sélectionnez un mode de jeu :\n");
            System.out.println("(1) Challenger\n(2) Défenseur\n(3) Duel\n");
            try {
                this.modeChose = sc.nextInt();
                if (this.modeChose == 1 || this.modeChose == 2 || this.modeChose == 3){
                    isNb = true;
                }
            } catch (InputMismatchException e) {
                sc.next();
                isNb = false;
            }
        } while (!isNb);
    }

    private void redirectToGame(){
        switch (this.gameChose){
            case 1:
                MoreOrLess moreLess = new MoreOrLess();
                moreLess.initTabEntries();
                moreLess.initMoreOrLess(this.modeChose);
                break;
            case 2:
                Mastermind mastermind = new Mastermind();
                mastermind.initMastermind(this.modeChose);
                break;
        }
    }

    public void endOfGame(int game, int gameMode, Scanner sc){
        boolean isNb = false;
        int nbUser = 0;
        this.gameChose = game;
        this.modeChose = gameMode;

        do {
            System.out.println("(1) Rejouer ?\n(2) Jouer à un autre jeu\n(3) Quitter");
            try {
                nbUser = sc.nextInt();
                if (nbUser > 0 && nbUser < 4){
                    isNb = true;
                }
            } catch (InputMismatchException e){
                sc.next();
            }
        } while(!isNb);

        switch (nbUser){
            case 1:
                redirectToGame();
                break;
            case 2:
                initGame();
                break;
            case 3:
                System.out.println("A bientôt !");
                System.exit(0);
                break;
        }
    }

    public int getNbEntry(Scanner sc){
        int nbUser = 0;
        boolean isNb = false;

        do {
            System.out.println("Entrez une combinaison à 4 chiffres :\n");
            try {
                nbUser = sc.nextInt();
                if (nbUser <= 9999){
                    isNb = true;
                }
            } catch (InputMismatchException e){
                sc.next();
            }
        } while(!isNb);
        return (nbUser);
    }

    public String fillOfZero(String newNbUser){
        int i = 0;
        int j = 0;
        StringBuilder nbUserStr = new StringBuilder();
        //TODO adapter en 4 en fonction du nombre choisi dans les config
        int nbToFill = 4 - newNbUser.length();

        while (i < nbToFill){
            nbUserStr.insert(i,'0');
            i += 1;
        }
        while (j < newNbUser.length()){
            nbUserStr.insert(i,Character.toString(newNbUser.charAt(j)));
            j += 1;
            i += 1;
        }
        return (nbUserStr.toString());
    }

    public void printSingleArray(int[] arr){
        for (int i = 0; i < arr.length; i++){
            System.out.println(arr[i]);
        }
    }

    public void printMultiArray(int[][] arr) {
        for (int i = 0; i < arr.length; i++){
            for (int j = 0; j < arr[i].length; j++){
                System.out.print(arr[i][j]);
            }
            System.out.print("\n");
        }
    }

    public void printArray(Integer[][] arr) {
        for (int i = 0; i < arr.length; i++){
            for (int j = 0; j < arr[i].length; j++){
                System.out.print(arr[i][j]);
            }
            System.out.print("\n");
        }
    }
}

