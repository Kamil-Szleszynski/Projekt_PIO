import java.io.File;
import java.io.PrintWriter;
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


        }
    }
}