package Models;

public class Note {
    private int id;
    private String text;
    private String modificationDate;
    private int idFolder;

    public Note() {
    }

    public Note(int id, String text, String modificationDate, int idFolder) {
        this.id = id;
        this.text = text;
        this.modificationDate = modificationDate;
        this.idFolder = idFolder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(String modificationDate) {
        this.modificationDate = modificationDate;
    }

    public int getIdFolder() {
        return idFolder;
    }

    public void setIdFolder(int idFolder) {
        this.idFolder = idFolder;
    }

    public String toString(){
        return this.text;
    }
}
