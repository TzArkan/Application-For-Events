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
    private JButton b1, b2, b3;
    private ControlButoane cb;
   
    // Clasa interna pentru controlul actiunilor butoanelor
    public class ControlButoane implements ActionListener {
        private JFrame f, g;

        
        // Metoda care gestionează evenimentele de acțiune
        public void actionPerformed(ActionEvent e) {
            // Dacă se apasă butonul "Utilizator"
            if (e.getSource() == b1) {
                if (f == null) f = new LoginUtilizator(Login.this);
                f.setLocationRelativeTo(null);// Centrăm fereastra pe ecran
                f.setSize(300, 150);// Setăm dimensiunea ferestrei
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// Setăm comportamentul la închidere
                f.setVisible(true);// Facem fereastra vizibilă
                setVisible(false);// Ascundem fereastra curentă
}               // Dacă se apasă butonul "Administrator"
            if (e.getSource() == b2) {
                if (g == null) g = new LoginAdmin(Login.this);
                g.setLocationRelativeTo(null);// Centrăm fereastra pe ecran
                g.setVisible(true);/// Facem fereastra vizibilă
                g.setSize(300, 150);// Setăm dimensiunea ferestrei
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// Setăm comportamentul la închidere
                setVisible(false); // Ascundem fereastra curentă
}   
            
}
    }
        
    // Constructorul clasei Login
    public Login(JFrame fereastraVeche) {
        super("Login");
        this.fereastraVeche = fereastraVeche;
        JPanel p1 = new JPanel(new GridLayout(1, 2));// Panou pentru butoanele de login
        JPanel p2 = new JPanel();// Panou pentru butonul "Inapoi"


        b1 = new JButton("Utilizator");// Creăm butonul "Utilizator"
        cb = new ControlButoane();// Instanțiem controlerul de butoane
        p1.add(b1);// Adăugăm butonul în panou
        b1.addActionListener(cb);// Adăugăm listener-ul de acțiune

        // Creăm butonul "Administrator"
        b2 = new JButton("Administrator");
        b2.addActionListener(cb);// Adăugăm listener-ul de acțiune
        p1.add(b2);// Adăugăm butonul în panou

        // Creăm butonul "Inapoi"
        b3 = new JButton("Inapoi");
        b3.addActionListener(cb);
        
        b3 = new JButton("Inapoi");
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fereastraVeche.setVisible(true);// Facem fereastra veche vizibilă
                dispose();// Închidem fereastra curentă
            }
        });
        p2.add(b3);// Adăugăm butonul în panou
        // Adăugăm panourile în fereastră
        this.add(p2, BorderLayout.SOUTH);// Panoul cu butonul "Inapoi" în partea de jos
        this.add(p1, BorderLayout.CENTER);// Panoul cu butoanele de login în centru
    }
}