public class Pasient {
    private String navn;
    private
}





   /* En Pasient er en typisk bruker av resepter. Pasienten har et navn og et
        fødselsnummer-tekststreng. Når en ny pasient registreres skal denne i tillegg få en unik ID.
        Pasienter har også en liste over reseptene de har fått utskrevet. Siden pasienten ofte vil
        bruke en resept kort tid etter at den er utskrevet, bruker vi en Stabel<Resept> til å lagre
        pasientens resepter. Det skal både være mulig å legge til nye resepter og hente ut hele
        reseptlisten.
        B2: Endre klassene som tar inn en int pasientid til å ta inn en Pasient pasient.


public class Pasient {
    private String navn;
    private long fodselsnummer;
    private int id;
    private static int teller=0;
    private Stabel<Resept> stabel = new Stabel<Resept>();

    public Pasient(String navn, long fodselsnummer) {
        this.navn=navn;
        this.fodselsnummer=fodselsnummer;
        stabel = new Stabel<Resept>();
        id=teller++;

          String navn;
  String fodselsnr;
  int id;
  int antNark;
  Lenkeliste<Resept> reseptliste = new Stabel<Resept>();

  public Pasient(String navn, String fodselsnr){
    this.navn = navn;
    this.fodselsnr = fodselsnr;



        */