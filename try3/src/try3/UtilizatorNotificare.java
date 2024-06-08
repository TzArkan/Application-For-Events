package try3;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class UtilizatorNotificare extends JFrame {

    private JFrame fereastraVeche;
    private JLabel l1;
    private JPanel p1;
    public ArrayList<DateEveniment> evenimente;

    public UtilizatorNotificare(JFrame fereastraVeche, String username){
        super("Notificari utilizator: "+username);
        File numeFisierUtilizator = new File(username+"Evenimente.txt");
        
        try {
            var br = new BufferedReader(new FileReader(numeFisierUtilizator));
            var linie = "";
            DateEveniment eveniment;
            String[] s;

            br.readLine();
            br.readLine();

            // Obține o instanță a formatului scurt de dată
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
           // Obține data curentă
            Date currentDate = new Date();
            // Formatează data folosind formatul scurt de dată
            String formattedDate = sdf.format(currentDate);

            p1 = new JPanel(new GridLayout(0, 1));
            l1 = new JLabel("Evenimentele de astazi, " + formattedDate + ": ");
            p1.add(l1);

            while ((linie = br.readLine()) != null) {
                s = linie.split("[$]");
                if (s.length >= 3) {
                    try {
                        Date eventDate = sdf.parse(s[3]);
                       // Compară data evenimentului cu data curentă
                        if (sdf.format(eventDate).equals(formattedDate)) {
                            String evenAzi="Evenimentul "+s[2]+" din categoria "+s[1]+" va avea loc astazi la ora "+s[4];
                            //Teatru$cuccuc$22/22/2022$22:22$asd$2313$0$0
                            JLabel eventLabel = new JLabel(evenAzi);
                            p1.add(eventLabel);
                        }
                    } catch (ParseException e) {
                        // Gestionare eroare de parsare dacă data nu este în formatul corect
                        System.err.println("Date format is incorrect for line: " + linie);
                    }
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        add(p1, BorderLayout.CENTER);
    }
}
