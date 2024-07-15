package modeloDTO;

public class Cliente {
    String id;
    String dni;
    String nom;
    String dir;
    String es;
    private String Email;
    public Cliente() {
    }

    public Cliente(String id, String dni, String nom, String dir, String es) {
        this.id = id;
        this.dni = dni;
        this.nom = nom;
        this.dir = dir;
        this.es = es;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getEs() {
        return es;
    }

    public void setEs(String es) {
        this.es = es;
    }


}
