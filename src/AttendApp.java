import javax.swing.*;

public class AttendApp {



    public static void main(String[] args){


        //SistemaStipendi sistemaStipendi = new SistemaStipendi();
        //sistemaStipendi.populateDB();

        //istanzia il singleton SistemaStipendi e DispositivoRilevamento

        JFrame frame = new JFrame("attendApp");
        frame.setContentPane(new AppInitiazor().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


    }

}
