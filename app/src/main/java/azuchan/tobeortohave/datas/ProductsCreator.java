package azuchan.tobeortohave.datas;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

/**
 * Created by dylan on 17/05/17.
 */

public class ProductsCreator {
    private String parsing;

    public ProductsCreator(String datas) {
        parsing = datas;
    }

    public Product[] generate() throws XmlPullParserException, IOException {
        ArrayList<Product> p = new ArrayList<>();

        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser xpp = factory.newPullParser();

        xpp.setInput(new StringReader(parsing));

        int eventType = xpp.getEventType();
        String lastTag = null;
        String[] pDatas = new String[7];

        while (eventType != XmlPullParser.END_DOCUMENT) {
            if(eventType == XmlPullParser.START_TAG) {
                lastTag = xpp.getName();
            } else if(eventType == XmlPullParser.END_TAG) {
                if(xpp.getName().equals("item")){
                    p.add(new Product(pDatas));
                }
            } else if(eventType == XmlPullParser.TEXT) {
                switch(lastTag){
                    case "ref" :
                        pDatas[0] = xpp.getText();
                        break;
                    case "nom" :
                        pDatas[1] = xpp.getText();
                        break;
                    case "prix" :
                        pDatas[2] = xpp.getText();
                        break;
                    case "lien" :
                        pDatas[3] = xpp.getText();
                        break;
                    case "categorie" :
                        pDatas[4] = xpp.getText();
                        break;
                    case "qte" :
                        pDatas[5] = xpp.getText();
                        break;
                    case "status" :
                        pDatas[6] = xpp.getText();
                        break;
                }
            }
            eventType = xpp.next();
        }

        return p.toArray(new Product[p.size()]);
    }
}
