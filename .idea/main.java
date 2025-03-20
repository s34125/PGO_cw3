```java
import java.time.LocalDate;

class Produkt {
    private int id;
    private String nazwa;
    private String kategoria;
    private double cena;
    private int iloscWMagazynie;

    public void wyswietlInformacje() {
        System.out.println("ID: " + id);
        System.out.println("Nazwa: " + nazwa);
        System.out.println("Kategoria: " + kategoria);
        System.out.println("Cena: " + cena + " zł");
        System.out.println("Ilość w magazynie: " + iloscWMagazynie);
        System.out.println("-------------------");
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNazwa() { return nazwa; }
    public void setNazwa(String nazwa) { this.nazwa = nazwa; }
    public String getKategoria() { return kategoria; }
    public void setKategoria(String kategoria) { this.kategoria = kategoria; }
    public double getCena() { return cena; }
    public void setCena(double cena) { this.cena = cena; }
    public int getIloscWMagazynie() { return iloscWMagazynie; }
    public void setIloscWMagazynie(int iloscWMagazynie) { this.iloscWMagazynie = iloscWMagazynie; }
}

class Klient {
    private int id;
    private String imie;
    private String nazwisko;
    private String email;
    private boolean czyStaly;

    public void wyswietlInformacje() {
        System.out.println("ID: " + id);
        System.out.println("Imię: " + imie);
        System.out.println("Nazwisko: " + nazwisko);
        System.out.println("Email: " + email);
        System.out.println("Stały klient: " + (czyStaly ? "Tak" : "Nie"));
        System.out.println("-------------------");
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getImie() { return imie; }
    public void setImie(String imie) { this.imie = imie; }
    public String getNazwisko() { return nazwisko; }
    public void setNazwisko(String nazwisko) { this.nazwisko = nazwisko; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public boolean isCzyStaly() { return czyStaly; }
    public void setCzyStaly(boolean czyStaly) { this.czyStaly = czyStaly; }
}

class Zamowienie {
    private int id;
    private Klient klient;
    private Produkt[] produkty;
    private int[] ilosci;
    private String dataZamowienia;
    private String status;
    private boolean znizkaZastosowana = false;

    public double obliczWartoscZamowienia() {
        double suma = 0.0;
        for (int i = 0; i < produkty.length; i++) {
            suma += produkty[i].getCena() * ilosci[i];
        }
        return znizkaZastosowana ? suma * 0.9 : suma;
    }

    public void zastosujZnizke() {
        if (klient.isCzyStaly()) znizkaZastosowana = true;
    }

    public void wyswietlSzczegoly() {
        System.out.println("Zamówienie ID: " + id);
        System.out.println("Data: " + dataZamowienia);
        System.out.println("Status: " + status);
        klient.wyswietlInformacje();
        System.out.println("Produkty:");
        for (int i = 0; i < produkty.length; i++) {
            System.out.printf("- %s, ilość: %d, cena: %.2f zł\n",
                produkty[i].getNazwa(), ilosci[i], produkty[i].getCena());
        }
        System.out.printf("Łączna wartość zamówienia: %.2f zł\n", obliczWartoscZamowienia());
        System.out.println("-------------------");
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Klient getKlient() { return klient; }
    public void setKlient(Klient klient) { this.klient = klient; }
    public Produkt[] getProdukty() { return produkty; }
    public void setProdukty(Produkt[] produkty) { this.produkty = produkty; }
    public int[] getIlosci() { return ilosci; }
    public void setIlosci(int[] ilosci) { this.ilosci = ilosci; }
    public String getDataZamowienia() { return dataZamowienia; }
    public void setDataZamowienia(String dataZamowienia) { this.dataZamowienia = dataZamowienia; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

class SklepKomputerowy {
    private Produkt[] produkty = new Produkt[10];
    private Klient[] klienci = new Klient[10];
    private Zamowienie[] zamowienia = new Zamowienie[10];
    private int liczbaProduktow = 0;
    private int liczbaKlientow = 0;
    private int liczbaZamowien = 0;

    public void dodajProdukt(Produkt produkt) {
        if (liczbaProduktow == produkty.length) {
            Produkt[] nowa = new Produkt[produkty.length * 2];
            System.arraycopy(produkty, 0, nowa, 0, produkty.length);
            produkty = nowa;
        }
        produkty[liczbaProduktow++] = produkt;
    }

    public void dodajKlienta(Klient klient) {
        if (liczbaKlientow == klienci.length) {
            Klient[] nowa = new Klient[klienci.length * 2];
            System.arraycopy(klienci, 0, nowa, 0, klienci.length);
            klienci = nowa;
        }
        klienci[liczbaKlientow++] = klient;
    }

    public Zamowienie utworzZamowienie(Klient klient, Produkt[] produkty, int[] ilosci) {
        Zamowienie zamowienie = new Zamowienie();
        zamowienie.setId(liczbaZamowien + 1);
        zamowienie.setKlient(klient);
        zamowienie.setProdukty(produkty);
        zamowienie.setIlosci(ilosci);
        zamowienie.setDataZamowienia(LocalDate.now().toString());
        zamowienie.setStatus("Nowe");

        if (liczbaZamowien == zamowienia.length) {
            Zamowienie[] nowa = new Zamowienie[zamowienia.length * 2];
            System.arraycopy(zamowienia, 0, nowa, 0, zamowienia.length);
            zamowienia = nowa;
        }
        zamowienia[liczbaZamowien++] = zamowienie;

        return zamowienie;
    }

    public void aktualizujStanMagazynowy(Zamowienie zamowienie) {
        Produkt[] produktyZamowienia = zamowienie.getProdukty();
        int[] ilosci = zamowienie.getIlosci();
        for (int i = 0; i < produktyZamowienia.length; i++) {
            Produkt produkt = produktyZamowienia[i];
            produkt.setIloscWMagazynie(produkt.getIloscWMagazynie() - ilosci[i]);
        }
    }

    public void zmienStatusZamowienia(int idZamowienia, String nowyStatus) {
        for (int i = 0; i < liczbaZamowien; i++) {
            if (zamowienia[i].getId() == idZamowienia) {
                zamowienia[i].setStatus(nowyStatus);
                return;
            }
        }
        System.out.println("Nie znaleziono zamówienia o ID: " + idZamowienia);
    }

    public void wyswietlProduktyWKategorii(String kategoria) {
        System.out.println("Produkty w kategorii '" + kategoria + "':");
        for (int i = 0; i < liczbaProduktow; i++) {
            Produkt p = produkty[i];
            if (p.getKategoria().equalsIgnoreCase(kategoria)) {
                p.wyswietlInformacje();
            }
        }
    }

    public void wyswietlZamowieniaKlienta(int idKlienta) {
        System.out.println("Zamówienia klienta o ID " + idKlienta + ":");
        for (int i = 0; i < liczbaZamowien; i++) {
            Zamowienie z = zamowienia[i];
            if (z.getKlient().getId() == idKlienta) {
                z.wyswietlSzczegoly();
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        SklepKomputerowy sklep = new SklepKomputerowy();

        Produkt produkt1 = new Produkt();
        produkt1.setId(1);
        produkt1.setNazwa("Laptop Dell XPS 13");
        produkt1.setKategoria("Laptop");
        produkt1.setCena(4999.99);
        produkt1.setIloscWMagazynie(10);

        Produkt produkt2 = new Produkt();
        produkt2.setId(2);
        produkt2.setNazwa("Mysz Logitech MX Master 3");
        produkt2.setKategoria("Mysz");
        produkt2.setCena(349.99);
        produkt2.setIloscWMagazynie(30);

        Produkt produkt3 = new Produkt();
        produkt3.setId(3);
        produkt3.setNazwa("Monitor Samsung 27\"");
        produkt3.setKategoria("Monitor");
        produkt3.setCena(1299.99);
        produkt3.setIloscWMagazynie(15);

        sklep.dodajProdukt(produkt1);
        sklep.dodajProdukt(produkt2);
        sklep.dodajProdukt(produkt3);

        Klient klient1 = new Klient();
        klient1.setId(1);
        klient1.setImie("Jan");
        klient1.setNazwisko("Kowalski");
        klient1.setEmail("jan.kowalski@example.com");
        klient1.setCzyStaly(true);

        Klient klient2 = new Klient();
        klient2.setId(2);
        klient2.setImie("Anna");
        klient2.setNazwisko("Nowak");
        klient2.setEmail("anna.nowak@example.com");
        klient2.setCzyStaly(false);

        sklep.dodajKlienta(klient1);
        sklep.dodajKlienta(klient2);

        Produkt[] produktyZamowienia1 = {produkt1, produkt2};
        int[] ilosciZamowienia1 = {1, 1};
        Zamowienie zamowienie1 = sklep.utworzZamowienie(klient1, produktyZamowienia1, ilosciZamowienia1);
        zamowienie1.zastosujZnizke();
        sklep.aktualizujStanMagazynowy(zamowienie1);

        System.out.println("Informacje o zamówieniu:");
        zamowienie1.wyswietlSzczegoly();

        System.out.println("\nStan magazynowy po zamówieniu:");
        sklep.wyswietlProduktyWKategorii("Laptop");
        sklep.wyswietlProduktyWKategorii("Mysz");

        sklep.zmienStatusZamowienia(zamowienie1.getId(), "Zrealizowane");
        System.out.println("\nStatus zamówienia po aktualizacji:");
        zamowienie1.wyswietlSzczegoly();

        Produkt[] produktyZamowienia2 = {produkt3, produkt2};
        int[] ilosciZamowienia2 = {2, 1};
        Zamowienie zamowienie2 = sklep.utworzZamowienie(klient2, produktyZamowienia2, ilosciZamowienia2);
        sklep.aktualizujStanMagazynowy(zamowienie2);

        System.out.println("\nZamówienia klienta Jan Kowalski:");
        sklep.wyswietlZamowieniaKlienta(1);
    }
}
```