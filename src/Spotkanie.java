import java.sql.Time;
import java.time.LocalDateTime;

public class Spotkanie {
    private LocalDateTime czasSpotkania;
    private String nazwaSpotkania;
    private Sala sala;
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
    }

    public Sala getSala() {
        return sala;
    }

    public String getGodzinaRozpoczecia() {
        if (this.czasSpotkania == null) return "";
        return String.format("%02d:%02d", czasSpotkania.getHour(), czasSpotkania.getMinute());
    }
}
