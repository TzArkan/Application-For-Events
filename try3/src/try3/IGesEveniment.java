package try3;

import javax.swing.JComponent;

public interface IGesEveniment {
    public boolean oraEvenimentValida(String ora);
    public boolean dataEvenimentValida(String dataEveniment);
    public boolean pretEvenimentValid(String s);
    public boolean numarBilete(String s);
    public void adaugaConstrangeri(JComponent c, int linie, int col, int latime, int inaltime, int anchor, int fill, int spatiuOX, int spatiuOY);
    
}
