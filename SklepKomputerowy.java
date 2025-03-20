import java.time.LocalDate;

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