public class Narkotisk extends Legemiddel{
    protected int styrkeNarko; //narkotisk styrke


    public Narkotisk(String navn, double pris, double virkestoff, int styrkeNarko) {
        super(navn, pris, virkestoff);
        this.styrkeNarko=styrkeNarko;
    }

    public int hentNarkotiskStyrke() {
        return styrkeNarko;
    }


    public void hentInfo(){
        System.out.println("Navn: " + this.hentNavn());
        System.out.println("ID: " + this.hentId());
        System.out.println("Pris: " + this.hentPris() + " kr ");
        System.out.println("Virkestoff: " + this.hentVirkestoff() + " mg");
        System.out.println("Narkotisk styrke: " + hentNarkotiskStyrke());
    }

}
