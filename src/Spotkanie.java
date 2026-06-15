import java.sql.Time;
import java.time.LocalDateTime;

public class Spotkanie {
    private LocalDateTime czasSpotkania;
    private String nazwaSpotkania;
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
}
