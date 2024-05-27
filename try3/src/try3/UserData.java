package try3;

public class UserData{
    private String numeEvenimente;
    private String dataEvenimente;
    private String oraEveniment;
    private String locatieEveniment;
    private String pretEveniment;
    private String numarBileteEveniment;

    public UserData(String numeEvenimente,String dataEvenimente,String ora,String locatie,String pret,String nrBilete){
        this.numeEvenimente=numeEvenimente;
        this.dataEvenimente=dataEvenimente;
        oraEveniment=ora;
        locatieEveniment=locatie;
        pretEveniment=pret;
        numarBileteEveniment=nrBilete;
    }
    
    public String getNume(){ return numeEvenimente;}
    public String getData(){ return dataEvenimente;}
    public String getOra(){ return oraEveniment;}
    public String getLocatie(){ return locatieEveniment;}
    public String getPret(){ return pretEveniment;}
    public String getNrBilete(){ return numarBileteEveniment;}
    


    
}
