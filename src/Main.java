import domain.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Date;
import java.util.Scanner;

public class Main {


    private AttendApp attendApp;
    private SistemaStipendi sistemaStipendi;
    private DispositivoRilevamento dispositivoRilevamento;
    private Scanner keyboard;


    public Main() {

        sistemaStipendi = new SistemaStipendi();
        attendApp = AttendApp.getIstanza();
        attendApp.registerObserver(sistemaStipendi);
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
                                    //RICHIESTA EVENTO ECCEZIONALE
                                    main.inviaRichiestaEventoEccezionale();
                                    break;
                                case "5":
                                    //RICHIESTA ORE STRAORDINARIE
                                    main.inviaRichiestaOreStraordinarie();
                                    break;
                                case "6":
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
        System.out.println("4.Invia richiesta evento eccezionale");
        System.out.println("5.Invia richiesta ore straordinarie");
        System.out.println("6.Esci");
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

     public void inviaRichiestaEventoEccezionale(){
        int choiceEvent,giorno,mese,anno;
        LocalDate dataInizio,dataFine;
        String yN;
        EventoComposite eventoComposite = new EventoComposite();
        do {
            System.out.println("Digita il tipo di evento..");
            System.out.println("1-Malattia");
            System.out.println("2-Ferie");
            System.out.println("3-Permessi");
            choiceEvent = keyboard.nextInt();
            switch (choiceEvent){
                case 1:
                    System.out.println("Digita la data di inizio..");
                    System.out.println("Digita il giorno..");
                    giorno = keyboard.nextInt();
                    System.out.println("Digita il mese..");
                    mese = keyboard.nextInt();
                    System.out.println("Digita l'anno");
                    anno = keyboard.nextInt();

                    dataInizio = LocalDate.of(anno,mese,giorno);

                    System.out.println("Digita la data di fine..");
                    System.out.println("Digita il giorno..");
                    giorno = keyboard.nextInt();
                    System.out.println("Digita il mese..");
                    mese = keyboard.nextInt();
                    System.out.println("Digita l'anno");
                    anno = keyboard.nextInt();

                    dataFine = LocalDate.of(anno,mese,giorno);

                    EventoMalattia eventoMalattia = new EventoMalattia(dataInizio,dataFine);
                    eventoComposite.add(eventoMalattia);

                    break;
                case 2:
                    System.out.println("Digita la data di inizio..");
                    System.out.println("Digita il giorno..");
                    giorno = keyboard.nextInt();
                    System.out.println("Digita il mese..");
                    mese = keyboard.nextInt();
                    System.out.println("Digita l'anno");
                    anno = keyboard.nextInt();

                    dataInizio = LocalDate.of(anno,mese,giorno);

                    System.out.println("Digita la data di fine..");
                    System.out.println("Digita il giorno..");
                    giorno = keyboard.nextInt();
                    System.out.println("Digita il mese..");
                    mese = keyboard.nextInt();
                    System.out.println("Digita l'anno");
                    anno = keyboard.nextInt();

                    dataFine = LocalDate.of(anno,mese,giorno);

                    EventoFerie eventoFerie = new EventoFerie(dataInizio,dataFine);
                    eventoComposite.add(eventoFerie);
                    break;

                case 3:
                    break;
            }
            System.out.println("Vuoi inviare una nuova richiesta? [y/n]");
            yN = keyboard.next();
        }while(yN.equals("y") || yN.equals("N"));

        attendApp.notifyObserverEventoEccezionale(eventoComposite);
        System.out.println("richiesta inviata correttamente!");

     }

     public void inviaRichiestaOreStraordinarie(){

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
