import domain.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AttendAppTest {

    AttendApp attendApp = AttendApp.getIstanza();
    SistemaStipendi sistemaStipendi = new SistemaStipendi();


    @Test
    void registraIngresso() {

        assertTrue(attendApp.registraIngresso());
    }

    @Test
    void registraUscita() {
        assertTrue(attendApp.registraUscita());
    }

    @Test
    void getRiepilogoMensile() {

        Riepilogo riepilogo = new Riepilogo(1,2021);
        Registrazione registrazione = new Registrazione(1,2021,42,9,0,18,0,attendApp.getIdRegistrazione(),new Dipendente(),new Filiale());
        riepilogo.addRegistrazione(registrazione);
        attendApp.addRiepilogo(attendApp.getIdRiepilogo(), riepilogo);
        assertNull(attendApp.getRiepilogoMensile(3, 2021));
        assertEquals(1,attendApp.getRiepilogoMensile(1,2021).getRegistrazioni().get(1+2021).getMese(),1);
    }

    @Test
    void getRiepilogoMensileDipendente() {
        Dipendente dipendente = new Dipendente();
        dipendente.setIdDipendente(1);
        Riepilogo riepilogo = new Riepilogo(1,2021);
        Registrazione registrazione = new Registrazione(3,2021,42,9,0,18,0,attendApp.getIdRegistrazione(),dipendente,new Filiale());
        riepilogo.addRegistrazione(registrazione);
        attendApp.addRiepilogo(1+2021, riepilogo);
        Map<Integer, Registrazione> riepilogoMensileDipendente = attendApp.getRiepilogoMensileDipendente(1,2021,1);
        Map<Integer, Registrazione> riepilogoMensileDipendenteDue = attendApp.getRiepilogoMensileDipendente(2,2021,2);
        assertNotNull(riepilogoMensileDipendente);
        assertNull(riepilogoMensileDipendenteDue);
    }


    @Test
    void getEventiEccezionali() {
        LocalDate dataInizio = LocalDate.of(2021,5,5);
        LocalDate dataFine = LocalDate.of(2021,6,5);
        EventoMalattia eventoMalattia = new EventoMalattia(dataInizio,dataFine);
        EventoComposite evento = new EventoComposite();
        evento.add(eventoMalattia);
        assertEquals(1,attendApp.getEventiEccezionali().size());
    }

    @Test
    void rifutaEventoEccezionale() {

        LocalDate dataInizio = LocalDate.of(2021,5,5);
        LocalDate dataFine = LocalDate.of(2021,6,5);
        EventoMalattia eventoMalattia = new EventoMalattia(dataInizio,dataFine);
        EventoComposite evento = new EventoComposite();
        evento.add(eventoMalattia);
        assertEquals(1,attendApp.getEventiEccezionali().size());
        attendApp.rifutaEventoEccezionale(evento);
        assertEquals(0,attendApp.getEventiEccezionali().size());
    }

    @Test
    void autorizzaEventoEccezionale() {
        LocalDate dataInizio = LocalDate.of(2021,5,5);
        LocalDate dataFine = LocalDate.of(2021,6,5);
        EventoMalattia eventoMalattia = new EventoMalattia(dataInizio,dataFine);
        EventoComposite evento = new EventoComposite();
        evento.add(eventoMalattia);
        attendApp.registerObserver(sistemaStipendi);
        assertEquals(0,sistemaStipendi.getEventiEccezionali().size());
        attendApp.autorizzaEventoEccezionale(evento);
        assertEquals(1,sistemaStipendi.getEventiEccezionali().size());
    }

    @Test
    void addEventoEccezionale() {
        LocalDate dataInizio = LocalDate.of(2021,5,5);
        LocalDate dataFine = LocalDate.of(2021,6,5);
        EventoMalattia eventoMalattia = new EventoMalattia(dataInizio,dataFine);
        EventoComposite evento = new EventoComposite();
        evento.add(eventoMalattia);
        assertEquals(0, attendApp.getEventiEccezionali().size());
        attendApp.addEventoEccezionale(evento);
        assertEquals(1,attendApp.getEventiEccezionali().size());
    }


}