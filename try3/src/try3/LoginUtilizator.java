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
    private JTextField t1;
    private JPasswordField t2;
    private JButton b1, b2;
    private JFrame parentFrame; 
    

    public LoginUtilizator(JFrame parentFrame) {
        super("Login Utilizator");
        this.parentFrame = parentFrame;

        JPanel p1 = new JPanel(new GridLayout(2, 2, 10, 10));
        p1.add(new JLabel("Nume utilizator"));
        t1 = new JTextField(20);
        p1.add(t1);
        p1.add(new JLabel("Parola"));
        t2 = new JPasswordField(15);
        p1.add(t2);
        this.add(p1);

        JPanel p2 = new JPanel();
        b1 = new JButton("OK");
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        p2.add(b1);

        b2 = new JButton("Inapoi");
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parentFrame.setVisible(true);
                dispose();
            }
        });
        p2.add(b2);
        this.add(p2, BorderLayout.SOUTH);

        
        

        this.setSize(300, 150);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void login() {
        String enteredUsername = t1.getText();
        String enteredPassword = new String(t2.getPassword());

        if (enteredUsername.isEmpty() || enteredPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Introduceti numele contului si parola.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String fileName = "loginDataUtilizator.txt";
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String username = parts[0];
                String password = parts[1];

                if (enteredUsername.equals(username) && enteredPassword.equals(password)) {
                    found = true;
                    break;
                }
            }

            if (found) {
                JOptionPane.showMessageDialog(this, "Autentificare reusita.", "Succes", JOptionPane.INFORMATION_MESSAGE);
                UtilizatorMeniu utilzEv= new UtilizatorMeniu(LoginUtilizator.this,enteredUsername);
                t1.setText("");
                t2.setText("");
                utilzEv.setSize(800,350);
                utilzEv.setLocationRelativeTo(null);
                utilzEv.setVisible(true);
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