package org.blocorganization.blocapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.appinvite.AppInviteInvitation;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.blocorganization.blocapp.bloc.BlocFragment;
import org.blocorganization.blocapp.campaigns.CampaignsFragment;
import org.blocorganization.blocapp.messages.MessagesFragment;
import org.blocorganization.blocapp.utils.NavBarFragment;

public class MainActivity extends AppCompatActivity
        implements NavBarFragment.NavBarFragmentListener,
                GoogleApiClient.OnConnectionFailedListener {

    private static final String ARMANDGRAY_COM = "http://armandgray.com";
    private static final String PLAY_STORE_ARMAND_GRAY = "https://play.google.com/store/search?q=armandgray&c=apps&hl=en";
    private static final String PLAY_STORE_BLOC = "https://play.google.com/store/apps/details?id=org.blocorganization.blocapp&hl=en";
    private static final String ANONYMOUS = "anonymous";
    private static final String MESS_TAG = "MESS_TAG";

    private static final int REQUEST_INVITE = 1;

    public static String username;
    public static String photoUrl;
    private static FirebaseAuth firebaseAuth;

    private FirebaseAnalytics firebaseAnalytics;
    private GoogleApiClient googleApiClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupToolBar();

        username = ANONYMOUS;

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API)
                .build();

        firebaseAnalytics = FirebaseAnalytics.getInstance(this);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser == null) {
            startActivity(new Intent(this, SignInActivity.class));
            finish();
            return;
        } else {
            username = firebaseUser.getDisplayName();
            if (firebaseUser.getPhotoUrl() != null) {
                photoUrl = firebaseUser.getPhotoUrl().toString();
            }
        }

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_fragment_container, new HomeFragment())
                .commit();
    }

    private void setupToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void sendInvitation() {
        Intent intent = new AppInviteInvitation.IntentBuilder(getString(R.string.invitation_title))
                .setMessage(getString(R.string.invitation_message))
                .setCallToActionText(getString(R.string.invitation_cta))
                .build();
        startActivityForResult(intent, REQUEST_INVITE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_INVITE) {
            if (resultCode == RESULT_OK) {
                Bundle payload = new Bundle();
                payload.putString(FirebaseAnalytics.Param.VALUE, "sent");
                firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SHARE,
                        payload);
            } else {
                Bundle payload = new Bundle();
                payload.putString(FirebaseAnalytics.Param.VALUE, "not sent");
                firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SHARE,
                        payload);
                // Sending failed or it was canceled, show failure message to
                // the user
            }
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Toast.makeText(this, "Google Play Services error.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sign_out_menu:
                firebaseAuth.signOut();
                Auth.GoogleSignInApi.signOut(googleApiClient);
                username = ANONYMOUS;
                startActivity(new Intent(this, SignInActivity.class));
                return true;
            case R.id.invite_menu:
                sendInvitation();
                return true;
            case R.id.meet_developer:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(ARMANDGRAY_COM)));
                return true;
            case R.id.see_more_apps:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(PLAY_STORE_ARMAND_GRAY)));
                return true;
            case R.id.rate_this_app:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(PLAY_STORE_BLOC)));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onHomeClick() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container, new HomeFragment())
                .commit();
    }

    @Override
    public void onCampaignsClick() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container, new CampaignsFragment())
                .commit();

    }

    @Override
    public void onMessagesClick() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container, new MessagesFragment(), MESS_TAG)
                .commit();
    }

    @Override
    public void onBlocClick() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container, new BlocFragment())
                .commit();
    }


}
