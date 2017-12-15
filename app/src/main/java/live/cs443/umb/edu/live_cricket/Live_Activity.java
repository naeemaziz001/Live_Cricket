package live.cs443.umb.edu.live_cricket;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;


public class Live_Activity extends AppCompatActivity {


    ImageView img1;

    ImageView imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.live_single);
        imageButton = (ImageView) findViewById(R.id.playbutton);



           MsharedOBJ.MstObj2=new ArrayList<>();
            SharedPreferences Mlivepf = getSharedPreferences("sportspref", MODE_PRIVATE);

            for (int i = 0; i < 1; i++) {
                if (Mlivepf.getString("status" + i, null).toLowerCase().contains("on")) {


                    Myobj MObje = new Myobj();
                    MObje.id = Integer.parseInt(Mlivepf.getString("id" + i, null));
                    MObje.cName = Mlivepf.getString("name" + i, null);
                    MObje.slink = Mlivepf.getString("mulink" + i, null);
                    MObje.simage = Mlivepf.getString("imageurl" + i, null);
                    MObje.stype = Mlivepf.getString("type" + i, null);
                    MObje.smtath = Mlivepf.getString("mobj" + i, null);
                    MObje.status = Mlivepf.getString("status" + i, null);
                    MObje.error = Mlivepf.getString("error" + i, null);
                    MObje.Descr = Mlivepf.getString("descrip" + i, null);
                    MObje.player = Mlivepf.getString("player" + i, "vitamio");
                    MsharedOBJ.tvLink= MObje.slink;
                    MsharedOBJ.MstObj2.add(MObje);
                }

            }
        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                if(MsharedOBJ.click==0)
                {
                    MsharedOBJ.click=1;
                    MsharedOBJ.curentindex = 0;
                    String link = MsharedOBJ.MstObj2.get(0).slink.trim();
                    MsharedOBJ.tvLink = link;
                    Intent intent = new Intent(Live_Activity.this, Max_Player.class);
                    startActivity(intent);

               

                }


            }

        });


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
                /*.setNeutralButton("Rate", new DialogInterface.OnClickListener() {
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