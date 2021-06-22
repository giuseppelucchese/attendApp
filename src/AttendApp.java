import javax.swing.*;

public class AttendApp {

    private static AttendApp singleton;

    public static AttendApp getIstanza(){
        if(singleton == null)
            singleton = new AttendApp();
        return singleton;
    }



}
