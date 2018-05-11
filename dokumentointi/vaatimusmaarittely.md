# Vaatimusmäärittely

**Tykkipeli**


## Sovelluksen tarkoitus

Sovellus on peli, jossa sivulta päin kuvatulla kaksiuloitteisella kartalla kaksi tykkitornia pyrkivät tuhoamaan toisensa erilaisia aseita/ammuksia käyttämällä. Eri aseilla/ammuksilla on erilaisia ominaisuuksia, jotka vaikuttavat mm. niiden lentoratoihin ja tuhovoimaan. Peliä pelataan vuorotellen siten, että molemmat pelaajat antavat ensin tulikomennot, jonka jälkeen ne toteutetaan samanaikaisesti.

(Kyseessä on siis klooni Aapelin (aapeli.com) Tykkipelistä sillä erotuksella, että peliä voi pelata vain paikallisesti ja kahdestaan.)


## Käyttäjät

Sovelluksella on vain yksi käyttäjärooli *pelaaja*.


## Käyttöliittymäluonnos

Sovelluksessa on kaksi eri näkymää: *päävalikkonäkymä* ja *pelinäkymä*.

* Päävalikon kautta voidaan siirtyä pelinäkymään sekä päästä käsiksi pelin asetuksiin ja huippupisteisiin.

* Pelinäkymä koostuu kaksiulotteisesta, sivulta päin kuvatusta kartasta jolla itse pelaaminen tapahtuu sekä kuvan yläreunassa olevista pistelaskureista. Kartta koostuu kahdesta alueesta: maasta ja ilmasta. Tykit sijaitsevat maan pinnalla.


<img src="https://raw.githubusercontent.com/oskarioskari/otm-harjoitustyo/master/dokumentointi/kuvat/vaatimusmaarittely.jpg" width="750">


## Perusversion tarjoama toiminnallisuus

* Peliä voi pelata 1v1 tilassa, ihminen ihmistä vastaan
* Peliä voi pelata yksin tietokonetta vastaan
* Pelissä on kolme eritasoista tekoälyvastustajaa, joista voi valita haluamansa
* Pelissä on käytettävissä kaksi keskenään erilaista asetta
* Pelin perusversiossa on yksi pelattava kartta
* Huippupisteet-tietokanta (paikallinen)


## Jatkokehitysideoita

Perusversion jälkeen peliä tullaan laajentamaan ajan salliessa seuraavasti:

* Oppiva tekoälyvastustaja
* Useampia pelattavia karttoja
* Useampia erilaisia aseita
* Taustamusiikki ja ääniefektit
* Kartan maan tuhoutuminen
* Ennen peliä satunnaisesti generoitava kartta
* Mahdollisuus lähettää huippupisteet verkossa olevalle listalle
