import domain.Dipendente;
import domain.Evento;
import domain.RichiestaStraordinaria;
import domain.Riepilogo;

import java.util.*;

public class SistemaStipendi {

    private HashMap<Integer,Dipendente> dipendenti;
    private LinkedList<Riepilogo> archivioRiepiloghi;
    private LinkedList<Evento> eventiEccezionali;
    private LinkedList<RichiestaStraordinaria> richiesteStraordinarie;

    public SistemaStipendi() {

        this.dipendenti = new HashMap<Integer, Dipendente>();
        this. archivioRiepiloghi = new LinkedList<Riepilogo>();
        this.eventiEccezionali = new LinkedList<Evento>();
        this.richiesteStraordinarie = new LinkedList<RichiestaStraordinaria>();


        this.addDipendente(new Dipendente("Giuseppe","Lucchese","3457746608","myemail@email.com", 1));
        this.addDipendente(new Dipendente("Mario","Rossi","3223344555","myemail@email.com", 2));
        this.addDipendente(new Dipendente("Mario","Bianchi","3456677888","myemail@email.com", 3));
        this.addDipendente(new Dipendente("Mario","Verdi","3453387605","myemail@email.com", 4));
        this.addDipendente(new Dipendente("Utente","Cinque","3453387705","myemail@email.com", 5));

    }



    public boolean validaRiepilogo(Riepilogo riepilogo){
        if(riepilogo.getMese()< 1 || riepilogo.getMese()> 12 ||  riepilogo.getAnno() > 3000 || riepilogo.getAnno() < 2000){
            return  false;
        }else{
            riepilogo.setValidato(true);
            this.archivioRiepiloghi.add(riepilogo);
            return true;
        }

    }

    public HashMap<Integer,Dipendente> getDipendenti() {
        return dipendenti;
    }

    public void addDipendente(Dipendente dipendente){
        int id = this.dipendenti.size();
        dipendente.setIdDipendente(id);
        this.dipendenti.put(id, dipendente);
    }

    public boolean accodaRichiestaEventiEccezionali(Evento evento){
        this.eventiEccezionali.add(evento);
        return true;
    }

    public boolean accodaRichiesteOreStraordinarie(RichiestaStraordinaria richiestaStraordinaria){
        if(richiestaStraordinaria.getNumOre() > 5) return false;
        this.richiesteStraordinarie.add(richiestaStraordinaria);
        return true;
    }

    public LinkedList<Riepilogo> getArchivioRiepiloghi() {
        return archivioRiepiloghi;
    }

    public LinkedList<Evento> getEventiEccezionali() {
        return eventiEccezionali;
    }

    public LinkedList<RichiestaStraordinaria> getRichiesteStraordinarie() {
        return richiesteStraordinarie;
    }
}
