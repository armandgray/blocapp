package org.blocorganization.blocapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    BullhornFragment bullFrag;

    public static final String BULLHORN_TAG = "bullhorn_tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bullFrag = new BullhornFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_fragment_container, bullFrag)
                .addToBackStack(null)
                .commit();

    }

    public void onBullhornClick(View view) {
        Toast.makeText(MainActivity.this, "News Feed Clicked", Toast.LENGTH_SHORT).show();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container, bullFrag)
                .addToBackStack(null)
                .commit();
    }

    public void onCalendarClick(View view) {
        Toast.makeText(MainActivity.this, "Campaigns Clicked", Toast.LENGTH_SHORT).show();
        CampaignsFragment campFrag = new CampaignsFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container, campFrag)
                .commit();

    }

    public void onEmoticonClick(View view) {
        Toast.makeText(MainActivity.this, "Messages Clicked", Toast.LENGTH_SHORT).show();
        MessagesFragment messFrag = new MessagesFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container, messFrag)
                .commit();
    }

    public void onPersonalClick(View view) {
        Toast.makeText(MainActivity.this, "Accounts Clicked", Toast.LENGTH_SHORT).show();
        AccountsFragment accFrag = new AccountsFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container, accFrag)
                .commit();
    }

    public void onBoldBClick(View view) {
        Toast.makeText(MainActivity.this, "Bloc Button Clicked", Toast.LENGTH_SHORT).show();
        BlocFragment blocFrag = new BlocFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container, blocFrag)
                .commit();
    }

}
