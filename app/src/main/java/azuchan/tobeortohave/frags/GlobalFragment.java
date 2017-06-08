package azuchan.tobeortohave.frags;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import azuchan.tobeortohave.R;
import azuchan.tobeortohave.datas.Commandes;
import azuchan.tobeortohave.datas.ProdAlert;

public class GlobalFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView mRecyclerView2;
    private CommandesAdapter mAdapter;
    private ProdAlertAdapter mAdapter2;

    public GlobalFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static GlobalFragment newInstance() {
        GlobalFragment fragment = new GlobalFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.global_view, container, false);

        //MON CODE

        mRecyclerView = (RecyclerView) v.findViewById(R.id.commandes);
        mRecyclerView2 = (RecyclerView) v.findViewById(R.id.prodAlert);

        // attention
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView2.setHasFixedSize(true);

        // specify an adapter (see also next example)
        mAdapter = new CommandesAdapter(Commandes.list, this.getActivity(), this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter2 = new ProdAlertAdapter(ProdAlert.list);
        mRecyclerView2.setAdapter(mAdapter2);

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this.getActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        LinearLayoutManager mLinearLayoutManager2 = new LinearLayoutManager(this.getActivity());
        mRecyclerView2.setLayoutManager(mLinearLayoutManager2);
        //FIN

        return v;
    }
}