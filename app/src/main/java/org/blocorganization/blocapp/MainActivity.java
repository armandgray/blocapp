package org.blocorganization.blocapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
import com.google.firebase.crash.FirebaseCrash;

import org.blocorganization.blocapp.bloc.BlocFragment;
import org.blocorganization.blocapp.campaigns.CampaignsFragment;
import org.blocorganization.blocapp.messages.MessagesFragment;
import org.blocorganization.blocapp.utils.NavBarFragment;

public class MainActivity extends AppCompatActivity
        implements NavBarFragment.NavBarFragmentListener,
                GoogleApiClient.OnConnectionFailedListener {

    public static final String TAG = "MainActivity";
    public static final String ANONYMOUS = "anonymous";
    public static final String MESS_TAG = "MESS_TAG";

    private static final int REQUEST_INVITE = 1;

    public static String username;
    public static String photoUrl;
    public static FirebaseAuth firebaseAuth;
    public static FirebaseUser firebaseUser;

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
        firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser == null) {
            // Not signed in, launch the Sign In activity
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

    public interface MainActivityListener {
        void fetchConfig();
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
        Log.d(TAG, "onActivityResult: requestCode=" + requestCode +
                ", resultCode=" + resultCode);

        if (requestCode == REQUEST_INVITE) {
            if (resultCode == RESULT_OK) {
                Bundle payload = new Bundle();
                payload.putString(FirebaseAnalytics.Param.VALUE, "sent");
                firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SHARE,
                        payload);
                // Check how many invitations were sent.
                String[] ids = AppInviteInvitation
                        .getInvitationIds(resultCode, data);
                Log.d(TAG, "Invitations sent: " + ids.length);
            } else {
                Bundle payload = new Bundle();
                payload.putString(FirebaseAnalytics.Param.VALUE, "not sent");
                firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SHARE,
                        payload);
                // Sending failed or it was canceled, show failure message to
                // the user
                Log.d(TAG, "Failed to send invitation.");
            }
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
        Toast.makeText(this, "Google Play Services error.", Toast.LENGTH_SHORT).show();
    }

    private void causeCrash() {
        throw new NullPointerException("Fake null pointer exception");
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
            case R.id.crash_menu:
                FirebaseCrash.logcat(Log.ERROR, TAG, "crash caused");
                causeCrash();
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
