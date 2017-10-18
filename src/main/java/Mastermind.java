import java.lang.reflect.Array;
import java.util.*;

public class Mastermind extends Game {

    private int nbPresent = 0;
    private int nbPut = 0;
    private int level = 0;
    private int computerAttempt = 0;
    private ArrayList<String> combinations=new ArrayList<String>();

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

        switch (gameMode){
            case 1:
                System.out.println("Votre proposition : " + nbUserStr + " -> indice : " +
                        this.nbPresent + " présents et " + this.nbPut + " bien placés\n");
            case 2:
                this.computerAttempt += 1;
                System.out.println("Proposition de l'ordinateur : " + nbUserStr + " -> indice : " +
                        this.nbPresent + " présents et " + this.nbPut + " bien placés");
        }
        return (nbPut == 4);
    }

    public int getMinNumber(String nbStr){
        int nbMin = 10;

        for (int i = 0; i < nbStr.length(); i++){
            if (Character.getNumericValue(nbStr.charAt(i)) < nbMin){
                nbMin = Character.getNumericValue(nbStr.charAt(i));
            }
        }
        return (nbMin);
    }

    public int getMaxNumber(String nbStr){
        int nbMax = 0;

        for (int i = 0; i < nbStr.length(); i++){
            if (Character.getNumericValue(nbStr.charAt(i)) > nbMax){
                nbMax = Character.getNumericValue(nbStr.charAt(i));
            }
        }
        return (nbMax);
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

        for (int i = 0; i < array.length; i++) {
            int randomPosition = rand.nextInt(array.length);
            int temp = array[i];
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
        int nbMin = getMinNumber(nbComputerStr);
        int nbMax = getMaxNumber(nbComputerStr);
        Random random = new Random();
        int nbRand = random.nextInt(nbMax - nbMin + 1) + nbMin;
        StringBuilder proposition = new StringBuilder();
        int[] randomArr = new int[nbComputerStr.length()];
        boolean isPropositionOk = false;
        ArrayList<Integer> alRand = new ArrayList<Integer>();

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

            randomArr = shuffleArray(randomArr);
            for (int i = 0; i < alRand.size(); i++){
                alRand.remove(i);
            }
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

    public int getComputerNb(int nbComputer, int nbToFind){
        int savePosition = 0;
        int until = 0;
        String nbToFindStr = String.valueOf(nbToFind);
        String nbComputerStr = String.valueOf(nbComputer);
        StringBuilder newNbComputerStr = new StringBuilder();
        Scanner sc = new Scanner(System.in);

        if (nbComputerStr.length() < 4){
            nbComputerStr = fillOfZero(nbComputerStr);
        }
        if (nbToFindStr.length() < 4){
            nbToFindStr = fillOfZero(nbToFindStr);
        }

        if (this.nbPresent == 4){
            this.combinations.add(nbComputerStr);
            while (!nbToFindStr.equals(nbComputerStr)){
                nbComputerStr = generateNumber(nbToFindStr,nbComputerStr);
                checkNb(Integer.parseInt(nbComputerStr),Integer.parseInt(nbToFindStr),2);
            }
            System.out.println("\nL'ordinateur a trouvé la combinaison en " + this.computerAttempt + " coups.");
            endOfGame(2,2,sc);
        }

        if (this.level == 9){
            this.level = 0;
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














