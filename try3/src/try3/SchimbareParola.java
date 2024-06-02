package try3;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;

public class SchimbareParola extends JFrame{
    private JPasswordField t1, t2, t3;
    private JButton b1, b2;
    private JFrame parentFrame;

    public SchimbareParola(JFrame parentFrame){
        
        super("Schimbare parola administrator");
        JPanel p1=new JPanel(new GridLayout(3,2));
        p1.add(new JLabel("Introduceti parola curenta"));
        t1=new JPasswordField(20);
        p1.add(t1);
        p1.add(new JLabel("Introduceti parola noua"));
        t2=new JPasswordField(20);
        p1.add(t2);
        p1.add(new JLabel("Reintroduceti noua parola"));
        t3=new JPasswordField(20);
        p1.add(t3);

        JPanel p2=new JPanel();
        b1=new JButton("Schimba");
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                schimba();
            }
        });
        p2.add(b1);

        b2=new JButton("Inapoi");
        b2.addActionListener((new ActionListener() {
            public void actionPerformed(ActionEvent e){
                parentFrame.setVisible(true);
                dispose();
            }
        }));
        p2.add(b2);
        
        this.add(p1);
        this.add(p2, BorderLayout.SOUTH);
        this.setSize(300,150);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        
    }

    private void schimba(){

        DateParola dp=new DateParola(new String(t1.getPassword()), new String(t2.getPassword()), new String(t3.getPassword()));
        
        if (dp.getParolaVeche().isEmpty() || dp.getParolaNoua().isEmpty() || dp.getParolaNReintrodusa().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Toate campurile trebuie completate cu informatiile corespunzatoare", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String fileName = "loginDataAdmin.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line=reader.readLine();
            String[] parts = line.split(",");
            String password = parts[1];

                if (dp.getParolaVeche().equals(password)==false) {
                    JOptionPane.showMessageDialog(this,"Parola curenta introdusa nu coincide cu parola contului", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else if(dp.getParolaNoua().equals(dp.getParolaNReintrodusa())==false)
                {
                    JOptionPane.showMessageDialog(this, "Parolele noi introduse trebuie sa coincida intre ele");
                    return;
                }
            String linieNoua = parts[0] + "," + dp.getParolaNoua() + ",Admin";
              
                         
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                writer.write(linieNoua);
                writer.newLine();
                JOptionPane.showMessageDialog(this, "Parola a fost actualizata cu succes.", "Succes", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "A aparut o eroare la inregistrarea datelor.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "A aparut o eroare la citirea datelor.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        t1.setText("");
        t2.setText("");
        t3.setText("");
                
        
    }
}

