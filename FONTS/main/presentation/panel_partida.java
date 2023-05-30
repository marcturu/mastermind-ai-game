package main.presentation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

//import main.presentation.classes.*;
import main.presentation.controller.Controlador_Presentacio;
public class panel_partida extends JPanel{
    private Controlador_Presentacio ctrlPresentacio;
   /*
    private Button[] buttons_intentada = new Button[4];
    private Button[] buttons_verificacio = new Button[4];
    private JButton b_color;
    private final JButton b_enrere = new JButton("Enrere");

    private void add_intentada(){
        for (int i = 0; i < 4; ++i){
            buttons_intentada[i] = new Button(1,false,CtrlPresentacio.get_num_colors());
        }
    }
    private void set_up_ui(){
        add_intentada();
    }


*/
   public panel_partida(Controlador_Presentacio ctrlPresentacio) {
       this.ctrlPresentacio = ctrlPresentacio;
       //setBounds(500, 300, 500, 300);
       //set_up_ui();
       //set_up_listeners();
   }

}
