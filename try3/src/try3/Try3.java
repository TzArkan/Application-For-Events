/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package try3;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Try3 extends JFrame {
    private JButton b1, b2;
    private ControlButoane cb;

    public class ControlButoane implements ActionListener {
        private JFrame f, g;

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == b1) {
                Login fereastraLogin = new Login(Try3.this);
                fereastraLogin.setLocationRelativeTo(null);
                fereastraLogin.setVisible(true);
                fereastraLogin.setSize(250, 125);
                setVisible(false); 
}
            if (e.getSource() == b2) {
                if (g == null) g = new Inregistrare(Try3.this);
                g.setLocationRelativeTo(null);
                g.setVisible(true);
                g.setSize(300, 175);
                setVisible(false); 
}
            
        }
    }

    public Try3() {
        super("Welcome");

        JPanel p1 = new JPanel(new GridLayout(1, 1));
        b1 = new JButton("Login");
        cb = new ControlButoane();
        p1.add(b1);
        b1.addActionListener(cb);

        b2 = new JButton("Inregistrare");
        p1.add(b2);
        b2.addActionListener(cb);

        this.add(p1);
    }

    public static void main(String[] args) {
        var f = new Try3();
        f.pack(); 
        f.setLocationRelativeTo(null);
        f.setSize(250,75);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}