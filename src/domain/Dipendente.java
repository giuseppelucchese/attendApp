package domain;

public class Dipendente {

    private final int idDipendente;
    private String nome;
    private String cognome;
    private String telefono;
    private String email;
    private final int codicebio;

    public Dipendente(int idDipendente, String nome, String cognome, String telefono, String email, int codicebio) {
        this.idDipendente = idDipendente;
        this.nome = nome;
        this.cognome = cognome;
        this.telefono = telefono;
        this.email = email;
        this.codicebio = codicebio;
    }

    public int getIdDipendente() {
        return idDipendente;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public int getCodicebio() {
        return codicebio;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
