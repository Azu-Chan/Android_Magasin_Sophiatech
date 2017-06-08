package azuchan.tobeortohave.datas;

/**
 * Created by dylan on 17/05/17.
 */

public class Product {
    public String ref;
    public String nom;
    public String prix;
    public String link;
    public String categorie;
    public String qte;
    public String status;

    public Product(String[] datas){
        ref = datas[0];
        nom = datas[1];
        prix = datas[2];
        link = datas[3];
        categorie = datas[4];
        qte = datas[5];
        status = datas[6];
    }

}
