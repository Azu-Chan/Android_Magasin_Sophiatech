package azuchan.tobeortohave.frags;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.PieData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import azuchan.tobeortohave.R;
import azuchan.tobeortohave.datas.Charts;

public class StatsFragment extends Fragment {

    public StatsFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static StatsFragment newInstance() {
        StatsFragment fragment = new StatsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.stats_view, container, false);

        BarChart b = (BarChart)v.findViewById(R.id.barChart);
        BarData data = new BarData(Charts.getClientsCentre(), Charts.getClientsMag());

        data.setBarWidth(0.70f); // set custom bar width
        b.setData(data);
        b.getDescription().setText("Flux de personnes");
        b.setFitBars(true); // make the x-axis fit exactly all bars
        b.invalidate();

        PieChart p = (PieChart)v.findViewById(R.id.pieChart);

        PieData data2 = new PieData(Charts.getStatutVendeurs());
        p.setData(data2);
        p.setDescription(null);
        p.invalidate(); // refresh

        return v;
    }
}