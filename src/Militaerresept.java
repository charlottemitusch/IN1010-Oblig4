public class Militaerresept extends HvitRes {
    public Militaerresept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit) {
        super(legemiddel, utskrivendeLege, pasientId, reit);
    }

    //skal være gratis
    public double prisAaBetale() {

        return 0.00;
    }
    //til senere utskift
    public void skrivUt() {
        super.skrivUtInfo();
        System.out.println("Farge: " + this.farge());
        System.out.println("Pris å betale : " + this.prisAaBetale());
    }
}
