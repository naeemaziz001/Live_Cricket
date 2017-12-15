package live.cs443.umb.edu.live_cricket;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;




public class MainTabView extends TabActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost); // initiate TabHost


        TabHost.TabSpec FixturesTab = tabHost.newTabSpec("Fixtures"); // Create a new TabSpec using tab host
        FixturesTab.setIndicator("Fixture");
        Intent intent3 = new Intent(this, Schedule_Fixtures.class);
        FixturesTab.setContent(intent3); // specify an intent to use to launch an activity as the tab content
        tabHost.addTab(FixturesTab);

        TabHost.TabSpec resultTab = tabHost.newTabSpec("Results"); // Create a new TabSpec using tab host
        resultTab.setIndicator("Results");
        Intent intent2 = new Intent(this, Result.class);
        resultTab.setContent(intent2); // specify an intent to use to launch an activity as the tab content
        tabHost.addTab(resultTab);


        TabHost.TabSpec liveTab = tabHost.newTabSpec("Live"); // Create a new TabSpec using tab host
        liveTab.setIndicator("Live");
        Intent intent4 = new Intent(this, Live_Activity.class);
        liveTab.setContent(intent4);
        tabHost.addTab(liveTab);
        tabHost.setCurrentTab(0);

    }


}
