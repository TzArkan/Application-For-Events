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

public class GestionareEvenCateg extends JFrame {
 private JTable t;// Tabel pentru afișarea evenimentelor
 private DefaultTableModel tabel;// Modelul tabelului
 private JButton b1,b4;// Butoane pentru abonare și întoarcere
 private JFrame parentFrame;// Fereastra părinte
 private JPanel dedicatButoane,capeteTabelPanel,checkboxPanel,checkboxPanel0,PanouTabelCheckboxCombinat;// Panouri pentru organizarea componentelor GUI
 private JCheckBox selectAllCheckbox;// Checkbox pentru selectarea/deselectarea tuturor checkbox-urilor
 
// Metodă pentru abonarea la un eveniment
public void abonareEveniment(Object cod, String username) {
    String numeFisierEvenimente = "dateEvenimente.txt";  
    File numeFisierUtilizator = new File(username + "Evenimente.txt");  
    List<String> lines = new ArrayList<>();
    String linieDeCopiat="";

    try (BufferedReader reader = new BufferedReader(new FileReader(numeFisierEvenimente))) {
        String line;
        int numarLinieCurent = 0;

        // Citirea evenimentului pe baza codului
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\\$");
            if (cod.equals(parts[0])) linieDeCopiat=line; // adauga date in tabel
        }
    } catch (IOException e) {
        e.printStackTrace();
        return;
    }


    // Adăugarea liniei în fișierul utilizatorului
    try (FileWriter fw = new FileWriter(numeFisierUtilizator, true);
         BufferedWriter writer = new BufferedWriter(fw)) {  // Open BufferedWriter in try-with-resources
        writer.write(linieDeCopiat + "$0" + "\n");
        JOptionPane.showMessageDialog(null, "Datele evenimentului au fost salvate cu succes.", "Succes", JOptionPane.INFORMATION_MESSAGE);
    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "A apărut o eroare la înregistrarea datelor.", "Eroare", JOptionPane.ERROR_MESSAGE);
    }
}

// Metodă pentru încărcarea evenimentelor din fișier
public void incarcaDinFisier(String username) {
    tabel.setRowCount(0); // Resetează tabelul
    File fisier = new File(username + "Evenimente.txt");

    try (
        BufferedReader reader2 = new BufferedReader(new FileReader(fisier))
    ) {
        String line, lineCategorii;
        reader2.readLine(); // Sari peste prima linie
        lineCategorii = reader2.readLine(); // Citește categoriile de pe a doua linie

        if (lineCategorii != null && !lineCategorii.trim().isEmpty()) {
            String[] partsCategorii = lineCategorii.split("\\$");

            for (String categorie : partsCategorii) {
                try (BufferedReader reader1 = new BufferedReader(new FileReader("dateEvenimente.txt"))) {
                    // Sari peste prima linie dacă este nevoie
                    reader1.readLine();
                    while ((line = reader1.readLine()) != null) {
                        String[] parts = line.split("\\$");
                        if (parts.length > 1 && categorie.equals(parts[1])) {
                            tabel.addRow(parts); // Adaugă datele în tabel
                        }
                    }
                }
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
// Constructorul clasei
 public GestionareEvenCateg(JFrame parentFrame, String username, String rol){
    
    super("Gestionarea evenimentelor curente");
    setLayout(new BorderLayout());
    tabel=new DefaultTableModel(50,8);
    String[] NumeColoane = {"Cod eveniment","Categorie eveniment", "Nume eveniment", "Data eveniment", "Ora eveniment", "Locatie eveniment","Pret bilet eveniment","Numar bilete disponibile","Numar bilete vandute"};
    tabel.setColumnIdentifiers(NumeColoane);
    incarcaDinFisier(username);

    t=new JTable(tabel);
    t.setRowHeight(20);
    int inaltimeLinie = t.getRowHeight();
    checkboxPanel = new JPanel();
    checkboxPanel.setLayout(new GridBagLayout()); 
    
    GridBagConstraints gbc=new GridBagConstraints();
    gbc.anchor=GridBagConstraints.WEST;
    // Adăugarea checkbox-urilor pentru fiecare linie din tabel
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

     // Buton pentru abonare la eveniment
    b1=new JButton("Aboneaza-ma la eveniment");
    b1.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            int rowCount = tabel.getRowCount();
            for(int linie = rowCount - 1; linie >= 0; linie--) {
                    JCheckBox checkBox = (JCheckBox)checkboxPanel.getComponent(linie);
                    if(checkBox.isSelected()) { //verifica daca checkboxu de pe linie e selectat
                        Object cod = t.getModel().getValueAt(linie, 0);
                        
                        abonareEveniment(cod,username);
                       
                       
                    }
            }
        }
        });
    dedicatButoane.add(b1);

        // Buton pentru întoarcerea la fereastra părinte
    b4=new JButton("Inapoi");
    b4.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            parentFrame.setVisible(true);
            dispose();
        }
    });
    dedicatButoane.add(b4);
    this.add(dedicatButoane, BorderLayout.SOUTH);


}
}
