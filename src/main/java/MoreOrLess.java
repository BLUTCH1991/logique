public class MoreOrLess extends Game{

    public void initMoreOrLess(int modeChose){
        switch (modeChose){
            case 1:
                System.out.println("Bienvenue dans le jeu plus ou moins en mode Challenger !");
                break;
            case 2:
                System.out.println("Bienvenue dans le jeu plus ou moins en mode Défenseur !");
                break;
            case 3:
                System.out.println("Bienvenue dans le jeu plus ou moins en mode Duel !");
                break;
        }
    }
}
