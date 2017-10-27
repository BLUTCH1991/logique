import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DevMode {

    private Logger logger = LogManager.getLogger(DevMode.class.getName());

    public int getUserChoice(Scanner sc, int nbUser, int min, int max){
        boolean isNb = false;

        do {
            try {
                nbUser = sc.nextInt();
                if (nbUser >= min && nbUser <= max){
                    isNb = true;
                }else{
                    System.out.println("Veuillez faire un choix valide ("+min+" à "+max+")");
                }
            } catch (InputMismatchException e){
                sc.next();
            }
        } while(!isNb);
        return (nbUser);
    }

    public void setNewProperty(Scanner sc, int nbUser, Property prop){
        String newValue;

        switch (nbUser){
            case 1:
                System.out.println("Veuillez saisir une nouvelle valeur (1 à 100) pour le nombre d'essais du Mastermind :");
                newValue = Integer.toString(getUserChoice(sc,nbUser,1,100));
                prop.setOneProperty("nbTryMd",newValue);
                logger.info("Le nombre d'essais pour le Mastermind est maintenant à : " + newValue);
                break;
            case 2:
                System.out.println("Veuillez saisir une nouvelle valeur (4 à 10) pour la taille de combinaison du Mastermind :");
                newValue = Integer.toString(getUserChoice(sc,nbUser,4,10));
                prop.setOneProperty("nbSizeMd",newValue);
                logger.info("La taille de combinaison pour le Mastermind est maintenant à : " + newValue);
                break;
            case 3:
                System.out.println("Veuillez saisir une nouvelle valeur (4 à 10) pour le nombre de chiffres utilisables du Mastermind :");
                newValue = Integer.toString(getUserChoice(sc,nbUser,4,10));
                prop.setOneProperty("nbDifferentDigit",newValue);
                logger.info("Le nombre d'essais pour le Plus ou Moins est maintenant à : " + newValue);
                break;
            case 4:
                System.out.println("Veuillez saisir une nouvelle valeur (1 à 100) pour le nombre d'essais du Plus ou Moins :");
                newValue = Integer.toString(getUserChoice(sc,nbUser,1,100));
                prop.setOneProperty("nbTryMol",newValue);
                logger.info("Le nombre d'essais pour le Plus ou Moins est maintenant à : " + newValue);
                break;
            case 5:
                System.out.println("Veuillez saisir une nouvelle valeur (4 à 10) pour la taille de combinaison du Plus ou Moins :");
                newValue = Integer.toString(getUserChoice(sc,nbUser,4,10));
                prop.setOneProperty("nbSizeMol",newValue);
                logger.info("La taille de combinaison pour le Plus ou Moins est maintenant à : " + newValue);
                break;
        }
    }

    public void editProperties(Scanner sc){
        int nbUser = 0;
        boolean quit = false;
        Property prop = Property.getInstance();

        while (!quit){
            prop.getAllProperties();
            System.out.println("Sélectionnez le paramètre souhaité en entrant le nombre correspondant : ");
            nbUser = getUserChoice(sc,nbUser,1,6);
            if (nbUser < 6){
                setNewProperty(sc,nbUser,prop);
            }else {
                quit = true;
            }
        }
    }

    public void initDevMode(){
        Scanner sc = new Scanner(System.in);
        int nbUser = 0;

        System.out.println("\nBienvenue sur l'interface développeur.\n");
        System.out.println("Voulez-vous accéder aux paramètres du jeu ?\n1) oui\n2) non\n");

        if ((getUserChoice(sc,nbUser,1,2)) == 1){
            editProperties(sc);
        }
    }
}
