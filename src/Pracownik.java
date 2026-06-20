import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Pracownik {
    private String imie;
    private String nazwisko;
    private String id;
    private String haslo;
    public Pracownik(String imie,String nazwisko,String id,String haslo){
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.id = id;
        this.haslo = haslo;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pracownik pracownik = (Pracownik) o;
        return Objects.equals(getId(), pracownik.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    public String getImie(){
        return imie;
    }
    public String getNazwisko(){
        return nazwisko;
    }
    public String getId(){
        return id;
    }
    public String getHaslo(){
        return haslo;
    }
}
