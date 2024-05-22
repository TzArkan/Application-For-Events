package try3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;
import javax.swing.table.*;

public class GestiuneEvenimenteCurente extends JFrame{
 private JTable t;
 private DefaultTableModel model;
 
 
 public GestiuneEvenimenteCurente(JFrame parentFrame){
    
    super("Gestionarea evenimentelor curente");
    model=new DefaultTableModel(10,6);
    t=new JTable(model);
    String fisierEvenimente="dateEvenimente.txt";
    try (BufferedReader reader = new BufferedReader(new FileReader(fisierEvenimente))) {
            String line;
            int linie=0;
            while ((line = reader.readLine()) != null) {
                // "\\$" pentru a lua $ ca delimitator
                String[] parts = line.split("\\$");
                if (parts.length == 6) {
                    for(int coloana=0;coloana<6;coloana++)
                    model.setValueAt(parts[coloana], linie, coloana);    
                }
                linie++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    var jp=new JScrollPane(t);
    add(jp);
 }
}