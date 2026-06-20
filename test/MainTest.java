import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private Main aplikacja;
    private File plikBazy;
    LocalDateTime data = LocalDateTime.of(2026, 6, 15, 14, 30);
    Spotkanie spotkanieTest = new Spotkanie(data, "Daily Scrum");
    @BeforeEach
    void setUp() {
        aplikacja = new Main();
        aplikacja.listaUzytkownikow = new HashMap<>(); // Ręcznie tworzymy czystą mapę
        plikBazy = new File("uzytkownicy.txt");

    }

    @AfterEach
    void tearDown() {
        if (plikBazy.exists()) {
            plikBazy.delete();
        }
    }

    @Test
    void testDodawaniaNowegoUzytkownika() {
        String udawaneWpisywanie = "Jan\nNowak\ntajne123\n105\n";
        System.setIn(new ByteArrayInputStream(udawaneWpisywanie.getBytes()));
        Scanner testowyScanner = new Scanner(System.in);
        Pracownik stworzony = aplikacja.tworzenieNowegoUzytkonika(testowyScanner);
        assertNotNull(stworzony);
        assertEquals("Jan", stworzony.getImie());
        assertEquals("105", stworzony.getId());
        assertTrue(aplikacja.listaUzytkownikow.containsKey("105"));
    }

    @Test
    void testTworzeniaPliku() {
        if (plikBazy.exists()) {
            plikBazy.delete();
        }
        aplikacja.tworzeniePlikuZPracownikami();
        assertTrue(plikBazy.exists(), "Plik 'uzytkownicy.txt' powinien zostać utworzony");
    }

    @Test
    void testNieistniejącyUżytkownik(){
        String simulatedInput = "999" + System.lineSeparator() + "quit" + System.lineSeparator();
        ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        Scanner testScanner = new Scanner(in);

        aplikacja.dodawanieUczestnikow(testScanner, spotkanieTest);

        assertTrue(spotkanieTest.getUczestnicy().isEmpty(),
                "Lista uczestników powinna być pusta po podaniu nieistniejącego ID.");
    }
}