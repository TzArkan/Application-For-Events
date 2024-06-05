package try3;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class UtilizatorBilete extends JFrame {

    private JFrame fereastraVeche;
    private JPanel p1;
    public ArrayList<DateEveniment> evenimente;

    public UtilizatorBilete(JFrame fereastraVeche, String username, String rol) {
        super("Bilete");
        if (rol.equals("Utilizator"))
        {
        File numeFisierUtilizator = new File("bileteEvenimente.txt");
        p1 = new JPanel(new GridLayout(0, 1));
        JLabel l1 = new JLabel("Biletele dumneavoastra: "+username);
        p1.add(l1);

        try (BufferedReader br = new BufferedReader(new FileReader(numeFisierUtilizator))) {
            String linie;
            while ((linie = br.readLine()) != null) {
                String[] segments = linie.split("[$]");
                if (segments[0].equals(username)) {
                    JLabel afisareBilet = new JLabel(linie);
                    p1.add(afisareBilet);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    else {
        File numeFisierUtilizator = new File("bileteEvenimente.txt");
        p1 = new JPanel(new GridLayout(0, 1));
        JLabel l1 = new JLabel("Biletele utilizatorilor: ");
        p1.add(l1);

        try (BufferedReader br = new BufferedReader(new FileReader(numeFisierUtilizator))) {
            String linie;
            while ((linie = br.readLine()) != null) {
                String[] segments = linie.split("[$]");
                
                    JLabel afisareBilet = new JLabel(linie);
                    p1.add(afisareBilet);
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        add(p1, BorderLayout.CENTER);
        pack();
        setVisible(true);
    }
}

