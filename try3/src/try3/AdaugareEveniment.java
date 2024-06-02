package try3;

import javax.swing.*;
import java.awt.*;
import java.text.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
//dawdawwad
public class AdaugareEveniment extends JFrame implements IGesEveniment {
    private JLabel[] l;
    private JLabel l0, pr;
    private JTextField[] t;
    private JButton b1, b2;
    private GridBagConstraints gbc;
    private JPanel p, p2;
    private JComboBox cb;
    private AscultatorCampData acd;
    private AscultatorCampOra aco;

    public AdaugareEveniment(JFrame parentFrame) {
        super("Adaugare de Evenimente");
        String[] etichete = { "Numele evenimentului", "Data evenimentului: zz/ll/yyyy", "Ora de incepere a evenimentului: xx:xx", "Locatia evenimentului", "Pretul unui bilet", "Numar de bilete disponibile" };
        l = new JLabel[6];
        t = new JTextField[6];
        for (int i = 0; i < 6; i++) {
            l[i] = new JLabel(etichete[i]);
            t[i] = new JTextField(10);
        }

        acd = new AscultatorCampData();
        t[1].addFocusListener(acd);
        aco = new AscultatorCampOra();
        t[2].addFocusListener(aco);
        l0 = new JLabel("Tipul evenimentului");
        pr = new JLabel("Lei");
        cb=new JComboBox();
        incarcaComboBox();

        GridBagLayout gbl = new GridBagLayout();
        gbc = new GridBagConstraints();
        p = new JPanel(gbl);

        adaugaConstrangeri(l0, 0, 0, 1, 1, GridBagConstraints.WEST, 0, 10, 0);
        adaugaConstrangeri(cb, 0, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 10, 0);
        adaugaConstrangeri(l[0], 1, 0, 1, 1, GridBagConstraints.WEST, 0, 10, 0);
        adaugaConstrangeri(t[0], 1, 1, 1, 1, GridBagConstraints.CENTER, 0, 10, 0);
        adaugaConstrangeri(l[1], 1, 2, 1, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH, 10, 0);
        adaugaConstrangeri(t[1], 1, 3, 1, 1, GridBagConstraints.CENTER, 0, 10, 0);
        adaugaConstrangeri(l[2], 2, 2, 1, 1, GridBagConstraints.WEST, 0, 10, 0);
        adaugaConstrangeri(t[2], 2, 3, 1, 1, GridBagConstraints.CENTER, 0, 10, 0);
        adaugaConstrangeri(l[3], 2, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH, 10, 0);
        adaugaConstrangeri(t[3], 2, 1, 1, 1, GridBagConstraints.CENTER, 0, 10, 0);
        adaugaConstrangeri(l[4], 3, 0, 1, 1, GridBagConstraints.WEST, 0, 10, 0);
        adaugaConstrangeri(t[4], 3, 1, 1, 1, GridBagConstraints.CENTER, 0, 10, 0);
        adaugaConstrangeri(l[5], 4, 0, 1, 1, GridBagConstraints.WEST, 0, 10, 0);
        adaugaConstrangeri(t[5], 4, 1, 1, 1, GridBagConstraints.CENTER, 0, 10, 0);
        adaugaConstrangeri(pr, 3, 2, 1, 1, GridBagConstraints.WEST, 0, 10, 0);

        b1 = new JButton("Adaugati eveniment");
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storeUserData();
            }
        });

        b2 = new JButton("Inapoi");
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parentFrame.setVisible(true);
                dispose();
            }
        });

        p2 = new JPanel();
        p2.add(b1);
        p2.add(b2);

        add(p, BorderLayout.CENTER);
        add(p2, BorderLayout.SOUTH);

        pack();  // Adjusts window to preferred size based on layout
        setLocationRelativeTo(null);  // Centers the window on the screen
    }

    private class AscultatorCampOra implements FocusListener {
        private Dialog o;
        private String ora;

        public void focusGained(FocusEvent e) {}

        public void focusLost(FocusEvent e) {
            ora = t[2].getText();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            try {
                LocalTime.parse(ora, formatter);
            } catch (DateTimeParseException z) {
                o = new JDialog(AdaugareEveniment.this, "Eroare");
                o.add(new JLabel("   Introduceti ora dupa formatul: HH:mm"));
                o.setBounds(200, 200, 270, 100);
                o.setVisible(true);
            }
        }
    }

    private class AscultatorCampData implements FocusListener {
        private Dialog d;
        private String data;

        public void focusGained(FocusEvent e) {}

        public void focusLost(FocusEvent e) {
            data = t[1].getText();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false); // Ensure strict parsing
            try {
                sdf.parse(data); // Try to parse the date
            } catch (ParseException nf) {
                d = new JDialog((JFrame) SwingUtilities.getWindowAncestor(t[1]), "Eroare");
                d.add(new JLabel("   Introduceti data dupa formatul: dd/MM/yyyy"));
                d.setBounds(250, 250, 270, 100);
                d.setVisible(true);
            }
        }
    }

    public void incarcaComboBox() {
        String numeFisier = "categoriiEvenimente.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(numeFisier))) {
            String line;
            while ((line = reader.readLine()) != null) {
                cb.addItem(line);
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean oraEvenimentValida(String ora) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        try {
            LocalTime.parse(ora, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public boolean dataEvenimentValida(String dataEveniment) {
        // Create a SimpleDateFormat instance with the desired format
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        // Set lenient to false to enforce strict date parsing
        sdf.setLenient(false);
    
        try {
            // Try to parse the date
            sdf.parse(dataEveniment);
            return true;
        } catch (ParseException e) {
            // If parsing fails, return false
            return false;
        }
    }

    public boolean pretEvenimentValid(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean numarBilete(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void storeUserData() {
    

        DateEveniment de=new DateEveniment(t[0].getText(), t[1].getText(), t[2].getText(), t[3].getText(), t[4].getText(), t[5].getText());
        
        
        if (numarBilete(de.getNrBilete()) == false || cb.getSelectedIndex() == 0 || de.getNume().isEmpty() || dataEvenimentValida(de.getData()) == false || oraEvenimentValida(de.getOra()) == false || de.getLocatie().isEmpty() || pretEvenimentValid(de.getPret()) == false) {
            JOptionPane.showMessageDialog(this, "Introduceti toate datele corespunzatoare evenimentului", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String fileName = "dateEvenimente.txt";
        try (FileWriter fw = new FileWriter(fileName, true)) {
            fw.write(cb.getSelectedItem() + "$" + de.getNume() + "$" + de.getData() + "$" + de.getOra() + "$" + de.getLocatie() + "$" + de.getPret() + "$" + de.getNrBilete() + "$" + "0" + "\n");
            fw.flush(); // Ensure data is written immediately
            JOptionPane.showMessageDialog(this, "Datele evenimentului au fost salvate cu succes.", "Succes", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "A aparut o eroare la inregistrarea datelor.", "Error", JOptionPane.ERROR_MESSAGE);
        }
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
        p.add(c, gbc);
    }
}