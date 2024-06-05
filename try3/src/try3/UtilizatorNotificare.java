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

            // Get the short date format instance
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            // Get the current date
            Date currentDate = new Date();
            // Format the date using the short date format
            String formattedDate = sdf.format(currentDate);

            p1 = new JPanel(new GridLayout(0, 1));
            l1 = new JLabel("Evenimentele de astazi, " + formattedDate + ": ");
            p1.add(l1);

            while ((linie = br.readLine()) != null) {
                s = linie.split("[$]");
                if (s.length >= 3) {
                    try {
                        Date eventDate = sdf.parse(s[3]);
                        // Compare the event date with the current date
                        if (sdf.format(eventDate).equals(formattedDate)) {
                            String evenAzi="Evenimentul "+s[2]+" din categoria "+s[1]+" va avea loc astazi la ora "+s[4];
                            //Teatru$cuccuc$22/22/2022$22:22$asd$2313$0$0
                            JLabel eventLabel = new JLabel(evenAzi);
                            p1.add(eventLabel);
                        }
                    } catch (ParseException e) {
                        // Handle parsing error if the date is not in the correct format
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
