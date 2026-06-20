import java.util.Objects;

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

    public void usunRezerwacje(){
        this.rezerwacjaID = null;
        this.zajete = false;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Miejsce miejsce = (Miejsce) o;
        return getNumerMiejsca() == miejsce.getNumerMiejsca();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getNumerMiejsca());
    }
}
