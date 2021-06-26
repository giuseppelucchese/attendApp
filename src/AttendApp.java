import domain.Dipendente;
import domain.Registrazione;
import domain.Riepilogo;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class AttendApp {

    private static AttendApp singleton;

    private String modality;
    private int idDipendenteLogged;
    private int codiceBioDipLogged;
    private SistemaStipendi sistemaStipendi;
    private HashMap<Integer,Riepilogo> riepiloghi;

    public AttendApp(){
        this.sistemaStipendi = SistemaStipendi.getIstanza();
    }

    public static AttendApp getIstanza(){
        if(singleton == null)
            singleton = new AttendApp();
        return singleton;
    }

    public boolean identificaDipendente(int codicebio){
        this.sistemaStipendi.getDipendenti().forEach( (id,dip)->{
            if(dip.getCodicebio() == codicebio)
            this.idDipendenteLogged = dip.getIdDipendente();
            this.codiceBioDipLogged = codicebio;
        });
        return idDipendenteLogged != 0;
    }

    public boolean validaRiepilogo(Riepilogo riepilogo){
        sistemaStipendi.validaRiepilogo(riepilogo);
     return true;
    }

    public void registraIngresso(){
        LocalDateTime now = LocalDateTime.now();
        int idRiepilogo = now.getYear()+now.getMonthValue()+this.idDipendenteLogged;
        int idRegistrazione = now.getYear()+now.getMonthValue()+now.getHour()+ now.getMinute() + now.getSecond() +this.idDipendenteLogged;
        //recupero i riepiloghi di un determinato id = meseannodipendente
        Riepilogo riepilogo = this.riepiloghi.get(idRiepilogo);

        Registrazione registrazione = new Registrazione();
        registrazione.setGiorno(now.getDayOfMonth());
        registrazione.setMese(now.getMonth().getValue());
        registrazione.setAnno(now.getYear());
        registrazione.setOraEntrata(now.getHour());
        registrazione.setMinEntrata(now.getMinute());
        registrazione.setIdRegistrazione(idRegistrazione);

        //
        riepilogo.getRegistrazioni().put(idRegistrazione,registrazione);

        //aggiungo il riepilogo ai riepiloghi totali
        this.riepiloghi.put(idRiepilogo, riepilogo);


    }

    public void registraUscita(){
        LocalDateTime now = LocalDateTime.now();
        // l'uscita si può registrare solo se viene registrato almeno un 'ingresso per quel dipendente per quel mese giorno anno
        int idRiepilogo = now.getYear()+now.getMonthValue()+this.idDipendenteLogged;
        int idRegistrazione = now.getYear()+now.getMonthValue()+now.getHour()+ now.getMinute() + now.getSecond() +this.idDipendenteLogged;
    }

    public void visualizzaRiepilogoMensile( int mese, int anno){

    }

    public void visualizzaRiepilogoMensileDipendente(int mese, int anno, int idDipendente){

    }

    public boolean eliminaRegistrazione(int idRegistrazione){
        return true;
    }

    public boolean idDipendenteMod(){
        return true;
    }

    public boolean isResponsabileMod(){
        return true;
    }

    public void setModality(String modality) {
        this.modality = modality;
    }

    public int getIdDipendenteLogged() {
        return idDipendenteLogged;
    }

    public void setIdDipendenteLogged(int idDipendenteLogged) {
        this.idDipendenteLogged = idDipendenteLogged;
    }

    public Dipendente getDipendenteLogged(){
        return this.sistemaStipendi.getDipendenti().get(getIdDipendenteLogged());
    }

}
