# Tykkipeli

Harjoitustyö kurssille Ohjelmistotekniikan menetelmät (kevät 2018).\
Lisäksi kansiosta *laskarit* löytyy kurssin laskuharjoitusten vastaukset.

## Dokumentaatio
[Vaatimusmäärittely](https://github.com/oskarioskari/otm-harjoitustyo/blob/master/dokumentointi/vaatimusmaarittely.md)\
[Tuntikirjanpito](https://github.com/oskarioskari/otm-harjoitustyo/blob/master/dokumentointi/tuntikirjanpito.md)\
[Arkkitehtuuri](https://github.com/oskarioskari/otm-harjoitustyo/blob/master/dokumentointi/arkkitehtuuri.md)

## Komentorivitoiminnot
### Testaus
Ohjelma testataan komennolla
```
mvn test
```
Testauskattavuusraportti luodaan komennolla
```
mvn test jacoco:report
```
### Checkstyle
Checkstyle tarkistukset suoritetaan komennolla
```
mvn jxr:jxr checkstyle:checkstyle
```
Mahdolliset virheilmoitukset löydät tiedostosta *target/site/checkstyle.html*
