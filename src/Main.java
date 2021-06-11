import Database.DBHandler;
import Models.Folder;
import Views.Notes;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Notes v = new Notes();
                v.setVisible(true);
                System.out.println("Hola");
                var db = new DBHandler();
                db.createTable();
                db.addFolder(new Folder(-1,"AAAAAAAAAHHHH"));
            }
        });

    }
}
