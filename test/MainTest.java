import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private Main aplikacja;
    private File plikBazy;
    private List<Sala> dostepneSale;
    private Spotkanie spotkanieTest;
    private Pracownik pracownik;
    private Sala E1;
    LocalDateTime data = LocalDateTime.of(2026, 6, 15, 14, 30);

    @BeforeEach
    void setUp() {
        aplikacja = new Main();
        aplikacja.listaUzytkownikow = new HashMap<>(); // Ręcznie tworzymy czystą mapę
        plikBazy = new File("uzytkownicy.txt");
        E1 = new Sala("E1",30);
        spotkanieTest = new Spotkanie(data, "Daily Scrum");
        dostepneSale = new ArrayList<>();
        dostepneSale.add(E1);
        dostepneSale.add(new Sala("B-202", 10));
        pracownik = new Pracownik("Jan", "Nowak", "1","1241");
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
    @Test
    void testMiejsceWolne() {
        String simulatedInput = "3\nquit\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        Scanner scanner = new Scanner(System.in);

        spotkanieTest.setSala(E1);

        aplikacja.rezerwujMiejsce(spotkanieTest, scanner, pracownik);

        Miejsce trzecieMiejsce = spotkanieTest.getMiejsca().get(2);
        assertTrue(trzecieMiejsce.isZajete(), "Miejsce 3 powinno być zajete");
        assertEquals("1", trzecieMiejsce.getRezerwacjaID(), "Id rezerwacji nie zgadza się z użytkownikiem");
        assertTrue(spotkanieTest.getUczestnicy().contains(pracownik), "Pracownik powinien być uczestnikiem");
    }

    @Test
    void testMiejsceZajete() {
        spotkanieTest.setSala(E1);
        String inputAnna = "2\nquit\n";
        System.setIn(new ByteArrayInputStream(inputAnna.getBytes()));
        Scanner scanner1 = new Scanner(System.in);

        Pracownik pracownik2 = new Pracownik("Anna", "Nowak", "3", "hasloAnna");
        aplikacja.rezerwujMiejsce(spotkanieTest, scanner1, pracownik2);

        Miejsce drugieMiejsce = spotkanieTest.getMiejsca().get(1);
        assertTrue(drugieMiejsce.isZajete());

        String inputPracownik = "2\n4\nquit\n";
        System.setIn(new ByteArrayInputStream(inputPracownik.getBytes()));
        Scanner scanner2 = new Scanner(System.in);

        aplikacja.rezerwujMiejsce(spotkanieTest, scanner2, pracownik);
        Miejsce czwarteMiejsce = spotkanieTest.getMiejsca().get(3);

        assertEquals(pracownik2.getId(), drugieMiejsce.getRezerwacjaID(), "MIejsce  powinno byc zerezerwowanie");

        assertTrue(czwarteMiejsce.isZajete(), "pracownik powinien rezerwoac miejsce nr 4");
        assertEquals(pracownik.getId(), czwarteMiejsce.getRezerwacjaID(), "MIejsce 4 nie zgadza sie z ID pracownika");
    }

    @Test
    void testZlyInput() {
        String simulatedInput = "abc\n99\n1\nquit\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        Scanner scanner = new Scanner(System.in);
        spotkanieTest.setSala(E1);
        assertDoesNotThrow(() -> {
            aplikacja.rezerwujMiejsce(spotkanieTest, scanner, pracownik);
        });

        Miejsce pierwszeMiejsce = spotkanieTest.getMiejsca().get(0);
        assertTrue(pierwszeMiejsce.isZajete());
    }
    @Test
    void testEtap4_SymulacjaInterakcjiZUserem() {

        String userInput = "Daily\n2026-06-22 09:00\n1\n";
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        Scanner scanner = new Scanner(in);

        String nazwa = scanner.nextLine();
        String dataInput = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dataCzas = LocalDateTime.parse(dataInput, formatter);

        int wyborSali = scanner.nextInt();
        Sala wybranaSala = dostepneSale.get(wyborSali - 1);

        Spotkanie spotkanie = new Spotkanie(dataCzas, nazwa);
        spotkanie.setSala(wybranaSala);
        spotkanie.setOrganizator(pracownik);

        assertEquals("Daily", spotkanie.getNazwaSpotkania());
        assertEquals(LocalDateTime.of(2026, 6, 22, 9, 0), spotkanie.getCzasSpotkania());
        assertEquals("E1", spotkanie.getSala().getNumerSali());
        assertEquals(30, spotkanie.getMiejsca().size());
    }

}