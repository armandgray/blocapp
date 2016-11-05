package org.blocorganization.blocapp.campaigns;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.blocorganization.blocapp.R;
import org.blocorganization.blocapp.utils.ImageThemeAdapter;
import org.blocorganization.blocapp.utils.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class CreateInfoDialog extends DialogFragment {

    public static final int THEME_LAYOUT_PARAMS = 100;
    public static final String THEMES = "themes";
    public static final String IMAGEURLS = "imageurls";

    DialogEndedListener mListener;

    private ViewPager vpCreate;
    private ImageThemeAdapter adapter;
    private List<String> themes = new ArrayList<>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (DialogEndedListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement DialogEndedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout to use as dialog or embedded fragment
        View rootView = inflater.inflate(R.layout.create_info_dialog, container, false);

//        vpCreate = (ViewPager) rootView.findViewById(R.id.vpCreate);


        themes.add("https://firebasestorage.googleapis.com/v0/b/blocapp-22b4d.appspot.com/o/themes%2Ftheme_bbq.jpg?alt=media&token=1db41d20-ad45-486f-8de6-298396b9cde2");
        themes.add("https://firebasestorage.googleapis.com/v0/b/blocapp-22b4d.appspot.com/o/themes%2Ftheme_bbq2.png?alt=media&token=6e6b1d1d-7349-40be-9dcd-3cd477c407ab");
        themes.add("https://firebasestorage.googleapis.com/v0/b/blocapp-22b4d.appspot.com/o/themes%2Ftheme_business.jpg?alt=media&token=74d1d0a2-484c-48b1-a568-65da23e2f495");
        themes.add("https://firebasestorage.googleapis.com/v0/b/blocapp-22b4d.appspot.com/o/themes%2Ftheme_call.jpg?alt=media&token=a3bff5a0-7768-4b40-bac5-67a5c5c4f94c");
        themes.add("https://firebasestorage.googleapis.com/v0/b/blocapp-22b4d.appspot.com/o/themes%2Ftheme_call2.jpg?alt=media&token=1b1f9242-4c46-4e63-8a50-61697df9237d");
        themes.add("https://firebasestorage.googleapis.com/v0/b/blocapp-22b4d.appspot.com/o/themes%2Ftheme_consent.jpg?alt=media&token=b56d1ec4-ebfc-420a-8bb1-adf75d47b004");
        themes.add("https://firebasestorage.googleapis.com/v0/b/blocapp-22b4d.appspot.com/o/themes%2Ftheme_donate.jpg?alt=media&token=84597e3f-5a37-4527-bb78-996094a95819");
        themes.add("https://firebasestorage.googleapis.com/v0/b/blocapp-22b4d.appspot.com/o/themes%2Ftheme_icecreamsocial.jpg?alt=media&token=d5746667-263d-4b9c-b503-4ac1f500cba4");
        themes.add("https://firebasestorage.googleapis.com/v0/b/blocapp-22b4d.appspot.com/o/themes%2Ftheme_mentoring.jpg?alt=media&token=2d7fd4a2-5f94-4ca9-9a2f-2e79b961cc4b");
        themes.add("https://firebasestorage.googleapis.com/v0/b/blocapp-22b4d.appspot.com/o/themes%2Ftheme_mentoring2.png?alt=media&token=b5139116-5567-4fd2-a579-8592ef06ba9c");
        themes.add("https://firebasestorage.googleapis.com/v0/b/blocapp-22b4d.appspot.com/o/themes%2Ftheme_networking.jpg?alt=media&token=0cdf8b5a-92a1-459a-bc1a-b79a8142395e");
        themes.add("https://firebasestorage.googleapis.com/v0/b/blocapp-22b4d.appspot.com/o/themes%2Ftheme_party.jpeg?alt=media&token=3180ec93-de1e-4fe8-be2b-1783cfc9bc3f");
        themes.add("https://firebasestorage.googleapis.com/v0/b/blocapp-22b4d.appspot.com/o/themes%2Ftheme_slopes.jpg?alt=media&token=5663b906-616e-4d9c-befb-89c55809e8f8");
        themes.add("https://firebasestorage.googleapis.com/v0/b/blocapp-22b4d.appspot.com/o/themes%2Ftheme_slopes2.jpg?alt=media&token=bf5c844b-3d05-4152-97ad-045ab35ce804");
        themes.add("https://firebasestorage.googleapis.com/v0/b/blocapp-22b4d.appspot.com/o/themes%2Ftheme_social.jpg?alt=media&token=1bffe860-c5af-4b82-bc67-0ab9a1ded51f");
        themes.add("https://firebasestorage.googleapis.com/v0/b/blocapp-22b4d.appspot.com/o/themes%2Ftheme_throwback.jpg?alt=media&token=466997e4-af6f-40b2-a872-1f494fce9dde");

        final RecyclerView rvThemes = (RecyclerView) rootView.findViewById(R.id.rvThemes);
        adapter = new ImageThemeAdapter(getActivity(), themes, THEME_LAYOUT_PARAMS);
        Log.v("THEME_LIST", themes.get(5));
        rvThemes.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvThemes.setLayoutManager(layoutManager);
        rvThemes.addOnItemTouchListener(new RecyclerItemClickListener(getContext(),
                new RecyclerItemClickListener.OnItemClickListener() {
                    LinearLayout lastClick;
                    @Override
                    public void onItemClick(View view, int position) {
                        Toast.makeText(getActivity(), "Image Clicked", Toast.LENGTH_SHORT).show();
                        LinearLayout image = (LinearLayout) view;
                        if (lastClick != null) {
                            lastClick.setBackgroundResource(R.drawable.background_square_shadow);
                        }
                        image.setBackgroundResource(R.drawable.gray_item_background);
                        lastClick = (LinearLayout) view;
                    }
                }));

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference()
                .child(IMAGEURLS)
                .child(THEMES);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                themes = (List) dataSnapshot.getValue();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Spinner spinner = (Spinner) rootView.findViewById(R.id.planets_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.planets_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        return rootView;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // The only reason you might override this method when using onCreateView() is
        // to modify any dialog characteristics. For example, the dialog includes a
        // title by default, but your custom layout might not need it. So here you can
        // remove the dialog title, but you must call the superclass to get the Dialog.
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    public interface DialogEndedListener {
        void onDialogEnded(String title);
    }

}
