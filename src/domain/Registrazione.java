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
    private Filiale filiale;

    public Registrazione() {
    }

    public Registrazione(int mese, int anno, int giorno, int oraEntrata, int minEntrata, int oraUscita, int minUscita, int idRegistrazione, Dipendente dipendente, Filiale filiale) {
        this.mese = mese;
        this.anno = anno;
        this.giorno = giorno;
        this.oraEntrata = oraEntrata;
        this.minEntrata = minEntrata;
        this.oraUscita = oraUscita;
        this.minUscita = minUscita;
        this.idRegistrazione = idRegistrazione;
        this.dipendente = dipendente;
        this.filiale = filiale;
    }

    public Dipendente getDipendente() {
        return dipendente;
    }

    public void setDipendente(Dipendente dipendente) {
        this.dipendente = dipendente;
    }

    public Filiale getFiliale() {
        return filiale;
    }

    public void setFiliale(Filiale filiale) {
        this.filiale = filiale;
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

    @Override
    public String toString() {
        return "Registrazione{" +
                "mese=" + mese +
                ", anno=" + anno +
                ", giorno=" + giorno +
                ", oraEntrata=" + oraEntrata +
                ", minEntrata=" + minEntrata +
                ", oraUscita=" + oraUscita +
                ", minUscita=" + minUscita +
                ", idRegistrazione=" + idRegistrazione +
                ", dipendente=" + dipendente +
                ", filiale=" + filiale +
                '}';
    }
}
