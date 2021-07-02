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
        riepilogo.setValidato(true);
        this.archivioRiepiloghi.add(riepilogo);
        return true;
    }

    public HashMap<Integer,Dipendente> getDipendenti() {
        return dipendenti;
    }

    public void addDipendente(Dipendente dipendente){
        int id = this.dipendenti.size();
        dipendente.setIdDipendente(id);
        this.dipendenti.put(id, dipendente);
    }

    public void accodaRichiestaEventiEccezionali(Evento evento){
        this.eventiEccezionali.add(evento);
    }

    public void accodaRichiesteOreStraordinarie(RichiestaStraordinaria richiestaStraordinaria){
        this.richiesteStraordinarie.add(richiestaStraordinaria);
    }

}
