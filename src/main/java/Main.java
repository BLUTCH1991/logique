
public class Main {
    public static void main(String[] args) {
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
