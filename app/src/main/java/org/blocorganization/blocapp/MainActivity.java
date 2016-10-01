package org.blocorganization.blocapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import org.blocorganization.blocapp.bloc.BlocFragment;
import org.blocorganization.blocapp.bloc.BlocSubFragment;
import org.blocorganization.blocapp.campaigns.CampaignsFragment;
import org.blocorganization.blocapp.home.ViewPagerFragment;
import org.blocorganization.blocapp.messages.MessagesFragment;
import org.blocorganization.blocapp.models.DataProvider;
import org.blocorganization.blocapp.models.Product;
import org.blocorganization.blocapp.utils.NavBarFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NavBarFragment.NavBarFragmentListener {

    public static final String BULLHORN_TAG = "bullhorn_tag";

    private final List<Product> products = DataProvider.productList;
    private final int numPages = products.size();
    private ViewPager mPager;

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

//        mPager = (ViewPager) findViewById(R.id.main_pager);
//        PagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
//        mPager.setAdapter(pagerAdapter);

    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ViewPagerFragment.newInstance(products.get(position));
        }

        @Override
        public int getCount() {
            return numPages;
        }
    }

    @Override
    public void onBullhornClick() {
        Toast.makeText(MainActivity.this, "News Feed Clicked", Toast.LENGTH_SHORT).show();
        BullhornFragment bullFrag = new BullhornFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container, bullFrag)
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
        NotificationsFragment accFrag = new NotificationsFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container, accFrag)
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
