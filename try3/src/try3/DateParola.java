package try3;

public class DateParola {
    private String parolaVeche;
    private String parolaNoua;
    private String parolaNReintrodusa;
    public DateParola(String parolaVeche, String parolaNoua, String parolaNReintrodusa){
        this.parolaVeche=parolaVeche;
        this.parolaNoua=parolaNoua;
        this.parolaNReintrodusa=parolaNReintrodusa;
    }
    public String getParolaVeche(){return parolaVeche;}
    public String getParolaNoua(){return parolaNoua;}
    public String getParolaNReintrodusa(){return parolaNReintrodusa;}
}
