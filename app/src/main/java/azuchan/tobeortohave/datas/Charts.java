package azuchan.tobeortohave.datas;

import android.graphics.Color;

import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by dylan on 17/05/17.
 */

public final class Charts {

    public static BarDataSet getClientsCentre(){

        ArrayList<BarEntry> entries = new ArrayList<>();

        for(int i = 8; i <= 20; i++){
                Random r = new Random();
                int valeur = 800 + r.nextInt(1000 - 800);

                entries.add(new BarEntry(i, valeur));
        }
        BarDataSet b = new BarDataSet(entries, "flux de passants au centre");

        b.setColor(Color.rgb(47, 226, 125));
        return b;
    }

    public static BarDataSet getClientsMag(){

        ArrayList<BarEntry> entries = new ArrayList<>();

        for(int i = 8; i <= 20; i++){
                Random r = new Random();
                int valeur = 20 + r.nextInt(130 - 20);

                entries.add(new BarEntry(i, valeur));
        }
        BarDataSet b = new BarDataSet(entries, "flux de passants dans votre magasin");

        b.setColor(Color.rgb(234, 25, 67));
        return b;
    }

    public static PieDataSet getStatutVendeurs(){

        List<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry(4, "De repos"));
        entries.add(new PieEntry(2, "En pause"));
        entries.add(new PieEntry(7, "Disponibles"));
        entries.add(new PieEntry(1, "Absence justifiée"));
        entries.add(new PieEntry(1, "Absence injustifiée"));

        PieDataSet set = new PieDataSet(entries, "");
        set.setColors(Color.rgb(203, 96, 224), Color.rgb(25, 112, 103),
                Color.rgb(80, 167, 196), Color.rgb(85, 132, 78),
                Color.rgb(127, 127, 127));

        return set;
    }
}
