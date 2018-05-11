# Testausdokumentti

## Yksikkö- ja integraatiotestaus

### Sovelluslogiikka

Sovelluksen sovelluslogiikan muodostavan paketin *tykkipeli.logic* luokkien toimintaa testaavat testit *GameLogicTest*, *GameStatusTest*, *GameAiTest* sekä *ObjectPhysicsTest*, joiden testitapaukset simuloivat erilaisia pelitilanteita.

Testit testaavat myös integraatiota, testaamalla *GameLogic* luokan metodeja, jotka käyttävät pysyväistallennuksesta vastaavan *HighScoreDao* luokan metodeja.

### DAO

Ohjelmassa on vain yksi DAO-luokka, *HighScoresDao*. Ennen jokaista testiä luodaan uusi .db tiedosto "testdatabase.db", joka poistetaan aina testin päätyttyä.

### Testauskattavuus

Sovelluksen testien (ilman käyttöliittymäluokkia) rivikattavuus on 97% ja haaraumakattavuus on 87%.

<img src="https://github.com/oskarioskari/otm-harjoitustyo/blob/master/dokumentointi/kuvat/testauskattavuus.png" width="1068">

## Järjestelmätestaus

Sovelluksen järjestelmätestaus on suoritettu manuaalisesti.

### Asennus

Sovelluksen asentamista ja toimintaa on testattu Linux-ympäristössä.

Pelaamista on testattu sekä ihmis- että tekoälypelaajaa vastaan käyttäen kaikkia vaikeusasteita.

### Toiminnallisuudet

Kaikki määrittelydokumentissa määritellyt vaatimukset on toteutettu ja sovellus toimii käyttöohjeen mukaisesti.

## Sovellukseen jääneet laatuongelmat.

* Sovelluksen päävalikon toiminnassa on havaittu harvinaisempi, satunnaisen oloisesti ilmenevä virhetilanne, jossa valikoiden välillä siirtyminen saa koko näkymän muuttumaan mustaksi. Virhe ei kuitenkaan estä sovelluksen toimintaa, sillä valikon painikkeet palautuvat normaaleiksi siirtämällä hiiren kursorin niiden päälle.
