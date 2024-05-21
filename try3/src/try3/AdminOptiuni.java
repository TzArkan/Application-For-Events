/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package try3;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AdminOptiuni extends JFrame {
    private JFrame fereastraVeche;
    private JButton b1, b2, b3;
    private ControlButoane cb;
   

    public class ControlButoane implements ActionListener {
        private JFrame f,g,h;

        public void actionPerformed(ActionEvent e) {
            /*if (e.getSource() == b1) {
                if (f == null) f = new VizualizareEveniment(AdminOptiuni.this);
                f.setLocationRelativeTo(null);
                f.setSize(300, 150);
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setVisible(true);
                setVisible(false);
            }*/
            if (e.getSource() == b2) {
                if (g == null) g = new AdaugareEveniment(AdminOptiuni.this);
                g.setLocationRelativeTo(null);
                g.setVisible(true);
                g.setSize(700,200);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setVisible(false);
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

        addWindowListener(new WindowAdapter() {
            
            public void windowClosing(WindowEvent e) {
                fereastraVeche.setVisible(true);
            }
        });
        cb = new ControlButoane();
        b1 = new JButton("Vizualizare Evenimente");
        p1.add(b1);
        b1.addActionListener(cb);

        b2 = new JButton("Adaugare Evenimente");
        b2.addActionListener(cb);
        p1.add(b2);
        
        b3 = new JButton("Stergere Evenimente");
        b3.addActionListener(cb);
        p1.add(b3);


        this.add(p1);
    }
}
