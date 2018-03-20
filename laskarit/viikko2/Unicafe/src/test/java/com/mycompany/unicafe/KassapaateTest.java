package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class KassapaateTest {

    Kassapaate kassa;
    Maksukortti kortti;

    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(1000);
    }

    @Test
    public void alussaTonniRahaa() {
        assertTrue(kassa.kassassaRahaa()==100000);
    }

    @Test
    public void alussaEiMyytyMaukkaita() {
        assertTrue(kassa.maukkaitaLounaitaMyyty()==0);
    }

    @Test
    public void alussaEiMyytyEdullisia() {
        assertTrue(kassa.edullisiaLounaitaMyyty()==0);
    }

    @Test
    public void kateisellaMaukkaanVaihtorahaOikein() {
        assertTrue(kassa.syoMaukkaasti(500)==100);
    }

    @Test
    public void kateisellaEdullisenVaihtorahaOikein() {
        assertTrue(kassa.syoEdullisesti(340)==100);
    }

    @Test
    public void kassanSummaKasvaaMaukkaasta() {
        kassa.syoMaukkaasti(500);
        assertTrue(kassa.kassassaRahaa()==100400);
    }

    @Test
    public void kassanSummaKasvaaEdullisesta() {
        kassa.syoEdullisesti(300);
        assertTrue(kassa.kassassaRahaa()==100240);
    }

    @Test
    public void maukkaidenMaaraKasvaa() {
        kassa.syoMaukkaasti(500);
        assertTrue(kassa.maukkaitaLounaitaMyyty()==1);
    }

    @Test
    public void edullistenMaaraKasvaa() {
        kassa.syoEdullisesti(500);
        assertTrue(kassa.edullisiaLounaitaMyyty()==1);
    }

    @Test
    public void vaihtorahaOikeinKunEiVaraaMaukkaaseen() {
        assertTrue(kassa.syoMaukkaasti(100)==100);
    }

    @Test
    public void vaihtorahaOikeinKunEiVaraaEdulliseen() {
        assertTrue(kassa.syoEdullisesti(100)==100);
    }

    @Test
    public void kassaEiMuutuKunEiVaraaMaukkaaseen() {
        kassa.syoMaukkaasti(100);
        assertTrue(kassa.kassassaRahaa()==100000);
    }

    @Test
    public void kassaEiMuutuKunEiVaraaEdulliseen() {
        kassa.syoEdullisesti(100);
        assertTrue(kassa.kassassaRahaa()==100000);
    }

    @Test
    public void myytyjenMaaraEiMuutuKunEiVaraaMaukkaaseen() {
        kassa.syoMaukkaasti(100);
        assertTrue(kassa.maukkaitaLounaitaMyyty()==0);
    }

    @Test
    public void myytyjenMaaraEiMuutuKunEiVaraaEdulliseen() {
        kassa.syoEdullisesti(100);
        assertTrue(kassa.edullisiaLounaitaMyyty()==0);
    }

    @Test
    public void korttimaksaminenToimiiMaukkaalle() {
        assertTrue(kassa.syoMaukkaasti(kortti)==true);
    }

    @Test
    public void korttimaksaminenToimiiEdulliselle() {
        assertTrue(kassa.syoEdullisesti(kortti)==true);
    }

    @Test
    public void korttiaVeloitetaanOikeinMaukkaasta() {
        kassa.syoMaukkaasti(kortti);
        assertTrue(kortti.saldo()==600);
    }

    @Test
    public void korttiaVeloitetaanOikeinEdullisesta() {
        kassa.syoEdullisesti(kortti);
        assertTrue(kortti.saldo()==760);
    }

    @Test
    public void korttimaksaminenLisaaMaukkaidenMaaraa() {
        kassa.syoMaukkaasti(kortti);
        assertTrue(kassa.maukkaitaLounaitaMyyty()==1);
    }

    @Test
    public void korttimaksaminenLisaaEdullistenMaaraa() {
        kassa.syoEdullisesti(kortti);
        assertTrue(kassa.edullisiaLounaitaMyyty()==1);
    }

    @Test
    public void korttimakaminenPalauttaaFalseMaukkaalle() {
        kortti = new Maksukortti(2);
        assertTrue(kassa.syoMaukkaasti(kortti)==false);
    }

    @Test
    public void korttimakaminenPalauttaaFalseEdulliselle() {
        kortti = new Maksukortti(2);
        assertTrue(kassa.syoEdullisesti(kortti)==false);
    }

    @Test
    public void korttiaEiVeloitetaJosEiVaraaMaukkaaseen() {
        kortti = new Maksukortti(2);
        kassa.syoMaukkaasti(kortti);
        assertTrue(kortti.saldo()==2);
    }

    @Test
    public void korttiaEiVeloitetaJosEiVaraaEdulliseen() {
        kortti = new Maksukortti(2);
        kassa.syoEdullisesti(kortti);
        assertTrue(kortti.saldo()==2);
    }

    @Test
    public void myytyjenMaukkaidenMaaraEiMuutuKorttimaksussa() {
        kortti = new Maksukortti(2);
        kassa.syoMaukkaasti(kortti);
        assertTrue(kassa.maukkaitaLounaitaMyyty()==0);
    }

    @Test
    public void myytyjenEdullistenMaaraEiMuutuKorttimaksussa() {
        kortti = new Maksukortti(2);
        kassa.syoEdullisesti(kortti);
        assertTrue(kassa.edullisiaLounaitaMyyty()==0);
    }

    @Test
    public void kassanRahamaaraEiMuutuKorttimaksussa() {
        kassa.syoMaukkaasti(kortti);
        assertTrue(kassa.kassassaRahaa()==100000);
    }

    @Test
    public void kortinSaldoKasvaaLadattaessaKortille() {
        kassa.lataaRahaaKortille(kortti,500);
        assertTrue(kortti.saldo()==1500);
    }

    @Test
    public void kassanSaldoKasvaaLadattaessaKortille() {
        kassa.lataaRahaaKortille(kortti,500);
        assertTrue(kassa.kassassaRahaa()==100500);
    }

    @Test
    public void korttilatausEiTeeMitaanJosSummaNegatiivinen() {
        kassa.lataaRahaaKortille(kortti,-100);
        assertTrue(kassa.kassassaRahaa()==100000 && kortti.saldo()==1000);
    }


}
