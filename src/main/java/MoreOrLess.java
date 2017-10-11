import java.lang.reflect.Array;
import java.util.*;

public class MoreOrLess extends Game{

    private Integer tabEntries[][] = new Integer[4][10];
    //private ArrayList<Integer> digitEntries = new ArrayList<Integer>();

    public boolean checkNb(int nbUser, int nbToFind, int mode){
        boolean isCorrect = true;
        String newNbToFind = String.valueOf(nbToFind);
        String newNbUser = String.valueOf(nbUser);

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < newNbUser.length();i++){
            if (newNbToFind.charAt(i) == newNbUser.charAt(i)){
                result.append("=");
            }
            if (newNbToFind.charAt(i) > newNbUser.charAt(i)){
                result.append("+");
                isCorrect = false;
            }
            if (newNbToFind.charAt(i) < newNbUser.charAt(i)){
                result.append("-");
                isCorrect = false;
            }
        }
        switch (mode){
            case 1:
                System.out.println("Proposition : " + nbUser + " -> Réponse : " + result);
            case 2:
                System.out.println("Proposition de l'ordinateur : " + nbUser + " -> Réponse : " + result);
        }
        return (isCorrect);
    }

    public int getNbEntry(Scanner sc){
        int nbUser = 0;
        boolean isNb = false;

        do {
            System.out.println("Entrez une combinaison à 4 chiffres (de 1000 à 9999) :\n");
            try {
                nbUser = sc.nextInt();
                if (nbUser <= 9999 && nbUser >= 1000){
                    isNb = true;
                }
            } catch (InputMismatchException e){
                sc.next();
            }
        } while(!isNb);
        return (nbUser);
    }

    public void initTabEntries(){
        for (int i = 0; i < this.tabEntries.length; i++){
            for (int j = 0; j < this.tabEntries[i].length; j++){
                this.tabEntries[i][j] = 10;
            }
        }
    }

    private int lastNbPosition(int i){
        int j = 0;

        while (this.tabEntries[i][j] != 10){
            j += 1;
        }
        return (j);
    }

    public int getNewDigit(int i, int digit, int way){
        int sizeLine = lastNbPosition(i);
        int randomNb = 0;
        boolean isOk = false;
        Random rand = new Random();

        //TODO si c'est + alors on doit regarer le nombre le plus grand de la ligne du tableau
        if (way == 1){
            while (!isOk){
                isOk = true;
                randomNb = rand.nextInt((9 - digit) + 1) + digit;
                for (int j = 0; j < this.tabEntries[i].length; j++){
                    if (this.tabEntries[i][j] != null && randomNb == this.tabEntries[i][j]){
                        isOk = false;
                    }
                }
            }
        }else{
            while (!isOk){
                isOk = true;
                randomNb = rand.nextInt(digit);
                for (int j = 0; j < this.tabEntries[i].length; j++){
                    if (this.tabEntries[i][j] != null && randomNb == this.tabEntries[i][j]){
                        isOk = false;
                    }
                }
            }
        }
        //System.out.println("Valeur de sizeline : " + sizeLine);
        this.tabEntries[i][sizeLine] = randomNb;
        System.out.println("<<<<<<<< ARRAY >>>>>>>>>>");
        printArray(this.tabEntries);
        System.out.println("<<<<<<<< END >>>>>>>>>>");

        return (randomNb);
    }

    public int getComputerNb(Random random, int nbUser, int nbComputer){
        int digit = 0;
        int digitComputerChoice = 0;
        String nbUserStr = String.valueOf(nbUser);
        String nbComputerStr = String.valueOf(nbComputer);
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < nbUserStr.length();i++){
            digitComputerChoice = 0;
            digit = 0;
            if (nbUserStr.charAt(i) > nbComputerStr.charAt(i)){
                digit = Character.getNumericValue(nbComputerStr.charAt(i));
                digitComputerChoice = this.getNewDigit(i,digit,1);
                result.append(digitComputerChoice);
            }
            if (nbUserStr.charAt(i) < nbComputerStr.charAt(i)){
                digit = Character.getNumericValue(nbComputerStr.charAt(i));
                digitComputerChoice = this.getNewDigit(i,digit,2);
                result.append(digitComputerChoice);
            }
            if (nbUserStr.charAt(i) == nbComputerStr.charAt(i)){
                result.append(nbUserStr.charAt(i));
            }
        }
        return (Integer.parseInt(result.toString()));
    }

    public void defender(){
        Scanner sc = new Scanner(System.in);
        int nbUser = this.getNbEntry(sc);
        Random random = new Random();
        int nbComputer = random.nextInt(9999 - 1000 + 1) + 1000;
        boolean endOfGame = false;
        int nbTry = 10;

        System.out.println("Vous avez choisi : " + nbUser);

        do {
            if (nbTry == 10){
                nbComputer = random.nextInt(9999 - 1000 + 1) + 1000;
            }else{
                nbComputer = getComputerNb(random,nbUser,nbComputer);
            }
            endOfGame = this.checkNb(nbComputer,nbUser,2);
            nbTry -= 1;
            if (nbTry == 0){
                endOfGame = true;
            }
        }while (!endOfGame);

        if (nbTry == 0){
            System.out.println("L'ordinateur n'a pas réussi à trouver la combinaison !\n");
        }else{
            System.out.println("L'ordinateur a trouvé la combinaison !\n");
        }
        endOfGame(1,2,sc);
    }

    public void challenger(){
        Random random = new Random();
        int nbToFind = random.nextInt(9999 - 1000 + 1) + 1000;
        Scanner sc = new Scanner(System.in);
        int nbUser = 0;
        int nbTry = 10;
        boolean endOfGame = false;

        System.out.println("Ton objectif est de trouver la bonne combinaison, en 10 coups maximum\n");

        do {
            nbUser = getNbEntry(sc);
            endOfGame = this.checkNb(nbUser,nbToFind,1);
            nbTry -= 1;
            if (nbTry == 0){
                endOfGame = true;
            }
        } while(!endOfGame);
        if (nbTry == 0){
            System.out.println("Looser !\n");
        }else{
            System.out.println("Good game !\n");
        }
        endOfGame(1,1,sc);
    }

    public void initMoreOrLess(int modeChose){
        switch (modeChose){
            case 1:
                System.out.println("Bienvenue dans le jeu plus ou moins en mode Challenger !");
                this.challenger();
                break;
            case 2:
                System.out.println("Bienvenue dans le jeu plus ou moins en mode Défenseur !");
                this.defender();
                break;
            case 3:
                System.out.println("Bienvenue dans le jeu plus ou moins en mode Duel !");
                break;
        }
    }

    private void printArray(Integer[][] arr) {
        for (int i = 0; i < arr.length; i++){
            for (int j = 0; j < arr[i].length; j++){
                System.out.print(arr[i][j]);
            }
            System.out.print("\n");
        }
    }
}
