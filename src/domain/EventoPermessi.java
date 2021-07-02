package domain;

import java.util.Date;

public class EventoPermessi extends Evento{

    private int oraInizio;
    private int minInizio;
    private int oraFine;
    private int minFine;

    public EventoPermessi(int oraInizio, int minInizio, int oraFine, int minFine) {
        this.oraInizio = oraInizio;
        this.minInizio = minInizio;
        this.oraFine = oraFine;
        this.minFine = minFine;
    }

    @Override
    public int getTotaleOre() {
        return oraInizio+oraFine+minFine%60+minInizio%60;
    }

}
