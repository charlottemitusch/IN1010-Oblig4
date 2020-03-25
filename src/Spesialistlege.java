class Spesialistlege extends Lege implements Godkjenningsfritak {

    protected int kontrollID;

    public Spesialistlege(String navn, int kontrollID) {
        super(navn);
        this.kontrollID = kontrollID;

    }

    public int hentKontrollID() {
        return kontrollID;
    }
}



