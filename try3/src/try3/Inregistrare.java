/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package try3;

import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;

public class Inregistrare extends JFrame {
    private JButton b1, b2;
    private JTextField t1;
    private JPasswordField t2;
    private JFrame parentFrame;

    public Inregistrare(JFrame parentFrame) {
        super("Inregistrare utilizator");
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
            @Override
            public void actionPerformed(ActionEvent e) {
                storeUserData();
            }
        });
        p2.add(b1);

        b2 = new JButton("Inapoi");
        b2.addActionListener(new ActionListener() {
            @Override
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

    private void storeUserData() {
        String username = t1.getText();
        String password = new String(t2.getPassword());
        
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Introduceti numele contului si parola.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        
        String fileName = "loginDataUtilizator.txt";
        try (FileWriter fw = new FileWriter(fileName, true)) {
            fw.write(username + "," + password + ",Utilizator\n");
            fw.flush(); // Ensure data is written immediately
            JOptionPane.showMessageDialog(this, "Utilizator inregistrat cu succes.", "Succes", JOptionPane.INFORMATION_MESSAGE);
            parentFrame.setVisible(true);
            dispose();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "A aparut o eroare la inregistrarea datelor.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}