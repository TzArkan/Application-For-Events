/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package try3;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Login extends JFrame {
    private JFrame fereastraVeche;
    private JButton b1, b2;
    private ControlButoane cb;
   

    public class ControlButoane implements ActionListener {
        private JFrame f, g;

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == b1) {
                if (f == null) f = new LoginUtilizator(Login.this);
                f.setLocationRelativeTo(null);
                f.setSize(300, 150);
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setVisible(true);
                setVisible(false);
}
            if (e.getSource() == b2) {
                if (g == null) g = new LoginAdmin(Login.this);
                g.setLocationRelativeTo(null);
                g.setVisible(true);
                g.setSize(300, 150);
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                setVisible(false); 
}
        }
    }

    public Login(JFrame fereastraVeche) {
        super("Login");
        this.fereastraVeche = fereastraVeche;
        JPanel p1 = new JPanel(new GridLayout(1, 2));

        addWindowListener(new WindowAdapter() {
            
            public void windowClosing(WindowEvent e) {
                fereastraVeche.setVisible(true);
            }
        });

        b1 = new JButton("Utilizator");
        cb = new ControlButoane();
        p1.add(b1);
        b1.addActionListener(cb);

        b2 = new JButton("Administrator");
        b2.addActionListener(cb);
        p1.add(b2);

        this.add(p1);
    }
}