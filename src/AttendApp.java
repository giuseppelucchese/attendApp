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
    private int codiceFiliale;
    private SistemaStipendi sistemaStipendi;
    private DispositivoRilevamento dispositivoRilevamento;
    private HashMap<Integer,Riepilogo> riepiloghi;

    public AttendApp(){
        this.sistemaStipendi = SistemaStipendi.getIstanza();
        this.riepiloghi = new HashMap<Integer,Riepilogo>();
        this.dispositivoRilevamento = DispositivoRilevamento.getIstanza();

        this.codiceFiliale = this.dispositivoRilevamento.getFilialeAssociata().getIdentificativo();
    }

    public static AttendApp getIstanza(){
        if(singleton == null)
            singleton = new AttendApp();
        return singleton;
    }

    public boolean identificaDipendente(){
        int codicebio = dispositivoRilevamento.getCodiceBio();
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
        int idRegistrazione= now.getYear()+now.getMonthValue()+now.getDayOfMonth()+idDipendenteLogged+codiceFiliale;
        int idRiepilogo = now.getYear()+now.getMonthValue(); // chiave del riepilogo mese+anno

        Riepilogo riepilogo = this.riepiloghi.get(idRiepilogo);
        if(riepilogo == null) riepilogo = new Riepilogo(now.getMonthValue(),now.getYear());

        Registrazione registrazione = riepilogo.getRegistrazioni().get(idRegistrazione);
        if(registrazione == null) registrazione = new Registrazione();

        registrazione.setIdRegistrazione(idRegistrazione);
        registrazione.setGiorno(now.getDayOfMonth());
        registrazione.setMese(now.getMonth().getValue());
        registrazione.setAnno(now.getYear());
        registrazione.setOraEntrata(now.getHour());
        registrazione.setMinEntrata(now.getMinute());
        registrazione.setDipendente(getDipendenteLogged());
        registrazione.setFiliale(dispositivoRilevamento.getFilialeAssociata());

        riepilogo.addRegistrazione(registrazione);

        //aggiungo il riepilogo ai riepiloghi totali
        this.riepiloghi.put(idRiepilogo, riepilogo);


    }

    public void registraUscita(){

        LocalDateTime now = LocalDateTime.now();
        int idRegistrazione= now.getYear()+now.getMonthValue()+now.getDayOfMonth()+idDipendenteLogged+codiceFiliale;
        int idRiepilogo = now.getYear()+now.getMonthValue(); // chiave del riepilogo mese+anno

        Riepilogo riepilogo = this.riepiloghi.get(idRiepilogo);
        if(riepilogo == null) riepilogo = new Riepilogo(now.getMonthValue(),now.getYear());

        Registrazione registrazione = riepilogo.getRegistrazioni().get(idRegistrazione);
        if(registrazione == null) registrazione = new Registrazione();

        registrazione.setIdRegistrazione(idRegistrazione);
        registrazione.setGiorno(now.getDayOfMonth());
        registrazione.setMese(now.getMonth().getValue());
        registrazione.setAnno(now.getYear());
        registrazione.setOraUscita(now.getHour());
        registrazione.setMinUscita(now.getMinute());
        registrazione.setDipendente(getDipendenteLogged());
        registrazione.setFiliale(dispositivoRilevamento.getFilialeAssociata());

        riepilogo.addRegistrazione(registrazione);

        //aggiungo il riepilogo ai riepiloghi totali
        this.riepiloghi.put(idRiepilogo, riepilogo);


    }

    public Riepilogo getRiepilogoMensile( int mese, int anno){
        return  this.riepiloghi.get(mese+anno);
    }

    public Map<Integer,Registrazione> getRiepilogoMensileDipendente(int mese, int anno, int idDipendente){

        if(getRiepilogoMensile(mese,anno) == null) return null;
        return getRiepilogoMensile(mese,anno).getRegistrazioniMensiliDipendente(idDipendente);

    }


    public boolean isDipendenteMod(){
        if (this.modality == "dipendente") return true;
        return false;
    }

    public boolean isResponsabileMod(){
        if (this.modality == "responsabile") return true;
        return false;
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
