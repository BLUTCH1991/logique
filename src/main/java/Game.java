import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Game{

    private static final Logger logger = LogManager.getLogger(Game.class);
    private int gameChose = 0;
    private int modeChose = 0;
    private static Property prop;

    /******************* GETTERS ********************/

    public Property getProp(){
        return prop;
    }

    /****************** METHODS **********************/

    public void executeEndOfGameChoice(int nbUser){
        switch (nbUser){
            case 1:
                redirectToGame();
                break;
            case 2:
                initGame();
                break;
            case 3:
                System.out.println("A bientôt !");
                logger.info("Fermeture de l'application");
                System.exit(0);
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
                isNb = (nbUser > 0 && nbUser < 4);
            } catch (InputMismatchException e){
                sc.next();
            }
        } while(!isNb);

        executeEndOfGameChoice(nbUser);
    }

    public int getNbEntry(Scanner sc, int game, int nbMax){
        int nbUser = 0;
        boolean isNb = false;

        do {
            if (game == 1){
                System.out.println("Entrez une combinaison à " + prop.getNbSizeMol() + " chiffres :\n");
            }else{
                System.out.println("Entrez une combinaison à " + prop.getNbSizeMd() + " chiffres :\n");
            }

            try {
                nbUser = sc.nextInt();
                isNb = (nbUser <= nbMax);
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

        nbToFill = (game == 1) ? prop.getNbSizeMol() - newNbUser.length() : prop.getNbSizeMd() - newNbUser.length();

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

    public int getMaxForRand(int limit, int nbMax){
        StringBuilder nbStr = new StringBuilder();

        for (int i = 0; i < limit; i++){
            nbStr.append(Integer.toString(nbMax));
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

    private void redirectToGame(){
        switch (this.gameChose){
            case 1:
                MoreOrLess moreLess = new MoreOrLess();
                moreLess.initMoreOrLess(this.modeChose);
                break;
            case 2:
                Mastermind mastermind = new Mastermind();
                mastermind.initMastermind(this.modeChose);
                break;
        }
    }

    private void gameModeChoice(Scanner sc){
        boolean isNb = false;

        do {
            System.out.println("Sélectionnez un mode de jeu :\n");
            System.out.println("(1) Challenger\n(2) Défenseur\n(3) Duel\n");
            try {
                this.modeChose = sc.nextInt();
                if (this.modeChose > 0 && this.modeChose < 4){
                    isNb = true;
                }
            } catch (InputMismatchException e) {
                sc.next();
                isNb = false;
            }
        } while (!isNb);
    }

    private void gameChoice(Scanner sc){
        boolean isNb = false;

        do {
            System.out.println("Sélectionnez un jeu :\n");
            System.out.println("(1) Plus ou moins\n(2) Mastermind\n");
            try {
                this.gameChose = sc.nextInt();
                if (this.gameChose > 0 && this.gameChose < 3){
                    isNb = true;
                }
            } catch (InputMismatchException e) {
                sc.next();
                isNb = false;
            }
        } while (!isNb);
    }


    public void initGame(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Bienvenue !\n");
        gameChoice(sc);
        gameModeChoice(sc);
        redirectToGame();
    }


    public void initApp(String args[]){
        logger.info("Lancement de l'application");
        prop = new Property();

        //Check if software is running in dev mode
        if (args.length > 0 && args[0].equals("42")){
            prop.setOneProperty("devMode", "true");
            DevMode dev = new DevMode();
            dev.initDevMode();
        }else{
            prop.setOneProperty("devMode", "false");
        }

        //Fill global variables with file properties
        prop.initProperties();
        initGame();
    }
}

