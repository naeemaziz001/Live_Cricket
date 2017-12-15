package live.cs443.umb.edu.live_cricket;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class Result extends Activity {

    ImageView img1;
    ListView listView;
    String team1[]=null;
    String team2[]=null;
    String whoWon[]=null;
    String scor1[]=null;
    String scor2[]=null;
    String status[]=null;
    int[] image_1=null;
    int[] image_2=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sehcdule);
        MsharedOBJ.click=0;
        this.img1 = ((ImageView)findViewById(R.id.imageView1));
        this.listView = ((ListView)findViewById(R.id.listView1));


        SharedPreferences resultpf = getSharedPreferences("resultpref", MODE_PRIVATE);
        int fsize = Integer.parseInt(resultpf.getString("size0", "0"));
        if(fsize>0 ) {
            team1 = new String[fsize];
            team2 = new String[fsize];
            whoWon = new String[fsize];
            scor1 = new String[fsize];
            scor2 = new String[fsize];
            status = new String[fsize];
            image_1 = new int[fsize];
            image_2 = new int[fsize];
            for (int i = 0; i < fsize; i++) {

                String st = resultpf.getString("result" + i, null);
                String[] separated = st.split("1_U1_1");
                team1[i] = separated[0].trim();
                team2[i] = "    " + separated[1].trim();
                whoWon[i] = separated[2].trim();
                scor1[i] = separated[3].trim();
                scor2[i] = separated[4].trim();
                status[i] = separated[5].trim();
                if (separated[0].trim().toLowerCase().contains("nz")) {

                    image_1[i] = R.drawable.nz;
                } else if (separated[0].trim().toLowerCase().trim().toLowerCase().equalsIgnoreCase("sl")) {
                    image_1[i] = R.drawable.sl;
                } else if (separated[0].trim().toLowerCase().equalsIgnoreCase("eng")) {
                    image_1[i] = R.drawable.eng;
                } else if (separated[0].trim().toLowerCase().equalsIgnoreCase("sa")) {
                    image_1[i] = R.drawable.sa;
                } else if (separated[0].trim().toLowerCase().equalsIgnoreCase("pak")) {
                    image_1[i] = R.drawable.pak;
                } else if (separated[0].trim().toLowerCase().equalsIgnoreCase("ind")) {
                    image_1[i] = R.drawable.ind;
                } else if (separated[0].trim().toLowerCase().equalsIgnoreCase("wi")) {
                    image_1[i] = R.drawable.wi;
                } else if (separated[0].trim().toLowerCase().equalsIgnoreCase("sc")) {
                    image_1[i] = R.drawable.sc;
                } else if (separated[0].trim().toLowerCase().equalsIgnoreCase("ban")) {
                    image_1[i] = R.drawable.ban;
                } else if (separated[0].trim().toLowerCase().equalsIgnoreCase("afg")) {
                    image_1[i] = R.drawable.afg;
                } else if (separated[0].trim().toLowerCase().equalsIgnoreCase("zim")) {
                    image_1[i] = R.drawable.zem;
                } else if (separated[0].trim().toLowerCase().equalsIgnoreCase("uae")) {
                    image_1[i] = R.drawable.uae;
                } else if (separated[0].trim().toLowerCase().equalsIgnoreCase("aus")) {
                    image_1[i] = R.drawable.aus;
                } else if (separated[0].trim().toLowerCase().equalsIgnoreCase("irl")) {
                    image_1[i] = R.drawable.irl;
                } else {
                    image_1[i] = R.drawable.irl;
                }
//next team
                if (separated[1].trim().toLowerCase().equalsIgnoreCase("nz")) {
                    image_2[i] = R.drawable.nz;
                } else if (separated[1].trim().toLowerCase().equalsIgnoreCase("sl")) {
                    image_2[i] = R.drawable.sl;
                } else if (separated[1].trim().toLowerCase().equalsIgnoreCase("eng")) {
                    image_2[i] = R.drawable.eng;
                } else if (separated[1].trim().toLowerCase().equalsIgnoreCase("sa")) {
                    image_2[i] = R.drawable.sa;
                } else if (separated[1].trim().toLowerCase().equalsIgnoreCase("pak")) {
                    image_2[i] = R.drawable.pak;
                } else if (separated[1].trim().toLowerCase().equalsIgnoreCase("ind")) {
                    image_2[i] = R.drawable.ind;
                } else if (separated[1].trim().toLowerCase().equalsIgnoreCase("wi")) {
                    image_2[i] = R.drawable.wi;
                } else if (separated[1].trim().toLowerCase().equalsIgnoreCase("sc")) {
                    image_2[i] = R.drawable.sc;
                } else if (separated[1].trim().toLowerCase().equalsIgnoreCase("ban")) {
                    image_2[i] = R.drawable.ban;
                } else if (separated[1].trim().toLowerCase().equalsIgnoreCase("afg")) {
                    image_2[i] = R.drawable.afg;
                } else if (separated[1].trim().toLowerCase().equalsIgnoreCase("zim")) {
                    image_2[i] = R.drawable.zem;
                } else if (separated[1].trim().toLowerCase().equalsIgnoreCase("uae")) {
                    image_2[i] = R.drawable.uae;
                } else if (separated[1].trim().toLowerCase().equalsIgnoreCase("aus")) {
                    image_2[i] = R.drawable.aus;
                } else if (separated[1].trim().toLowerCase().equalsIgnoreCase("irl")) {
                    image_2[i] = R.drawable.irl;
                } else {
                    image_2[i] = R.drawable.irl;
                }


            }

            ItemAdapterresults localItemAdapterSchedule = new ItemAdapterresults(this, this.scor1, this.scor2, this.whoWon, this.status, this.image_1, this.image_2);
            this.listView.setAdapter(localItemAdapterSchedule);
        }
        else
        {
            Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_SHORT).show();
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
             public void onItemClick(AdapterView<?> parent, View view, int position, long id)
             {

             }
        });

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
    public void onexit() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Exit");
        builder.setMessage("Do you want to Exit?")
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {

                                dialog.dismiss();
                                //airsdk.startSmartWallAd();
                                //ma.callSmartWallAd();
                                finish();

                                //main.startInterstitialAd(AdType.smartwall);
                            }
                        })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.dismiss();

                        //main.startInterstitialAd(AdType.smartwall);

                    }
                });
               /* .setNeutralButton("Rate", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        ratee(getApplicationContext().getPackageName());
                    }
                });*/
        builder.show();

    }
    public void ratee(String pkg) {
        try {
            Intent mintent = new Intent(Intent.ACTION_VIEW);
            mintent.setData(Uri.parse("market://details?id=" + pkg));
            startActivity(mintent);
        } catch (Exception e1) {
            Toast.makeText(getApplicationContext(),
                    "No Application Found to open link", Toast.LENGTH_SHORT)
                    .show();
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            //use smart wall on app exit.

            onexit();

        }
        return super.onKeyDown(keyCode, event);
    }

}
