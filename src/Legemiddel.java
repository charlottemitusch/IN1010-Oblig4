public abstract class Legemiddel {
    protected String navn;
    protected int id;
    protected static int idTeller = 0;
    protected double pris;
    protected double virkestoff;

    public Legemiddel(String navn, double pris, double virkestoff) {
        this.navn = navn;
        this.pris = pris;
        this.virkestoff = virkestoff;
        this.id = idTeller++;
    }

    public int hentId() {
        return id;
    }

    public String hentNavn() {
        return navn;
    }

    public double hentPris() {
        return pris;
    }

    public double hentVirkestoff() {
        return virkestoff;
    }

    public double settNyPris(double nyPris) {
        pris = nyPris;
        return nyPris;

    }

    public void hentInfo() {

        System.out.println("Navn: " + this.hentNavn());
        System.out.println("ID: " + this.hentId());
        System.out.println("Pris: " + this.hentPris() + " kr ");
        System.out.println("Virkestoff: " + this.hentVirkestoff());

    }

}

