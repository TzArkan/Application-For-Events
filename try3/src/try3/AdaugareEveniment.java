/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package try3;


import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AdaugareEveniment extends JFrame{
    private JLabel[] l;
    private JTextField[] t;
    private JCheckBox c;
    private JButton b1,b2, b3;
    private GridBagConstraints gbc;
    private JPanel p;
    public AdaugareEveniment(JFrame parentFrame){
        super("Adaugare de Evenimente");
        String[] etichete={"IDProdus", "UPC", "Cantitate", "Pret", "Total", "Platit", "Rest de bani"};
        l=new JLabel[7];
        t=new JTextField[7];
              for(int i=0; i<7; i++) {
            l[i]=new JLabel(etichete[i]);
            t[i]=new JTextField(10);
        }
                
        c=new JCheckBox("Este un produs sezonier? Da/Nu", false);
        GridBagLayout gbl=new GridBagLayout();
        gbc=new GridBagConstraints();
         p=new JPanel(gbl);
         
        adaugaConstrangeri(l[0],0, 0, 1, 1, GridBagConstraints.WEST, 0, 10,0);
        adaugaConstrangeri(t[0],0, 1, 1, 1, GridBagConstraints.CENTER, 0, 10, 0);
        adaugaConstrangeri(l[1],0, 2, 1, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH, 10,0);
        adaugaConstrangeri(t[1],0, 3, 1, 1, GridBagConstraints.CENTER, 0, 10, 0);
        adaugaConstrangeri(c,1, 0, 2, 1, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,10,0);
        adaugaConstrangeri(l[2],1, 2, 1, 1, GridBagConstraints.WEST, 0,10,0);
         adaugaConstrangeri(t[2],1, 3, 1, 1, GridBagConstraints.CENTER, 0,10,0);
         adaugaConstrangeri(l[3],2, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH,10,0);
        adaugaConstrangeri(t[3],2, 1, 1, 1, GridBagConstraints.CENTER, 0,10,0);
        adaugaConstrangeri(l[4],2, 2, 1, 1, GridBagConstraints.WEST, 0,10,0);
        adaugaConstrangeri(t[4],2, 3, 1, 1, GridBagConstraints.CENTER, 0,10,0);
        adaugaConstrangeri(l[5],3, 0, 1, 1, GridBagConstraints.WEST, 0,10,0);
        adaugaConstrangeri(t[5],3, 1, 1, 1, GridBagConstraints.CENTER, 0,10,0);
        adaugaConstrangeri(l[6],3, 2, 1, 1, GridBagConstraints.EAST, GridBagConstraints.BOTH,10,0);
        adaugaConstrangeri(t[6],3, 3, 1, 1, GridBagConstraints.CENTER, 0,10,0);
        b1=new JButton("Another product");
        b2=new JButton("OK");
        b3=new JButton("Payment");
        
        add(p);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                parentFrame.setVisible(true);
            }
        });
        p=new JPanel(gbl);
        adaugaConstrangeri(b1, 0,0,1,1, GridBagConstraints.CENTER, GridBagConstraints.NONE,20,0);
         adaugaConstrangeri(b2, 0,1,1,1, GridBagConstraints.CENTER, GridBagConstraints.NONE,20,0);
          adaugaConstrangeri(b3, 0,2,1,1, GridBagConstraints.CENTER, GridBagConstraints.NONE,20,0);
          
          add(p, BorderLayout.SOUTH);
        
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
