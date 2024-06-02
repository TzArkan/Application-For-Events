package try3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class UtilizatorAbonamente extends JFrame {
 private JTable t;
 private DefaultTableModel tabel;
 private JButton b1,b2,b3;
 private JFrame parentFrame;
 private JPanel dedicatButoane,capeteTabelPanel,checkboxPanel,checkboxPanel0,PanouTabelCheckboxCombinat;
 private JCheckBox selectAllCheckbox;
 
 private void stergeLinie(int numarLinie, String username) {
    File fisier = new File(username+"Evenimente.txt");
    try (BufferedReader reader = new BufferedReader(new FileReader(fisier))) {
        List<String> lines = new ArrayList<>();
        String linie;
        int numarLinieCurent = 0;

        linie = reader.readLine();  // ca sa introduca prima linie cu numele categoriilor, fara sa verifice nimic
        lines.add(linie);
        
        
        while ((linie = reader.readLine()) != null) {
            if (numarLinieCurent != numarLinie) {
                lines.add(linie);
            }
            numarLinieCurent++;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fisier))) {
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

public void cumparaBilet(int linie, String username) {
    File fisier1 = new File(username + "Evenimente.txt");

    try (BufferedReader reader = new BufferedReader(new FileReader(fisier1))) {
        StringBuilder fileContent = new StringBuilder();
        String line;
        
        // Read the first line and append it to fileContent
        line = reader.readLine();
        fileContent.append(line).append("\n");
        int budget = Integer.parseInt(line.trim());

        // Read the second line (the budget line)
        line = reader.readLine();
        fileContent.append(line).append("\n");
        
        // Initialize line counter
        int currentLine = 1;
        
        while ((line = reader.readLine()) != null) {
            currentLine++;
            
            if (currentLine == linie + 2) {  // Adjust for the offset (first two lines)
                String[] parts = line.split("\\$");
                
                // Update budget by subtracting the s[5] value
                int cost = Integer.parseInt(parts[5].trim());
                budget -= cost;
                // Update s[6] and s[7]
                int nrBileteDisponibile = Integer.parseInt(parts[6].trim());
                int nrBileteVandute = Integer.parseInt(parts[7].trim());
                int nrBileteAchizitionate = Integer.parseInt(parts[8].trim());
                if(nrBileteDisponibile<=0) throw new IOException("BileteIndisponibile");
                if(budget<=0) throw new IOException("Buget insuficient");
                nrBileteDisponibile--;
                nrBileteVandute++;
                nrBileteAchizitionate++;
                
                // Construct the new line
                String newLine = parts[0] + "$" + parts[1] + "$" + parts[2] + "$" + parts[3] + "$" + parts[4] + "$" + parts[5] + "$" + nrBileteDisponibile + "$" + nrBileteVandute + "$" + nrBileteAchizitionate;
                fileContent.append(newLine).append("\n");
            } else {
                fileContent.append(line).append("\n");
            }
        }
        
        fileContent.delete(0, fileContent.indexOf("\n") + 1);
        fileContent.insert(0, budget + "\n");
        String finalContent = fileContent.toString();

        // Write the updated content back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fisier1))) {
            writer.write(finalContent);
            JOptionPane.showMessageDialog(null, "Bilet cumparat", "Succes", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "A aparut o eroare la inregistrarea datelor.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "A aparut o eroare la cumparare", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Formatul numerelor din fisier este incorect.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

 public void incarcaDinFisier(String username) {
    File fisier = new File(username+"Evenimente.txt");
    tabel.setRowCount(0); 
    try (BufferedReader reader = new BufferedReader(new FileReader(fisier))) {
        String line;
        line = reader.readLine();   // ca sa sara peste prima linie, care e pentru categorii, citim prina linie inainte de while
        line = reader.readLine();   // sa sara si peste a doua, ca are banii acolo
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\\$");     // \\$ ca sa ia caracterul $
            
                tabel.addRow(parts); // adauga date in tabel
            
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

 public UtilizatorAbonamente(JFrame parentFrame, String username, String rol){
    
    super("Gestionarea abonamentelor: "+username);
    setLayout(new BorderLayout());
    tabel=new DefaultTableModel(50,9);
    incarcaDinFisier(username);

    String[] NumeColoane = {"Categorie eveniment", "Nume eveniment", "Data eveniment", "Ora eveniment", "Locatie eveniment","Pret bilet eveniment","Numar bilete disponibile","Numar bilete vandute","Bilet achizitionat"};
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

    b1=new JButton("Dezaboneaza-ma");
    b1.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            int rowCount = tabel.getRowCount();
            for(int linie = rowCount - 1; linie >= 0; linie--) {
                JCheckBox checkBox = (JCheckBox)checkboxPanel.getComponent(linie);
                if(checkBox.isSelected()) { //verifica daca checkboxu de pe linie e selectat
                    
                    // sterge linia din fisierul text, corespunzatoare liniei din tabel
                    stergeLinie(linie,username);
                    // sterge checkboxu corespunzator din tabel
                    checkboxPanel.remove(linie);
                    // daca e selectat, sterge randul din tabel
                    tabel.removeRow(linie);
                }
            }
        }
    });
    dedicatButoane.add(b1);

    b3=new JButton("Cumpara bilet");
    b3.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            int rowCount = tabel.getRowCount();
            for(int linie = rowCount - 1; linie >= 0; linie--) {
                    JCheckBox checkBox = (JCheckBox)checkboxPanel.getComponent(linie);
                    if(checkBox.isSelected()) { //verifica daca checkboxu de pe linie e selectat
                        cumparaBilet(linie,username);
                    
                    }
            }
        }
        });
    dedicatButoane.add(b3);

    b2=new JButton("Inapoi");
    b2.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            parentFrame.setVisible(true);
            dispose();
        }
    });
    dedicatButoane.add(b2);
    this.add(dedicatButoane, BorderLayout.SOUTH);


}
}
