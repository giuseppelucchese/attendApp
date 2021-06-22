public class DispositivoRilevamento {

    private static DispositivoRilevamento singleton;



    public static DispositivoRilevamento getIstanza(){
        if(singleton == null)
            singleton = new DispositivoRilevamento();
        return singleton;
    }


}
