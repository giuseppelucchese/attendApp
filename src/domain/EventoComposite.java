package domain;

import java.util.LinkedList;

public class EventoComposite extends Evento{

    private LinkedList<Evento> eventi;
    private int totaleOre;

    public EventoComposite() {

        eventi = new LinkedList<Evento>();
    }


    public void add(Evento evento){
        this.eventi.add(evento);
    }

    public void remove(Evento evento){
        this.eventi.remove(evento);
    }


    @Override
    public int getTotaleOre() {
        this.eventi.forEach(evento -> {
            this.totaleOre += evento.getOre();
        });
        return  totaleOre;
    }
}
