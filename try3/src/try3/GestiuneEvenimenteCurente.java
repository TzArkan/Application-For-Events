package try3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class GestiuneEvenimenteCurente extends JFrame{
 private JTable t;
 private DefaultTableModel tabel;
 private JButton b1,b2,b3;
 private JFrame parentFrame;
 private JPanel dedicatButoane,capeteTabelPanel,checkboxPanel,checkboxPanel0,PanouTabelCheckboxCombinat;
 private JCheckBox selectAllCheckbox;
 
 private void stergeLinie(int numarLinie) {
    String numeFisier = "dateEvenimente.txt";
    try (BufferedReader reader = new BufferedReader(new FileReader(numeFisier))) {
        List<String> lines = new ArrayList<>();
        String linie;
        int numarLinieCurent = 0;
        while ((linie = reader.readLine()) != null) {
            if (numarLinieCurent != numarLinie) {
                lines.add(linie);
            }
            numarLinieCurent++;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(numeFisier))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}


public void stergereEveniment(){
    int rowCount = tabel.getRowCount();
    for(int linie = rowCount - 1; linie >= 0; linie--) {
        JCheckBox checkBox = (JCheckBox)checkboxPanel.getComponent(linie);
        if(checkBox.isSelected()) { //verifica daca checkboxu de pe linie e selectat
            // daca e selectat, sterge randul din tabel
            tabel.removeRow(linie);
            // sterge linia din fisierul text, corespunzatoare liniei din tabel
            stergeLinie(linie);
            // sterge checkboxu corespunzator din tabel
            checkboxPanel.remove(linie);
        }
    }
    // reface partea de checkbox din tabel
    checkboxPanel.revalidate();
    checkboxPanel.repaint();
}

 public void loadDataFromFile() {
    tabel.setRowCount(0); 
    try (BufferedReader reader = new BufferedReader(new FileReader("dateEvenimente.txt"))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\\$");     // \\$ ca sa ia caracterul $
            
                tabel.addRow(parts); // adauga date in tabel
            
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
 
 public GestiuneEvenimenteCurente(JFrame parentFrame){
    
    super("Gestionarea evenimentelor curente");
    setLayout(new BorderLayout());
    tabel=new DefaultTableModel(50,8);
    loadDataFromFile();

    String[] NumeColoane = {"Categorie eveniment", "Nume eveniment", "Data eveniment", "Ora eveniment", "Locatie eveniment","Pret bilet eveniment","Numar bilete disponibile","Numar bilete vandute"};
    tabel.setColumnIdentifiers(NumeColoane);

    t=new JTable(tabel);
    t.setRowHeight(20);
    int inaltimeLinie = t.getRowHeight();
    checkboxPanel = new JPanel();
    checkboxPanel.setLayout(new GridBagLayout()); 
    
    GridBagConstraints gbc=new GridBagConstraints();
    gbc.anchor=GridBagConstraints.WEST;
    
    for (int i = 0; i < tabel.getRowCount(); i++) {
            JCheckBox checkBox = new JCheckBox();
            checkBox.setPreferredSize(new Dimension(20, inaltimeLinie));
            gbc.gridy=i;
            checkboxPanel.add(checkBox,gbc);
        }

    
    
    
    PanouTabelCheckboxCombinat= new JPanel();
    PanouTabelCheckboxCombinat.setLayout(new BorderLayout());

    capeteTabelPanel = new JPanel(new BorderLayout());
    selectAllCheckbox = new JCheckBox();
    capeteTabelPanel.add(selectAllCheckbox, BorderLayout.WEST);
    capeteTabelPanel.add(t.getTableHeader(), BorderLayout.CENTER);

    checkboxPanel0 = new JPanel();
    checkboxPanel0.add(checkboxPanel,BorderLayout.NORTH);    
    PanouTabelCheckboxCombinat.add(capeteTabelPanel,BorderLayout.NORTH);
    PanouTabelCheckboxCombinat.add(checkboxPanel0,BorderLayout.WEST);
    PanouTabelCheckboxCombinat.add(t,BorderLayout.CENTER);
    
    JScrollPane scroll=new JScrollPane(PanouTabelCheckboxCombinat);
    
    
    add(scroll,BorderLayout.CENTER);

    
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    selectAllCheckbox.addItemListener(new ItemListener() {
        
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                // selectarea tuturor checkboxurilor
                for (int i = 0; i < checkboxPanel.getComponentCount(); i++) {
                    JCheckBox checkBox = (JCheckBox) checkboxPanel.getComponent(i);
                    checkBox.setSelected(true);
                }
            } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                // deselectarea tuturor checkboxurilor
                for (int i = 0; i < checkboxPanel.getComponentCount(); i++) {
                    JCheckBox checkBox = (JCheckBox) checkboxPanel.getComponent(i);
                    checkBox.setSelected(false);
                }
            }
        }
    });
    
    
    dedicatButoane=new JPanel();
    b1=new JButton("Modifica eveniment");
    b1.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            modificareEveniment();
        }
    });
    dedicatButoane.add(b1);

    b2=new JButton("Sterge eveniment");
    b2.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            stergereEveniment();
        }
    });
    dedicatButoane.add(b2);
    
    b3=new JButton("Inapoi");
    b3.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            parentFrame.setVisible(true);
            dispose();
        }
    });
    dedicatButoane.add(b3);
    this.add(dedicatButoane, BorderLayout.SOUTH);

}

 
}


    