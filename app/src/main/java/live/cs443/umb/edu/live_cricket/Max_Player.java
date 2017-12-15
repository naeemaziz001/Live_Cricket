package live.cs443.umb.edu.live_cricket;

import java.io.IOException;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;

import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.Toast;

public class Max_Player extends Activity  {

	 	Intent localIntent1;
	 	 private static final boolean GLOBAL_DEBUG = false;
		@SuppressLint({ "InlinedApi", "SetJavaScriptEnabled" })
		 @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.max_activty);
       
         getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
			MsharedOBJ.click=0;

                 if(isOnline1()==true)
       	      {


                	  localIntent1 = new Intent("android.intent.action.VIEW");
				      localIntent1.setData(Uri.parse(MsharedOBJ.tvLink));
				      localIntent1.putExtra("decode_mode", (byte)2);
				      localIntent1.putExtra("title","Live");
				      localIntent1.putExtra("secure_uri", true);
				      localIntent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);



				      if (ispackgexited(getApplicationContext(),"com.mxtech.videoplayer.ad"))
				      {
				    	   localIntent1.setComponent(new ComponentName("com.mxtech.videoplayer.ad", "com.mxtech.videoplayer.ad.ActivityScreen"));
				    	   startActivity(localIntent1);
				    	   finish();
				      }
				      else  if (ispackgexited(getApplicationContext(),"com.mxtech.videoplayer.pro"))
				      {
				    	  localIntent1.setComponent(new ComponentName("com.mxtech.videoplayer.pro", "com.mxtech.videoplayer.ActivityScreen"));
				    	  startActivity(localIntent1);
				    	  finish();
				      }
				      else
				      {
				    	  
				    	  AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
								  Max_Player.this);

				  			// set title
				  			alertDialogBuilder.setTitle("Player Not Found");

				  			// set dialog message
				  			alertDialogBuilder
				  				.setMessage("You Must Install MX player to view this Channel install it now")
				  				.setCancelable(false)
				  				.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
				  					public void onClick(DialogInterface dialog,int id) {
				  						Intent localIntent = new Intent("android.intent.action.VIEW");
				  						localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); 
				  						localIntent.setData(Uri.parse("market://details?id=" + "com.mxtech.videoplayer.ad"));
							            startActivity(localIntent);
							        	
				  					finish();
				  					}
				  				  })
				  				.setNegativeButton("No",new DialogInterface.OnClickListener() {
				  					public void onClick(DialogInterface dialog,int id) {
				  						// if this button is clicked, just close
				  						// the dialog box and do nothing
				  						dialog.cancel();
				  						finish();
				  					}
				  				});

				  				// create alert dialog
				  				AlertDialog alertDialog = alertDialogBuilder.create();

				  				// show it
				  				alertDialog.show();
				    	 
				      }
	  				
       	      }
                 else {
         			Toast.makeText(getApplicationContext(), "No Network Connection",
         					Toast.LENGTH_LONG).show();
         			finish();
         		}

	}
    



	 protected boolean isOnline1() {
			ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			if (cm.getActiveNetworkInfo() != null
					&& cm.getActiveNetworkInfo().isAvailable()
					&& cm.getActiveNetworkInfo().isConnected()) {

				
				return true;
			} else {
				
				return false;
			}
		}
	
	 private boolean appInstalledOrNot(String uri)
	    {
	        PackageManager pm = getPackageManager();
	        boolean app_installed = false;
	        try
	        {
	               pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
	               app_installed = true;
	        }
	        catch (PackageManager.NameNotFoundException e)
	        {
	               app_installed = false;
	        }
	        return app_installed ;
	}
		public static boolean ispackgexited(Context context,String paramString) {
	        PackageManager pm = context.getPackageManager();
	        boolean app_installed = false;
	        try {
	            pm.getPackageInfo(paramString, PackageManager.GET_ACTIVITIES);
	            app_installed = true;
	        }
	        catch (PackageManager.NameNotFoundException e) {
	            app_installed = false;
	        }
	        return app_installed ;
	}
	    public boolean isOnline() {
	        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	               NetworkInfo netInfo = cm.getActiveNetworkInfo();
	               if (netInfo != null && netInfo.isConnected()) {
	                   try {
	                       URL url = new URL("http://www.google.com");
	                       HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
	                       urlc.setConnectTimeout(3000);
	                       urlc.connect();
	               if (urlc.getResponseCode() == 200) {
	                      // return new Boolean(true);
	                       return true;
	               }
	               } catch (MalformedURLException e1) {
	                       // TODO Auto-generated catch block
	                       e1.printStackTrace();
	           } catch (IOException e) {
	                       // TODO Auto-generated catch block
	                       e.printStackTrace();
	               }
	               }
	               return false;
	       }
	   
	    
	    public void onResume() {
	        super.onResume();
	     }
	    
	    @Override
   		public boolean onKeyDown(int keyCode, KeyEvent event) {
   			if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {			
   				
   			
   				finish();
   				
   			}
   			return super.onKeyDown(keyCode, event);
   		}
	    
	
	    
	    
}