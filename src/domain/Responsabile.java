package domain;

public class Responsabile {

    private final int idResponsabile;
    private String nome;
    private String cognome;
    private String email;
    private String username;
    private String password;

    public Responsabile(int idResponsabile, String nome, String cognome, String email, String username, String password) {
        this.idResponsabile = idResponsabile;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public int getIdResponsabile() {
        return idResponsabile;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
