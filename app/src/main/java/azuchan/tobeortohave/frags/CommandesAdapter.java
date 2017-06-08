package azuchan.tobeortohave.frags;

/**
 * Created by dylan on 04/06/17.
 */

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import azuchan.tobeortohave.R;
import azuchan.tobeortohave.datas.Commande;
import azuchan.tobeortohave.datas.Commandes;

public class CommandesAdapter extends RecyclerView.Adapter<azuchan.tobeortohave.frags.CommandesAdapter.CommandesViewHolder> {
    List<Commande> commandes;
    private Activity a;
    private Fragment f;

    CommandesAdapter(List<Commande> c, Activity a, Fragment f){
        this.commandes = c;
        this.a = a;
        this.f =f;
    }

    @Override
    public CommandesViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.commande, viewGroup, false);
        CommandesViewHolder cvh = new CommandesViewHolder(v);
        return cvh;
    }

    public void onBindViewHolder(CommandesViewHolder commViewHolder, int i) {
        final int pi = i;
        commViewHolder.ref.setText(commandes.get(i).ref);
        commViewHolder.qte.setText(commandes.get(i).qte);
        commViewHolder.mag.setText(commandes.get(i).mag);

            commViewHolder.cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(a);

                    // set title
                    alertDialogBuilder.setTitle("Confirmation d'annulation");

                    String msg = "Voulez-vous vraiment annuler cette commande ?";

                    // set dialog message
                    alertDialogBuilder
                            .setMessage(msg)
                            .setCancelable(false)
                            .setPositiveButton("Confrimer",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    // if this button is clicked
                                    Commandes.list.remove(pi);

                                    Fragment fragment = GlobalFragment.newInstance();

                                    FragmentTransaction ft = f.getFragmentManager().beginTransaction();
                                    ft.replace(R.id.mainFrame, fragment);
                                    ft.commit();
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

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return commandes.size();
    }

    public static class CommandesViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView ref;
        TextView qte;
        TextView mag;

        CommandesViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.listCom);

            ref = (TextView)itemView.findViewById(R.id.refCom);
            qte = (TextView)itemView.findViewById(R.id.qteCom);
            mag = (TextView)itemView.findViewById(R.id.magCom);
        }
    }
}