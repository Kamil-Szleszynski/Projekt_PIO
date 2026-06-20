import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Spotkanie {
    private LocalDateTime czasSpotkania;
    private String nazwaSpotkania;
    private Sala sala;
    private Set<Pracownik> uczestnicy = new HashSet<>();
    private Pracownik organizator;
    private ArrayList<Miejsce> miejsca = new ArrayList<>();
    Spotkanie(LocalDateTime czasSpotkania,String nazwaSpotkania){
        this.czasSpotkania = czasSpotkania;
        this.nazwaSpotkania = nazwaSpotkania;
    }
    LocalDateTime getCzasSpotkania(){
        return czasSpotkania;
    }

    String getNazwaSpotkania(){
        return nazwaSpotkania;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
        int liczbaMiejsc = sala.getLiczbaMiejsc();
        for(int i = 0; i < liczbaMiejsc;i++){
            Miejsce miejsceDoDodania = new Miejsce(i+1);
            miejsca.add(miejsceDoDodania);
        }

    }

    public void setOrganizator(Pracownik organizator){
        this.organizator = organizator;
    }

    public Pracownik getOrganizator(){
        return organizator;
    }


    public Sala getSala() {
        return sala;
    }

    public String getGodzinaRozpoczecia() {
        if (this.czasSpotkania == null) return "";
        return String.format("%02d:%02d", czasSpotkania.getHour(), czasSpotkania.getMinute());
    }

    public boolean addUczestnik(Pracownik uczestnik){
        if(!uczestnicy.add(uczestnik)){
            return false;
        }else{
            return true;
        }
    }

    public Set<Pracownik> getUczestnicy() {
        return uczestnicy;
    }

    public ArrayList<Miejsce> getMiejsca() {
        return miejsca;
    }
}
