//sette inn elementer/sortere fra minst til størst

class SortertLenkeliste<T extends Comparable<T>> extends Lenkeliste<T> {


    public void leggTil(T x) {
        //sjekker om liste er tom
        if (foran.neste == null) {
            super.leggTil(x);
            return;
        }
        // sorterer og bruker super som referanse for å kalle på leggTil() fra Lenkeliste.
        for (int i = 0; i < stoerrelse(); i++) {
            if (hent(i).compareTo(x) > 0) {
                super.leggTil(i, x);
                return;
            }
        }

        super.leggTil(x);
    }


        // ettersom metoden leggTil() sorterer elementer riktig fra minst til størst vil alltid det største elementet være sist
        @Override
        public T fjern () {
            int lengde = stoerrelse();
            if (lengde == 0) {
                throw new UgyldigListeIndeks(0);
            }
            return fjern(lengde - 1);
        }

        @Override
        public void sett ( int pos, T x){
            throw new UnsupportedOperationException();
        }

        @Override
        public void leggTil ( int pos, T x){
            throw new UnsupportedOperationException();
        }


    }




