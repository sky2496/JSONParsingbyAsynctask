package bd.nasimdeveloper.jsonparsingbyasynctask;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ImageView mobileImage;
    TextView companyName, mobileName, screenSize, battery, ram;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mobileImage = findViewById(R.id.imageView);

        companyName = findViewById(R.id.companyNameTV);
        mobileName = findViewById(R.id.mobileNameTV);
        screenSize = findViewById(R.id.screenSizeTV);
        battery = findViewById(R.id.batteryTV);
        ram = findViewById(R.id.ramTV);

        String api = "https://androidtutorialpoint.com/api/MobileJSONArray.json";



        class MobileTask extends AsyncTask<String, Void, String>
        {


            @SuppressLint("WrongThread")
            @Override
            protected String doInBackground(String... strings) {

                String s = strings[0];

                try {
                    String s1;
                    URL url = new URL(s);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.connect();
                    InputStream stream = connection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(stream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                    String line = "";
                    StringBuilder stringBuilder = new StringBuilder();
                    if((line=bufferedReader.readLine())!=null){
                        stringBuilder.append(line);
                    }

                    s1 = stringBuilder.toString();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
                 
            }

            @Override
            protected void onPostExecute(String s) {

                try {
                    JSONArray jsonArray = new JSONArray(s);

                    JSONObject jsonObject = jsonArray.getJSONObject(0);

                    companyName.setText(jsonObject.get("companeName").toString());
                    mobileName.setText(jsonObject.get("name").toString());
                    battery.setText(jsonObject.get("battery").toString());
                    ram.setText(jsonObject.get("ram").toString());
                    screenSize.setText(jsonObject.get("screenSize").toString());




                } catch (JSONException e) {
                    e.printStackTrace();
                }


                super.onPostExecute(s);
            }
        }
        new MobileTask().execute(api);




    }
}
