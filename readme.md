Servlet

1.  Kasutage ObjectMapper-it Java objektide konverteerimiseks Json-iks
    ja tagasi. Klassis json.JsonConverter on selle tööga alustatud.

    Hiljem on plaanis ObjectMapper-it veebirakenduse sees kasutada aga
    kõigepealt võiks selle kasutamise eraldi läbi proovida.

    Klassis json.JsonConverterRecord on sama asi aga klassi asemel on
    record. Proovige ka see läbi.

    Klassis json.JsonConverterList on list json kujul. Tehke sellest
    ObjectMapperi abil Post objektide list.

    Tüübi parameetriks peate kirjutama Post\[\].class või new
    TypeReference\<List\<Post\>\>() {}

2.  Käivitage veebiserver ja veenduge projektis olev HelloServlet, et
    vastab (http://localhost:8080/hello).

    Gradle sakk paremas ääres -\> Tasks -\> gretty -\> appRun

    (või appStart, kui appRun ei toimi)

    Kasutage Postman-i või mõnda muud Http klient päringu saatmiseks.

    Lisage HelloServlet-ile doPost meetod. Postitage sinna mingi sisend
    ja veenduge, et see kohale jõuab.

    String input = Util.readStream(request.getInputStream()). (klass
    Util on projektiga kaasas)

3.  Veenduge, et teil õnnestub Json formaadis sisendit servletile saata
    ja vastusena tagasi saada.

    a)  Saatke Postman-ist päring, mille sisuks on Json kood. Määrake ka
        vastav Content-Type.

    b)  Lugege Servletist päringu sisu ja teisendage see Java objektiks.
        Vajalik on vastavate väljadega Java objekti, milles Json-ist
        tulnud infot hoida.

    c)  Muutke sisendist saadud Java objektil mõne välja väärtust ja
        teisendage muudetud objekt Json-iks tagasi.

    d)  Saatke Json kliendile tagasi. Määrake ka vastav Content-Type.

4.  Looge listener, mis teatab rakenduse käivitamisest.

5.  Looge listener-is isend klassist Post ja tehke see HelloServlet-ile
    kättesaadavaks. Seda saate teha kasutades ServletContext-i
    setAttribute() meetodit.

6.  Looge uus servlet nimega OtherServlet ja jätke sellelt ära
    @WebServlet annotatsioon. Seega käivitamisel seda servlet-i
    automaatselt ei laeta.

    Tehke loodud servlet-ist uus isend ja registreerige see manuaalselt.

    Seda saate teha eelmises punktis loodud listener klassi abil.

    sce.getServletContext().addServlet("`<nimi>`{=html}",
    `<servlet-i isend>`{=html})

    Meetod addServlet() tagastab objekti mille abil saab määrata mis on
    uue servlet-i aadressiks (addMapping() meetod).

7.  Lisage Post klassile Lombok teegi abil setter-id ja getter-id.

    Sõltuvusteks build.gradle failis on:

    compileOnly 'org.projectlombok:lombok:1.18.40'

    annotationProcessor 'org.projectlombok:lombok:1.18.40'

    Veenduge, et getterid/setterid on olemas.

    a)  Kirjutage kood, mis kasutab teie tehtud klassi ja veenduge, et
        see kompileerub IDEA-s.

    b)  Veenduge, et kood kompileerub ka käsurealt:

        > gradlew build \# Windows (Cmd) ./gradlew build \#
        > Mac/Linux/Windows PowerShell

    Lisage konstruktor kõigi argumentidega ja konstruktor ilma
    argumentideta ja veenduge, et ka see töötab.

8.  Kasutage Lombok teeki Java record-itega töö lihtsustamiseks.

    Paketis "records" on record Task. Record-i puhul peame kõik väjad
    konstruktoris ette andma ja hiljem neid muuta ei saa. Kui välju on
    palju, siis on selline pikk arugmentide nimekiri halvasti loetav,
    kuna koodist pole selgelt näha, mis väärtus, mis väljale pannakse.

    Lisage record-ile Task annotatsioon @Builder.

    Lisatud võimalusi saate kasutada nii:

    Task task1 = Task.builder() .name("Task 1") .build();

    Record tüüpi objekte ei saa muuta. Muudatuste tegemiseks peame
    tegema uue objekti ja sellele kopeerima vanad väärtused ja asendama
    selle väärtuse, mida muuta soovime.

    Selle teeb mugavamaks Lombok-i @With annotatsioon.

    Lisage record-ile Task annotatsioon @With.

    Task task1 = ... task1 = task1.withCompleted(true);

9.  Kontrollige koodi vastavust projekti stiilireeglitele läbi Idea ja
    läbi Gradle.

    Stiilireeglid on projekti testide projektis:
    https://github.com/mkalmo2/icd0011tests.

    a)  Paigaldage PMD plugin.

        Määrake reeglite fail

        Settings -\> Other Settings -\> PMD

        Seadete faili leiate projekti testide juurest pmd/ruleset.xml.

        Käivitage kontroll

        Parem klõps kataloogil, mille sisu soovime kontrollida -\> Run
        PMD -\> Custom Rules -\> ruleset

    b)  Määrake failis build.gradle reeglite asukoht ja käivtage task
        pmdMain.

10. Proovige kasutada projekti teste HelloServlet-iga suhtlemiseks.

https://github.com/mkalmo2/icd0011tests (test Hw03.java)

Testide projekti peaksite avama servleti projektist eraldi. Seega
peaksid mõlemad projektid korraga avatud olema.

a)  Veenduge, et servlet-i rakendus ei tööta ja pange käima esimene
    (baseUrlResponds()) test. Test peaks ebaõnnestuma.

b)  Pange käima servlet-i rakendus ja käivitage esimene test. Test peaks
    õnnestuma.

c)  Määrake HelloServlet-i aadressiks "api/orders". Lisage doPost()
    meetodisse rida, mis trükib saadetud sisendi konsooli
    (System.out.println()).

    Pange käima servlet-i rakendus ja käivitage teine test. Vaadake
    servlet-i rakenduse konsoolist, mis info välja trükiti.

d)  Uurige lähemalt testi ja rakenduse omavahelist suhtulust.

    Kui kirjutate testi algusesse rea:

    enableLogging();

    siis näite, millist infot test saadab ja mida server vastab.

Seletused ja lahendused: https://youtu.be/-vm2WwtFZtM
