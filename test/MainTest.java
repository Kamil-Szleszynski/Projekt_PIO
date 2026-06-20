import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.PrintWriter;
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
    @Test
    void testUczestnikOK_UczestnikwSpotkaniu() {
        try (PrintWriter writer = new PrintWriter(plikBazy)) {
            writer.println("Jan|Kowalski|101|haslo1");
        } catch (Exception e) {
            fail("Nie udało się przygotować pliku bazy do testu");
        }

        aplikacja.getFromFileListaUzytkownikow();
        String simulatedInput = "101" + System.lineSeparator() +
                "101" + System.lineSeparator() +
                "quit" + System.lineSeparator();
        ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        Scanner testScanner = new Scanner(in);

        aplikacja.dodawanieUczestnikow(testScanner, spotkanieTest);
        assertEquals(1, spotkanieTest.getUczestnicy().size(),
                "Spotkanie powinno mieć dokładnie 1 uczestnika.");

        Pracownik oczekiwanyPracownik = aplikacja.listaUzytkownikow.get("101");
        assertTrue(spotkanieTest.getUczestnicy().contains(oczekiwanyPracownik),
                "Jan powinien znajdować się na liście uczestników.");
    }
}