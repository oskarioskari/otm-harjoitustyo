# Arkkitehtuurikuvaus

## Rakenne
### Ohjelman rakenne yleisellä tasolla
Ohjelman toiminnalliset osat (tykkipeli.ui, tykkipeli.logic, tykkipeli.dao) noudattavat kolmitasoista kerrosarkkitehtuuria, joiden lisäksi ohjelman toimintaan liittyvät olioluokat on jaettu kahteen pakettiin (tykkipeli.objects, tykkipeli.physicobjects).\
Oheessa rakennetta havainnollistava kuva:\
<img src="https://github.com/oskarioskari/otm-harjoitustyo/blob/master/dokumentointi/kuvat/pakkausrakenne.png" width="521">
\
Pakkaus *tykkipeli.ui* sisältää javafx:llä toteutetun käyttöliittymän, *tykkipeli.logic* sisältää sovelluslogiikan ja *tykkipeli.dao* sisältää pysyväistallennuksesta vastaavat osat.\
Näiden lisäksi *tykkipeli.physicobjects* sisältää pelin graafiset ja fysiikkaa noudattavat objektit sekä *tykkipeli.objects* sisältää muut pelin objektit.

### Tarkempi luokkakaavio
Luokkakaavio:\
<img src="https://github.com/oskarioskari/otm-harjoitustyo/blob/master/dokumentointi/kuvat/UML-viikko6.png" width="470">
\

## Toiminnallisuudet
### Fysiikkamallinnus
Pelissä ammusten lentoradat lasketaan reaaliajassa käyttäen hyväksi Velocity Verlet -algoritmia.\

Sekvenssikaavio tilanteesta, jossa ammusta liikutetaan yksi askel:\
<img src="https://github.com/oskarioskari/otm-harjoitustyo/blob/master/dokumentointi/kuvat/sekvenssikaavio-moveAmmo.png" width="732">
