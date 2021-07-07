import domain.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;

import static org.junit.jupiter.api.Assertions.*;

class SistemaStipendiTest {

    SistemaStipendi sistemaStipendi = new SistemaStipendi();

    @Test
    void validaRiepilogo() {
        Riepilogo riepilogoCorretto = new Riepilogo(7,2021);
        Riepilogo riepilogoErrato = new Riepilogo(13,44565);
        assertTrue(sistemaStipendi.validaRiepilogo(riepilogoCorretto));
        assertFalse(sistemaStipendi.validaRiepilogo(riepilogoErrato));
    }


    @Test
    void addDipendente() {
        Dipendente dipendente = new Dipendente();
        sistemaStipendi.addDipendente(dipendente);
        assertEquals(dipendente,sistemaStipendi.getDipendenti().get(dipendente.getIdDipendente()));
    }

    @Test
    void accodaRichiestaEventiEccezionali() {
        LocalDate dataInizio = LocalDate.of(2021,5,5);
        LocalDate dataFine = LocalDate.of(2021,6,5);
        EventoMalattia eventoMalattia = new EventoMalattia(dataInizio,dataFine);
        EventoComposite evento = new EventoComposite();
        evento.add(eventoMalattia);
        assertTrue(sistemaStipendi.accodaRichiestaEventiEccezionali(evento));
    }

    @Test
    void accodaRichiesteOreStraordinarie() {
        LocalDate data = LocalDate.of(2021,5,5);
        RichiestaStraordinaria richiestaStraordinaria = new RichiestaStraordinaria(data,4);
        RichiestaStraordinaria richiestaErrore = new RichiestaStraordinaria(data, 6);
        assertTrue(sistemaStipendi.accodaRichiesteOreStraordinarie(richiestaStraordinaria));
        assertFalse(sistemaStipendi.accodaRichiesteOreStraordinarie(richiestaErrore));
    }
}