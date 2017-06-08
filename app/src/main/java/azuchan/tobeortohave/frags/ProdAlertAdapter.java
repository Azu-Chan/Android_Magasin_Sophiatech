package azuchan.tobeortohave.frags;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import azuchan.tobeortohave.R;
import azuchan.tobeortohave.datas.UnitProdAlert;

/**
 * Created by dylan on 08/06/17.
 */

public class ProdAlertAdapter extends RecyclerView.Adapter<azuchan.tobeortohave.frags.ProdAlertAdapter.ProdAlertViewHolder> {
    List<UnitProdAlert> liste;

    ProdAlertAdapter(List<UnitProdAlert> liste){
        this.liste = liste;
    }

    @Override
    public ProdAlertAdapter.ProdAlertViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.prod_alert, viewGroup, false);
        ProdAlertAdapter.ProdAlertViewHolder cvh = new ProdAlertAdapter.ProdAlertViewHolder(v);
        return cvh;
    }

    public void onBindViewHolder(ProdAlertAdapter.ProdAlertViewHolder commViewHolder, int i) {
        commViewHolder.ref.setText(liste.get(i).ref);
        commViewHolder.status.setText(liste.get(i).status);

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return liste.size();
    }

    public static class ProdAlertViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView ref;
        TextView status;

        ProdAlertViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.listProdAlert);

            ref = (TextView)itemView.findViewById(R.id.reffCom);
            status = (TextView)itemView.findViewById(R.id.statCom);
        }
    }
}
