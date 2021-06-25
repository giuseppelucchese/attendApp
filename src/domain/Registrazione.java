package domain;

public class Registrazione {

    private int mese;
    private int anno;
    private int giorno;
    private int oraEntrata;
    private int minEntrata;
    private int oraUscita;
    private int minUscita;
    private int idRegistrazione;
    private Dipendente dipendente;

    public Registrazione() {
    }

    public Dipendente getDipendente() {
        return dipendente;
    }

    public void setDipendente(Dipendente dipendente) {
        this.dipendente = dipendente;
    }

    public int getIdRegistrazione() {
        return idRegistrazione;
    }

    public void setIdRegistrazione(int idRegistrazione) {
        this.idRegistrazione = idRegistrazione;
    }

    public int getMese() {
        return mese;
    }

    public void setMese(int mese) {
        this.mese = mese;
    }

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public int getGiorno() {
        return giorno;
    }

    public void setGiorno(int giorno) {
        this.giorno = giorno;
    }

    public int getOraEntrata() {
        return oraEntrata;
    }

    public void setOraEntrata(int oraEntrata) {
        this.oraEntrata = oraEntrata;
    }

    public int getMinEntrata() {
        return minEntrata;
    }

    public void setMinEntrata(int minEntrata) {
        this.minEntrata = minEntrata;
    }

    public int getOraUscita() {
        return oraUscita;
    }

    public void setOraUscita(int oraUscita) {
        this.oraUscita = oraUscita;
    }

    public int getMinUscita() {
        return minUscita;
    }

    public void setMinUscita(int minUscita) {
        this.minUscita = minUscita;
    }
}
