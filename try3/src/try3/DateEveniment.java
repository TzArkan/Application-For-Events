package try3;

public class DateEveniment{
    private String numeEvenimente;
    private String dataEvenimente;
    private String oraEveniment;
    private String locatieEveniment;
    private String pretEveniment;
    private String numarBileteEveniment;

    public DateEveniment(String numeEvenimente,String dataEvenimente,String oraEveniment,String locatieEveniment,String pretEveniment,String numarBileteEveniment){
        this.numeEvenimente=numeEvenimente;
        this.dataEvenimente=dataEvenimente;
        this.oraEveniment=oraEveniment;
        this.locatieEveniment=locatieEveniment;
        this.pretEveniment=pretEveniment;
        this.numarBileteEveniment=numarBileteEveniment;
    }
    
    public String getNume(){ return numeEvenimente;}
    public String getData(){ return dataEvenimente;}
    public String getOra(){ return oraEveniment;}
    public String getLocatie(){ return locatieEveniment;}
    public String getPret(){ return pretEveniment;}
    public String getNrBilete(){ return numarBileteEveniment;}
    


    
}
