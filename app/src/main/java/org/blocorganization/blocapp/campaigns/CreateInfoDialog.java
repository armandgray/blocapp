package org.blocorganization.blocapp.campaigns;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.blocorganization.blocapp.R;
import org.blocorganization.blocapp.utils.DatePickerFragment;
import org.blocorganization.blocapp.utils.ImageThemeAdapter;
import org.blocorganization.blocapp.utils.RecyclerItemClickListener;
import org.blocorganization.blocapp.utils.SaveChangesDialogFragment;
import org.blocorganization.blocapp.utils.SpinnerAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CreateInfoDialog extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    public static final int THEME_LAYOUT_PARAMS = 100;
    public static final String THEMES = "themes";
    public static final String IMAGEURLS = "imageurls";
    public static final String RES = "res";
    public static final String VENUES = "venues";
    public static final String TYPES = "types";
    public static final String DIALOG = "DIALOG";

    DialogEndedListener mListener;

    private ImageThemeAdapter adapter;
    private List<String> themes = new ArrayList<>();
    private List<String> venues = new ArrayList<>();
    private List<String> types = new ArrayList<>();

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


        DatabaseReference mDatabaseResources = FirebaseDatabase.getInstance().getReference().child(RES);
        DatabaseReference dbThemes = mDatabaseResources.child(IMAGEURLS).child(THEMES);

        // Load Recycler with jpg from Firebase
        final RecyclerView rvThemes = (RecyclerView) rootView.findViewById(R.id.rvThemes);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvThemes.setLayoutManager(layoutManager);
        rvThemes.addOnItemTouchListener(new RecyclerItemClickListener(getContext(),
                new RecyclerItemClickListener.OnItemClickListener() {
                    LinearLayout lastClick;
                    @Override
                    public void onItemClick(View view, int position) {
                        LinearLayout image = (LinearLayout) view;
                        if (lastClick != null) {
                            lastClick.setBackgroundResource(R.drawable.background_square_shadow);
                        }
                        image.setBackgroundResource(R.drawable.background_square_selected_shadow);
                        lastClick = (LinearLayout) view;
                    }
                }));

        dbThemes.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                themes = (List) dataSnapshot.getValue();
                if (rvThemes.getAdapter() == null) {
                    adapter = new ImageThemeAdapter(getActivity(), themes, THEME_LAYOUT_PARAMS);
                    rvThemes.setAdapter(adapter);
                } else {
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

        final Spinner spVenue = (Spinner) rootView.findViewById(R.id.spVenue);
        spVenue.setDropDownVerticalOffset(100);

        // get venues from Firebase
        DatabaseReference dbVenues = mDatabaseResources.child(VENUES);
        dbVenues.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                venues = (List) dataSnapshot.getValue();
                if (spVenue.getAdapter() == null) {
                    SpinnerAdapter adVenues = new SpinnerAdapter(venues, getActivity());
                    spVenue.setAdapter(adVenues);
                } else {
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        final Spinner spType = (Spinner) rootView.findViewById(R.id.spType);
        spType.setDropDownVerticalOffset(100);

        // get types from Firebase
        DatabaseReference dbTypes = mDatabaseResources.child(TYPES);
        dbTypes.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                types = (List) dataSnapshot.getValue();
                if (spType.getAdapter() == null) {
                    SpinnerAdapter adType = new SpinnerAdapter(types, getActivity());
                    spType.setAdapter(adType);
                } else {
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        RelativeLayout editDateLayout = (RelativeLayout) rootView.findViewById(R.id.editDateLayout);
        editDateLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerFragment newFragment = new DatePickerFragment();
                newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
            }
        });

        ImageView ivSubmit = (ImageView) rootView.findViewById(R.id.ivSubmit);
        ivSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SaveChangesDialogFragment().show(
                        getActivity().getSupportFragmentManager(), DIALOG);
            }
        });

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

        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        // Do something with the time chosen by the user
    }

    public interface DialogEndedListener {
        void onDialogEnded(String title);
    }

}
