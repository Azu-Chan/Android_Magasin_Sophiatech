package azuchan.tobeortohave.frags;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import azuchan.tobeortohave.activity.ProductsActivity;
import azuchan.tobeortohave.R;

/**
 * Created by dylan on 17/05/17.
 */

public class MagFragment extends Fragment {
    public MagFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static MagFragment newInstance() {
        MagFragment fragment = new MagFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.mag_view, container, false);

        initButtons(v);

        return v;
    }

    private void initButtons(View v) {

        Button b1 =(Button)v.findViewById(R.id.buttonSophia);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProductsActivity.class);
                intent.putExtra("MAGASIN", "sophiatech");
                startActivity(intent);
            }
        });

        Button b2 =(Button)v.findViewById(R.id.buttonMag1);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProductsActivity.class);
                intent.putExtra("MAGASIN", "autreMag1");
                startActivity(intent);
            }
        });

        Button b3 =(Button)v.findViewById(R.id.buttonMag2);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProductsActivity.class);
                intent.putExtra("MAGASIN", "autreMag2");
                startActivity(intent);
            }
        });

        Button b4 =(Button)v.findViewById(R.id.buttonMag3);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProductsActivity.class);
                intent.putExtra("MAGASIN", "autreMag3");
                startActivity(intent);
            }
        });
    }
}
