package try3;

public class DateUtilizator {
    private String numeUtilizator;
    private String emailUtilizator;
    private String parolaUtilizator;

    public DateUtilizator(String numeUtilizator,String emailUtilizator,String parolaUtilizator){
        this.numeUtilizator=numeUtilizator;
        this.emailUtilizator=emailUtilizator;
        this.parolaUtilizator=parolaUtilizator;
    }

    public String getNume(){return numeUtilizator;}
    public String getEmail(){return emailUtilizator;}
    public String getParola(){return parolaUtilizator;}
}
