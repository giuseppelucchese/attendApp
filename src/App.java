import javax.swing.*;

public class App {

    private AttendApp attendApp;
    private SistemaStipendi sistemaStipendi;
    private DispositivoRilevamento dispositivoRilevamento;

    private JFrame frame;

    public App(){
        initialize();
        attendApp = AttendApp.getIstanza();
        sistemaStipendi = SistemaStipendi.getIstanza();
        dispositivoRilevamento = DispositivoRilevamento.getIstanza();
    }

    public static void main(String[] args){
        App app = new App();
        app.frame.setVisible(true);
    }

    private void initialize(){

        frame = new JFrame("attendApp");
        frame.setContentPane(new ModalitySelection().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        sistemaStipendi.initialize();

    }
}
