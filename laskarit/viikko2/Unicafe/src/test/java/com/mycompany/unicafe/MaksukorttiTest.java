package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }

    @Test
    public void alkusaldoOikein() {
    	assertTrue(kortti.saldo()==10);
    }

    @Test
    public void rahanLataaminenToimii() {
    	kortti.lataaRahaa(10);
    	assertTrue(kortti.saldo()==20);
    }

    @Test
    public void rahanVahentaminenKunRahaaRiittavasti() {
    	kortti.otaRahaa(5);
    	assertTrue(kortti.saldo()==5);
    }

    @Test
    public void rahanVahentaminenKunRahaaLiianVahan() {
    	kortti.otaRahaa(15);
    	assertTrue(kortti.saldo()==10);
    }

    @Test
    public void otaRahaaMetodiPalauttaaTrue() {
    	assertTrue(kortti.otaRahaa(5)==true);
    }

    @Test
    public void otaRahaaMetodiPalauttaaFalse() {
    	assertTrue(kortti.otaRahaa(15)==false);
    }
}
