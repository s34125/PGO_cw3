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