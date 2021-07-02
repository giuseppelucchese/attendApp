package domain;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class EventoFerie extends Evento{

    private static final int HOURSFORDAY = 8;
    private LocalDate dataInizio;
    private LocalDate dataFine;


    public EventoFerie(LocalDate dataInizio, LocalDate dataFine) {
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
    }

    @Override
    public int getTotaleOre() {
        int numGiorni = (int) ChronoUnit.DAYS.between(dataFine,dataInizio);
        return HOURSFORDAY * numGiorni;
    }
}
