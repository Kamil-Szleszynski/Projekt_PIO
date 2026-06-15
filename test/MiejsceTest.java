import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MiejsceTest {
    @Test
    void sprawdzanieKontrsuktora(){
        Miejsce miejsce = new Miejsce(10);

        assertEquals(10,miejsce.getNumerMiejsca());
    }
}