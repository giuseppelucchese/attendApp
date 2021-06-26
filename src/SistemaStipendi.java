import domain.Dipendente;
import domain.Riepilogo;

import java.util.*;

public class SistemaStipendi {

    private static SistemaStipendi singleton;

    private HashMap<Integer,Dipendente> dipendenti;
    private LinkedList<Riepilogo> archivioRiepiloghi;

    public SistemaStipendi() {

        this.dipendenti = new HashMap<Integer, Dipendente>();
        this. archivioRiepiloghi = new LinkedList<Riepilogo>();

        this.addDipendente(new Dipendente("Giuseppe","Lucchese","3457746608","myemail@email.com", 1));
        this.addDipendente(new Dipendente("Mario","Rossi","3223344555","myemail@email.com", 2));
        this.addDipendente(new Dipendente("Mario","Bianchi","3456677888","myemail@email.com", 3));
        this.addDipendente(new Dipendente("Mario","Verdi","3453387605","myemail@email.com", 4));
        this.addDipendente(new Dipendente("Utente","Cinque","3453387705","myemail@email.com", 5));

    }

    public static SistemaStipendi getIstanza(){
        if(singleton == null)
            singleton = new SistemaStipendi();
        return singleton;
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
}
