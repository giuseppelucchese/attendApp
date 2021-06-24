package domain;

public class Filiale {

    private String nome;
    private final int identificativo;

    public Filiale(String nome, int identificativo) {
        this.nome = nome;
        this.identificativo = identificativo;
    }

    public Filiale(){
     this.identificativo = (int) (Math.random() * 10);
     this.nome = "Filiale_"+this.identificativo;
    }

    public String getNome() {
        return nome;
    }

    public int getIdentificativo() {
        return identificativo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
