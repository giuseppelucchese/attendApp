import domain.Registrazione;
import domain.Riepilogo;

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
        int choice_dip;
        int choice_menu;
        int choice_resp;
        do {
            choice_menu = main.showModalitySelection();
            switch (choice_menu) {
                case 1:
                    //DIPENDENTE
                    main.dispositivoRilevamento = DispositivoRilevamento.getIstanza();

                    if(main.isLogged()){
                        main.attendApp.setModality("dipendente");
                        do {
                            choice_dip = main.showMenuDipendente();
                            switch (choice_dip) {
                                case 0:
                                    //PROFILO PERSONALE
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
                                    break;

                                default:
                                    // System.exit(1);
                            }
                        } while(choice_dip != 3);
                        break;
                    }

                case 2:
                    main.attendApp.setModality("responsabile");
                    Scanner keyboard = new Scanner(System.in);
                    do {
                        choice_resp = main.showMenuResponsabile();
                        switch (choice_resp) {
                            case 1:
                                int mese,anno,idDipendente,idRegistrazione;
                                System.out.println("Digita il mese ... [es. digita 1 per indicare Gennaio]");
                                mese = keyboard.nextInt();
                                System.out.println("Digita l'anno  ...");
                                anno = keyboard.nextInt();
                                Riepilogo riepilogo = main.attendApp.getRiepilogoMensile(mese,anno);
                                main.showDipendenti();
                                System.out.println("Digita l'id del dipendente da gestire ...");
                                idDipendente = keyboard.nextInt();
                                main.showRegistrazioniMensiliDipendente(mese,anno,idDipendente);
                                System.out.println("Vuoi eliminare una registrazione ? [y/n] ");
                                String yN = keyboard.nextLine();
                                while (yN.equals("y") || yN.equals("Y")){
                                    System.out.println("Digita l'id della registrazione da eliminare");
                                    idRegistrazione = keyboard.nextInt();
                                    riepilogo.eliminaRegistrazione(idRegistrazione);
                                    System.out.println("Vuoi eliminare un'altra registrazione? [y/n]");
                                    yN = keyboard.nextLine();
                                }
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
                    } while(choice_resp != 3);
                case 3:
                    System.exit(1);
                default:
                   // System.exit(1);
            }
        } while(choice_menu != 3);

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
        System.out.println("2 Valida riepilogo");
        System.out.println("3.Esci");
        System.out.println("--------------");
        System.out.println("Effettua una scelta:");
        optionn = keyboard.nextInt();

        return optionn;

    }

    public boolean isLogged() {
        boolean isLogged = false;
        while (!isLogged) {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Appoggia il dito per rilevare l'impronta e procedere all'autenticazione...");
            while (!keyboard.nextLine().equals("a")) {
                System.out.println("Lettura errata, riprova...");
                System.out.println("Appoggia il dito per rilevare l'impronta e procedere all'autenticazione...");

            }
            isLogged = attendApp.identificaDipendente();
        }
        return isLogged;
    }
     public void showDipendenti(){
         sistemaStipendi.getDipendenti().forEach((id,dip)->{
             System.out.println("idDipendente:" + dip.getIdDipendente() +" " + " Nome: " + dip.getNome() + "Cognome: " + dip.getCognome());
         });
     }

     public void showRegistrazioniMensiliDipendente(int mese, int anno, int idDipendente){
        attendApp.getRiepilogoMensileDipendente(mese,anno,idDipendente).forEach((id,reg)->{
            System.out.println(reg.toString());
        });
     }


}
