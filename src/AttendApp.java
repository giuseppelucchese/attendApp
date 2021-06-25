import domain.Riepilogo;

import javax.swing.*;

public class AttendApp {

    private static AttendApp singleton;

    private String modality;
    private int idDipendenteLogged;
    private SistemaStipendi sistemaStipendi;

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
        });
        return idDipendenteLogged != 0;

    }

    public boolean validaRiepilogo(Riepilogo riepilogo){
     return true;
    }

    public boolean registraIngresso(){
        //getCodiceBio in dispositivo rilevamento e chiamata a attendApp.identificaDipendente(codiceBio)
        return true;
    }

    public boolean registraUscita(){
        return true;
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
}
