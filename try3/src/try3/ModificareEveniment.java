package try3;

import javax.swing.*;
import java.awt.*;
import java.text.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class ModificareEveniment extends JFrame implements IGesEveniment {
    private JLabel[] l;
    private JLabel l0,pr;
    private JTextField[] t;
    private JButton b1,b2;
    private GridBagConstraints gbc;
    private JPanel p;
    private JComboBox cb;
    private AscultatorCampData acd;
    private AscultatorCampOra aco;
    

    public ModificareEveniment(JFrame parentFrame, int linie){
        super("Modificare Eveniment");
        String[] etichete={"Numele evenimentului","Data evenimentului: zz/ll/yyyy", "Ora de incepere a evenimentului: xx:xx","Locatia evenimentului", "Pretul unui bilet","Numar de bilete disponibile"};
        l=new JLabel[6];
        t=new JTextField[6];
        Dimension textFieldDimension = new Dimension(200, 25);
              for(int i=0; i<6; i++) {
            l[i]=new JLabel(etichete[i]);
            t[i]=new JTextField();               
        }
        incarcaDateDinFisier(linie);
        for(int i=0; i<6; i++) {
            t[i].setPreferredSize(new Dimension(100,100)); // Set the preferred size
            t[i].setMinimumSize(new Dimension(100,25));
        }            
        
        
        acd=new AscultatorCampData();
        t[1].addFocusListener(acd);
        aco=new AscultatorCampOra();
        t[2].addFocusListener(aco);
        l0=new JLabel("Tipul evenimentului");
        pr=new JLabel("Lei");        
        cb=new JComboBox();

        incarcaComboBox();
        
        GridBagLayout gbl=new GridBagLayout();
        gbc=new GridBagConstraints();
        p=new JPanel(gbl);
        adaugaConstrangeri(l0,0, 0, 1, 1, GridBagConstraints.WEST, 0, 10,0); 
        adaugaConstrangeri(cb,0, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,10,0);
        adaugaConstrangeri(l[0],1, 0, 1, 1, GridBagConstraints.WEST, 0, 10,0);
        adaugaConstrangeri(t[0],1, 1, 1, 1, GridBagConstraints.CENTER, 0, 10, 0);
        adaugaConstrangeri(l[1],1, 2, 1, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH, 10,0);
        adaugaConstrangeri(t[1],1, 3, 1, 1, GridBagConstraints.CENTER, 0, 10, 0);
        adaugaConstrangeri(l[2],2, 2, 1, 1, GridBagConstraints.WEST, 0,10,0);
        adaugaConstrangeri(t[2],2, 3, 1, 1, GridBagConstraints.CENTER, 0,10,0);
        adaugaConstrangeri(l[3],2, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH,10,0);
        adaugaConstrangeri(t[3],2, 1, 1, 1, GridBagConstraints.CENTER, 0,10,0);
        adaugaConstrangeri(l[4],3, 0, 1, 1, GridBagConstraints.WEST, 0,10,0);
        adaugaConstrangeri(t[4],3, 1, 1, 1, GridBagConstraints.CENTER, 0,10,0);
        adaugaConstrangeri(l[5],4, 0, 1, 1, GridBagConstraints.WEST, 0,10,0);
        adaugaConstrangeri(t[5],4, 1, 1, 1, GridBagConstraints.CENTER, 0,10,0);
        adaugaConstrangeri(pr,3, 2, 1, 1, GridBagConstraints.WEST, 0,10,0);
        //adaugaConstrangeri(l[5],3, 2, 1, 1, GridBagConstraints.WEST, 0,10,0);
        //adaugaConstrangeri(t[5],3, 3, 1, 1, GridBagConstraints.CENTER, 0,10,0);
        //adaugaConstrangeri(l[6],4, 2, 1, 1, GridBagConstraints.EAST, GridBagConstraints.BOTH,10,0);
        //adaugaConstrangeri(t[6],4, 3, 1, 1, GridBagConstraints.CENTER, 0,10,0);
        
        b1=new JButton("Modificati evenimentul");
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stocareDate(linie);
            }
        });
        
        b2 = new JButton("Inapoi");
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parentFrame.setVisible(true);
                dispose();
            }
        });
        
        add(p);
        
        p=new JPanel(gbl);
        adaugaConstrangeri(b1, 0,0,1,1, GridBagConstraints.CENTER, GridBagConstraints.NONE,20,0);
        adaugaConstrangeri(b2, 0,1,1,1, GridBagConstraints.CENTER, GridBagConstraints.NONE,20,0);
          
        add(p, BorderLayout.SOUTH);
        
    }

    public void incarcaDateDinFisier(int linie) {
            
        try (BufferedReader reader = new BufferedReader(new FileReader("dateEvenimente.txt"))) {
            String line;
            for(int i=0; i<linie;i++) reader.readLine();
            line = reader.readLine();                
                String[] parts = line.split("\\$");     // \\$ ca sa ia caracterul $
                
                for(int i=0; i<6; i++) {
                    t[i]= new JTextField(parts[i+1]);
                }
                
                
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private class AscultatorCampOra implements FocusListener{
        private Dialog o;
        private String ora;
        public void focusGained(FocusEvent e){}
        public void focusLost(FocusEvent e){
          ora=t[2].getText();
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
          try {
            LocalTime.parse(ora, formatter);
            } catch (DateTimeParseException z) {
                o=new JDialog(ModificareEveniment.this, "Eroare");
                o.add(new JLabel("   Introduceti ora dupa formatul: HH:mm"));
                o.setBounds(200,200, 270, 100);
                o.setVisible(true);
            }//catch
        }//metoda focusLost
        
       }

    private class AscultatorCampData implements FocusListener{
        private Dialog d;
        private String data;
        public void focusGained(FocusEvent e){}
        public void focusLost(FocusEvent e){
          data=t[1].getText();
         DateFormat df=DateFormat.getDateInstance(DateFormat.SHORT);
         try{
          df.parse(data);
         }catch(ParseException nf){	
          d=new JDialog(ModificareEveniment.this, "Eroare");
          d.add(new JLabel("   Introduceti data dupa formatul: zz/ll/yyyy"));
          d.setBounds(250,250, 270, 100);
          d.setVisible(true);
          }//catch
        }//metoda focusLost
        
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
    

       public boolean dataEvenimentValida(String dataEveniment) {
        DateFormat df=DateFormat.getDateInstance(DateFormat.SHORT);
        try {
            
            df.parse(dataEveniment);
            return true;
        } catch (ParseException e) {
            
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

    public void stocareDate(int linie) {
        String numeEveniment = t[0].getText();
        String dataEveniment = t[1].getText();
        String oraEveniment = t[2].getText();
        String locatieEveniment = t[3].getText();
        String pretEveniment = t[4].getText();
        String numarBileteEveniment = t[5].getText();
        
        if (numarBilete(numarBileteEveniment)==false || cb.getSelectedIndex()==0 || numeEveniment.isEmpty() || dataEvenimentValida(dataEveniment)==false || oraEvenimentValida(oraEveniment)==false || locatieEveniment.isEmpty() || pretEvenimentValid(pretEveniment)==false) {
            JOptionPane.showMessageDialog(this, "Introduceti toate datele corespunzatoare evenimentului", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String newLine = cb.getSelectedItem() + "$" + numeEveniment + "$" + dataEveniment + "$" + oraEveniment + "$" + locatieEveniment + "$" + pretEveniment + "$" + numarBileteEveniment + "$0";
        
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("dateEvenimente.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (linie >= 0 && linie < lines.size()) {
            lines.set(linie, newLine);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("dateEvenimente.txt"))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
            JOptionPane.showMessageDialog(this, "Datele evenimentului au fost salvate cu succes.", "Succes", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "A aparut o eroare la inregistrarea datelor.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void adaugaConstrangeri(JComponent c,int linie, int col, int latime, int inaltime, int anchor, int fill, int spatiuOX, int spatiuOY) {
        gbc.gridx = col;
        gbc.gridy = linie;
        gbc.gridwidth= latime;
        gbc.gridheight = inaltime;
        gbc.anchor = anchor;
        double weightx = 0.0;
        double weighty = 0.0;
        if(latime > 1)weightx = 1.0;
        if(inaltime > 1)weighty = 1.0;
        switch(fill){
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
        gbc.insets=new Insets(0,2*spatiuOX,0,2*spatiuOY);
        p.add(c, gbc);
}

    
}
