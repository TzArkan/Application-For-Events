package try3;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;

public class AdaugaFonduri extends JFrame {
    private JButton b1, b2;
    private JTextField t1;
    private JFrame parentFrame;

    public AdaugaFonduri(JFrame parentFrame,String username) {
        super("Adauga fonduri: "+username);
        this.parentFrame = parentFrame;

        JPanel p1 = new JPanel(new GridLayout(1, 1, 10, 10));
        p1.add(new JLabel("Adauga fonduri"));
        t1 = new JTextField(20);
        p1.add(t1);

        JPanel p2 = new JPanel();
        b1 = new JButton("Adauga");
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adaugaBani(username,t1);
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
        this.add(p1);
        this.add(p2, BorderLayout.SOUTH);

        this.setSize(300, 150);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void adaugaBani(String username, JTextField t1) {
        File fisier = new File(username + "Evenimente.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(fisier))) {
            // Read the first line of the file
            String firstLine = reader.readLine();
            if (firstLine == null) {
                // File is empty or does not contain any data
                // Handle this case as needed
                return;
            }
            
            // Parse the value from the text field
            String fieldValue = t1.getText();
            if (!sumaValida(fieldValue)) {
                // The value in the text field is not a valid integer
                // Handle this case as needed
                return;
            }
            
            int incrementValue = Integer.parseInt(fieldValue);
            int currentValue = Integer.parseInt(firstLine);
            
            // Increment the initial number by the parsed value
            int newValue = currentValue + incrementValue;
            
            // Write the new incremented value back to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fisier))) {
                writer.write(String.valueOf(newValue));
                writer.newLine(); // Write a new line separator
                // Copy the remaining lines from the original file
                JOptionPane.showMessageDialog(this, "Fonduri adaugate cu succes.", "Succes", JOptionPane.INFORMATION_MESSAGE);
    
                String line;
                while ((line = reader.readLine()) != null) {
                    writer.write(line);
                    writer.newLine(); // Write a new line separator
                }
            } catch (IOException e) {
                e.printStackTrace();
                // Handle IO exception
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle IO exception
        }
    }

    public boolean sumaValida(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}