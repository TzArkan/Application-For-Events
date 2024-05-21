package try3;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class EvenimenteUtilizator extends JFrame {
    private JFrame fereastraVeche;
    private JButton b1, b2, b3, b4, b5, b6, b7, b8, b9;
    private ControlButoane cb;
   

    public class ControlButoane implements ActionListener {
        private JFrame f,g,h;

        public void actionPerformed(ActionEvent e) {
          
        }
        
    }
public EvenimenteUtilizator(JFrame fereastraVeche) {
    super("Evenimentele utilizatorului");
    this.fereastraVeche = fereastraVeche;
    JPanel p1 = new JPanel(new GridLayout(9, 1));

    addWindowListener(new WindowAdapter() {
        
        public void windowClosing(WindowEvent e) {
            fereastraVeche.setVisible(true);
        }
    });
    cb = new ControlButoane();
    b1 = new JButton("Muzica");
    p1.add(b1);
    b1.addActionListener(cb);

    b2 = new JButton("Inaugurari");
    b2.addActionListener(cb);
    p1.add(b2);
    
    b3 = new JButton("Lansare de Carte");
    b3.addActionListener(cb);
    p1.add(b3);

    b4 = new JButton("Sportiv");
    b4.addActionListener(cb);
    p1.add(b4);

    b5 = new JButton("Expozitii de Arta");
    b5.addActionListener(cb);
    p1.add(b5);

    b6 = new JButton("Serbare");
    b6.addActionListener(cb);
    p1.add(b6);

    b7 = new JButton("Serbare");
    b7.addActionListener(cb);
    p1.add(b7);

    b8 = new JButton("Teatru");
    b8.addActionListener(cb);
    p1.add(b8);

    b9 = new JButton("Evenimente marcate");
    b9.addActionListener(cb);
    p1.add(b9);
    this.add(p1);
}
}


