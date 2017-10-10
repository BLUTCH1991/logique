public class Mastermind extends Game {

    public void initMastermind(int modeChose){
        switch (modeChose){
            case 1:
                System.out.println("Bienvenue sur Mastermind en mode Challenger !");
                break;
            case 2 :
                System.out.println("Bienvenue sur Mastermind en mode Défenseur !");
                break;
            case 3:
                System.out.println("Bienvenue sur Mastermind en mode Duel !");
                break;
        }
    }
}
