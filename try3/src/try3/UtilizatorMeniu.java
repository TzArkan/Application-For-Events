/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package try3;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.*;


// Definim clasa UtilizatorMeniu, care extinde JFrame
public class UtilizatorMeniu extends JFrame{
        // Declarații pentru ferestre, butoane, combobox-uri, etichete și panouri
    private JFrame fereastraVeche,f,g,h,i,k;
    private JButton b1, b2, b3, b4, b5, b6, b7, b8;
    private JComboBox cbx1,cbx2;
    private JLabel l1,l2,l3,l4,l5,l6, l7;
    private JPanel p1,p2;
    private GridBagConstraints gbc;

    // Constructorul clasei UtilizatorMeniu
    public UtilizatorMeniu(JFrame fereastraVeche,String username, String rol) {
        super("Utilizator: "+username);// Setăm titlul ferestrei
        this.fereastraVeche = fereastraVeche;// Stocăm referința la fereastra veche pentru a reveni la ea
        // Inițializăm etichetele
        l1=new JLabel("Categorii de evenimente la care esti abonat");
        l2=new JLabel("Aboneaza-te la urmatoarele categorii de evenimente");
        l3=new JLabel("Vezi toate evenimentele curente");
        l7=new JLabel("Vezi evenimentele la ale caror categorii esti abonat");
        l4=new JLabel("Vezi evenimentele curente la care esti abonat");
        l5=new JLabel("Buget utilizator: "+buget(username));
        l6=new JLabel("Vezi codurile biletelor");
        p2 = new JPanel();// Inițializăm panoul p2

        // Inițializăm combobox-urile și le încărcăm cu date
        cbx1=new JComboBox();
        cbx2=new JComboBox();
        incarcaComboBox1(username,cbx1);
        incarcaComboBox2();
        cbx1.setPreferredSize(new Dimension(150, 30));
        cbx2.setPreferredSize(new Dimension(150, 30));
        // Setăm layout-ul pentru panoul principal
        GridBagLayout gbl= new GridBagLayout();
        gbc= new GridBagConstraints();
        p1 = new JPanel(gbl);
            // Inițializăm butoanele și adăugăm acțiuni pentru acestea
        b1 = new JButton("Dezaboneaza-ma");
        b1.setPreferredSize(new Dimension(150, 30));
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Acțiune pentru dezabonare
                stergeCategorieAbonament(username,(String)cbx1.getSelectedItem());
                cbx1.removeAllItems();
                incarcaComboBox1(username, cbx1);
            }
        });

        b2 = new JButton("Aboneaza-ma");
        b2.setPreferredSize(new Dimension(150, 30));
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Acțiune pentru abonare
                if(cbx2.getSelectedIndex() != 0){incarcaCategorieAbonament(username,(String)cbx2.getSelectedItem());
                cbx1.removeAllItems();
                incarcaComboBox1(username, cbx1);}
            }
        });

        b3 = new JButton("Vezi");
        b3.setPreferredSize(new Dimension(150, 30));
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                // Acțiune pentru a vedea toate evenimentele curente
                if (f == null) f = new GestiuneEvenimenteCurente(UtilizatorMeniu.this,username,rol);
                f.setLocation(0, 0);
                f.setSize(1300, 300);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.setVisible(true);
                f=null;
            }
        });

        b8 = new JButton("Vezi");
        b8.setPreferredSize(new Dimension(150, 30));
        b8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                // Acțiune pentru a vedea evenimentele categoriei abonate
                if (k == null) k = new GestionareEvenCateg(UtilizatorMeniu.this,username,rol);
                k.setLocation(0, 0);
                k.setSize(1300, 300);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                k.setVisible(true);
                k=null;
            }
        });


        
        
        b4 = new JButton("Vezi");
        b4.setPreferredSize(new Dimension(150, 30));
        b4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                // Acțiune pentru a vedea evenimentele curente la care este abonat
                if (g == null) g = new UtilizatorAbonamente(UtilizatorMeniu.this,username,rol);
                g.setLocation(0, 0);
                g.setSize(1300, 300);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                g.setVisible(true);
                g=null;
            }
        });
        b6 = new JButton("Adauga fonduri");
        b6.setPreferredSize(new Dimension(150, 30));
        b6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                // Acțiune pentru a adăuga fonduri
                if (h == null) h = new AdaugaFonduri(UtilizatorMeniu.this,username);
                h.setSize(300, 100);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                h.setVisible(true);
                h=null;
            }
        });

        b7 = new JButton("Bilete");
        b7.setPreferredSize(new Dimension(150, 30));
        b7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                // Acțiune pentru a vedea codurile biletelor
                if (i == null) i = new UtilizatorBilete(UtilizatorMeniu.this,username,rol);
                i.setSize(300, 200);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                i.setVisible(true);
                i=null;
            }
        });

        

        
    
        // Adăugăm componentele la panoul principal folosind constrângerile
        adaugaConstrangeri(l1,0,0,1,1, GridBagConstraints.WEST,0,10,0);
        adaugaConstrangeri(b1,0,2,1,1, GridBagConstraints.CENTER,0,10,0);
        adaugaConstrangeri(cbx1,0,1,1,1, GridBagConstraints.CENTER,0,10,0);
        
        adaugaConstrangeri(l2,1,0,1,1, GridBagConstraints.WEST,0,10,0);
        adaugaConstrangeri(b2,1,2,1,1, GridBagConstraints.CENTER,0,10,0);
        adaugaConstrangeri(cbx2,1,1,1,1, GridBagConstraints.CENTER,0,10,0);
        
        adaugaConstrangeri(l3,2,0,1,1, GridBagConstraints.WEST,0,10,0);
        adaugaConstrangeri(b3,2,2,1,1, GridBagConstraints.CENTER,0,10,0);
        adaugaConstrangeri(l7,3,0,1,1, GridBagConstraints.WEST,0,10,0);
        adaugaConstrangeri(b8,3,2,1,1, GridBagConstraints.CENTER,0,10,0);
        adaugaConstrangeri(l4,4,0,1,1, GridBagConstraints.WEST,0,10,0);
        adaugaConstrangeri(b4,4,2,1,1, GridBagConstraints.CENTER,0,10,0);
        adaugaConstrangeri(l5,5,0,1,1, GridBagConstraints.WEST,0,10,0);
        adaugaConstrangeri(b6,5,2,1,1, GridBagConstraints.CENTER,0,10,0);
        adaugaConstrangeri(l6,6,0,1,1, GridBagConstraints.WEST,0,10,0);
        adaugaConstrangeri(b7,6,2,1,1, GridBagConstraints.CENTER,0,10,0);
        
        
        
        b5 = new JButton("Inapoi");
        b5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fereastraVeche.setVisible(true);
                dispose();
            }
        });
        p2.add(b5);
        // Configurăm fereastra principală
        add(p1,BorderLayout.CENTER);
        add(p2,BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);

        
    }

    
    // Metodă pentru a adăuga constrângerile pentru componentele din layout
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
// Metodă pentru a obține bugetul unui utilizator dintr-un fișier
    public String buget(String username){
        String numeFisier = username + "Evenimente.txt";
        String line="";
        try (BufferedReader reader = new BufferedReader(new FileReader(numeFisier))) {
           
            line = reader.readLine();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }
    // Metodă pentru a șterge o categorie de abonament din fișier
    public void stergeCategorieAbonament(String username, String stringToDelete) {
        String numeFisier = username + "Evenimente.txt";
        File file = new File(numeFisier);
    
        try {
            StringBuilder content = new StringBuilder();
            boolean fileExists = file.exists();
     
            if (fileExists && file.length() != 0) {
                // Citim conținutul existent
                try (BufferedReader reader = new BufferedReader(new FileReader(numeFisier))) {
                    // Adauga prima linie
                    content.append(reader.readLine()).append(System.lineSeparator());
                    // Citeste a doua linie
                    String secondLine = reader.readLine();
                    // Daca a doua linie este goala
                    if (secondLine != null) {
                        // Elimina partea din linie
                        secondLine = secondLine.replace(stringToDelete+"$", "");
                        // Afauga partea modificata
                        content.append(secondLine).append(System.lineSeparator());
                    }
                    // Adauga si restul randului
                    String line;
                    while ((line = reader.readLine()) != null) {
                        content.append(line).append(System.lineSeparator());
                    }
                }
            } else {
                return;
            }
    
            // Scrie textul modificat inapoi in fisier
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(content.toString());
            }
    
            JOptionPane.showMessageDialog(null, "Categoria a fost stearsa cu succes.", "Succes", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "A aparut o eroare la inregistrarea datelor.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
// Metodă pentru a încărca o categorie de abonament în fișier
    public void incarcaCategorieAbonament(String username, String newString) {
        String numeFisier = username + "Evenimente.txt";
        File file = new File(numeFisier);

        try {
            StringBuilder content = new StringBuilder();
            boolean fileExists = file.exists();

            if (fileExists) {
                // Read the existing content
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    // Append the first line
                    content.append(reader.readLine()).append(System.lineSeparator());
                    // Append the second line
                    content.append(newString).append("$");
                    // Append the rest of the file
                    String line;
                    while ((line = reader.readLine()) != null) {
                        content.append(line).append(System.lineSeparator());
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

    // Metodă pentru a încărca categoriile de abonament în combobox-ul cbx1
    public void incarcaComboBox1(String username, JComboBox cbx1) {
        String numeFisier = username + "Evenimente.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(numeFisier))) {
            // Skip the first line
            reader.readLine();
            // Read the second line
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

     // Metodă pentru a încărca categoriile de evenimente în combobox-ul cbx2
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

