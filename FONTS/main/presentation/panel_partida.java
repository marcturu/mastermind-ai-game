package main.presentation;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import main.presentation.controller.Controlador_Presentacio;
import main.presentation.classes.button;

public class panel_partida extends JPanel {
    private final Controlador_Presentacio ctrlPresentacio;
    private JButton[][] buttons_intentada;
    private JButton[][] buttons_verificacio;
    private button[] buttons_col = new button[4];
    private final JButton b_help = new JButton("Ajuda");
    private final JButton b_try = new JButton("Try");
    private final JButton b_guardar_partida = new JButton("Guardar partida");
    private final JButton b_eliminar_partida = new JButton("Eliminar partida");
    private         JPanel rightPanel = new JPanel(new GridLayout(6, 1));

    private void add_panel_rondes() {
        buttons_intentada = new JButton[ctrlPresentacio.get_num_rondes()][4];
        buttons_verificacio = new JButton[ctrlPresentacio.get_num_rondes()][4];
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        for (int i = 0; i < buttons_intentada.length; ++i) {
            JPanel rowPanelVerificacio = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
            JPanel rowPanelIntentada = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
            rowPanelIntentada.add(new JLabel("Intentada " + (i + 1)));
            rowPanelVerificacio.add(new JLabel("Verificacio " + (i + 1)));
            for (int j = 0; j < buttons_verificacio[0].length; ++j) {
                buttons_intentada[i][j] = new JButton();
                buttons_intentada[i][j].setPreferredSize(new Dimension(40, 40));
                buttons_intentada[i][j].setBackground(null);
                rowPanelIntentada.add(buttons_intentada[i][j]);
                buttons_verificacio[i][j] = new JButton();
                buttons_verificacio[i][j].setPreferredSize(new Dimension(40, 40));
                buttons_verificacio[i][j].setBackground(null);
                rowPanelVerificacio.add(buttons_verificacio[i][j]);
            }
            leftPanel.add(rowPanelIntentada);
            leftPanel.add(rowPanelVerificacio);
        }

        JPanel spacingPanel = new JPanel();
        spacingPanel.setPreferredSize(new Dimension(10, 10));

        leftPanel.add(Box.createVerticalGlue()); // Espacio en blanco entre los paneles de verificación e intento
        leftPanel.add(spacingPanel); // Espacio en la parte inferior

        add(leftPanel);
    }

    private void setButtons_col(boolean ver){
        JPanel buttonsColPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0)); // Alineación central y espacio horizontal de 5 píxeles
        for (int i = 0; i < buttons_col.length; i++) {
            buttons_col[i] = new button(0,ver,ctrlPresentacio.get_num_colors());
            buttons_col[i].setPreferredSize(new Dimension(60, 60));
            buttonsColPanel.add(buttons_col[i]);
        }
        buttonsColPanel.add(Box.createVerticalGlue()); // Añadir un espacio en blanco debajo
        rightPanel.add(buttonsColPanel);
    }


    private void set_up_ui() {
        setLayout(new GridLayout(1, 2, 20, 0)); // Añadir espacios horizontales de 20 píxeles entre los paneles

        // Panel izquierdo
        add_panel_rondes();

        setButtons_col(false);

        JPanel buttonsPanel = new JPanel(new GridLayout(4, 1, 0, 10)); // Añadir espacios verticales de 10 píxeles entre los botones
        buttonsPanel.add(b_try);
        buttonsPanel.add(b_help);
        buttonsPanel.add(b_guardar_partida);
        buttonsPanel.add(b_eliminar_partida);
        rightPanel.add(buttonsPanel);

        add(rightPanel);
    }

    private void set_up_listeners() {
        b_try.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Integer> try_button = new ArrayList<Integer>();
                for (int i = 0; i < 4; ++i){
                    try_button.add(buttons_col[i].get_color());
                }
                try {
                    ctrlPresentacio.set_try(try_button);
                    setButtons_intentada(try_button);
                    setButtons_col(true);
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        b_help.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // CtrlPersistencia.set_ajuda_partida();
                // penalizar user
            }
        });

        b_guardar_partida.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // CtrlPersistencia.guardar_partida_a_mitges();
                // CtrlPersistencia.canvia_a_menu_principal();
            }
        });

        b_eliminar_partida.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ctrlPresentacio.canvia_a_menu_principal();
            }
        });
    }
    private void setButtons_intentada(List<Integer> try_button){
        for (int i = 0; i < 4; ++i){
            switch (try_button.get(i)){
                case 0:
                    buttons_intentada[ctrlPresentacio.get_num_ronda_actual()][i].setBackground(null);
                    break;
                case 1:
                    buttons_intentada[ctrlPresentacio.get_num_ronda_actual()][i].setBackground(Color.RED);
                    break;
                case 2:
                    buttons_intentada[ctrlPresentacio.get_num_ronda_actual()][i].setBackground(Color.GREEN);
                    break;
                case 3:
                    buttons_intentada[ctrlPresentacio.get_num_ronda_actual()][i].setBackground(Color.BLUE);
                    break;
                case 4:
                    buttons_intentada[ctrlPresentacio.get_num_ronda_actual()][i].setBackground(Color.YELLOW);
                    break;
                case 5:
                    buttons_intentada[ctrlPresentacio.get_num_ronda_actual()][i].setBackground(Color.MAGENTA);
                    break;
                case 6:
                    buttons_intentada[ctrlPresentacio.get_num_ronda_actual()][i].setBackground(Color.CYAN);
                    break;
                case 7:
                    buttons_intentada[ctrlPresentacio.get_num_ronda_actual()][i].setBackground(new Color(165, 42, 42));
                    break;
                case 8:
                    buttons_intentada[ctrlPresentacio.get_num_ronda_actual()][i].setBackground(Color.GRAY);
            }

        }
    }
    public panel_partida(Controlador_Presentacio ctrlPresentacio) {
        this.ctrlPresentacio = ctrlPresentacio;
        set_up_ui();
        set_up_listeners();
    }

}
