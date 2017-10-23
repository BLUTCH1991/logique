import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class Mastermind extends Game {

    private static final Logger logger = LogManager.getLogger(Mastermind.class);
    private int nbPresent = 0;
    private int nbPresentComputer = 0;
    private int nbPut = 0;
    private int nbPutComputer = 0;
    private int level = 0;
    private int computerAttempt = 0;
    private ArrayList<String> combinations=new ArrayList<String>();
    private int nbSize = Property.nbSizeMd;
    private int[][] records = new int[10][Property.nbSizeMd + 1];
    protected int[] usableNbs = new int[Property.nbDifferentDigit];


    public int getCustomRandom(){
        Random random = new Random();
        int max = usableNbs.length - 1;
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < Property.nbSizeMd; i++){
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
                if (user == 1){
                    this.nbPresent -= (nbInUser - nbInFind);
                }else{
                    this.nbPresentComputer -= (nbInUser - nbInFind);
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
                        this.nbPresentComputer + " présents et " + this.nbPutComputer + " bien placés");
                break;
            case 3:
                this.computerAttempt += 1;
                System.out.println("L'ordinateur a joué.\n");
                break;
        }
    }

    public boolean checkNb(int nbUser, int nbToFind, int gameMode, int user){
        String nbToFindStr = String.valueOf(nbToFind);
        String nbUserStr = String.valueOf(nbUser);
        this.nbPresent = 0;
        this.nbPresentComputer = 0;
        this.nbPut = 0;
        this.nbPutComputer = 0;
        int[] nbFound = new int[this.nbSize];

        if (nbUserStr.length() < this.nbSize){
           nbUserStr = fillOfZero(nbUserStr,2);
        }
        if (nbToFindStr.length() < this.nbSize){
            nbToFindStr = fillOfZero(nbToFindStr,2);
        }

        for (int i = 0; i < nbUserStr.length(); i++){
            if (nbUserStr.charAt(i) == nbToFindStr.charAt(i)){
                if (user == 1){
                    this.nbPut += 1;
                }else {
                    this.nbPutComputer += 1;
                }

            }
            //TODO find a good way to empty this tab
            for (int k = 0; k < nbFound.length; k++){
                nbFound[k] = 42;
            }
            for (int j = 0; j < nbToFindStr.length(); j++){
                if (nbToFindStr.charAt(j) != '\0' && nbToFindStr.charAt(j) == nbUserStr.charAt(i)){
                    if (!isNbInTab(nbFound,nbToFindStr.charAt(j))){
                        nbFound[j] = nbToFindStr.charAt(j);
                        if (user == 1){
                            this.nbPresent += 1;
                        }else{
                            this.nbPresentComputer += 1;
                        }

                    }
                }
            }
        }
        balanceNbPresent(nbUserStr,nbToFindStr,user);
        printClue(gameMode,nbUserStr);
        return (user == 1) ? this.nbPut == this.nbSize : this.nbPutComputer == this.nbSize;
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
        int[] randomArr = new int[this.nbSize];
        boolean isPropositionOk = false;
        ArrayList<Integer> alRand = new ArrayList<Integer>();

        if (nbComputerStr.length() < this.nbSize){
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
            if (this.computerAttempt == Property.nbTryMd){
                break;
            }
        }
        if (this.computerAttempt == Property.nbTryMd){
            System.out.println("\nL'ordinateur n'a pas réussi a trouvé la bonne combinaison.");
        }else{
            System.out.println("\nL'ordinateur a trouvé la combinaison en " + this.computerAttempt + " coups.");
        }
        endOfGame(2,2,sc);
    }

    public int getComputerNb(int nbComputer, int nbToFind, int gameMode){
        int savePosition = 0;
        String nbToFindStr = String.valueOf(nbToFind);
        String nbComputerStr = String.valueOf(nbComputer);
        StringBuilder newNbComputerStr = new StringBuilder();
        Scanner sc = new Scanner(System.in);

        if (nbComputerStr.length() < this.nbSize){
            nbComputerStr = fillOfZero(nbComputerStr,2);
        }
        if (nbToFindStr.length() < this.nbSize){
            nbToFindStr = fillOfZero(nbToFindStr,2);
        }

        if (this.nbPresentComputer == this.nbSize){
            this.combinations.add(nbComputerStr);
            if (gameMode == 2){
                sizeReached(nbComputerStr,nbToFindStr,sc);
            }
        }

        if (this.level == 10){
            this.level = 0;
        }

        this.nbPresentComputer = howManyPresentComputer(nbComputer,nbToFind);
        if (this.nbPresentComputer == 0){
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
        }
        return (Integer.parseInt(newNbComputerStr.toString()));
    }

    public boolean ifExist(String nbToFindStr, int nbToCheck){
        for (int j = 0; j < nbToFindStr.length(); j++){
            if (Character.getNumericValue(nbToFindStr.charAt(j)) == nbToCheck){
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

        for (int i = 0; i < this.nbSize; i++){
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
            if (Character.getNumericValue(nbToFindStr.charAt(j)) == nbToCheck){
                result += 1;
            }
        }
        return (result);
    }

    public int howManyPresentComputer(int nbComputer, int nbToFind){
        int result = 0;
        String nbToFindStr = String.valueOf(nbToFind);
        String nbComputerStr = String.valueOf(nbComputer);
        int[] nbFound = new int[this.nbSize];
        boolean isExist = false;

        if (nbComputerStr.length() < this.nbSize){
            nbComputerStr = fillOfZero(nbComputerStr,2);
        }
        if (nbToFindStr.length() < this.nbSize){
            nbToFindStr = fillOfZero(nbToFindStr,2);
        }

        for (int i = 0; i < nbComputerStr.length(); i++){
            //TODO find a good way to empty this tab
            for (int k = 0; k < nbFound.length; k++){
                nbFound[k] = 42;
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

        do {
            System.out.print("Entrez une combinaison à " + Property.nbSizeMd + " chiffres ");
            System.out.print("avec les chiffres suivants : ");
            printUsableNbs();
            try {
                nbUser = sc.nextInt();
                if (nbUser <= nbMax && isUsableNb(usableNbs,Integer.toString(nbUser))){
                    isNb = true;
                }
            } catch (InputMismatchException e){
                sc.next();
            }
        } while(!isNb);
        return (nbUser);
    }

    public void printUsableNbs(){
        for (int i = 0; i < Property.nbDifferentDigit; i++){
            System.out.print(i);
            if (i < Property.nbDifferentDigit - 1){
                System.out.print(" - ");
            }
        }
        System.out.println("\n");
    }

    public void initUsableNbs(){
        for (int i = 0; i < Property.nbDifferentDigit - 1; i++){
            usableNbs[i] = i;
        }
    }

    public void initMastermind(int modeChose){
        initUsableNbs();
        switch (modeChose){
            case 1:
                System.out.println("Bienvenue sur Mastermind en mode Challenger !");
                ChallengerMd challenger = new ChallengerMd();
                challenger.startChallenger();
                logger.info("Jeu choisi : Mastermind");
                logger.info("Mode de jeu choisi : Challenger");
                break;
            case 2 :
                System.out.println("Bienvenue sur Mastermind en mode Défenseur !");
                DefenderMd defender = new DefenderMd();
                initRecordsTab();
                defender.startDefenderMd();
                logger.info("Jeu choisi : Mastermind");
                logger.info("Mode de jeu choisi : Défenseur");
                break;
            case 3:
                System.out.println("Bienvenue sur Mastermind en mode Duel !");
                DuelMd duel = new DuelMd();
                initRecordsTab();
                duel.startDuel();
                logger.info("Jeu choisi : Mastermind");
                logger.info("Mode de jeu choisi : Duel");
                break;
        }
    }
}
