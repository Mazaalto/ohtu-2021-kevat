/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ohtu.verkkokauppa.Kauppa;
import ohtu.verkkokauppa.Kirjanpito;
import ohtu.verkkokauppa.Pankki;
import ohtu.verkkokauppa.Tuote;
import ohtu.verkkokauppa.Varasto;
import ohtu.verkkokauppa.Viitegeneraattori;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author mazaalto
 */

//Tämä on verkkokaupan 1 testi

public class KauppaTest {

    private Pankki pankki;
    private Viitegeneraattori viite;
    private Varasto varasto;


    public KauppaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        pankki = mock(Pankki.class);
        viite = mock(Viitegeneraattori.class);
        varasto = mock(Varasto.class);
        
        
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
       
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);
        
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);              

        // tehdään ostokset
        k.aloitaAsiointi();
        //testataan tehtävänannon juttu
        //System.out.println(k.getKirjanpito());
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(),anyInt());   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }
    
    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaParametreilla() {
        
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        
        //TEHTÄVÄ TÄSSÄ, eli tsekkaan että tekstit ja muut on oikein
        //String nimi, int viitenumero, String tililta, String tilille, int summa)
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), anyString(), eq(5));
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }
    // aloitetaan asiointi, koriin lisätään kaksi eri tuotetta, joita varastossa on ja suoritetaan ostos, 
    // varmista että kutsutaan pankin metodia tilisiirto oikealla asiakkaalla, tilinumerolla ja summalla
    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaParametreillaKunKaksiEriTuotetta() {
        
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(43);

        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        
        //määritellään toinen tuote, teht.annonmukaan
        when(varasto.saldo(2)).thenReturn(11);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(1, "piimä", 10));
        
        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(2);     // ostetaan kanssa tuote 2, eli hapanta piimää
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        
        //TEHTÄVÄ TÄSSÄ, eli tsekkaan että tekstit ja muut on oikein
        //String nimi, int viitenumero, String tililta, String tilille, int summa)
        verify(pankki).tilisiirto(eq("pekka"), eq(43), eq("12345"), anyString(), eq(15));
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }
    // aloitetaan asiointi, koriin lisätään kaksi samaa tuotetta, jota on varastossa tarpeeksi ja suoritetaan ostos, varmista että kutsutaan pankin metodia tilisiirto oikealla asiakkaalla, tilinumerolla ja summalla
    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaParametreillaKunKaksiSamaaTuotetta() {
        
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        
        //otetaan toinen tuote
        // sama tuote, mutta saldo vaan eri (9) kun otetaan toinen
        
        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(1);     // ostetaan kanssa tuote 2, eli hapanta piimää
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        
        //TEHTÄVÄ TÄSSÄ, eli tsekkaan että tekstit ja muut on oikein
        //String nimi, int viitenumero, String tililta, String tilille, int summa)
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), anyString(), eq(10));
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }
    //aloitetaan asiointi, koriin lisätään tuote, jota on varastossa tarpeeksi ja tuote joka on loppu ja suoritetaan ostos, 
    //varmista että kutsutaan pankin metodia tilisiirto oikealla asiakkaalla, tilinumerolla ja summalla
    
    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaParametreillaKunToistaTuotettaEiOle() {
        
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        
        //otetaan toinen tuote
        //määritellään toinen tuote, teht.annonmukaan
        when(varasto.saldo(2)).thenReturn(0);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(1, "proteiinimaito", 10));
        
        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(2);     // ostetaan kanssa tuote 2, eli hapanta piimää
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        
        //TEHTÄVÄ TÄSSÄ, eli tsekkaan että tekstit ja muut on oikein
        //String nimi, int viitenumero, String tililta, String tilille, int summa)
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), anyString(), eq(5));
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }
    @Test
    public void pankkiPyytaaEriViiteNumeroa() {
        Kauppa kauppa = new Kauppa(varasto, pankki, viite);
        
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("jorma", "123456");
        

        // tarkistetaan että tässä vaiheessa viitegeneraattorin metodia seuraava()
        // on kutsuttu kerran
        verify(viite, times(1)).uusi();
        
        //otetaan toinen tuote
        //määritellään toinen tuote, teht.annonmukaan
        when(varasto.saldo(2)).thenReturn(0);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(1, "proteiinimaito", 10));
        
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(2);
        kauppa.tilimaksu("jorma", "1234567");

        // tarkistetaan että tässä vaiheessa viitegeneraattorin metodia seuraava()
        // on kutsuttu kaksi kertaa
            
        verify(viite, times(2)).uusi();

       
    }
    
    @Test
    public void poistaKoristaToimii() {
        Kauppa kauppa = new Kauppa(varasto, pankki, viite);
        
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10);
        Tuote t = new Tuote(1, "maitoa", 5);
        when(varasto.haeTuote(1)).thenReturn(t);
        
        kauppa.aloitaAsiointi();
        kauppa.poistaKorista(1);
        Kirjanpito pito = kauppa.getKirjanpito();
        
        //tarkistetaan että on 
        verify(varasto, times(1)).palautaVarastoon(t);
        //verify(kauppa, times(1)).getKirjanpito();

       
    }
    
    /*
    @Test
    public void getKirjanpitoToimii() {
        Kauppa kauppa = new Kauppa(varasto, pankki, viite);
        
        when(kauppa.getKirjanpito());
        
        //tarkistetaan että on 
        verify(kauppa, times(1)).getKirjanpito();

       
    }
    */
}


