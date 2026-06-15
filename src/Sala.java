public class Sala {

    private String numerSali;
    private int liczbaMiejsc;

    Sala(String numerSali, int liczbaMiejsc){
        this.liczbaMiejsc = liczbaMiejsc;
        this.numerSali = numerSali;
    }


    public String getNumerSali(){
        return numerSali;
    }

    public int getLiczbaMiejsc(){
        return liczbaMiejsc;
    }
}
