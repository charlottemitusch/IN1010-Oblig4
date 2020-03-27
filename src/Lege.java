public class Lege implements Comparable<Lege>{
    protected String navn;
    Lenkeliste<Resept> utskrevedeResepter = new Lenkeliste<Resept>();
    protected int ikkeSpesialist;


    public Lege(String navn) {
        this.navn = navn;
    }

    public String hentNavn() {

        return navn;
    }

    public int hentKontrollId(){
        return ikkeSpesialist;
    }

    public String toString() {
        return "Navn: " + navn + "\nTittel: Lege";
    }

    public int compareTo(Lege lege) {
        return navn.compareTo(lege.hentNavn());
    }

    public Lenkeliste<Resept> hentUtskrevedeResepter(){
        return this.utskrevedeResepter;

    }

    public boolean sjekkLege(Legemiddel legemiddel){
        return (!(this instanceof Spesialistlege) && legemiddel instanceof Narkotisk);
    }


    public HvitRes skrivHvitResept(Legemiddel legemiddel, Pasient pasient, int reit) throws
            UlovligUtskrift{
        if (sjekkLege(legemiddel)) throw new UlovligUtskrift(this, legemiddel);
        HvitRes resept = new HvitRes(legemiddel, this, pasient, reit);
        utskrevedeResepter.leggTil(resept);
        return resept;
    }

    public Militaerresept skrivMillitaerResept(Legemiddel legemiddel, Pasient pasient, int
            reit) throws UlovligUtskrift{
        if (sjekkLege(legemiddel)) throw new UlovligUtskrift(this, legemiddel);
Militaerresept resept = new Militaerresept(legemiddel,this, pasient, reit);
        utskrevedeResepter.leggTil(resept);
        return resept;
    }

    public PResept skrivPResept(Legemiddel legemiddel, Pasient pasient) throws UlovligUtskrift {
        if (sjekkLege(legemiddel)) throw new UlovligUtskrift(this, legemiddel);
        PResept resept = new PResept(legemiddel,this, pasient);
        utskrevedeResepter.leggTil(resept);
        return resept;
    }

    public BlåRes skrivBlaaResept(Legemiddel legemiddel, Pasient pasient, int reit) throws
            UlovligUtskrift {
        if (sjekkLege(legemiddel)) throw new UlovligUtskrift(this, legemiddel);
        BlåRes resept = new BlåRes(legemiddel,this, pasient, reit);
        utskrevedeResepter.leggTil(resept);
        return resept;
    }

}
