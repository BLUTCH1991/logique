import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.Properties;

public class Property {

    private static final Property instance = new Property();
    private Property(){}

    private int nbSizeMol = 0;
    private int nbSizeMd = 0;
    private int nbTryMol = 0;
    private int nbTryMd = 0;
    private String devMode = "";
    private int nbDifferentDigit = 0;
    private static final Logger logger = LogManager.getLogger(Property.class);
    private Properties prop = new Properties();
    private InputStream input = null;

    /**************** GETTERS ********************/

    public static Property getInstance(){
        return instance;
    }

    public int getNbSizeMd() {
        return this.nbSizeMd;
    }

    public int getNbTryMd() {
        return this.nbTryMd;
    }

    public int getNbSizeMol() {
        return this.nbSizeMol;
    }

    public int getNbTryMol() {
        return this.nbTryMol;
    }

    public String getDevMode() {
        return this.devMode;
    }

    public int getNbDifferentDigit() {
        return this.nbDifferentDigit;
    }

    /**************** METHODS ***********************/

    public void initProperties() {
        InputStream input = null;

        try {
            input = new FileInputStream("./src/main/ressources/config.properties");
            this.prop.load(input);

            this.nbSizeMol = Integer.valueOf(prop.getProperty("nbSizeMol"));
            this.nbSizeMd = Integer.valueOf(prop.getProperty("nbSizeMd"));
            this.nbTryMol = Integer.valueOf(prop.getProperty("nbTryMol"));
            this.nbTryMd = Integer.valueOf(prop.getProperty("nbTryMd"));
            this.nbDifferentDigit = Integer.valueOf(prop.getProperty("nbDifferentDigit"));
            this.devMode = prop.getProperty("devMode");

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void setOneProperty(String key, String value) {
        try {
            this.prop.load(new FileInputStream("./src/main/ressources/config.properties"));
            this.prop.put(key, value);
            FileOutputStream output = new FileOutputStream("./src/main/ressources/config.properties");
            this.prop.store(output, "");
        } catch (IOException ex) {
            logger.error("Error on setOneProperty()");
            ex.printStackTrace();
        }
    }

    public void getAllProperties() {
        try {
            input = new FileInputStream("./src/main/ressources/config.properties");
            this.prop.load(input);

            System.out.println("\n- MASTERMIND -");
            System.out.println("1) Nombre d'essais : " + this.prop.getProperty("nbTryMd"));
            System.out.println("2) Nombre de chiffres de la combinaison : " + this.prop.getProperty("nbSizeMd"));
            System.out.println("3) Nombre de chiffres utilisables : " + this.prop.getProperty("nbDifferentDigit") + "\n");
            System.out.println("- PLUS OU MOINS -");
            System.out.println("4) Nombre d'essais : " + this.prop.getProperty("nbTryMol"));
            System.out.println("5) Nombre de chiffres de la combinaison : " +  this.prop.getProperty("nbSizeMol") + "\n");
            System.out.println("6) Quitter le fichier de configuration\n");

        } catch (IOException ex) {
            logger.error("Error on getAllProperties()");
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
