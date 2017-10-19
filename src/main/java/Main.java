
public class Main {
    public static void main(String[] args) {
        Property prop = new Property();


        if (args.length > 0 && args[0].equals("42")){
            prop.setOneProperty("devMode", "true");
        }else{
            prop.setOneProperty("devMode", "false");
        }

        prop.initProperties();

        Game game = new Game();
        game.initGame();
    }
}
