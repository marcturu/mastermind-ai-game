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

    private JButton b_set_visible = new JButton("Solucion visible");
    private JButton[] buttons_sol = new JButton[4];
    private JButton[] buttons_help = new JButton[4];
    private final JButton b_help = new JButton("Ajuda");
    private final JButton b_try = new JButton("Try");
    private final JButton b_guardar_partida = new JButton("Guardar partida");
    private final JButton b_eliminar_partida = new JButton("Eliminar partida");
    private JPanel rightPanel = new JPanel(new GridLayout(6, 1));

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

    private void setButtons(boolean ver){
        JPanel topPanel = new JPanel();
        JLabel label;
        try {
            if (ctrlPresentacio.get_solucio() == null && ctrlPresentacio.get_j1_cm()) label =  new JLabel("Introdueix solucio");
            else {
                if (!ver) label = new JLabel("Introdueix Sequencia Intentada");
                else label = new JLabel("Introdueix Sequencia Verificacio");
            }
            topPanel.add(label);
        }catch (NullPointerException ex){}


        JPanel buttonsColPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        rightPanel.removeAll();
        for (int i = 0; i < buttons_col.length; i++) {
            buttons_col[i] = new button(0, ver, ctrlPresentacio.get_num_colors());
            buttons_col[i].setPreferredSize(new Dimension(60, 60));
            buttonsColPanel.add(buttons_col[i]);
        }

        rightPanel.setLayout(new GridBagLayout()); // Utilizar GridBagLayout para el rightPanel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        rightPanel.add(topPanel, gbc);

        gbc.gridy = 1;
        rightPanel.add(buttonsColPanel, gbc);

        JPanel buttonsPanel = new JPanel(new GridLayout(7, 1, 0, 2));
        buttonsPanel.add(b_try);
        buttonsPanel.add(b_guardar_partida);
        buttonsPanel.add(b_eliminar_partida);


        JPanel buttonsSol = new JPanel(new FlowLayout());
        buttons_sol = new JButton[4];
        for (int i = 0; i < buttons_sol.length; i++) {
            buttons_sol[i] = new JButton();
            buttons_sol[i].setPreferredSize(new Dimension(60, 60));// TODO CAMBIAR
            buttons_sol[i].setVisible(false);
            buttonsSol.add(buttons_sol[i]);
        }
        if(!ver) b_set_visible.setVisible(false);
        if(ver) b_set_visible.setVisible(true);
        buttonsPanel.add(b_set_visible);
        buttonsPanel.add(buttonsSol);



        JPanel buttonsHelp = new JPanel(new FlowLayout());
        buttons_help = new JButton[4];
        for (int i = 0; i < buttons_help.length; i++) {
            buttons_help[i] = new JButton();
            buttons_help[i].setPreferredSize(new Dimension(60, 60));// TODO CAMBIAR
            buttons_help[i].setVisible(false);
            buttonsHelp.add(buttons_help[i]);
        }
        if(ver) b_help.setVisible(false);
        if(!ver) b_help.setVisible(true);


        //SET
        for(int i = 0; i < buttons_help.length; ++i) {
            //System.out.println("SE CREA EL ACTION LISTENER");

            final int buttonIndex = i;
            buttons_help[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //System.out.println("ENTRARA");
                    if(!ctrlPresentacio.get_and_set_ajuda()){
                        buttons_help[buttonIndex].setBackground(get_color_by_id(ctrlPresentacio.get_solucio().get(buttonIndex)));
                        buttons_help[buttonIndex].setOpaque(true);
                    }
                }
            });
        }

        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        rightPanel.add(buttonsPanel, gbc);

        add(rightPanel);
        revalidate();
        repaint();

    }


    private void set_up_ui() {
        setLayout(new GridLayout(1, 2, 20, 0)); // Añadir espacios horizontales de 20 píxeles entre los paneles

        // Panel izquierdo
        add_panel_rondes();

        setButtons(false);


    }

    private void set_up_listeners() {
        b_try.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Integer> try_button = new ArrayList<Integer>();
                for (int i = 0; i < 4; ++i) {
                    try_button.add(buttons_col[i].get_color());
                }
                try {
                    List<Integer> list = ctrlPresentacio.set_try(try_button);
                    if(ctrlPresentacio.partida_acabada()){
                        ctrlPresentacio.canvia_a_menu_principal();
                        return;
                    }
                    if(ctrlPresentacio.get_j1_cm() && !ctrlPresentacio.get_j2_user()){

                        if(ctrlPresentacio.get_num_ronda_actual()>1){
                            System.out.println("Llista verificacio: " + list.subList(4,8));
                            setButtons_verificacio(list.subList(4,8));

                        }
                        setButtons_intentada(list.subList(0,4));
                        setButtons(true);
                    }
                    else if(!ctrlPresentacio.get_j1_cm() && !ctrlPresentacio.get_j2_user()) {
                        System.out.println(list);
                        setButtons_intentada(list.subList(0,4));
                        setButtons_verificacio(list.subList(4,8));
                        setButtons(false);
                    }
                    else {
                        if (ctrlPresentacio.get_num_ronda_actual() == 0) setButtons(false);
                        if (!ctrlPresentacio.get_es_intent()){
                            System.out.println(ctrlPresentacio.get_num_ronda_actual());
                            if(ctrlPresentacio.get_num_ronda_actual()>0) {
                                setButtons_intentada(list.subList(0, 4));
                                setButtons(true);
                            }
                        }else {
                            if(ctrlPresentacio.get_num_ronda_actual()>0) {
                                System.out.println(list);
                                setButtons_verificacio(list.subList(4, 8));
                                setButtons(false);
                            }
                        }
                    }

                }catch (Exception ex){
                    System.out.println(ex.getMessage());
                    System.out.println(ex.getStackTrace());
                    JOptionPane.showMessageDialog(null,  ex.getMessage() , "Error", JOptionPane.ERROR_MESSAGE);
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
                ctrlPresentacio.guardar_partida_a_mitges();
                ctrlPresentacio.canvia_a_menu_principal();
            }
        });

        b_eliminar_partida.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ctrlPresentacio.canvia_a_menu_principal();
            }
        });

        b_set_visible.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //if (ctrlPresentacio.jugador1_es_codemaker()) {
                    for (int i = 0; i < 4; ++i) {
                        buttons_sol[i].setVisible(!buttons_sol[i].isVisible());
                        if (ctrlPresentacio.get_solucio() != null) {
                            buttons_sol[i].setBackground(get_color_by_id(ctrlPresentacio.get_solucio().get(i)));
                            buttons_sol[i].setOpaque(true);
                        }
                    }
                //} else {
                //    JOptionPane.showMessageDialog(null, "No pots veure la solucio!!!", "Error", JOptionPane.ERROR_MESSAGE);
                //}
            }
        });

        b_help.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                for(int i = 0; i < 4; ++i) {
                    buttons_help[i].setVisible(!buttons_help[i].isVisible());
                }
            }
        });
    }
    private void setButtons_intentada(List<Integer> try_button){
        for (int i = 0; i < 4; ++i){
            if (ctrlPresentacio.get_j2_user()){
                buttons_intentada[ctrlPresentacio.get_num_ronda_actual()-1][i].setBackground(get_color_by_id(try_button.get(i)));
                //FOR MACOS ONLY
                buttons_intentada[ctrlPresentacio.get_num_ronda_actual()-1][i].setOpaque(true);
            }
            else {
                buttons_intentada[ctrlPresentacio.get_num_ronda_actual() - 1][i].setBackground(get_color_by_id(try_button.get(i)));
                //FOR MACOS ONLY
                buttons_intentada[ctrlPresentacio.get_num_ronda_actual() - 1][i].setOpaque(true);
            }
        }
    }

    private Color get_color_by_id(int id){
        switch (id){
            case 0:
                return null;
            case 1:
                return Color.RED;
            case 2:
                return Color.GREEN;
            case 3:
                return Color.BLUE;
            case 4:
                return Color.YELLOW;
            case 5:
                return Color.MAGENTA;
            case 6:
                return Color.CYAN;
            case 7:
                return new Color(165, 42, 42);
            case 8:
                return Color.GRAY;
            case 9:
                return Color.WHITE;
            case 10:
                return Color.BLACK;
        }
        return null;
    }


    private void setButtons_verificacio(List<Integer> try_button){

        for (int i = 0; i < 4; ++i){
            System.out.println("get " + try_button.get(i) + ctrlPresentacio.get_j2_user());
            switch (try_button.get(i)){
                case 0:
                    if (ctrlPresentacio.get_j2_user()) buttons_verificacio[ctrlPresentacio.get_num_ronda_actual()-1][i].setBackground(null);
                    else if (!ctrlPresentacio.get_j1_cm() && !ctrlPresentacio.get_j2_user()) buttons_verificacio[ctrlPresentacio.get_num_ronda_actual()-1][i].setBackground(null);
                    else buttons_verificacio[ctrlPresentacio.get_num_ronda_actual()-2][i].setBackground(null);
                    break;
                case 9:
                    if (ctrlPresentacio.get_j2_user()) buttons_verificacio[ctrlPresentacio.get_num_ronda_actual()-1][i].setBackground(Color.white);
                    else if (!ctrlPresentacio.get_j1_cm() && !ctrlPresentacio.get_j2_user()) buttons_verificacio[ctrlPresentacio.get_num_ronda_actual()-1][i].setBackground(Color.white);
                    else buttons_verificacio[ctrlPresentacio.get_num_ronda_actual()-2][i].setBackground(Color.white);
                    break;
                case 10:
                    if (ctrlPresentacio.get_j2_user()) buttons_verificacio[ctrlPresentacio.get_num_ronda_actual()-1][i].setBackground(Color.black);
                    else if (!ctrlPresentacio.get_j1_cm() && !ctrlPresentacio.get_j2_user()) buttons_verificacio[ctrlPresentacio.get_num_ronda_actual()-1][i].setBackground(Color.black);
                    else buttons_verificacio[ctrlPresentacio.get_num_ronda_actual()-2][i].setBackground(Color.black);

            }
            if (ctrlPresentacio.get_j2_user()) buttons_verificacio[ctrlPresentacio.get_num_ronda_actual()-1][i].setOpaque(true);
            else if (!ctrlPresentacio.get_j1_cm() && !ctrlPresentacio.get_j2_user()) buttons_verificacio[ctrlPresentacio.get_num_ronda_actual()-1][i].setOpaque(true);
            else buttons_verificacio[ctrlPresentacio.get_num_ronda_actual()-2][i].setOpaque(true);

        }
    }

    private void get_intents_anteriors() {
        List<List<Integer>> intents_anteriors = ctrlPresentacio.get_intents_anteriors();
        System.out.println("Intents anteriors: " + intents_anteriors);
        if(intents_anteriors == null) return;
        List<List<Integer>> verificacions_anteriors = ctrlPresentacio.get_verificacions_anteriors();
        System.out.println("Verificacions anteriors: " + verificacions_anteriors);
        System.out.println("Num ronda actual: " + ctrlPresentacio.get_num_ronda_actual());
        if(verificacions_anteriors == null) return;

        for(int i = 0; i < intents_anteriors.size(); ++i) {
            for(int j = 0; j < 4; ++j) {
                buttons_intentada[i][j].setBackground(get_color_by_id(intents_anteriors.get(i).get(j)));
                buttons_intentada[i][j].setOpaque(true);
            }
        }
        for(int i = 1; i < verificacions_anteriors.size(); ++i) {
            for (int j = 0; j < 4; ++j) {
                buttons_verificacio[i-1][j].setBackground(get_color_by_id(verificacions_anteriors.get(i).get(j)));
                buttons_verificacio[i-1][j].setOpaque(true);
            }
        }
        if (ctrlPresentacio.get_solucio() == null){
            setButtons(false);
        }
        else if((intents_anteriors.size() == verificacions_anteriors.size()-1) && !(ctrlPresentacio.get_num_ronda_actual() == 1)) {
            setButtons(false);
        }
        else{
            setButtons(true);
        }
    }


    public panel_partida(Controlador_Presentacio ctrlPresentacio) {
        this.ctrlPresentacio = ctrlPresentacio;
        set_up_ui();
        get_intents_anteriors();
        set_up_listeners();

    }

}
