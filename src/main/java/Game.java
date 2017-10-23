import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Scanner;

public class Game {

    private static final Logger logger = LogManager.getLogger(Game.class);
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
                logger.info("Lancement de l'application");
                System.exit(0);
                break;
        }
    }

    public int getNbEntry(Scanner sc, int game, int nbMax){
        int nbUser = 0;
        boolean isNb = false;

        do {
            if (game == 1){
                System.out.println("Entrez une combinaison à " + Property.nbSizeMol + " chiffres :\n");
            }else{
                System.out.println("Entrez une combinaison à " + Property.nbSizeMd + " chiffres :\n");
            }

            try {
                nbUser = sc.nextInt();
                if (nbUser <= nbMax){
                    isNb = true;
                }
            } catch (InputMismatchException e){
                sc.next();
            }
        } while(!isNb);
        return (nbUser);
    }

    public String fillOfZero(String newNbUser, int game){
        int i = 0;
        int j = 0;
        int nbToFill = 0;
        StringBuilder nbUserStr = new StringBuilder();

        if (game == 1){
            nbToFill = Property.nbSizeMol - newNbUser.length();
        }else{
            nbToFill = Property.nbSizeMd - newNbUser.length();
        }

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

    public int getMaxForRand(int limit){
        StringBuilder nbStr = new StringBuilder();

        for (int i = 0; i < limit; i++){
            nbStr.append("9");
        }
        return (Integer.valueOf(nbStr.toString()));
    }

    public int getMinForRand(int limit){
        StringBuilder nbStr = new StringBuilder();

        nbStr.append("1");
        for (int i = 0; i < (limit - 1); i++){
            nbStr.append("0");
        }
        return (Integer.valueOf(nbStr.toString()));
    }
}

