package org.blocorganization.blocapp;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.view.Menu;
import android.view.MenuItem;

import org.blocorganization.blocapp.home.HomeFragment;
import org.blocorganization.blocapp.notifications.NotificationsFragment;
import org.blocorganization.blocapp.bloc.BlocFragment;
import org.blocorganization.blocapp.campaigns.CampaignsFragment;
import org.blocorganization.blocapp.messages.MessagesFragment;
import org.blocorganization.blocapp.utils.NavBarFragment;

public class MainActivity extends AppCompatActivity
        implements NavBarFragment.NavBarFragmentListener,
                NavigationView.OnNavigationItemSelectedListener {

    public static final String NOTI_FRAG = "NOTI_FRAG";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu menu = navigationView.getMenu();

        MenuItem tools= menu.findItem(R.id.drawer_title);
        SpannableString s = new SpannableString(tools.getTitle());
        s.setSpan(new TextAppearanceSpan(this, R.style.nav_titles), 0, s.length(), 0);
        tools.setTitle(s);

        navigationView.setNavigationItemSelectedListener(this);

        HomeFragment homeFrag = new HomeFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_fragment_container, homeFrag)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBullhornClick() {
        BullhornFragment homeFrag = new BullhornFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container, homeFrag)
                .commit();
    }

    @Override
    public void onCampaignsClick() {
        CampaignsFragment campFrag = new CampaignsFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container, campFrag)
                .commit();

    }

    @Override
    public void onMessagesClick() {
        MessagesFragment messFrag = new MessagesFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container, messFrag)
                .commit();
    }

    @Override
    public void onNotificationsClick() {
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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
