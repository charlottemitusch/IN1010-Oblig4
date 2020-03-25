public class Vanedannende extends Legemiddel {
    protected int styrkeVane; // vanedannende styrke

    public Vanedannende(String navn, double pris, double virkestoff, int styrkeVane) {
        super(navn, pris, virkestoff);
        this.styrkeVane=styrkeVane;
    }

    public int hentVandedannendeStyrke() {
        return styrkeVane;
    }

    public void hentInfo(){
        System.out.println("Navn: " + this.hentNavn());
        System.out.println("ID: " + this.hentId());
        System.out.println("Pris: " + this.hentPris() + " kr ");
        System.out.println("Virkestoff: " + this.hentVirkestoff() + " mg");
        System.out.println("Narkotisk styrke: " + hentVandedannendeStyrke());
    }
}
