# Käyttöohje

Lataa tiedosto [Tykkipeli-1.0.jar](https://github.com/oskarioskari/otm-harjoitustyo/releases/download/v1.0/Tykkipeli-1.0.jar)\
Lataa pelin [graafiset elementit](https://github.com/oskarioskari/otm-harjoitustyo/tree/master/Tykkipeli/res/pictures) pelin asennushakemistossa sijaitsevaan kansioon *res/pictures/*. Tarvittaessa luo kyseinen hakemisto.

## Konfigurointi

Ohjelma olettaa, että sen käynnistyshakemistossa on hakemisto *res/pictures* jossa on pelin käyttämät graafiset elementit. Kansiota *res/* käytetään myös pelin huippupistetietokannan tallennushakemistona ja sen puuttuminen estää pelin käynnistyksen.

## Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla
```
java -jar Tykkipeli-1.0.jar
```

## Pelaaminen

### Valikko

Ennen pelin aloittamista voi päävalikon "Settings"-alavalikosta valita, että haluaako peliä pelata yksin tietokonetta vastaan, vaiko toista ihmispelaajaa vastaan. Lisäksi tietokonetta vastaan pelatessa on mahdollista pelin vaikeusaste.

Huippupisteitä voi tarkastella valitsemalla päävalikosta kohdan "High Scores". Huippupisteet valikossa voi valita katsottavaksi kolme parasta tulosta kultakin vaikeusasteelta.

Pelaaminen aloitetaan valitsemalla päävalikosta vaihtoehto "Start Game".

Valinta "Quit Game" sulkee pelin.

### Peli

Pelissä on kaksi eri vaihetta: tähtäysvaihe ja ampumisvaihe.

Tähtäysvaiheessa kumpikin pelaaja tähtää vuorollaan tykillään kohti toista pelaajaa. Painamalla ENTER pelaaja lopettaa tähtäämisen. Molempien pelaajien tähdättyä peli siirtyy ampumisvaiheeseen.

Ampumisvaiheessa tykit ampuvat kohti toisiaan pelaajien tekemien tähtäämisten mukaisesti. Ampumisvaihe päättyy, kun molempien pelaajien ampumat ammukset ovat osuneet joko maahan tai toiseen pelaajaan.

Pelissä pelaaja 1 on aina vasemmalla ja pelaaja 2 (tai tietokoneen ohjaama pelaaja) on oikealla. Kummallakin pelaajalla on "elämää" 100 pisteen verran.

Pelissä on valittavana kaksi erilaista ammustyyppiä:
* Ammus 1: Osuessaan tuottaa vahinkoa 10 pisteen verran.
* Ammus 2: Osuessaan tuottaa vahinkoa 25 pisteen verran. Ammuksella on neljä kertaa suurempi ilmanvastuskerroin, kuin ammuksella 1.

Pelissä on satunnaisesti muuttuva tuuli, joka vaikuttaa ammusten lentoratoihih. Pelin yläreunassa oleva "Wind"-palkki kertoo sen hetkisen tuulen suunnan ja voimakkuuden.

Näppäinkomennot:
* Tykin piipun kääntäminen: Nuolinäppäimet ylös/alas
* Ampumisvoiman säätäminen: Nuolinäppäimet oikea/vasen
* Ampuminen/tähtäys valmis: ENTER
* Valitse ammus 1: Numeronäppäin 1
* Valitse ammus 2: Numeronäppäin 2

Pelatessasi tietokonetta vastaan, voit tallentaa saavuttamasi pistemäärän pelin päätyttyä painamalla näppäintä "S". Pisteiden tallennuksen yhteydessä pelin kysyy nimimerkkiä, jolla haluat tallentaa pisteet.

Pisteiden lasku tapahtuu seuraavalla tavalla:
* Pelaaja saa yhden pisteen jokaista 10 jäljelle jäänyttä elämäpistettä kohti.
* Pelaaja saa yhden pisteen jokaisesta vastustajaan osumasta ammuksesta.
