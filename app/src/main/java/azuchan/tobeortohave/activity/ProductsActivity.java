package azuchan.tobeortohave.activity;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import azuchan.tobeortohave.R;
import azuchan.tobeortohave.datas.MyMagSave;
import azuchan.tobeortohave.datas.ProdAlert;
import azuchan.tobeortohave.datas.Product;
import azuchan.tobeortohave.datas.ProductsCreator;
import azuchan.tobeortohave.datas.UnitProdAlert;

public class ProductsActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        Intent i= getIntent();
        Bundle b = i.getExtras();

        String magasin = (String)b.get("MAGASIN");

        int SDK_INT = android.os.Build.VERSION.SDK_INT;

        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

            //MON CODE

            mRecyclerView = (RecyclerView) findViewById(R.id.products);

            // attention
            mRecyclerView.setHasFixedSize(true);

            String datas = ProductsActivity.loadRSS("http://projandroid.katazu.fr/generaterss.php?mag=" + magasin);

            Product[] prod = null;

            try {
                prod = new ProductsCreator(datas).generate();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            boolean boo = !magasin.equals("sophiatech");

            if(!boo){
                ProdAlert.list = new ArrayList<>();
                for(Product p : prod){
                    if(p.status.equals("LIVRAISON") || p.status.equals("RUPTURE DE STOCK"))
                        ProdAlert.list.add(new UnitProdAlert(p.ref, p.status));
                }
            }

            // specify an adapter (see also next example)
            mAdapter = new ProductsAdapter(prod, boo, magasin, this);
            mRecyclerView.setAdapter(mAdapter);

            LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mLinearLayoutManager);
            //FIN
        }
    }

    public static String loadRSS(String path){
        BufferedReader im = null;
        String res = "";
        URL url = null;
        String ch;
        try {
            url = new URL(path);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            im = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            while((ch=im.readLine()) != null){
                res += ch;
            }

            im.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return res;
    }
}
