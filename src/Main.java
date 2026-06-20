import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Main{
    public Map<String ,Pracownik > listaUzytkownikow; //id pracownik(haslo)
    public Scanner scanner;
    private final String FILEUZYTKOWNICY = "uzytkownicy.txt";
    public static void main(String[] args) {
        Main aplikacja = new Main();
        aplikacja.listaUzytkownikow = new HashMap<>();
    }

    public Map<String,Pracownik> getListaUzytkownikow(){
        return listaUzytkownikow;
    }
    public boolean getFromFileListaUzytkownikow(){
        try{
            Scanner scanner = new Scanner(new File(FILEUZYTKOWNICY));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\|");
                Pracownik pracownik = new Pracownik(parts[0],parts[1],parts[2],parts[3]);
                listaUzytkownikow.put(parts[2], pracownik);
            }
            scanner.close();
            return true;
        }
        catch (Exception e){
            System.out.println("Nie znaleziono pliku z uzytkownikami!");
            return false;
        }
    }
    public void tworzeniePlikuZPracownikami(){
        File plik = new File(FILEUZYTKOWNICY);
        try {
            plik.createNewFile();
        }catch(Exception e){
            System.out.println("Nie udalo sie stworzyc nowego pliku");
            System.exit(1);
        }
        System.out.println("Stworzona nowy plik z uzytkownikami");
    }
    public Pracownik tworzenieNowegoUzytkonika(Scanner scanner){
        String imie, nazwisko, haslo, id;
        System.out.println("Podaj imie:");
        imie = scanner.nextLine();
        System.out.println("Podaj nazwisko:");
        nazwisko = scanner.nextLine();
        System.out.println("Podaj haslo:");
        while(true) {
            haslo = scanner.nextLine();
            if(!haslo.equals("quit"))
                break;
            System.out.println("Haslem nie moze byc quit");
        }
        System.out.println("Podaj id:");
        while(true){
            id = scanner.nextLine();
            if(!listaUzytkownikow.containsKey(id)){
                break;
            }
            System.out.println("Podane id juz istnieje, podaj inne");
        }
        Pracownik pracownik = new Pracownik(imie,nazwisko,id,haslo);
        listaUzytkownikow.put(id,pracownik);
        File plik = new File(FILEUZYTKOWNICY);
        try{
            PrintWriter printWriter = new PrintWriter(new java.io.FileWriter(plik, true)); //true czyli append
            printWriter.println(imie+"|"+nazwisko+"|"+id+"|"+haslo);
            printWriter.close();
        } catch(Exception e){
            System.out.println("Nie udal sie zapis do pliku");
            System.exit(1);
        }
        return pracownik;
    }
    private Pracownik zaloguj(Scanner scanner){
        System.out.println("Podaj id:");
        String id;
        while(true){
            id = scanner.nextLine();
            if(listaUzytkownikow.containsKey(id)){
                break;
            }
            System.out.println("Podane id nie istnieje, podaj inne");
        }
        System.out.println("Podaj haslo, lub wpisz quit zeby wyjsc");
        String haslo;
        while(true){
            haslo = scanner.nextLine();
            if(haslo.equals(listaUzytkownikow.get(id).getHaslo())){
                break;
            }
            else if(haslo.equals("quit")){
                return null;
            }
            System.out.println("Niepoprawne haslo, sprobuj jeszcze raz");
        }
        return listaUzytkownikow.get(id);
    }

    public void dodawanieUczestnikow(Scanner scanner, Spotkanie spotkanie) {
        String inputUzytkownik;

        System.out.println("--- Dodawanie uczestników do spotkania ---");
        System.out.println("(Wpisz 'quit' aby zakończyć dodawanie osób)");

        while (true) {
            System.out.println("Podaj ID użytkownika, którego chcesz dodać:");
            inputUzytkownik = scanner.nextLine();

            if (inputUzytkownik.equalsIgnoreCase("quit")) {
                System.out.println("Zakończono dodawanie uczestników.");
                break;
            }

            if (!listaUzytkownikow.containsKey(inputUzytkownik)) {
                System.out.println("Użytkownik o ID '" + inputUzytkownik + "' nie istnieje w bazie danych! Spróbuj ponownie.");
                continue;
            }

            Pracownik wybranyPracownik = listaUzytkownikow.get(inputUzytkownik);
            if ( !spotkanie.addUczestnik(wybranyPracownik) ) {
                System.out.println(wybranyPracownik.getImie() + " już znajduje się na liście uczestników tego spotkania.");
            } else {
                System.out.println("Pomyślnie dodano: " + wybranyPracownik.getImie() + " " + wybranyPracownik.getNazwisko());
            }

        }
    }

    public void rezerwujMiejsce(Spotkanie spotkanie, Scanner scanner, Pracownik pracownik) {
        String input;
        ArrayList<Miejsce> listaMiejsc = spotkanie.getMiejsca();


        if (spotkanie.getSala() == null || listaMiejsc.isEmpty()) {
            System.out.println("Błąd: To spotkanie nie ma jeszcze przypisanej sali lub miejsc!");
            return;
        }

        while (true) {
            System.out.println("\n--- Rezerwowanie Miejsc ---");
            System.out.println("Dostępne miejsca w sali (" + spotkanie.getSala().getNumerSali() + "):");

            for (Miejsce m : listaMiejsc) {
                String stan = m.isZajete() ? "[ZAJĘTE przez ID: " + m.getRezerwacjaID() + "]" : "[WOLNE]";
                System.out.println("Miejsce nr " + m.getNumerMiejsca() + " - " + stan);
            }

            System.out.println("\nWpisz numer miejsca, które chcesz zarezerwować (lub wpisz 'quit', aby zakończyć):");
            input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("quit")) {
                System.out.println("Zakończono rezerwację miejsc.");
                break;
            }

            try {
                int numerMiejsca = Integer.parseInt(input);
                Miejsce wybraneMiejsce = null;

                for (Miejsce m : listaMiejsc) {
                    if (m.getNumerMiejsca() == numerMiejsca) {
                        wybraneMiejsce = m;
                        break;
                    }
                }

                if (wybraneMiejsce == null) {
                    System.out.println("Niepoprawny numer miejsca! Wybierz numer z listy powyżej.");
                    continue;
                }
                if (wybraneMiejsce.isZajete()) {
                    System.out.println("To miejsce jest już zajęte! Wybierz inne.");
                    continue;
                }

                wybraneMiejsce.setRezerwacjaID(pracownik.getId());
                spotkanie.addUczestnik(pracownik);

                System.out.println("Sukces! Zarezerwowano miejsce nr " + numerMiejsca + " dla " + pracownik.getId());
                return;

            } catch (NumberFormatException e) {
                System.out.println("Błąd: Podaj prawidłowy numer lub wpisz 'wyjdz'.");
            }
        }
    }
}