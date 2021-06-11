package Database;

import java.sql.*;
import java.util.ArrayList;

import Models.*;

public class DBHandler {
    private final String url = "jdbc:sqlite:notas.db";

    private static DBHandler instance = null;

    public static DBHandler getInstance() {
        if (instance == null){
            instance = new DBHandler();
        }
        return instance;
    }

    public void createTable(){
        String query = "CREATE TABLE IF NOT EXISTS folders(id INTEGER PRIMARY KEY, name TEXT)";
        String queryNota = "CREATE TABLE IF NOT EXISTS notes(id INTEGER PRIMARY KEY, text TEXT, dateModified TEXT, idFolder INTEGER)";
        try{
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute(query);
            stmt.execute(queryNota);
            conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addFolder(Folder folder){
        String query = "INSERT INTO folders(name) VALUES (?)";
        try{
            Connection conn = DriverManager.getConnection(url);
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, folder.getName());
            pstmt.executeUpdate();
            conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addNote(Note note){
        String query = "INSERT INTO notes(text,dateModified,idFolder) VALUES (?,?,?)";
        try{
            Connection conn = DriverManager.getConnection(url);
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, note.getText());
            pstmt.setString(2, note.getModificationDate());
            pstmt.setInt(3, note.getIdFolder());
            pstmt.executeUpdate();
            conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public final static int ORDER_BY_ID_ASC = 1, ORDER_BY_ID_DESC = 2,ORDER_BY_NAME = 3;
    public ArrayList<Folder> selectAllFolder(int orderBy ){
        String query = "SELECT * FROM folders";
        switch(orderBy) {
            case ORDER_BY_ID_ASC:
                query = "SELECT * FROM folders ORDER BY id asc";
                break;
            case ORDER_BY_ID_DESC:
                query = "SELECT * FROM folders ORDER BY id desc";
                break;
            case ORDER_BY_NAME:
                query = "SELECT * FROM folders ORDER BY name asc";
                break;
        }
        var folders = new ArrayList<Folder>();
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                folders.add(new Folder(id,name));
            }

            conn.close();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return folders;
    }
    public ArrayList<Note> selectNotes(int idFolder, int orderBy){
        String query = "SELECT * FROM notes";
        switch (orderBy){
            case 1:
                query = "SELECT * FROM notes WHERE idFolder = " + idFolder + " ORDER BY id asc";
                break;
            case 2:
                query = "SELECT * FROM notes WHERE idFolder = " + idFolder + " ORDER BY id desc";
                break;
            case 3:
                query = "SELECT * FROM notes WHERE idFolder = " + idFolder + " ORDER BY dateModified asc";
                break;
            case 4:
                query = "SELECT * FROM notes WHERE idFolder = " + idFolder + " ORDER BY dateModified desc";
                break;
        }
        var notes = new ArrayList<Note>();
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(query);


            while (rs.next()) {
                int id = rs.getInt("id");
                String text = rs.getString("text");
                String modificationDate = rs.getString("dateModified");

                notes.add(new Note(id,text,modificationDate,idFolder ));
            }
            conn.close();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return notes;
    }

    public void deleteNFolder(int id){
        String query = "DELETE FROM folders WHERE id = " + id;
        try {
            Connection conn = DriverManager.getConnection(url);
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.execute();
            conn.close();

        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void deleteNote(int id) {
        String query = "DELETE FROM notes WHERE id = " + id;
        try {
            Connection conn = DriverManager.getConnection(url);
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.execute();
            conn.close();

        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateNote(Note note) {
        String query = "UPDATE notes SET text = '" + note.getText() + "', dateModified = '" + note.getModificationDate() + "' WHERE id = " + note.getId();
        try {
            Connection conn = DriverManager.getConnection(url);
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.execute();
            conn.close();
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateLastNote(Note note) {
        String query = "UPDATE notes SET text = '" + note.getText() + "', dateModified = '" + note.getModificationDate() + "' WHERE id = (SELECT max(id) FROM notes)";
        try {
            Connection conn = DriverManager.getConnection(url);
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.execute();
            conn.close();
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
