/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package try3;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;

public class LoginUtilizator extends JFrame implements ILogin{
    private JTextField t1;// Câmp de text pentru numele utilizatorului
    private JPasswordField t2;// Câmp de text pentru parolă
    private JButton b1, b2;// Butoanele OK și Înapoi
    private JFrame parentFrame; // Fereastra părinte
    
    // Constructorul clasei LoginUtilizator
    public LoginUtilizator(JFrame parentFrame) {
        super("Login Utilizator");
        this.parentFrame = parentFrame;
// Creăm panoul pentru câmpurile de introducere a datelor
        JPanel p1 = new JPanel(new GridLayout(2, 2, 10, 10));
        p1.add(new JLabel("Nume utilizator"));
        t1 = new JTextField(20);// Inițializăm câmpul pentru numele utilizatorului
        p1.add(t1);
        p1.add(new JLabel("Parola"));
        t2 = new JPasswordField(15);// Inițializăm câmpul pentru parolă
        p1.add(t2);
        this.add(p1);
         // Creăm panoul pentru butoane
        JPanel p2 = new JPanel();
        b1 = new JButton("OK");// Inițializăm butonul OK
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login();// Apelăm metoda de autentificare la apăsarea butonului OK
            }
        });
        p2.add(b1);

        b2 = new JButton("Inapoi");// Inițializăm butonul Înapoi
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parentFrame.setVisible(true);// Facem fereastra părinte vizibilă
                dispose();// Închidem fereastra curentă
            }
        });
        p2.add(b2);
        this.add(p2, BorderLayout.SOUTH);// Adăugăm panoul cu butoane în partea de sud a ferestrei

        
        
         // Setăm proprietățile ferestrei
        this.setSize(300, 150);
        this.setLocationRelativeTo(null);// Centrăm fereastra pe ecran
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// Setăm comportamentul la închidere
    }
    // Metoda pentru autentificare
    private void login() {
        String enteredUsername = t1.getText();// Preluăm numele utilizatorului
        String enteredPassword = new String(t2.getPassword());// Preluăm parola
        String rol="";
         // Verificăm dacă câmpurile sunt goale
        if (enteredUsername.isEmpty() || enteredPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Introduceti numele contului si parola.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String fileName = "loginDataUtilizator.txt";// Numele fișierului cu datele de autentificare
        boolean found = false;
         // Citim datele din fișier
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String username = parts[0];
                String password = parts[1];
                rol=parts[3];
                    // Verificăm dacă datele introduse corespund cu cele din fișier
                if (enteredUsername.equals(username) && enteredPassword.equals(password)) {
                    found = true;
                    break;
                }
            }
            // Dacă autentificarea este reușită
            if (found) {
                
                JOptionPane.showMessageDialog(this, "Autentificare reusita.", "Succes", JOptionPane.INFORMATION_MESSAGE);

                // Creăm și afișăm meniul utilizatorului
                UtilizatorMeniu utilizMeniu = new UtilizatorMeniu(LoginUtilizator.this, enteredUsername, rol);
                utilizMeniu.setSize(800, 350);
                utilizMeniu.setLocationRelativeTo(null);
                utilizMeniu.setVisible(true);

                // Creăm și afișăm notificările utilizatorului
                UtilizatorNotificare utilizNotif = new UtilizatorNotificare(LoginUtilizator.this, enteredUsername);
                utilizNotif.setSize(500, 150);
                utilizNotif.setLocationRelativeTo(null);
                utilizNotif.setVisible(true);

                // Resetăm câmpurile de text și ascundem fereastra curentă
                t1.setText("");
                t2.setText("");
                setVisible(false);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Nume utilizator sau parola incorecta.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "A aparut o eroare la citirea datelor.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}