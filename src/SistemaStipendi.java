public class SistemaStipendi {

    private static SistemaStipendi singleton;



    public static SistemaStipendi getIstanza(){
        if(singleton == null)
            singleton = new SistemaStipendi();
        return singleton;
    }

    public void initialize(){

    }
}
