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
                // Crează o nouă fereastră de autentificare (Login)
                Login fereastraLogin = new Login(Try3.this);
                fereastraLogin.setLocationRelativeTo(null);
                fereastraLogin.setVisible(true);
                fereastraLogin.setSize(250, 125);
                setVisible(false); 
}
            if (e.getSource() == b2) {
                // Crează o nouă fereastră de înregistrare (Inregistrare) dacă nu există deja
                if (g == null) g = new Inregistrare(Try3.this);
                g.setLocationRelativeTo(null);
                g.setVisible(true);
                g.setSize(300, 175);
                setVisible(false); 
}
            
        }
    }

    public Try3() {
        super("Bine ai venit!");// Setează titlul ferestrei JFrame

        JPanel p1 = new JPanel(new GridLayout(1, 1));// Creează un panou cu o grilă 1x1
        b1 = new JButton("Login");// Creează un buton "Login"
        cb = new ControlButoane();// Creează o instanță a ActionListener
        p1.add(b1);// Adaugă butonul "Login" la panou
        b1.addActionListener(cb);// Atașează ActionListener la buton

        b2 = new JButton("Inregistrare");// Creează un buton "Inregistrare"
        p1.add(b2);// Adaugă butonul "Inregistrare" la panou
        b2.addActionListener(cb);// Atașează ActionListener la buton


        this.add(p1);// Adaugă panoul la JFrame
    }

    public static void main(String[] args) {
        var f = new Try3();// Creează o instanță a clasei Try3
        f.pack(); // Asigură-te că fereastra este suficient de mare pentru a afișa conținutul
        f.setLocationRelativeTo(null);// Centrează fereastra pe ecran
        f.setSize(250,75);// Setează dimensiunea inițială a ferestrei
        f.setVisible(true);// Face fereastra vizibilă
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Închide aplicația când fereastra este închisă
    }
}