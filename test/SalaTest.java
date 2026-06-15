import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SalaTest {
    @Test
    void sprawdzanieKontrsuktora(){
        Sala sala = new Sala("F104",30);

        assertEquals("F104",sala.getNumerSali());
        assertEquals(30,sala.getLiczbaMiejsc());
    }
}