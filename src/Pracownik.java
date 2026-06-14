import java.io.File;
import java.util.HashMap;
import java.util.Map;
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
