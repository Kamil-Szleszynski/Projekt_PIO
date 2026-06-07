public class Pracownik {
    private String imie;
    private String nazwisko;
    private String id;
    public Pracownik(String imie,String nazwisko,String id){
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.id = id;
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
}
