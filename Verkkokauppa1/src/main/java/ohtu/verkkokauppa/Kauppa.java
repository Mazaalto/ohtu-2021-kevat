package ohtu.verkkokauppa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Kauppa implements NewInterface {

    private Varasto varasto;
    private Pankki pankki;
    private Ostoskori ostoskori;
    private Viitegeneraattori viitegeneraattori;
    private String kaupanTili;
    private Kirjanpito kirjanpito;
    
    @Autowired
    public Kauppa() {
        this.kirjanpito = new Kirjanpito();
        varasto = new Varasto(kirjanpito);
        pankki = new Pankki(kirjanpito);
        viitegeneraattori = new Viitegeneraattori();
        kaupanTili = "33333-44455";
    }

    public Kirjanpito getKirjanpito() {
        return kirjanpito;
    }

    
    

    @Override
    public void aloitaAsiointi() {
        ostoskori = new Ostoskori();
    }

    public void poistaKorista(int id) {
        Tuote t = varasto.haeTuote(id);
        varasto.palautaVarastoon(t);
    }

    public void lisaaKoriin(int id) {
        if (varasto.saldo(id) > 0) {
            Tuote t = varasto.haeTuote(id);
            ostoskori.lisaa(t);
            varasto.otaVarastosta(t);
        }
    }

    @Override
    public boolean tilimaksu(String nimi, String tiliNumero) {
        int viite = viitegeneraattori.uusi();
        int summa = ostoskori.hinta();

        return pankki.tilisiirto(nimi, viite, tiliNumero, kaupanTili, summa);
    }
    

}
