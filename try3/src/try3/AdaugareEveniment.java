/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package try3;


import javax.swing.*;
import java.awt.*;
import java.text.*;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.*;
import java.util.Date;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AdaugareEveniment extends JFrame{
    private JLabel[] l;
    private JLabel l0,pr;
    private JTextField[] t;
    private JButton b1;
    private GridBagConstraints gbc;
    private JPanel p;
    private JComboBox cb;
    private AscultatorCampData acd;
    private AscultatorCampOra aco;
    

    public AdaugareEveniment(JFrame parentFrame){
        super("Adaugare de Evenimente");
        String[] etichete={"Numele evenimentului","Data evenimentului: zz/ll/yyyy", "Ora de incepere a evenimentului: xx:xx","Locatia evenimentului", "Pretul unui bilet"};
        l=new JLabel[5];
        t=new JTextField[5];
              for(int i=0; i<5; i++) {
            l[i]=new JLabel(etichete[i]);
            t[i]=new JTextField(10);
        }
        acd=new AscultatorCampData();
        t[1].addFocusListener(acd);
        aco=new AscultatorCampOra();
        t[2].addFocusListener(aco);
        l0=new JLabel("Tipul evenimentului");
        pr=new JLabel("Lei");        
        cb=new JComboBox(new String[]{"Muzica","Inaugurari","Lansare de carte","Sport","Expozitii de arta","Teatru","Film","Festival"});
        
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
        adaugaConstrangeri(pr,3, 2, 1, 1, GridBagConstraints.WEST, 0,10,0);
        //adaugaConstrangeri(l[5],3, 2, 1, 1, GridBagConstraints.WEST, 0,10,0);
        //adaugaConstrangeri(t[5],3, 3, 1, 1, GridBagConstraints.CENTER, 0,10,0);
        //adaugaConstrangeri(l[6],4, 2, 1, 1, GridBagConstraints.EAST, GridBagConstraints.BOTH,10,0);
        //adaugaConstrangeri(t[6],4, 3, 1, 1, GridBagConstraints.CENTER, 0,10,0);
        b1=new JButton("Adaugati eveniment");
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storeUserData();
            }
        });
        
        
        
        add(p);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                parentFrame.setVisible(true);
            }
        });
        p=new JPanel(gbl);
        adaugaConstrangeri(b1, 0,0,1,1, GridBagConstraints.CENTER, GridBagConstraints.NONE,20,0);
        
          
        add(p, BorderLayout.SOUTH);
        
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
                o=new JDialog(AdaugareEveniment.this, "Eroare");
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
          d=new JDialog(AdaugareEveniment.this, "Eroare");
          d.add(new JLabel("   Introduceti data dupa formatul: zz/ll/yyyy"));
          d.setBounds(250,250, 270, 100);
          d.setVisible(true);
          }//catch
        }//metoda focusLost
        
       }

        public static boolean oraEvenimentValida(String ora) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                try {

                LocalTime.parse(ora, formatter);
                return true; 
                } catch (DateTimeParseException e) {
                return false; 
        }
    }
    

       public static boolean dataEvenimentValida(String dataEveniment) {
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

    private void storeUserData() {
        String numeEveniment = t[0].getText();
        String dataEveniment = t[1].getText();
        String oraEveniment = t[2].getText();
        String locatieEveniment = t[3].getText();
        String pretEveniment = t[4].getText();
        
        if (numeEveniment.isEmpty() || dataEvenimentValida(dataEveniment)==false || oraEvenimentValida(oraEveniment)==false || locatieEveniment.isEmpty() || pretEvenimentValid(pretEveniment)==false) {
            JOptionPane.showMessageDialog(this, "Introduceti toate datele corespunzatoare evenimentului", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String fileName = "dateEvenimente.txt";
        try (FileWriter fw = new FileWriter(fileName, true)) {
            fw.write(numeEveniment + "$" + dataEveniment + "$" + oraEveniment + "$" + locatieEveniment + "$" + pretEveniment + "\n");
            fw.flush(); // Ensure data is written immediately
            JOptionPane.showMessageDialog(this, "Datele evenimentului au fost salvate cu succes.", "Succes", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "A aparut o eroare la inregistrarea datelor.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void adaugaConstrangeri(JComponent c,int linie, int col, int latime, int inaltime, int anchor, int fill, int spatiuOX, int spatiuOY) {
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
