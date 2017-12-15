package live.cs443.umb.edu.live_cricket;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.HashMap;
import android.support.v7.app.AlertDialog;
import android.content.DialogInterface;

import org.json.JSONArray;
import org.json.JSONObject;
import android.content.SharedPreferences;
import org.json.JSONException;
import android.widget.Toast;
import android.content.Intent;
public class MainActivity extends AppCompatActivity {
    private ProgressDialog pDialog;
    ArrayList<HashMap<String, String>> contactList;
    public static String temp="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contactList = new ArrayList<>();

        //if internet available than fetch data online from json file
        if (isOnline()) {
            try {
                new MainActivity.GetData().execute();
            }catch (Exception e)
            {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                builder1.setTitle("Network Problem!");
                builder1.setMessage("Please connect the device with internet!");
                builder1.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder1.show();
            }
        }

    }

    private class GetData extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Fetching the Data...");
            pDialog.setCancelable(false);
            pDialog.show();
            pDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {

                    finish();
                }
            });

        }

        @Override
    protected Void doInBackground(Void... arg0) {
        HttpHandler sh = new HttpHandler();  //convertStreamToString

        // Making a request to url and getting response
        String jsonStr="";
        try {


                jsonStr = sh.makeServiceCall("http://www.rivajtech.com/livesportsdata.json");


            if (jsonStr != null) {
                try {
                    //jsonStr=bcsdhc(jsonStr);

                    JSONObject jsonObj = new JSONObject(jsonStr);

                    JSONArray sports = jsonObj.getJSONArray("sports");
                    JSONArray mfeed = jsonObj.getJSONArray("feed");
                    JSONArray mresult = jsonObj.getJSONArray("result");
                    JSONArray mfixture = jsonObj.getJSONArray("fixture");
                    JSONArray mapp = jsonObj.getJSONArray("app");


                    SharedPreferences sportPref = MainActivity.this.getSharedPreferences("sportspref", 0);
                    SharedPreferences.Editor speditor = sportPref.edit();
                    for (int i = 0; i < sports.length(); i++) {
                        JSONObject c = sports.getJSONObject(i);

                        String id = c.getString("id");
                        String name = c.getString("name");
                        String mulink = c.getString("ulink");
                        String imageurl = c.getString("imgurl");
                        String type = c.getString("type");
                        String mobj = c.getString("obj");
                        String status = c.getString("status");
                        String error = c.getString("error");
                        String descrip = c.getString("channel_Des");


                        speditor.putString("id" + i, id);
                        speditor.putString("name" + i, name);
                        speditor.putString("mulink" + i, mulink);
                        speditor.putString("imageurl" + i, imageurl);
                        speditor.putString("type" + i, type);
                        speditor.putString("mobj" + i, mobj);
                        speditor.putString("status" + i, status);
                        speditor.putString("error" + i, error);
                        speditor.putString("descrip" + i, descrip);
                        speditor.putString("player" + i, c.getString("player"));

                        // tmp hash map for single contact
                        HashMap<String, String> msports = new HashMap<>();

                        // adding each child node to HashMap key => value
                        msports.put("id", id);
                        msports.put("name", name);
                        msports.put("link", mulink);
                        temp=mulink;
                        msports.put("obj", mobj);

                        // adding sports to contact list
                        contactList.add(msports);
                    }
                    speditor.commit();
                    SharedPreferences feedPref = MainActivity.this.getSharedPreferences("feedspref", 0);
                    SharedPreferences.Editor feededitor = feedPref.edit();
                    final JSONObject feedobje = mfeed.getJSONObject(0);

                    int feedsize = Integer.parseInt(feedobje.getString("size"));

                    for (int i = 0; i < feedsize; i++) {

                        feededitor.putString("size" + i, feedsize + "");
                        String ml = "mfeed" + i;
                        feededitor.putString("feed" + i, feedobje.getString(ml));
                    }
                    feededitor.commit();
                    SharedPreferences resPref = MainActivity.this.getSharedPreferences("resultpref", 0);
                    SharedPreferences.Editor reseditor = resPref.edit();
                    JSONObject resultobje = mresult.getJSONObject(0);
                    int resulsize = Integer.parseInt(resultobje.getString("size"));
                    for (int i = 0; i < resulsize; i++) {

                        reseditor.putString("size" + i, resulsize + "");
                        reseditor.putString("result" + i, resultobje.getString(("mresult" + i)));

                    }
                    reseditor.commit();

                    SharedPreferences fixPref = MainActivity.this.getSharedPreferences("fixspref", 0);
                    SharedPreferences.Editor fixeditor = fixPref.edit();
                    JSONObject fixtureobje = mfixture.getJSONObject(0);
                    int fixsize = Integer.parseInt(fixtureobje.getString("size"));
                    for (int i = 0; i < fixsize; i++) {

                        fixeditor.putString("size" + i, fixsize + "");
                        fixeditor.putString("fixture" + i, fixtureobje.getString(("mfixture" + i)));

                    }
                    fixeditor.commit();

                    JSONObject appobje = mapp.getJSONObject(0);



                    SharedPreferences appPref = MainActivity.this.getSharedPreferences("apppref", 0);
                    SharedPreferences.Editor appeditor = appPref.edit();
                    appeditor.putInt("first_time" , 1);
                    appeditor.putString("status", appobje.getString("status"));
                    appeditor.putString("totallinks", appobje.getString("totalL"));
                    appeditor.putString("dosr", appobje.getString("dosr"));
                    appeditor.putString("myadd", appobje.getString("myadd"));
                    appeditor.commit();

                } catch (final JSONException e) {
                    // Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this,
                                    "Could'nt get the data from server! ",
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                //Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this,
                                "No Internet Connection!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }
        } catch (Exception e) {


        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        // Dismiss the progress dialog
        if (pDialog.isShowing())
            pDialog.dismiss();

        SharedPreferences getst = MainActivity.this.getSharedPreferences("apppref", 0);
        String stat=getst.getString("totallinks","");

        Intent i = new Intent(MainActivity.this, MainTabView.class);
        startActivity(i);
        finish();

       /* if(stat.trim().toLowerCase().contains("original"))
        {
           *//* Intent i = new Intent(MainActivity.this, Mlive.class);
            startActivity(i);
            finish();*//*
        }
        else
        //(stat.trim().toLowerCase().contains("appmultiple"))
        {
           *//* Intent i = new Intent(MainActivity.this, MainTab.class);
            startActivity(i);
            finish();*//*
        }*/




    }


}
    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm.getActiveNetworkInfo() != null
                && cm.getActiveNetworkInfo().isAvailable()
                && cm.getActiveNetworkInfo().isConnected()) {


            return true;
        } else {

            return false;
        }
    }
}
