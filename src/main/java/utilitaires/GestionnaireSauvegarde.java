package utilitaires;

import metroproject.Metro;

import java.io.*;

public class GestionnaireSauvegarde {
    public static void sauvegarde(Metro m) {
        try {
            FileOutputStream fileOut =
                    new FileOutputStream("metro.svg");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(m);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static Metro chargement() {
        Metro m = null;
        try {
            FileInputStream fileIn = new FileInputStream("metro.svg");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            m = (Metro) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
        }
        return m;
    }

}
