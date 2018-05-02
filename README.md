# Tykkipeli

Harjoitustyö kurssille Ohjelmistotekniikan menetelmät (kevät 2018).\
Harjoitustyön aiheena on pienimuotoinen peli, nimeltä *Tykkipeli*. Pelissä kaksi pelaajaa antavat ensin tulikomentonsa tykeille (ampumiskulma ja voimakkuus), jonka jälkeen tykit ampuvat yhtäaikaa.\
Pelin tavoitteena on tuhota toisen pelaajan tykki.\
\
[Linkki viikon 5 releaseen](https://github.com/oskarioskari/otm-harjoitustyo/releases/tag/v0.5)

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

### .jar:n generointi
Suoritettavan .jar-tiedoston generointi tapahtuu komennolla
```
mvn package
```
### JavaDoc
JavaDoc generoidaan komennolla
```
mvn javadoc:javadoc
```
JavaDoc löytyy hakemistosta *target/site/apidocs/index.html*
