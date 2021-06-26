import domain.Registrazione;

import java.io.IOException;
import java.util.Scanner;

public class Main {


    private AttendApp attendApp;
    private SistemaStipendi sistemaStipendi;
    private DispositivoRilevamento dispositivoRilevamento;


    public Main() {

        sistemaStipendi = SistemaStipendi.getIstanza();
        attendApp = AttendApp.getIstanza();

    }


    public static void main(String[] args) throws IOException {

        Main main = new Main();
        int option = main.showModalitySelection();

        switch (option) {
            case 1:
                //DIPENDENTE
                main.dispositivoRilevamento = DispositivoRilevamento.getIstanza();

                if(main.isLogged()){
                    main.attendApp.setModality("dipendente");
                    int choice_dip = main.showMenuDipendente();
                    switch (choice_dip) {
                        case 0:
                            System.out.println(main.attendApp.getDipendenteLogged().toString());
                            break;
                        case 1:
                            //REGISTRA ENTRATA
                            main.attendApp.registraIngresso();
                            System.out.println("Ingresso registrato correttamente!");
                            break;
                        case 2:
                            main.attendApp.registraUscita();
                            System.out.println("Uscita registrata correttamente!");
                            break;
                        case 3:
                            main.attendApp.setIdDipendenteLogged(0);
                            main.showModalitySelection();
                            break;

                        default:
                            // System.exit(1);
                    }
                    break;
                }

            case 2:
                main.attendApp.setModality("responsabile");
                int choice_resp = main.showMenuResponsabile();
                switch (choice_resp) {
                    case 1:
                        // Perform "original number" case.
                        break;
                    case 2:
                        // Perform "encrypt number" case.
                        break;
                    case 3:
                        main.showModalitySelection();
                        break;

                    default:
                       // System.exit(1);
                }
            case 3:
                System.exit(1);
            default:
               // System.exit(1);
        }

    }

    public int showModalitySelection() {

        int option = 0;
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Main Menu:");
        System.out.println("--------------");
        System.out.println("1.Modalità Dipendente");
        System.out.println("2.Modalità Responsabile");
        System.out.println("3.Esci");
        System.out.println("--------------");
        System.out.println("Seleziona la modalità di avvio:");
        option = keyboard.nextInt();

        if (option == 1) {
            this.attendApp.setModality("dipendente");
        } else {
            this.attendApp.setModality("Responsabile");
        }

        return option;

    }

    public  int showMenuDipendente() {

        int optionn = 0;
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Dipendente Main Menu:");
        System.out.println("--------------");
        System.out.println("0.Profilo Personale");
        System.out.println("1.Registra Entrata");
        System.out.println("2.Registra Uscita");
        System.out.println("3.Esci");
        System.out.println("--------------");
        System.out.println("Effettua una scelta:");
        optionn = keyboard.nextInt();

        return optionn;

    }

    public  int showMenuResponsabile() {

        int optionn = 0;
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Responsabile Main Menu:");
        System.out.println("--------------");
        System.out.println("1.Gestisci Riepilogo Mensile");
        System.out.println("2.Modalità Responsabile");
        System.out.println("3.Esci");
        System.out.println("--------------");
        System.out.println("Effettua una scelta:");
        optionn = keyboard.nextInt();

        return optionn;

    }

    public boolean isLogged(){
        boolean isLogged = false;
        while(!isLogged){
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Appoggia il dito per rilevare l'impronta e procedere all'autenticazione...");
            while (!keyboard.nextLine().equals("a")){
                System.out.println("Lettura errata, riprova...");
                System.out.println("Appoggia il dito per rilevare l'impronta e procedere all'autenticazione...");

            }
            isLogged = attendApp.identificaDipendente(dispositivoRilevamento.getCodiceBio());
        }
        return isLogged;
    }



}
