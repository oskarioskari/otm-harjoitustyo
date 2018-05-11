# Arkkitehtuurikuvaus

## Rakenne
### Ohjelman rakenne yleisellä tasolla
Ohjelman toiminnalliset osat (tykkipeli.ui, tykkipeli.logic, tykkipeli.dao) noudattavat kolmitasoista kerrosarkkitehtuuria.\
Näiden lisäksi ohjelman toimintaan liittyvät olioluokat on jaettu kahteen pakettiin (tykkipeli.objects, tykkipeli.physicobjects).\
Käyttöliittymän toimintoja on osittain jaettu pakettiin tykkipeli.ui.uihelpers.\
Oheessa rakennetta havainnollistava kuva:\
<img src="https://github.com/oskarioskari/otm-harjoitustyo/blob/master/dokumentointi/kuvat/pakkausrakenne.png" width="521">
\
Pakkaus *tykkipeli.ui* sisältää javafx:llä toteutetun käyttöliittymän, *tykkipeli.logic* sisältää sovelluslogiikan ja *tykkipeli.dao* sisältää pysyväistallennuksesta vastaavat osat.\
Näiden lisäksi *tykkipeli.physicobjects* sisältää pelin graafiset ja fysiikkaa noudattavat objektit, *tykkipeli.objects* sisältää muut pelin objektit ja *tykkipeli.ui.uihelpers* sisältää käyttöliittymän apuluokat.

### Tarkempi luokkakaavio
Luokkakaavio:\
<img src="https://github.com/oskarioskari/otm-harjoitustyo/blob/master/dokumentointi/kuvat/UML-v1_0.png" width="470">

## Käyttöliittymä
Käyttöliittymä sisältää kolme erilaista näkymää:
* Päävalikko
* Peli
* Pisteiden tallennus

Jokainen näkymä on totetutettu omana Stage-olionaan, eli ne avataan eri ruutuihin. Lisäksi päävalikossa voidaan siirtyä eri alavalikoissa, jotka on toteutettu Scene-olioina, jotka sijoitetaan päävalikon Stageen.

## Toiminnallisuudet
### Fysiikkamallinnus
Pelissä ammusten lentoradat lasketaan reaaliajassa käyttäen hyväksi Velocity Verlet -algoritmia. Pelissä käytetään algoritmin lyhyempää versiota, jossa ei käytetä puoliaskeleen nopeutta.
Algoritmi etenee karkeasti seuraavalla tavalla:
1. Laske kappaleen sijainti ajan &#916;t kuluttua käyttäen nykyistä nopeutta ja kiihtyvyyttä.
2. Laske kappaleen uusi kokonaiskiihtyvyys uudessa sijainnissa.
3. Laske kappaleen uusi nopeus käyttäen sekä vanhaa että uutta kokonaiskiihtyvyyttä.

Yksityiskohtainen kuvaus algoritmin toiminnasta löytyy [Wikipediasta](https://en.wikipedia.org/wiki/Verlet_integration#Velocity_Verlet).

Sekvenssikaavio tilanteesta, jossa ammusta liikutetaan yksi askel:\
<img src="https://github.com/oskarioskari/otm-harjoitustyo/blob/master/dokumentointi/kuvat/sekvenssikaavio-moveAmmo_new.png" width="732">

### Tietojen pysyväistallennus
Pakkauksen *tykkipeli.dao* luokka *HighScoresDao* vastaa tietojen pysyväistallennuksesta. Luokka käyttää *sqlite-jdbc* pakettia. Pelissä pysyvästi tallennetaan pelaajien saavuttamia huippupisteitä ja niihin liittyviä nimimerkkejä.

Tiedot tallennetaan hakemistossa *res/* sijaitsevaan tiedostoon *highscores.db*. Kyseinen tiedosto sisältää SQL-tietokannan, jossa on kolme tietokantataulua:
* scoresEasy
* scoresNormal
* scoresHard

Kaikki tietokantataulut sisältävät kaksi saraketta:
* id - Pelaajan nimimerkki (merkkijono)
* score - Pelaajan saavuttama pistemäärä (kokonaisluku)

## Ohjelman rakenteeseen jääneet heikkoudet
### Käyttöliittymä
* Pelissä eri päänäkymät avataan aina omiin ikkunoihinsa, jolloin päävalikko jää taustalle auki, kun peli alkaa. Tämä mahdollistaa ei haluttujan toimintoja, kuten esimerkiksi tekoälyn vaikeusasteen vaihtamisen kesken pelin.
* Pelinäkymän sulkeminen ei sulje itse peliä, vaan valitsemalla päävalikosta "Start Game" peli jatkaa tilanteesta johon oltiin jääty.

### Sovelluslogiikka
* GameLogic luokka jäi melko laajaksi ja monia sen toimintoja olisi voinut mahdollisesti jakaa useammalle luokalle.
