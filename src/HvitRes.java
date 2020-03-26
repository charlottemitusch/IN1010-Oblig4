    class HvitRes extends Resept {

    public HvitRes(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
        super(legemiddel, utskrivendeLege, pasient, reit);
    }
    @Override
    public String farge() {
        return "hvit";
    }


}
