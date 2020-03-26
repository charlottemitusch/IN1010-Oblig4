class Stabel<T> extends Lenkeliste<T>  {
    public void leggPaa(T x){

        leggTil(x);
    }

    public T taAv(){
        int lengde = stoerrelse();
        if(lengde == 0){
            throw new UgyldigListeIndeks(0);
        }
        return fjern(lengde-1);
    }

}






