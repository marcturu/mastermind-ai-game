package main.presentation.classes;

import javax.swing.JButton;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;


public class button extends JButton implements ActionListener{

    private int color;
    int num_colors;
    boolean verificacio;

    public button(int color, boolean verificacio, int num_colors) {
        this.color = color;
        this.verificacio = verificacio;
        this.num_colors = num_colors;
        setPreferredSize(new Dimension(50, 50));
        setFocusPainted(false);
        canviar_color();
        addActionListener(this);
    }

    public int get_color() {
        return color;
    }

    public void ciclar_color() {
        if (verificacio){
            if (color == 0) color = 9;
            else if (color == 9) color = 10;
            else if (color == 10) color = 0;
        }
        else{
            if (color == num_colors)
                color = 1;
            else
                color++;
        }
        canviar_color();
    }

    public void canviar_color() {
        switch (color) {
            case 0:
                setBackground(null);
                break;
            case 1:
                setBackground(Color.RED);
                break;
            case 2:
                setBackground(Color.GREEN);
                break;
            case 3:
                setBackground(Color.BLUE);
                break;
            case 4:
                setBackground(Color.YELLOW);
                break;
            case 5:
                setBackground(Color.MAGENTA);
                break;
            case 6:
                setBackground(Color.CYAN);
                break;
            case 7:
                setBackground(new Color(165, 42, 42));
                break;
            case 8:
                setBackground(Color.GRAY);
                break;
            case 9:
                setBackground(Color.WHITE);
                break;
            case 10:
                setBackground(Color.BLACK);
        }

        //FOR MACOS ONLY
        setOpaque(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ciclar_color();
    }
}