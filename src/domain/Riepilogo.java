package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Riepilogo {

    private int mese;
    private int anno;
    private boolean validato;

    private Map<Integer,Registrazione> registrazioni;

    public Riepilogo(int mese, int anno) {
        this.mese = mese;
        this.anno = anno;

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

    public boolean isValidato() {
        return validato;
    }

    public void setValidato(boolean validato) {
        this.validato = validato;
    }

    public Map<Integer,Registrazione> getRegistrazioniMensiliDipendente(int idDipendente){

        return  registrazioni.entrySet().stream()
                .filter(x -> x.getValue().getDipendente().getIdDipendente() == idDipendente)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));


    }

    public Map<Integer,Registrazione> getRegistrazioniGiornaliereDipendente(int giorno, int idDipendente){

        return  registrazioni.entrySet().stream()
                .filter(x -> x.getValue().getGiorno() == giorno  && x.getValue().getDipendente().getIdDipendente() == idDipendente)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    }

    public void addRegistrazione (Registrazione registrazione){
        int id = this.registrazioni.size()-1;
        registrazione.setIdRegistrazione(id);
        this.registrazioni.put(id, registrazione);
    }

//    public Map<Integer,Registrazione> getAllRegistrazioniMensili(int giorno, int mese){
//        return  registrazioni.entrySet().stream()
//                .filter(x -> x.getValue().getGiorno() == giorno && x.getValue().getMese() == mese && x.getValue().getAnno() == anno)
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
//    }


    public Map<Integer, Registrazione> getRegistrazioni() {
        return registrazioni;
    }
}
