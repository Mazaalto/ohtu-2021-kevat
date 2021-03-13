package ohtu.intjoukkosovellus;

//vk4. teht.5 Refaktoroi luokan IntJoukko koodi mahdollisimman siistiksi
// poistetaan copypaste
public class IntJoukko {

    //tässä ilmeisesti totetutettu taulukkorakene, johon voidaan lisätä tai poistaa tai pyytää osia
    public final static int KAPASITEETTI = 5, // aloitustalukon koko
            OLETUSKASVATUS = 5;              // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;               // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] lukutaulukko;             // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;               // Tyhjässä joukossa alkioiden_määrä on nolla. 

    // konstruktori, ilman parametrejä korjattu
    public IntJoukko() {
        alustaTaulukko(KAPASITEETTI, OLETUSKASVATUS);
    }

    //tämä uusi metodi, johon viedään copypasteja pois
    public void alustaTaulukko(int alkukoko, int lisattavaTila) {
        if (onkoPositiviinen(alkukoko) && onkoPositiviinen(lisattavaTila)) {
            lukutaulukko = new int[alkukoko];
            for (int i = 0; i < lukutaulukko.length; i++) {
                lukutaulukko[i] = 0;
            }
            alkioidenLkm = 0;
            this.kasvatuskoko = lisattavaTila;

        } else {
            throw new IndexOutOfBoundsException("Taulukon alkukoko ja lisättävä tila tulee olla positiviisia");
        }

    }

    //konstruktori parametrillä
    public IntJoukko(int kapasiteetti) {
        alustaTaulukko(kapasiteetti, OLETUSKASVATUS);
    }

    //konstruktori kahdella parametrillä
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        alustaTaulukko(kapasiteetti, kasvatuskoko);

    }

    public boolean onkoPositiviinen(int luku) {
        if (luku < 0) {
            return false;
        }
        return true;

    }

    //lisätään joukkoon luku
    public boolean lisaa(int luku) {
        //jos taulukko on tyhjä, luku lisätään alkuun ja lisätään alkioiden määrää, sekä palautetaan true
        if (alkioidenLkm == 0) {
            lisataanLukuTaulukkoon(luku, 0);
            return true;
        }
        //jos luku ei kuulu taulukkoon, lisätään se
        if (!kuuluu(luku)) {
            lisataanLukuTaulukkoon(luku, alkioidenLkm);
            //jos tila alkaa loppua kesken tehdään uusi taulukko
            if (alkioidenLkm % lukutaulukko.length == 0) {
                taulukonKoonKasvatus();
            }
            return true;
        }
        return false;
    }

    public void lisataanLukuTaulukkoon(int luku, int kohta) {
        lukutaulukko[kohta] = luku;
        alkioidenLkm++;

    }

    //jos tila alkaa loppua kesken tehdään uusi taulukko
    public void taulukonKoonKasvatus() {
        int[] taulukkoPieni = lukutaulukko;
        //ensiksi tallennetaan lukutaulukko talteen taulukkoPieneen
        kopioiTaulukko(lukutaulukko, taulukkoPieni);
        lukutaulukko = new int[alkioidenLkm + kasvatuskoko];
        //sitten tallennetaan lukutaulukko 
        kopioiTaulukko(taulukkoPieni, lukutaulukko);

    }

    // kuuluuko luku joukkoon
    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukutaulukko[i]) {
                return true;
            }
        }
        return false;
    }

    //poistetaan luku joukosta
    public boolean poista(int luku) {
        int kohta = -1;
        int apu;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukutaulukko[i]) {
                kohta = i; //siis luku löytyy tuosta kohdasta :D
                lukutaulukko[kohta] = 0;
                break;
            }
        }
        //siirretään vasemmalle, koska poistettiin alkio
        if (kohta != -1) {
            siirraAlkioitaVasemmalle(kohta);
            alkioidenLkm--;
            return true;
        }
        return false;
    }

    public void siirraAlkioitaVasemmalle(int kohta) {
        int apuMuuttuja;
        for (int j = kohta; j < alkioidenLkm - 1; j++) {
            apuMuuttuja = lukutaulukko[j];
            lukutaulukko[j] = lukutaulukko[j + 1];
            lukutaulukko[j + 1] = apuMuuttuja;
        }

    }

    //kopioidaan taulukko
    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        System.arraycopy(vanha, 0, uusi, 0, vanha.length);

    }

    public int alkioidenLukuMaara() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        String tulostus = "{";

        if (alkioidenLkm != 0) {
            for (int i = 0; i < alkioidenLkm - 1; i++) {
                tulostus += lukutaulukko[i];
                tulostus += ", ";
            }
            tulostus += lukutaulukko[alkioidenLkm - 1];
        }
        tulostus += "}";
        return tulostus;

    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = lukutaulukko[i];
        }
        return taulu;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko yhdiste = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();

        for (int i = 0; i < aTaulu.length; i++) {
            yhdiste.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            yhdiste.lisaa(bTaulu[i]);
        }
        return yhdiste;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko leikkaus = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();

        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    leikkaus.lisaa(bTaulu[j]);
                }
            }
        }
        return leikkaus;

    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko erotus = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();

        for (int i = 0; i < aTaulu.length; i++) {
            erotus.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            erotus.poista(bTaulu[i]);
        }

        return erotus;
    }

}
