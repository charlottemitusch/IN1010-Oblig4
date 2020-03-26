import java.util.Iterator;

class Lenkeliste<T> implements Liste<T> {
    private int stoerrelse = 0;

    //indre klasse
    public class Node {
        T data;
        Node neste = null;


        public Node(T x) {
            this.data = x;
        }
    }

    public Node foran = new Node(null);

    public class LenkelisteIterator implements Iterator<T>, Liste<T> {
        private Liste<T> privatListe;
        private int indeks = 0;

        public LenkelisteIterator(Liste<T> lx) {
            privatListe = lx;
        }

        @Override
        public boolean hasNext() {
            return indeks<privatListe.stoerrelse();
        }

        @Override
        public T next() {
            return privatListe.hent(indeks++);
        }

        public LenkelisteIterator iterator(){
            return new LenkelisteIterator(this);
        }

        public int stoerrelse() {

            return stoerrelse;
        }


        //først inn, først ut, FIFO
        public void leggTil(T x) {
            //sjekkerom listen er tom
            if (foran.neste == null) {
                foran.neste = new Node(x);
            } else {
                Node node = foran;
                //finner siste plass i listen
                while (node.neste != null) {
                    node = node.neste;
                }

                node.neste = new Node(x);
            }
            stoerrelse++;
        }


        // fjerne og returne elementet i starten av listen
        public T fjern() {
            Node node = foran.neste;

            if (foran.neste == null) {
                throw new UgyldigListeIndeks(-1);
            } else {
                foran.neste = node.neste;
            }
            stoerrelse--;
            return node.data;

        }


        //sette inne element i gitt posisjon og overskrive det som var der fra før
        public void sett(int pos, T x) {
            Node node = hentNode(pos);
            node.data = x;
        }


        public void leggTil(int pos, T x) {
            if (pos < 0 || pos > stoerrelse()) {
                throw new UgyldigListeIndeks(pos);
            } else if (pos == 0) {
                Node node = new Node(x);
                node.neste = foran.neste;
                foran.neste = node;
                stoerrelse++;
            } else if (pos == stoerrelse) {
                leggTil(x);
            } else {
                Node node = foran;
                for (int i = 0; i < pos; i++) {
                    node = node.neste;
                }
                Node nyNode = new Node(x);
                nyNode.neste = node.neste;
                node.neste = nyNode;
                stoerrelse++;
            }
        }

        @Override
        public T hent(int pos) {
            Node node = hentNode(pos);
            return node.data;
        }

        public Node hentNode(int pos) {
            if (pos < 0 || pos >= stoerrelse()) {
                throw new UgyldigListeIndeks(pos);
            }
            Node node = foran.neste;
            for (int i = 0; i < pos; i++) {
                node = node.neste;

            }
            return node;

        }


        @Override
        public T fjern(int pos) {
            if (pos < 0 || pos >= stoerrelse) {
                throw new UgyldigListeIndeks(pos);
            }
            Node node = foran;
            for (int i = 0; i < pos; i++) {
                node = node.neste;
            }
            Node fjern = node.neste;
            node.neste = fjern.neste;
            stoerrelse--;
            return fjern.data;

        }

    }
    }

