package domain;

public abstract class Evento {
    private String tipo;
    private Dipendente dipendente;



    public abstract int getTotaleOre();




    public void setDipendente(Dipendente dipendente){
        this.dipendente = dipendente;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    // metodi per gestire i componenti

    public void add(Evento evento)	{
    }

    public void remove(Evento evento)	{
    }

}
