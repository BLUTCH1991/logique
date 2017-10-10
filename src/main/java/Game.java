import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {

    public void initGame(){
        int gameChose = 0;
        int modeChose = 0;
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome in logic games !\n");

        gameChose = gameChoice(sc);
        modeChose = gameModeChoice(sc);

        sc.close();

        this.redirectToGame(gameChose,modeChose);
    }

    private int gameChoice(Scanner sc){
        boolean isNb = false;
        int gameChose = 0;

        do {
            System.out.println("Sélectionnez un jeu :\n");
            System.out.println("(1) Plus ou moins\n(2) Mastermind like\n");
            try {
                gameChose = sc.nextInt();
                if (gameChose == 1 || gameChose == 2){
                    isNb = true;
                }
            } catch (InputMismatchException e) {
                sc.next();
                isNb = false;
            }
        } while (!isNb);

        return (gameChose);
    }

    private int gameModeChoice(Scanner sc){
        boolean isNb = false;
        int modeChose = 0;

        do {
            System.out.println("Sélectionnez un mode de jeu :\n");
            System.out.println("(1) Challenger\n(2) Défenseur\n(3) Duel");
            try {
                modeChose = sc.nextInt();
                if (modeChose == 1 || modeChose == 2 || modeChose == 3){
                    isNb = true;
                }
            } catch (InputMismatchException e) {
                sc.next();
                isNb = false;
            }
        } while (!isNb);

        return (modeChose);
    }

    private void redirectToGame(int gameChoice, int modeChose){
        switch (gameChoice){
            case 1:
                MoreOrLess moreLess = new MoreOrLess();
                moreLess.initMoreOrLess(modeChose);
                break;
            case 2:
                Mastermind mastermind = new Mastermind();
                mastermind.initMastermind(modeChose);
                break;
        }
    }
}

