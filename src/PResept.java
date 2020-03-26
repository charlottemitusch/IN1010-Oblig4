public class PResept extends HvitRes {
    public PResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient) {
        super(legemiddel, utskrivendeLege, pasient, 3);
    }


    //skal ha 108kr i avslag
    public double prisAaBetale() {
        double fullpris = legemiddel.hentPris();
        if (fullpris >= 108) {
            return fullpris - 108;
        } else {
            return 0.0;
        }
    }
    //til senere utskrift
    public void skrivUt() {
        super.skrivUtInfo();
        System.out.println("Farge: " + this.farge());
        System.out.println("Pris å betale : " + this.prisAaBetale());
    }
}
