/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package try3;

import javax.swing.*;

import java.awt.*;
import java.text.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.*;



public class UtilizatorMeniu extends JFrame{
    private JFrame fereastraVeche;
    private JButton b1, b2, b3, b4, b5, b6;
    private ControlButoane cb;
    private JComboBox cbx1,cbx2;
    private JLabel l1,l2,l3,l4,l5;
    private JPanel p1,p2;
    private GridBagConstraints gbc;

   

    public class ControlButoane implements ActionListener {
        private JFrame f,g,h;

        public void actionPerformed(ActionEvent e) {

        
        }
        
    }

    public UtilizatorMeniu(JFrame fereastraVeche,String username) {
        super("Optiuni utilizator");
        this.fereastraVeche = fereastraVeche;
        l1=new JLabel("Categorii de evenimente la care esti abonat");
        l2=new JLabel("Aboneaza-te la urmatoarele categorii de evenimente");
        l3=new JLabel("Vezi toate evenimentele curente");
        l4=new JLabel("Vezi evenimentele curente la care esti abonat");
        l5=new JLabel("Sterge toate preferintele curente");
        p2 = new JPanel();

        cb = new ControlButoane();

        cbx1=new JComboBox();
        cbx2=new JComboBox();
        incarcaComboBox1(username,cbx1);
        incarcaComboBox2();
        cbx1.setPreferredSize(new Dimension(150, 30));
        cbx2.setPreferredSize(new Dimension(150, 30));

        GridBagLayout gbl= new GridBagLayout();
        gbc= new GridBagConstraints();
        p1 = new JPanel(gbl);
        
        b1 = new JButton("Dezaboneaza-ma");
        b1.setPreferredSize(new Dimension(150, 30));
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                stergeCategorieAbonament(username,(String)cbx1.getSelectedItem());
                cbx1.removeAllItems();
                incarcaComboBox1(username, cbx1);
            }
        });

        b2 = new JButton("Aboneaza-ma");
        b2.setPreferredSize(new Dimension(150, 30));
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                incarcaCategorieAbonament(username,(String)cbx2.getSelectedItem());
                cbx1.removeAllItems();
                incarcaComboBox1(username, cbx1);
            }
        });

        b3 = new JButton("Vezi");
        b3.setPreferredSize(new Dimension(150, 30));
        b3.addActionListener(cb);
        
        b4 = new JButton("Vezi");
        b4.setPreferredSize(new Dimension(150, 30));
        b4.addActionListener(cb);

        b6 = new JButton("Sterge");
        b6.setPreferredSize(new Dimension(150, 30));
        b6.addActionListener(cb);
        b6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                stergePreferinteUtilizator(username);
                cbx1.removeAllItems();
                
            }
        });

        adaugaConstrangeri(l1,0,0,1,1, GridBagConstraints.WEST,0,10,0);
        adaugaConstrangeri(b1,0,2,1,1, GridBagConstraints.CENTER,0,10,0);
        adaugaConstrangeri(cbx1,0,1,1,1, GridBagConstraints.CENTER,0,10,0);
        
        adaugaConstrangeri(l2,1,0,1,1, GridBagConstraints.WEST,0,10,0);
        adaugaConstrangeri(b2,1,2,1,1, GridBagConstraints.CENTER,0,10,0);
        adaugaConstrangeri(cbx2,1,1,1,1, GridBagConstraints.CENTER,0,10,0);
        
        adaugaConstrangeri(l3,2,0,1,1, GridBagConstraints.WEST,0,10,0);
        adaugaConstrangeri(b3,2,2,1,1, GridBagConstraints.CENTER,0,10,0);
        adaugaConstrangeri(l4,3,0,1,1, GridBagConstraints.WEST,0,10,0);
        adaugaConstrangeri(b4,3,2,1,1, GridBagConstraints.CENTER,0,10,0);
        adaugaConstrangeri(l5,4,0,1,1, GridBagConstraints.WEST,0,10,0);
        adaugaConstrangeri(b6,4,2,1,1, GridBagConstraints.CENTER,0,10,0);
        
        
        b5 = new JButton("Inapoi");
        b5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fereastraVeche.setVisible(true);
                dispose();
            }
        });
        p2.add(b5);

        add(p1,BorderLayout.CENTER);
        add(p2,BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);

        
    }

    public void adaugaConstrangeri(JComponent c, int linie, int col, int latime, int inaltime, int anchor, int fill, int spatiuOX, int spatiuOY) {
        gbc.gridx = col;
        gbc.gridy = linie;
        gbc.gridwidth = latime;
        gbc.gridheight = inaltime;
        gbc.anchor = anchor;
        double weightx = 0.0;
        double weighty = 0.0;
        if (latime > 1) weightx = 1.0;
        if (inaltime > 1) weighty = 1.0;
        switch (fill) {
            case GridBagConstraints.HORIZONTAL -> {
                gbc.weightx = weightx;
                gbc.weighty = 0.0;
            }
            case GridBagConstraints.VERTICAL -> {
                gbc.weighty = weighty;
                gbc.weightx = 0.0;
            }
            case GridBagConstraints.BOTH -> {
                gbc.weightx = weightx;
                gbc.weighty = weighty;
            }
            case GridBagConstraints.NONE -> {
                gbc.weightx = 0.0;
                gbc.weighty = 0.0;
            }
        }
        gbc.fill = fill;
        gbc.insets = new Insets(0, 2 * spatiuOX, 0, 2 * spatiuOY);
        p1.add(c, gbc);
    }

    public void stergePreferinteUtilizator(String username){
        File fisier = new File(username+"Evenimente.txt");

        if (fisier.exists()) {
            if (fisier.delete()) {
                JOptionPane.showMessageDialog(null, "Preferintele au fost sterse", "Succes", JOptionPane.INFORMATION_MESSAGE);
        
            } else {
                System.out.println("Nu s-au putut sterge preferintele");
            }
        } else {
            System.out.println("Nu aveti nicio preferinta existenta");
        }
    }

    public void stergeCategorieAbonament(String username, String newString) {
        String numeFisier = username + "Evenimente.txt";
        File file = new File(numeFisier);

        try {
            StringBuilder content = new StringBuilder();
            boolean fileExists = file.exists();

            if (fileExists&&file.length() != 0) {
                // Read the existing content
                try (BufferedReader reader = new BufferedReader(new FileReader(numeFisier))) {
                    String line;
                    boolean isFirstLine = true;
                    while ((line = reader.readLine()) != null) {
                        if (isFirstLine) {
                            // Modify the first line to remove newString
                            String[] items = line.split("\\$");
                            for (int i = 0; i < items.length; i++) {
                            // Add the rest of the items except newString
                            if (!items[i].equals(newString)) {
                                content.append(items[i]).append("$");
                }
            }
                content.append("\n");
                isFirstLine = false;
                        } else {
                            content.append(line).append(System.lineSeparator());
                        }
                    }
                }
            } else {
                // If the file doesn't exist, initialize the first line with the new string
                content.append(newString).append("$").append(System.lineSeparator());
            }

            // Write the modified or new content back to the file
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(content.toString());
            }

            JOptionPane.showMessageDialog(null, "Datele evenimentului au fost salvate cu succes.", "Succes", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "A aparut o eroare la inregistrarea datelor.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public void incarcaCategorieAbonament(String username, String newString) {
        String numeFisier = username + "Evenimente.txt";
        File file = new File(numeFisier);

        try {
            StringBuilder content = new StringBuilder();
            boolean fileExists = file.exists();

            if (fileExists) {
                // Read the existing content
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    boolean isFirstLine = true;
                    while ((line = reader.readLine()) != null) {
                        if (isFirstLine) {
                            // Modify the first line to include the new string
                            content.append(line).append(newString).append("$").append(System.lineSeparator());
                            isFirstLine = false;
                        } else {
                            content.append(line).append(System.lineSeparator());
                        }
                    }
                }
            } else {
                // If the file doesn't exist, initialize the first line with the new string
                content.append(newString).append("$").append(System.lineSeparator());
            }

            // Write the modified or new content back to the file
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(content.toString());
            }

            JOptionPane.showMessageDialog(null, "Datele evenimentului au fost salvate cu succes.", "Succes", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "A aparut o eroare la inregistrarea datelor.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    
    public void incarcaComboBox1(String username, JComboBox cbx1) {
        String numeFisier = username + "Evenimente.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(numeFisier))) {
            String line = reader.readLine();
            if (line != null) {
                String[] items = line.split("\\$");
                for (String item : items) {
                    cbx1.addItem(item);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    public void incarcaComboBox2() {
        String numeFisier = "categoriiEvenimente.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(numeFisier))) {
            String line;
            while ((line = reader.readLine()) != null) {
                cbx2.addItem(line);
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    

}

