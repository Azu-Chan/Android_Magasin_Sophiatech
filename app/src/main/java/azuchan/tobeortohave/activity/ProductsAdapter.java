package azuchan.tobeortohave.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import azuchan.tobeortohave.R;
import azuchan.tobeortohave.datas.Commande;
import azuchan.tobeortohave.datas.Commandes;
import azuchan.tobeortohave.datas.LoadImg;
import azuchan.tobeortohave.datas.MyMagSave;
import azuchan.tobeortohave.datas.Product;

/**
 * Created by dylan on 17/05/17.
 */

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder> {
    Product[] products;
    boolean affich;
    private Activity a;
    private String mag;

    public ProductsAdapter(Product[] p, boolean mag, String magasin, Activity a){
        products = p;
        affich = mag;
        this.mag = magasin;
        this.a = a;
    }

    @Override
    public ProductsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product, viewGroup, false);
        ProductsViewHolder pvh = new ProductsViewHolder(v);
        return pvh;
    }

    public void onBindViewHolder(ProductsViewHolder prodViewHolder, int i) {
        final int pi = i;
        prodViewHolder.id.setText(products[i].ref);
        prodViewHolder.nom.setText(products[i].nom);
        prodViewHolder.prix.setText(products[i].prix + " €");
        prodViewHolder.categorie.setText(products[i].categorie);
        prodViewHolder.qte.setText(products[i].qte);
        prodViewHolder.status.setText("  " + products[i].status + "  ");

        if(!affich || new Integer(products[i].qte) < 1)
            prodViewHolder.comm.setVisibility(View.INVISIBLE);
        else
            prodViewHolder.comm.setVisibility(View.VISIBLE);

        new LoadImg(prodViewHolder.link).execute(products[i].link);

        if(prodViewHolder.comm.getVisibility() == View.VISIBLE){
            prodViewHolder.comm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(a);

                    // set title
                    alertDialogBuilder.setTitle("Confirmation de commande");

                    String msg = "Commande de " + products[pi].ref;

                    msg += ", vous en avez ";
                    if (MyMagSave.qtes.containsKey(products[pi].ref)) {
                        msg += MyMagSave.qtes.get(products[pi].ref);
                    } else {
                        msg += "0";
                    }
                    msg += " en stock.";

                    final EditText input = new EditText(a);
                    input.setInputType(InputType.TYPE_CLASS_NUMBER);
                    input.setRawInputType(Configuration.KEYBOARD_12KEY);

                    // set dialog message
                    alertDialogBuilder
                            .setMessage(msg)
                            .setView(input)
                            .setCancelable(false)
                            .setPositiveButton("Commander",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    // if this button is clicked
                                    Commandes.list.add(new Commande(products[pi].ref, input.getText().toString(), mag));
                                }
                            })
                            .setNegativeButton("Annuler",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    // if this button is clicked, just close
                                    // the dialog box and do nothing
                                    dialog.cancel();
                                }
                            });

                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();

                    // show it
                    alertDialog.show();
                }
            });
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return products.length;
    }

    public static class ProductsViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView id;
        TextView nom;
        TextView prix;
        ImageView link;
        Button comm;
        TextView categorie;
        TextView qte;
        TextView status;

        ProductsViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.card_view);

            nom = (TextView)itemView.findViewById(R.id.nomView);
            id = (TextView)itemView.findViewById(R.id.refView);
            prix = (TextView)itemView.findViewById(R.id.prixView);
            link = (ImageView) itemView.findViewById(R.id.imgView);
            categorie = (TextView)itemView.findViewById(R.id.catView);
            qte = (TextView)itemView.findViewById(R.id.qteView);
            status = (TextView)itemView.findViewById(R.id.statView);
            comm = (Button) itemView.findViewById(R.id.commandView);
        }
    }
}