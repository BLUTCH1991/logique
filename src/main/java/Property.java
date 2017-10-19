import java.io.*;
import java.util.Properties;


public class Property extends Game {

    public static int nbSizeMol = 0;
    public static int nbSizeMd = 0;
    public static int nbTryMol = 0;
    public static int nbTryMd = 0;


    public void initProperties(){
        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("./src/main/ressources/config.properties");

            // load a properties file
            prop.load(input);

            nbSizeMol = Integer.valueOf(prop.getProperty("nbSizeMol"));
            nbSizeMd = Integer.valueOf(prop.getProperty("nbSizeMd"));
            nbTryMol = Integer.valueOf(prop.getProperty("nbTryMol"));
            nbTryMd = Integer.valueOf(prop.getProperty("nbTryMd"));

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

    public void setProperties(){
        java.util.Properties prop = new java.util.Properties();
        OutputStream output = null;

        try {
            output = new FileOutputStream("./src/main/ressources/config.properties");

            // set the properties value
            prop.setProperty("nbSize", "4");
            prop.setProperty("nbTry", "12");

            // save properties to project root folder
            prop.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
