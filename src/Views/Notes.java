package Views;

import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;

public class Notes extends JFrame {
    private JPanel mainPanel;
    private JList noteList;
    private JTextField searchNoteTextField; //TODO este textfield tiene que servir para buscar las notas dentro de la carpeta
    private JButton addNoteButton; //TODO tiene que limpiar el textArea del JPanel noteInput, es decir, limpiar el textfield del titulo
    private JComboBox filterNoteComboBox; //TODO tiene que filtrar las notas como por nombre, por fecha
    private JTextArea textArea1;
    private JButton saveNoteButton; //TODO al darle click tiene que guardar las notas
    public JList folderList; //TODO mostrar las carpetas disponibles
    private JButton editNoteButton; //TODO editar la nota, es decir, pasar al text area el contenido de la nota y el titulo
    private JButton deleteNoteButton1;
    private JButton editFolderButton;
    private JButton deleteFolderButton;
    private JLabel folderTextField;
    private JLabel mainFolderTextField;
    private JButton addFolderButton;
    private JTextField nameNoteTextField;
    private JPanel folderJPanel;
    private JPanel noteJPanel;
    private JPanel noteInputJPanel;

    public Notes (){
        super("NoteApp");
        add(mainPanel);
        setSize(800,600);
        textArea1.setLineWrap(true);
        textArea1.setWrapStyleWord(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    DefaultListCellRenderer renderer = (DefaultListCellRenderer) folderList.getCellRenderer();

}
