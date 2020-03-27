public class Pasient {
    public String navn;
    public String fodselsnr;
    public int id;
    public static int teller=0;
    public Stabel<Resept> reseptliste= new Stabel<Resept>();

    public Pasient (String navn, String fodselsnr){
        this.navn=navn;
        this.fodselsnr=fodselsnr;
        reseptliste = new Stabel<Resept>();
        id=teller++;

    }
    public int hentId() {
        return id;
    }

    public String hentNavn() {
        return navn;
    }

    public String hentFodselsnummer() {
        return fodselsnr;
    }

    public void leggTilResept(Resept resept) {
        reseptliste.leggPaa(resept);
    }

    public Stabel<Resept> hentReseptListe() {
        return reseptliste;
    }

    @Override
    public String toString() {
        return navn + " (fnr " + fodselsnr + ")" ;
    }

}





   /* En Pasient er en typisk bruker av resepter. Pasienten har et navn og et
        fødselsnummer-tekststreng. Når en ny pasient registreres skal denne i tillegg få en unik ID.
        Pasienter har også en liste over reseptene de har fått utskrevet. Siden pasienten ofte vil
        bruke en resept kort tid etter at den er utskrevet, bruker vi en Stabel<Resept> til å lagre
        pasientens resepter. Det skal både være mulig å legge til nye resepter og hente ut hele
        reseptlisten.
        B2: Endre klassene som tar inn en int pasientid til å ta inn en Pasient pasient.


p


        */