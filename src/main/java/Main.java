import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Lancement de l'application");
        Property prop = new Property();

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

        Game game = new Game();
        game.initGame();
    }
}
