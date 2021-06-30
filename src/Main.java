import domain.Registrazione;
import domain.Responsabile;
import domain.Riepilogo;

import java.io.IOException;
import java.util.Scanner;

public class Main {


    private AttendApp attendApp;
    private SistemaStipendi sistemaStipendi;
    private DispositivoRilevamento dispositivoRilevamento;
    private Scanner keyboard;


    public Main() {

        sistemaStipendi = SistemaStipendi.getIstanza();
        attendApp = AttendApp.getIstanza();
        keyboard = new Scanner(System.in);

    }


    public static void main(String[] args) throws IOException {

        Main main = new Main();
        String choice_dip;
        String choice_menu;
        String choice_resp;
        Scanner keyboard = new Scanner(System.in);
        do {
            main.showModalitySelection();
            choice_menu = keyboard.next();
            switch ( choice_menu) {
                case "1":
                    //DIPENDENTE
                    main.dispositivoRilevamento = DispositivoRilevamento.getIstanza();
                    if(main.isLogged()){
                        main.attendApp.setModality("dipendente");
                        do {
                            main.showMenuDipendente();
                            choice_dip = keyboard.next();
                            switch (choice_dip) {
                                case "0":
                                    //PROFILO PERSONALE
                                    System.out.println(main.attendApp.getDipendenteLogged().toString());
                                    break;
                                case "1":
                                    //REGISTRA ENTRATA
                                    main.attendApp.registraIngresso();
                                    System.out.println("Ingresso registrato correttamente!");
                                    System.out.println("Premi un tasto + invio per continuare..");
                                    keyboard.next();
                                    break;
                                case "2":
                                    //REGISTRA USCITA
                                    main.attendApp.registraUscita();
                                    System.out.println("Uscita registrata correttamente!");
                                    System.out.println("Premi un tasto + invio per continuare..");
                                    keyboard.next();
                                    break;
                                case "3":
                                    //VISUALIZZA RIEPILOGO MENSILE PERSONALE
                                    main.visualizzaRiepilogoMensilePersonale();
                                    break;
                                case "4":
                                    main.attendApp.setIdDipendenteLogged(0);
                                    break;

                                default:
                                    // System.exit(1);
                            }
                        } while(!choice_dip.equals("4"));
                        break;
                    }

                case "2":
                    main.attendApp.setModality("responsabile");
                    do {
                        main.showMenuResponsabile();
                        choice_resp = keyboard.next();
                        switch (choice_resp) {
                            case "1":
                                //GESTISCI RIEPILOGO MENSILE
                                main.gestisciRiepilogoMensile();
                                break;
                            case "2":
                                // VALIDA E INVIA RIEPILOGO
                                main.validaInviaRiepilogo();
                                break;
                        }
                    } while(!choice_resp.equals("3"));

            }
        } while(!choice_menu.equals("3"));

    }

    public Riepilogo getRiepilogoMensile(){
        int mese,anno;
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Digita il mese ... [es. digita 1 per indicare Gennaio]");
        mese = keyboard.nextInt();
        System.out.println("Digita l'anno  ...");
        anno = keyboard.nextInt();
        return attendApp.getRiepilogoMensile(mese,anno);
    }


    public boolean eliminaRegistrazione(int idRegistrazione, Riepilogo riepilogo){
        if(riepilogo.eliminaRegistrazione(idRegistrazione)){
            System.out.println("Registrazione eliminata!");
            return true;
        } else{
            System.out.println("Registrazione inesistente!");
            return false;
        }
    }
    public void showModalitySelection() {

        System.out.println("Main Menu:");
        System.out.println("--------------");
        System.out.println("1.Modalità Dipendente");
        System.out.println("2.Modalità Responsabile");
        System.out.println("3.Esci");
        System.out.println("--------------");
        System.out.println("Seleziona la modalità di avvio:");

    }

    public  void showMenuDipendente() {

        System.out.println("Dipendente Main Menu:");
        System.out.println("--------------");
        System.out.println("0.Profilo Personale");
        System.out.println("1.Registra Entrata");
        System.out.println("2.Registra Uscita");
        System.out.println("3.Visualizza riepilogo mensile personale");
        System.out.println("4.Esci");
        System.out.println("--------------");
        System.out.println("Effettua una scelta:");


    }

    public  void showMenuResponsabile() {

        System.out.println("Responsabile Main Menu:");
        System.out.println("--------------");
        System.out.println("1.Gestisci Riepilogo Mensile");
        System.out.println("2.Valida e invia riepilogo");
        System.out.println("3.Esci");
        System.out.println("--------------");
        System.out.println("Effettua una scelta:");
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
     public boolean showRegistrazioniMensiliDipendente(int mese, int anno, int idDipendente){

        if(attendApp.getRiepilogoMensileDipendente(mese,anno,idDipendente) == null){
            System.out.println("Nessuna registrazione per il dipendente con id" + idDipendente +  "per il mese: " + mese + " e per l'anno: " + anno);
            return false;
        }else{
            attendApp.getRiepilogoMensileDipendente(mese,anno,idDipendente).forEach((id,reg)->{
                System.out.println("id: " + id + " " + reg.toString());
            });
            return true;
        }

     }
     public void visualizzaRiepilogoMensilePersonale(){
         int mese,anno;
         System.out.println("Digita il mese ... [es. digita 1 per indicare Gennaio]");
         mese = keyboard.nextInt();
         System.out.println("Digita l'anno  ...");
         anno = keyboard.nextInt();
         showRegistrazioniMensiliDipendente(mese,anno,attendApp.getIdDipendenteLogged());
         System.out.println("Premi un tasto + invio per continuare..");
         keyboard.next();
     }

     public void gestisciRiepilogoMensile(){
         Riepilogo riepilogo;
         int idDipendente,idRegistrazione;
         String yN;
         riepilogo = getRiepilogoMensile();
         if(riepilogo == null) {
             System.out.println("non ci sono riepiloghi per questo mese/anno");
             return;
         } else{
             showDipendenti();
             System.out.println("Digita l'id del dipendente da gestire ...");
             idDipendente = keyboard.nextInt();
             boolean existsRegs = showRegistrazioniMensiliDipendente(riepilogo.getMese(),riepilogo.getAnno(),idDipendente);
             if(existsRegs){
                 System.out.println("Vuoi eliminare una registrazione ? [y/n] ");
                 yN = keyboard.next();
                 while (yN.equals("y") || yN.equals("Y")){
                     System.out.println("Digita l'id della registrazione da eliminare");
                     idRegistrazione = keyboard.nextInt();
                     eliminaRegistrazione(idRegistrazione,riepilogo);
                     System.out.println("Vuoi eliminare un'altra registrazione? [y/n]");
                     yN = keyboard.next();
                 }
             }
         }
         System.out.println("Premi un tasto + invio per continuare..");
         keyboard.next();
     }

     public void validaInviaRiepilogo(){
        Riepilogo riepilogo;
        String yN;
         riepilogo = getRiepilogoMensile();
         if(riepilogo == null) {
             System.out.println("non ci sono riepiloghi per questo mese/anno");
             return;
         } else{

             System.out.println("Vuoi validare e inviare il riepilogo al sistema stipendi? [y/n]");
             yN = keyboard.next();
             if(yN.equals("y") || yN.equals("Y")){
                 attendApp.validaRiepilogo(riepilogo);
                 System.out.println("Riepilogo validato e archiviato dal sistema stipendi.");
             }else{
                 System.out.println("Operazione annullata..");
             }
         }
         System.out.println("Premi un tasto + invio per continuare..");
         keyboard.next();
     }


}
