package org.blocorganization.blocapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import org.blocorganization.blocapp.home.HomeFragment;
import org.blocorganization.blocapp.notifications.NotificationsFragment;
import org.blocorganization.blocapp.bloc.BlocFragment;
import org.blocorganization.blocapp.campaigns.CampaignsFragment;
import org.blocorganization.blocapp.messages.MessagesFragment;
import org.blocorganization.blocapp.utils.NavBarFragment;

public class MainActivity extends AppCompatActivity implements NavBarFragment.NavBarFragmentListener {

    public static final String BULLHORN_TAG = "bullhorn_tag";
    public static final String NOTI_FRAG = "NOTI_FRAG";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        HomeFragment homeFrag = new HomeFragment();
//        getSupportFragmentManager()
//                .beginTransaction()
//                .add(R.id.main_fragment_container, homeFrag)
//                .addToBackStack(null)
//                .commit();

    }

    @Override
    public void onBullhornClick() {
        Toast.makeText(MainActivity.this, "News Feed Clicked", Toast.LENGTH_SHORT).show();
//        BullhornFragment bullFrag = new BullhornFragment();
        HomeFragment homeFrag = new HomeFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container, homeFrag)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onCampaignsClick() {
        Toast.makeText(MainActivity.this, "Campaigns Clicked", Toast.LENGTH_SHORT).show();
        CampaignsFragment campFrag = new CampaignsFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container, campFrag)
                .commit();

    }

    @Override
    public void onMessagesClick() {
        Toast.makeText(MainActivity.this, "Messages Clicked", Toast.LENGTH_SHORT).show();
        MessagesFragment messFrag = new MessagesFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container, messFrag)
                .commit();
    }

    @Override
    public void onNotificationsClick() {
        Toast.makeText(MainActivity.this, "Accounts Clicked", Toast.LENGTH_SHORT).show();
        NotificationsFragment notiFrag = new NotificationsFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container, notiFrag, NOTI_FRAG)
                .commit();
    }

    @Override
    public void onBlocClick() {
        BlocFragment blocFrag = new BlocFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container, blocFrag)
                .commit();
    }

}
