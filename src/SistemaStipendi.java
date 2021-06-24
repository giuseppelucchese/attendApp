import domain.Dipendente;
import domain.Riepilogo;

import java.util.ArrayList;

public class SistemaStipendi {

    private static SistemaStipendi singleton;

    private ArrayList<Dipendente> dipendenti;
    private ArrayList<Riepilogo> archivioRiepiloghi;

    public SistemaStipendi() {
//        Dipendente dipendente_uno = new Dipendente(1,"Giuseppe","Lucchese","3457746608","myemail@email.com", 1);
//        Dipendente dipendente_due = new Dipendente(2,"Mario","Rossi","3223344555","myemail@email.com", 2);
//        Dipendente dipendente_tre = new Dipendente(3,"Mario","Bianchi","3456677888","myemail@email.com", 3);
//        Dipendente dipendente_quattro = new Dipendente(4,"Mario","Verdi","3453387605","myemail@email.com", 4);
//
//        this.addDipendente(dipendente_uno);
//        this.addDipendente(dipendente_due);
//        this.addDipendente(dipendente_tre);
//        this.addDipendente(dipendente_quattro);
    }

    public static SistemaStipendi getIstanza(){
        if(singleton == null)
            singleton = new SistemaStipendi();
        return singleton;
    }

    public void validaRiepologo(Riepilogo riepilogo){

    }

    public ArrayList<Dipendente> getDipendenti() {
        return dipendenti;
    }

    public void addDipendente(Dipendente dipendente){
        this.dipendenti.add(dipendente);
    }
}
