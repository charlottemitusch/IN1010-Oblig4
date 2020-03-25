public abstract class HvitRes extends Resept {

    public HvitRes(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit){
        super(legemiddel, utskrivendeLege, pasientId, reit);
    }
    @Override
    public String farge() {
        return "hvit";
    }


}
