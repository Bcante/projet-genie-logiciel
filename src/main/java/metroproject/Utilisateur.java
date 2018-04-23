package metroproject;

public class Utilisateur {
    private int x,y;
    private String nom;

    public Utilisateur(String nom) {
        this(-1,-1,nom);
    }

    public Utilisateur(int x, int y, String nom) {
        this.x = x;
        this.y = y;
        this.nom = nom;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
