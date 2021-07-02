package domain;

public abstract class Evento {
    private int ore;
    private Dipendente dipendente;



    public abstract int getTotaleOre();


    public int getOre(){
        return ore;
    }

    public void setDipendente(Dipendente dipendente){
        this.dipendente = dipendente;
    }


    // metodi per gestire i componenti

    public void add(Evento evento)	{
    }

    public void remove(Evento evento)	{
    }

}
