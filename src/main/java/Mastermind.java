import java.lang.reflect.Array;
import java.util.Arrays;

public class Mastermind extends Game {

    private int nbPresent = 0;
    private int nbPut = 0;
    private int level = 0;
    private int olNbPresent = 0;

    //TODO adapt whith parameter number
    private int[][] records = new int[10][5];

    public int balanceNbPresent(String nbUserStr, String nbToFindStr, int nbPresent){
        int count = 9;
        int nbInUser = 0;
        int nbInFind = 0;

        while (count >= 0){
            nbInFind = 0;
            nbInUser = 0;
            for (int z = 0; z < nbUserStr.length(); z++){
                if (Character.getNumericValue(nbUserStr.charAt(z)) == count){
                    nbInUser += 1;
                }
            }
            for (int e = 0; e < nbToFindStr.length(); e++){
                if (Character.getNumericValue(nbToFindStr.charAt(e)) == count){
                    nbInFind += 1;
                }
            }
            if (nbInFind > 0 && nbInUser > 0) {
                nbPresent -= (nbInUser - nbInFind);
            }
            count -= 1;
        }
        return (nbPresent);
    }

    public boolean checkIfNbExist(int[] nbFound, int nb){
        boolean isExist = false;

        for (int k = 0; k < nbFound.length; k++) {
            if (nb == nbFound[k]) {
                isExist = true;
            }
        }
        return (isExist);
    }

    public boolean checkNb(int nbUser, int nbToFind, int gameMode){
        String nbToFindStr = String.valueOf(nbToFind);
        String nbUserStr = String.valueOf(nbUser);
        this.nbPresent = 0;
        this.nbPut = 0;
        int[] nbFound = new int[nbToFindStr.length()];
        boolean isExist = false;

        if (nbUserStr.length() < nbToFindStr.length()){
           nbUserStr = fillOfZero(nbUserStr);
        }

        for (int i = 0; i < nbUserStr.length(); i++){
            if (nbUserStr.charAt(i) == nbToFindStr.charAt(i)){
                this.nbPut += 1;
            }
            //TODO find a good way to empty this tab
            for (int k = 0; k < nbFound.length; k++){
                nbFound[k] = 42;
            }
            for (int j = 0; j < nbToFindStr.length(); j++){
                if (nbToFindStr.charAt(j) != '\0' && nbToFindStr.charAt(j) == nbUserStr.charAt(i)){
                    isExist = checkIfNbExist(nbFound,nbToFindStr.charAt(j));
                    if (!isExist){
                        nbFound[j] = nbToFindStr.charAt(j);
                        this.nbPresent += 1;
                    }
                }
            }
        }

        this.nbPresent = balanceNbPresent(nbUserStr,nbToFindStr,this.nbPresent);

        System.out.println("\nlevel : " + (this.level - 1) + "\n");
        printMultiArray(this.records);
        System.out.println("\nNombre à trouver : " + nbToFind);

        switch (gameMode){
            case 1:
                System.out.println("Votre proposition : " + nbUserStr + " -> indice : " +
                        this.nbPresent + " présents et " + this.nbPut + " bien placés\n");
            case 2:
                System.out.println("Proposition de l'ordinateur : " + nbUserStr + " -> indice : " +
                        this.nbPresent + " présents et " + this.nbPut + " bien placés");
        }
        return (nbPut == 4);
    }

    public int getComputerNb(int nbComputer, int nbToFind){
        int savePosition = 0;
        int until = 0;
        String nbToFindStr = String.valueOf(nbToFind);
        String nbComputerStr = String.valueOf(nbComputer);
        StringBuilder newNbComputerStr = new StringBuilder();

        if (nbComputerStr.length() < 4){
            nbComputerStr = fillOfZero(nbComputerStr);
        }
        if (nbToFindStr.length() < 4){
            nbToFindStr = fillOfZero(nbToFindStr);
        }

        if (this.nbPresent == 0){
            fillRecords(this.level,nbToFindStr,2);
            this.level += 1;
            newNbComputerStr = getFullNbOfLevel(this.level);
        } else {
            if (!ifExist(nbToFindStr,this.level)){
                fillRecords(this.level,nbToFindStr,2);
            }else{
                fillRecords(this.level,nbToFindStr,3);
            }
            for (int i = 0; i < this.records.length; i++){
                if (this.records[i][this.records[i].length - 1] > 0){
                    until = this.records[i][this.records[i].length - 1];
                    for (int j = 0; j < until; j++){
                        newNbComputerStr.append(i);
                        savePosition += 1;
                    }
                }
            }
            while (savePosition < nbToFindStr.length()){
                newNbComputerStr.append(this.level);
                savePosition += 1;
            }
            this.level += 1;
        }
        return (Integer.parseInt(newNbComputerStr.toString()));
    }

    public boolean ifExist(String nbToFindStr, int nbToCheck){
        for (int j = 0; j < nbToFindStr.length(); j++){
            if (nbToFindStr.charAt(j) != '\0' && Character.getNumericValue(nbToFindStr.charAt(j)) == nbToCheck){
                return true;
            }
        }
        return false;
    }

    public void initRecordsTab(){
        for (int i = 0; i < this.records.length; i++){
            for (int j = 0; j < this.records[i].length; j++){
                this.records[i][j] = 0;
            }
        }
    }

    public StringBuilder getFullNbOfLevel(int level){
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < 4; i++){
            str.append(level);
        }
        return (str);
    }

    /*
        This function have to fill a line according nb presents numbers
        0 = Init
        1 = Good position
        2 = No existent
        3 = Potential position
     */
    public void fillRecords(int i, String nbToFindStr, int flag){
        for (int j = 0; j < this.records[i].length - 1; j++){
            this.records[i][j] = flag;
        }
        switch (flag){
            case 2:
                this.records[i][this.records[i].length - 1] = 0;
            case 3:
                this.records[i][this.records[i].length - 1] = howManyInNb(nbToFindStr,i);
        }
    }

    public int howManyInNb(String nbToFindStr, int nbToCheck){
        int result = 0;

        for (int j = 0; j < nbToFindStr.length(); j++){
            if (nbToFindStr.charAt(j) != '\0' && Character.getNumericValue(nbToFindStr.charAt(j)) == nbToCheck){
                result += 1;
            }
        }
        return (result);
    }

    public void initMastermind(int modeChose){
        switch (modeChose){
            case 1:
                System.out.println("Bienvenue sur Mastermind en mode Challenger !");
                ChallengerMd challenger = new ChallengerMd();
                challenger.startChallenger();
                break;
            case 2 :
                System.out.println("Bienvenue sur Mastermind en mode Défenseur !");
                DefenderMd defender = new DefenderMd();
                initRecordsTab();
                defender.startDefenderMd();
                break;
            case 3:
                System.out.println("Bienvenue sur Mastermind en mode Duel !");
                break;
        }
    }
}














