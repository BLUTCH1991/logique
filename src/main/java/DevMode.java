import java.util.InputMismatchException;
import java.util.Scanner;

public class DevMode {

    public int getNewNbSizeProperty(Scanner sc, int nbUser){
        boolean isNb = false;

        do {
            try {
                nbUser = sc.nextInt();
                if (nbUser > 3 && nbUser < 11){
                    isNb = true;
                }else{
                    System.out.println("Veuillez faire un choix valide (4 à 10) :");
                }
            } catch (InputMismatchException e){
                sc.next();
            }
        } while(!isNb);
        return (nbUser);
    }

    public int getNewNbTryProperty(Scanner sc, int nbUser){
        boolean isNb = false;
        do {
            try {
                nbUser = sc.nextInt();
                if (nbUser > 0 && nbUser < 101){
                    isNb = true;
                }else{
                    System.out.println("Veuillez faire un choix valide (1 à 100) :");
                }
            } catch (InputMismatchException e){
                sc.next();
            }
        } while(!isNb);
        return (nbUser);
    }

    public int getUserChoiceProperty(Scanner sc, int nbUser){
        boolean isNb = false;

        do {
            try {
                nbUser = sc.nextInt();
                if (nbUser > 0 && nbUser < 6){
                    isNb = true;
                }else {
                    System.out.println("Veuillez faire un choix valide (1 à 5) :");
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
                newValue = Integer.toString(getNewNbTryProperty(sc,nbUser));
                prop.setOneProperty("nbTryMd",newValue);
                break;
            case 2:
                System.out.println("Veuillez saisir une nouvelle valeur (4 à 10) pour la taille de combinaison du Mastermind :");
                newValue = Integer.toString(getNewNbSizeProperty(sc,nbUser));
                prop.setOneProperty("nbSizeMd",newValue);
                break;
            case 3:
                System.out.println("Veuillez saisir une nouvelle valeur (1 à 100) pour le nombre d'essais du Plus ou Moins :");
                newValue = Integer.toString(getNewNbTryProperty(sc,nbUser));
                prop.setOneProperty("nbTryMol",newValue);
                break;
            case 4:
                System.out.println("Veuillez saisir une nouvelle valeur (4 à 10) pour la taille de combinaison du Plus ou Moins :");
                newValue = Integer.toString(getNewNbSizeProperty(sc,nbUser));
                prop.setOneProperty("nbSizeMol",newValue);
                break;
        }
    }

    public void editProperties(Scanner sc){
        int nbUser = 0;
        boolean quit = false;
        String newValue = "";
        Property prop = new Property();

        while (!quit){
            Property.getAllProperties();
            System.out.println("Sélectionnez le paramètre souhaité en entrant le nombre correspondant : ");
            nbUser = getUserChoiceProperty(sc,nbUser);
            if (nbUser < 5){
                setNewProperty(sc,nbUser,prop);
            }else {
                quit = true;
            }
        }
    }

    public int getUserChoice(Scanner sc, int nbUser){
        boolean isNb = false;

        do {
            try {
                nbUser = sc.nextInt();
                if (nbUser == 1 || nbUser == 2){
                    isNb = true;
                }else{
                    System.out.println("Veuillez faire un choix valide (1 ou 2)");
                }
            } catch (InputMismatchException e){
                sc.next();
            }
        } while(!isNb);
        return (nbUser);
    }

    public void initDevMode(){
        Scanner sc = new Scanner(System.in);
        int nbUser = 0;

        System.out.println("\nBienvenue sur l'interface développeur.\n");
        System.out.println("Voulez-vous accéder aux paramètres du jeu ?\n1) oui\n2) non\n");

        nbUser = getUserChoice(sc, nbUser);

        switch (nbUser){
            case 1:
                editProperties(sc);
                break;
            case 2:
                break;
        }

    }
}
