import domain.Filiale;

import java.util.Scanner;

public class DispositivoRilevamento {

    private static DispositivoRilevamento singleton;

    private int identificativo;
    private String nome;
    private Filiale filiale;

    public DispositivoRilevamento(int identificativo, String nome, Filiale filiale) {
        this.identificativo = identificativo;
        this.nome = nome;
        this.filiale = filiale;
    }

    public DispositivoRilevamento(){
        this.identificativo =  (int)(Math.random()*10);
        this.nome = "Dispositivo di filiale";
        this.filiale = new Filiale();
    }


    public static DispositivoRilevamento getIstanza(){
        if(singleton == null)
            singleton = new DispositivoRilevamento();
        return singleton;
    }


    public int getIdentificativo() {
        return identificativo;
    }

    public String getNome() {
        return nome;
    }

    public Filiale getFilialeAssociata() {
        return filiale;
    }

    public int getCodiceBio(){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Appoggia il dito per rilevare l'impronta e procedere all'autenticazione...");
        while (!keyboard.nextLine().equals("/n")){
            System.out.println("Lettura errata, riprova");

        }
            return (int)(Math.random()*(5));

    }


}
