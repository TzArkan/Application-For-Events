/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package try3;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AdminOptiuni extends JFrame{
    private JFrame fereastraVeche;
    private JButton b1, b2, b3;
    private ControlButoane cb;
   

    public class ControlButoane implements ActionListener {
        private JFrame f,g,h;

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == b1) {
                if (f == null) f = new GestiuneEvenimenteCurente(AdminOptiuni.this);
                f.setLocation(0, 0);
                f.setSize(800, 300);
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setVisible(true);
                f=null;         // ca sa stearga instanta actuala a gestiunii tabelare
            }
            if (e.getSource() == b2) {
                if (g == null) g = new AdaugareEveniment(AdminOptiuni.this);
                int oX=350;
                int oY=350;
                g.setSize(800,200);
                g.setLocation(oX, oY);
                g.setVisible(true);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
            
                
            
            /*if (e.getSource() == b3) {
                if (h == null) h = new StergereEveniment(AdminOptiuni.this);
                h.setLocationRelativeTo(null);
                h.setVisible(true);
                h.setSize(300, 150);
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                setVisible(false);
            }*/
        }
        
    }

    public AdminOptiuni(JFrame fereastraVeche) {
        super("Optiuni administrator");
        this.fereastraVeche = fereastraVeche;
        JPanel p1 = new JPanel(new GridLayout(3, 1));
        
        JPanel p2 = new JPanel();
        
        cb = new ControlButoane();
        b1 = new JButton("Gestionare Evenimente Curente");
        p1.add(b1);
        b1.addActionListener(cb);

        b2 = new JButton("Adaugare Evenimente");
        b2.addActionListener(cb);
        p1.add(b2);
        
        b3 = new JButton("Inapoi");
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fereastraVeche.setVisible(true);
                dispose();
            }
        });
        p2.add(b3);

        this.add(p1);
        this.add(p2, BorderLayout.SOUTH);
    }
}
