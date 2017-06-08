package azuchan.tobeortohave.datas;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoadImg extends AsyncTask<String, Void, Bitmap> {
    ImageView i;

    public LoadImg(ImageView i){
        super();
        this.i = i;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        InputStream im = null;
        Bitmap b = null;
        URL uIm = null;

        try {
            uIm = new URL(params[0]);

            HttpURLConnection connection = (HttpURLConnection) uIm.openConnection();

            im = connection.getInputStream();
            b = BitmapFactory.decodeStream(im);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return b;
    }

    protected void onPostExecute(Bitmap res) {
        i.setImageBitmap(res);
    }
}