# Tykkipeli

Harjoitustyö kurssille Ohjelmistotekniikan menetelmät (kevät 2018).\
Valitsemanani harjoitustyön aiheena on pienimuotoinen peli, nimeltään *Tykkipeli*.\
Pelissä kaksi pelaajaa antavat ensin tulikomentonsa tykeille (ampumiskulma ja voimakkuus), jonka jälkeen tykit ampuvat toisiaan samanaikaisesti.\
Pelin tavoitteena on tuhota toisen pelaajan tykki.\
\
[Loppupalautus](https://github.com/oskarioskari/otm-harjoitustyo/releases/tag/v1.0)\
[Viikon 6 release](https://github.com/oskarioskari/otm-harjoitustyo/releases/tag/v0.6)\
[Viikon 5 release](https://github.com/oskarioskari/otm-harjoitustyo/releases/tag/v0.5)

## Dokumentaatio
[Käyttöohje](https://github.com/oskarioskari/otm-harjoitustyo/blob/master/dokumentointi/kayttoohje.md)\
[Vaatimusmäärittely](https://github.com/oskarioskari/otm-harjoitustyo/blob/master/dokumentointi/vaatimusmaarittely.md)\
[Arkkitehtuuri](https://github.com/oskarioskari/otm-harjoitustyo/blob/master/dokumentointi/arkkitehtuuri.md)\
[Testausdokumentti](https://github.com/oskarioskari/otm-harjoitustyo/blob/master/dokumentointi/testaus.md)\
[Tuntikirjanpito](https://github.com/oskarioskari/otm-harjoitustyo/blob/master/dokumentointi/tuntikirjanpito.md)

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
Testauskattavuusraporttia voi tarkastella hakemistosta *target/site/jacoco/index.html*

### Checkstyle
Checkstyle tarkistukset suoritetaan komennolla
```
mvn jxr:jxr checkstyle:checkstyle
```
Mahdolliset virheilmoitukset löydät hakemistosta *target/site/checkstyle.html*

### .jar:n generointi
Suoritettavan .jar-tiedoston generointi tapahtuu komennolla
```
mvn package
```
Suoritettava .jar-tiedosto löytyy hakemistosta *target/*

### JavaDoc
JavaDoc generoidaan komennolla
```
mvn javadoc:javadoc
```
JavaDoc löytyy hakemistosta *target/site/apidocs/index.html*
