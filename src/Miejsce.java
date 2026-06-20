public class Miejsce {
    private int numerMiejsca;
    private boolean zajete;
    private String rezerwacjaID;
    Miejsce(int numerMiejsca){
        this.zajete = false;
        this.numerMiejsca = numerMiejsca;
    }


    public int getNumerMiejsca() {
        return numerMiejsca;
    }

    public boolean isZajete() {
        return zajete;
    }

    public String getRezerwacjaID() {
        return rezerwacjaID;
    }

    public void setRezerwacjaID(String rezerwacjaID) {
        this.rezerwacjaID = rezerwacjaID;
        this.zajete = true;
    }
}
