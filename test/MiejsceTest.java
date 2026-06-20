import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MiejsceTest {
    @Test
    void sprawdzanieKontrsuktora(){
        Miejsce miejsce = new Miejsce(10);

        assertEquals(10,miejsce.getNumerMiejsca());
    }

    @Test
    void sprawdzenieGet(){
        Miejsce miejsce = new Miejsce(12);
        assertEquals(null,miejsce.getRezerwacjaID());
        assertEquals(false,miejsce.isZajete());

        miejsce.setRezerwacjaID("2");

        assertEquals("2",miejsce.getRezerwacjaID());
        assertEquals(true,miejsce.isZajete());

    }
    @Test
    void sprawdzenieUswaniaRezerwacji(){
        Miejsce miejsce = new Miejsce(12);
        miejsce.setRezerwacjaID("2");
        miejsce.usunRezerwacje();
        assertEquals(null,miejsce.getRezerwacjaID());
        assertEquals(false,miejsce.isZajete());

    }
}