class BlåRes extends Resept {
    public BlåRes(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        super(legemiddel, utskrivendeLege, pasient, reit);
    }

    public String farge() {

        return "blå";
    }

    //skal betale 1/4 av pris
    public double prisAaBetale() {
        return legemiddel.hentPris() * 0.25;
    }

    //til siste test
    public void skrivUt() {
        super.skrivUtInfo();
        System.out.println("Farge: " + this.farge());
        System.out.println("Pris å betale : " + this.prisAaBetale());
    }
}




