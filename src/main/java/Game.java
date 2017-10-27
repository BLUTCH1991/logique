import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Game{

    private static final Logger logger = LogManager.getLogger(Game.class);
    private int gameChose = 0;
    private int modeChose = 0;
    private Property prop = Property.getInstance();


    /****************** METHODS **********************/

    public void printEndChallenger(int nbTry, long nbToFind){
        if (nbTry == 0){
            System.out.println("Vous avez perdu ! La solution était : " + nbToFind + "\n");
        }else {
            System.out.println("C'est gagné ! Bien joué !\n");
        }
    }

    public void printEndDefender(int nbTry){
        if (nbTry == 0){
            System.out.println("L'ordinateur n'a pas réussi à trouver la combinaison !\n");
        }else{
            System.out.println("L'ordinateur a trouvé la combinaison !\n");
        }
    }

    public void printEndDuel(long nbToFind, boolean endOfGameComputer, int nbTry){
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

    public void executeEndOfGameChoice(int userChoice){
        switch (userChoice){
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
        int userChoice = 0;
        this.gameChose = game;
        this.modeChose = gameMode;

        do {
            System.out.println("(1) Rejouer ?\n(2) Jouer à un autre jeu\n(3) Quitter");
            try {
                userChoice = sc.nextInt();
                isNb = (userChoice > 0 && userChoice < 4);
            } catch (InputMismatchException e){
                sc.next();
            }
        } while(!isNb);

        executeEndOfGameChoice(userChoice);
    }

    public long getNbEntry(Scanner sc, int game, long nbMax){
        long nbUser = 0;
        boolean isNb = false;

        do {
            if (game == 1){
                System.out.println("Entrez une combinaison à " + this.prop.getNbSizeMol() + " chiffres :\n");
            }else{
                System.out.println("Entrez une combinaison à " + this.prop.getNbSizeMd() + " chiffres :\n");
            }

            try {
                nbUser = sc.nextLong();
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

        nbToFill = (game == 1) ? this.prop.getNbSizeMol() - newNbUser.length() : this.prop.getNbSizeMd() - newNbUser.length();

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

    public long getMaxForRand(int limit, int nbMax){
        StringBuilder nbStr = new StringBuilder();
        for (int i = 0; i < limit; i++){
            nbStr.append(Integer.toString(nbMax));
        }
        return (Long.valueOf(nbStr.toString()));
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

        //Check if software is running in dev mode
        if (args.length > 0 && args[0].equals("42")){
            this.prop.setOneProperty("devMode", "true");
            DevMode dev = new DevMode();
            dev.initDevMode();
        }else{
            this.prop.setOneProperty("devMode", "false");
        }

        //Fill global variables with file properties
        this.prop.initProperties();
        initGame();
    }
}
