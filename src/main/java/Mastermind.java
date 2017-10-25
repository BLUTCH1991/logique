import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class Mastermind extends Game {

    private Property prop = super.getProp();
    private int nbSizeMd = prop.getNbSizeMd();
    private int nbTryMd = prop.getNbTryMd();
    private String devMode = prop.getDevMode();
    private int nbDifferentDigit = prop.getNbDifferentDigit();
    private static final Logger logger = LogManager.getLogger(Mastermind.class);
    private int nbPresent = 0;
    private int nbPresComputer = 0;
    private int nbPut = 0;
    private int nbPutComputer = 0;
    private int level = 0;
    private int computerAttempt = 0;
    private ArrayList<String> combinations=new ArrayList<String>();
    private int[][] records = new int[10][1];
    private int[] usableNbs = new int[nbDifferentDigit];

    /*************** GETTERS ***********************/

    public int getNbSizeMd() {
        return this.nbSizeMd;
    }

    public int getNbTryMd() {
        return this.nbTryMd;
    }

    public int[] getUsableNbs() {
        return this.usableNbs;
    }

    public String getDevMode(){
        return this.devMode;
    }

    /************** METHODS **************************/

    public int getCustomRandom(){
        Random random = new Random();
        int max = this.usableNbs.length - 1;
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < this.nbSizeMd; i++){
            result.append(random.nextInt(max+1));
        }
        return Integer.valueOf(result.toString());
    }

    public int balanceComputerPresent(String nbUserStr, String nbToFindStr, int nbPresentComputer){
        int count = 9;
        int nbInUser, nbInFind;

        while (count >= 0){
            nbInFind = 0;
            nbInUser = 0;
            for (int i = 0; i < nbUserStr.length(); i++){
                if (Character.getNumericValue(nbUserStr.charAt(i)) == count){
                    nbInUser += 1;
                }
            }
            for (int i = 0; i < nbToFindStr.length(); i++){
                if (Character.getNumericValue(nbToFindStr.charAt(i)) == count){
                    nbInFind += 1;
                }
            }
            if (nbInFind > 0 && nbInUser > 0) {
                nbPresentComputer -= (nbInUser - nbInFind);
            }
            count -= 1;
        }
        return nbPresentComputer;
    }

    public void balanceNbPresent(String nbUserStr, String nbToFindStr, int user){
        int count = 9;
        int nbInUser, nbInFind;

        while (count >= 0){
            nbInFind = 0;
            nbInUser = 0;
            for (int i = 0; i < nbUserStr.length(); i++){
                if (Character.getNumericValue(nbUserStr.charAt(i)) == count){
                    nbInUser += 1;
                }
            }
            for (int i = 0; i < nbToFindStr.length(); i++){
                if (Character.getNumericValue(nbToFindStr.charAt(i)) == count){
                    nbInFind += 1;
                }
            }
            if (nbInFind > 0 && nbInUser > 0) {
                if (user == 1){
                    this.nbPresent -= (nbInUser - nbInFind);
                }else{
                    this.nbPresComputer -= (nbInUser - nbInFind);
                }
            }
            count -= 1;
        }
    }

    public boolean isNbInTab(int[] nbFound, int nb){
        boolean isExist = false;

        for (int k = 0; k < nbFound.length; k++) {
            if (nb == nbFound[k]) {
                isExist = true;
            }
        }
        return (isExist);
    }

    public void printClue(int gameMode, String nbUserStr){
        switch (gameMode){
            case 1:
                System.out.println("Votre proposition : " + nbUserStr + " -> indice : " +
                        this.nbPresent + " présents et " + this.nbPut + " bien placés\n");
                break;
            case 2:
                this.computerAttempt += 1;
                System.out.println("Proposition de l'ordinateur : " + nbUserStr + " -> indice : " +
                        this.nbPresComputer + " présents et " + this.nbPutComputer + " bien placés");
                break;
            case 3:
                this.computerAttempt += 1;
                System.out.println("L'ordinateur a joué.\n");
                break;
        }
    }

    public void countNbPresent(String nbToFindStr, String nbUserStr, int[] nbFound, int i, int user){
        for (int j = 0; j < nbToFindStr.length(); j++){
            if (nbToFindStr.charAt(j) != '\0' && nbToFindStr.charAt(j) == nbUserStr.charAt(i)){
                if (!isNbInTab(nbFound,nbToFindStr.charAt(j))){
                    nbFound[j] = nbToFindStr.charAt(j);
                    if (user == 1){
                        this.nbPresent += 1;
                    }else{
                        this.nbPresComputer += 1;
                    }

                }
            }
        }
    }

    public boolean checkNb(int nbUser, int nbToFind, int gameMode, int user){
        String nbToFindStr = String.valueOf(nbToFind);
        String nbUserStr = String.valueOf(nbUser);
        this.nbPresent = 0;
        this.nbPresComputer = 0;
        this.nbPut = 0;
        this.nbPutComputer = 0;
        int[] nbFound = new int[this.nbSizeMd];

        //Example : get 131 but nb size = 4 so generate 0131
        if (nbUserStr.length() < this.nbSizeMd){
           nbUserStr = fillOfZero(nbUserStr,2);
        }
        if (nbToFindStr.length() < this.nbSizeMd){
            nbToFindStr = fillOfZero(nbToFindStr,2);
        }

        //count the numbers present and well placed
        for (int i = 0; i < nbUserStr.length(); i++){
            if (nbUserStr.charAt(i) == nbToFindStr.charAt(i)){
                if (user == 1){
                    this.nbPut += 1;
                }else {
                    this.nbPutComputer += 1;
                }
            }
            //Reset array
            for (int k = 0; k < nbFound.length; k++){
                nbFound[k] = 10;
            }
            countNbPresent(nbToFindStr,nbUserStr,nbFound,i,user);
        }
        balanceNbPresent(nbUserStr,nbToFindStr,user);
        printClue(gameMode,nbUserStr);
        return (user == 1) ? this.nbPut == this.nbSizeMd : this.nbPutComputer == this.nbSizeMd;
    }

    public boolean checkProposition(String proposition){
        for (int i = 0; i < this.combinations.size(); i++){
            if (this.combinations.get(i).equals(proposition)){
                return false;
            }
        }
        return true;
    }

    public static int[] shuffleArray(int[] array){
        Random rand = new Random();
        int randomPosition = 0;
        int temp = 0;

        for (int i = 0; i < array.length; i++) {
            randomPosition = rand.nextInt(array.length);
            temp = array[i];
            array[i] = array[randomPosition];
            array[randomPosition] = temp;
        }
        return array;
    }

    public int myRandom(ArrayList alRand, int limit){
        int i = 0;
        int j = 0;
        int nbToReturn = 0;

        if (alRand.size() == 1){
            return (Integer.valueOf(alRand.get(0).toString()));
        }

        while (i < limit){
            j++;
            i++;
            if (j == alRand.size() - 1){
                j = 0;
            }
        }
        nbToReturn = Integer.valueOf(alRand.get(j).toString());
        alRand.remove(j);
        return (nbToReturn);
    }

    public String generateNumber(String nbToFindStr, String nbComputerStr){
        Random random = new Random();
        int nbRand = 0;
        StringBuilder proposition = new StringBuilder();
        int[] randomArr = new int[this.nbSizeMd];
        boolean isPropositionOk = false;
        ArrayList<Integer> alRand = new ArrayList<Integer>();

        //Example : get 131 but nb size = 4 so generate 0131
        if (nbComputerStr.length() < this.nbSizeMd){
            nbComputerStr = fillOfZero(nbComputerStr,2);
        }

        //Create an array with random numbers possibilities
        for (int i = 0; i < nbComputerStr.length(); i++){
            randomArr[i] = Character.getNumericValue(nbComputerStr.charAt(i));
            alRand.add(randomArr[i]);
        }

        //Generate new combination
        while (!isPropositionOk){
            proposition.setLength(0);

            for (int i = 0; i < nbToFindStr.length(); i++){
                nbRand = myRandom(alRand,random.nextInt(30 - 5 + 1) + 5);

                //Add the random number to the proposition
                proposition.append(Integer.toString(nbRand));
            }

            //Get a new random array
            randomArr = shuffleArray(randomArr);

            //Empty the list
            for (int i = 0; i < alRand.size(); i++){
                alRand.remove(i);
            }

            //Create new list from array
            for (int i = 0; i < randomArr.length; i++){
                alRand.add(randomArr[i]);
            }

            //Check if proposition already exists
            isPropositionOk = checkProposition(proposition.toString());
        }
        //Add proposition to combinations table
        this.combinations.add(proposition.toString());
        return (proposition.toString());
    }

    public void sizeReached(String nbComputerStr, String nbToFindStr, Scanner sc){
        while (!nbToFindStr.equals(nbComputerStr)){
            nbComputerStr = generateNumber(nbToFindStr,nbComputerStr);
            checkNb(Integer.parseInt(nbComputerStr),Integer.parseInt(nbToFindStr),2,2);
            if (this.computerAttempt == this.nbTryMd){
                break;
            }
        }
        if (this.computerAttempt == this.nbTryMd){
            System.out.println("\nL'ordinateur n'a pas réussi a trouvé la bonne combinaison.");
        }else{
            System.out.println("\nL'ordinateur a trouvé la combinaison en " + this.computerAttempt + " coups.");
        }
        endOfGame(2,2,sc);
    }

    public boolean ifExist(String nbToFindStr, int nbToCheck){
        for (int j = 0; j < nbToFindStr.length(); j++){
            if (Character.getNumericValue(nbToFindStr.charAt(j)) == nbToCheck){
                return true;
            }
        }
        return false;
    }

    public StringBuilder newNbComputerPres(String nbToFindStr, StringBuilder newNbComputerStr){
        int savePosition = 0;

        this.records[this.level][this.records[this.level].length - 1] = !ifExist(nbToFindStr,this.level) ? 0 : howManyInNb(nbToFindStr,this.level);
        for (int i = 0; i < this.records.length; i++){
            if (this.records[i][this.records[i].length - 1] > 0){
                for (int j = 0; j < this.records[i][this.records[i].length - 1]; j++){
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
        return newNbComputerStr;
    }

    public int getComputerNb(int nbComputer, int nbToFind, int gameMode){
        String nbToFindStr = String.valueOf(nbToFind);
        String nbComputerStr = String.valueOf(nbComputer);
        StringBuilder newNbComputerStr = new StringBuilder();
        Scanner sc = new Scanner(System.in);

        //Example : get 131 but nb size = 4 so generate 0131
        if (nbComputerStr.length() < this.nbSizeMd){
            nbComputerStr = fillOfZero(nbComputerStr,2);
        }
        if (nbToFindStr.length() < this.nbSizeMd){
            nbToFindStr = fillOfZero(nbToFindStr,2);
        }

        //All nb are present, start to find the good combination
        if (this.nbPresComputer == this.nbSizeMd){
            this.combinations.add(nbComputerStr);
            if (gameMode == 2){
                sizeReached(nbComputerStr,nbToFindStr,sc);
            }
        }

        if (this.level == 10){
            this.level = 0;
        }

        this.nbPresComputer = countNbPresComputer(nbComputer,nbToFind);
        newNbComputerStr = (this.nbPresComputer == 0) ? getFullNbOfLevel(this.level + 1) : newNbComputerPres(nbToFindStr,newNbComputerStr);
        return (Integer.parseInt(newNbComputerStr.toString()));
    }

    public void initRecordsTab(){
        for (int i = 0; i < this.records.length; i++){
            for (int j = 0; j < this.records[i].length; j++){
                this.records[i][j] = 0;
            }
        }
    }

    public StringBuilder getFullNbOfLevel(int level){
        this.level += 1;
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < this.nbSizeMd; i++){
            str.append(level);
        }
        return str;
    }

    public int howManyInNb(String nbToFindStr, int nbToCheck){
        int result = 0;

        for (int j = 0; j < nbToFindStr.length(); j++){
            if (Character.getNumericValue(nbToFindStr.charAt(j)) == nbToCheck){
                result += 1;
            }
        }
        return (result);
    }

    public int countNbPresComputer(int nbComputer, int nbToFind){
        int result = 0;
        String nbToFindStr = String.valueOf(nbToFind);
        String nbComputerStr = String.valueOf(nbComputer);
        int[] nbFound = new int[this.nbSizeMd];
        boolean isExist = false;

        //Example : get 131 but nb size = 4 so generate 0131
        if (nbComputerStr.length() < this.nbSizeMd){
            nbComputerStr = fillOfZero(nbComputerStr,2);
        }
        if (nbToFindStr.length() < this.nbSizeMd){
            nbToFindStr = fillOfZero(nbToFindStr,2);
        }

        for (int i = 0; i < nbComputerStr.length(); i++){
            for (int k = 0; k < nbFound.length; k++){
                nbFound[k] = 10;
            }
            for (int j = 0; j < nbToFindStr.length(); j++){
                if (nbToFindStr.charAt(j) == nbComputerStr.charAt(i)){
                    isExist = isNbInTab(nbFound,nbToFindStr.charAt(j));
                    if (!isExist){
                        nbFound[j] = nbToFindStr.charAt(j);
                        result += 1;
                    }
                }
            }
        }
        result = balanceComputerPresent(nbComputerStr,nbToFindStr,result);
        return (result);
    }

    public boolean isUsableNb(int[] usableNbs, String nbUserStr){
        for (int i = 0; i < nbUserStr.length(); i++){
            if (Character.getNumericValue(nbUserStr.charAt(i)) > usableNbs.length - 1){
                return false;
            }
        }
        return true;
    }

    public int getNbEntryMd(Scanner sc, int nbMax, int[] usableNbs){
        int nbUser = 0;
        boolean isNb = false;
        String nbUserStr = "";

        do {
            System.out.print("Entrez une combinaison à " + this.nbSizeMd + " chiffres ");
            System.out.print("avec les chiffres suivants : ");
            printUsableNbs();
            try {
                nbUserStr = sc.nextLine();
                nbUser = Integer.parseInt(nbUserStr);
                if (nbUser <= nbMax && isUsableNb(usableNbs,Integer.toString(nbUser)) && nbUserStr.length() == nbSizeMd){
                    isNb = true;
                }
            } catch (InputMismatchException e){
                sc.next();
            }
        } while(!isNb);
        return (nbUser);
    }

    public void printUsableNbs(){
        for (int i = 0; i < this.nbDifferentDigit; i++){
            System.out.print(i);
            if (i < this.nbDifferentDigit - 1){
                System.out.print(" - ");
            }
        }
        System.out.println("\n");
    }

    public void initUsableNbs(){
        for (int i = 0; i < this.nbDifferentDigit - 1; i++){
            this.usableNbs[i] = i;
        }
    }

    public void initMastermind(int modeChose){
        initUsableNbs();
        switch (modeChose){
            case 1:
                logger.info("Jeu choisi : Mastermind");
                logger.info("Mode de jeu choisi : Challenger");
                System.out.println("Bienvenue sur Mastermind en mode Challenger !");
                ChallengerMd challenger = new ChallengerMd();
                challenger.startChallenger();
                break;
            case 2 :
                logger.info("Jeu choisi : Mastermind");
                logger.info("Mode de jeu choisi : Défenseur");
                System.out.println("Bienvenue sur Mastermind en mode Défenseur !");
                DefenderMd defender = new DefenderMd();
                initRecordsTab();
                defender.startDefenderMd();
                break;
            case 3:
                logger.info("Jeu choisi : Mastermind");
                logger.info("Mode de jeu choisi : Duel");
                System.out.println("Bienvenue sur Mastermind en mode Duel !");
                DuelMd duel = new DuelMd();
                initRecordsTab();
                duel.startDuel();
                break;
        }
    }
}
