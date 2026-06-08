import java.util.ArrayList;
import java.util.List;

public class Biuro {
    private Lokalizacja lokalizacja;
    private List<Object> sale;

    public Biuro(Lokalizacja lokalizacja) {
        this.lokalizacja = lokalizacja;
        this.sale = new ArrayList<>();
    }

    public Lokalizacja getLokalizacja() {
        return lokalizacja;
    }

    public List<Object> getSale() {
        return sale;
    }

    public void dodajSale(Object sala) {
        this.sale.add(sala);
    }
}