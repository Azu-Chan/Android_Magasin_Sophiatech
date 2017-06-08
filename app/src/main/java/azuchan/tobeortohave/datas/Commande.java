package azuchan.tobeortohave.datas;

/**
 * Created by dylan on 04/06/17.
 */

public class Commande {
    public String ref;
    public String qte;
    public String mag;

    public Commande(String r, String q, String m){
        ref = r;
        qte = q;
        mag = m;
    }
}