public abstract class Resept {
    protected Legemiddel legemiddel;
    protected Lege utskrivendeLege;
    protected Pasient pasient;
    protected int reit;
    protected int id;
    protected static int idTeller = 0;

    public Resept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        this.legemiddel = legemiddel;
        this.utskrivendeLege = utskrivendeLege;
        this.pasient = pasient;
        this.reit = reit;
        id = idTeller++;

    }

    public int hentId() {
        return id;
    }

    public Legemiddel hentHeleLegemiddel(){
        return this.legemiddel;
    }

    public String hentLegemiddel() {
        return legemiddel.hentNavn();
    }

    public String hentLege() {
        return utskrivendeLege.hentNavn();
    }

    public int hentPasientId() {
        return id;
    }

    public int hentReit() {
        return reit;
    }

    public boolean bruk() {
        if (reit > 0) {
            reit--;
            return true;
        }
        return false;
    }

    abstract public String farge();

    abstract public double prisAaBetale();

    public String toString() { //Metode toString() som returnerer et tilpasset utskrift til et objekt av klassen.
        return legemiddel + "\nSkrevet ut av " + utskrivendeLege + " til pasient: " + id + "\nReit igjen: " + reit;
    }


    public void skrivUtInfo() {
        System.out.println("ID: " + this.hentId());
        System.out.println("Legemiddel: " + this.hentLegemiddel());
        System.out.println("Utskrivende lege: " + this.hentLege());
        System.out.println("PasientID: " + this.hentPasientId());
        System.out.println("Reit: " + this.hentReit());
    }


}












