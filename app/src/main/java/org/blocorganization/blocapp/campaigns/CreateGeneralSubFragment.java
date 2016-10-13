package org.blocorganization.blocapp.campaigns;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.CellIdentityCdma;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.blocorganization.blocapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateGeneralSubFragment extends Fragment {

    public static final String CREATE_KEY = "CREATE_KEY";
    public static final String[] SOCIAL_CAMPAIGN = {"Social", "BLOC Party", "Dom's Lounge",
            "•\tPossibility of additional donors\n" +
            "•\tRepresentational image of diversity \n" +
            "•\tMaking prospective students feel comfortable and in a homely environment\n" +
            "o\tIncreasing the yield of students from MSAP weekend. \n"};
    public static final String[] CALL_CAMPAIGN = {"Call", "BLOC Forum", "SCC", "contribution"};
    public static final String[] CONSENT_CAMPAIGN = {"Consent", "ASK Campaign", "SCC", "contribution"};
    public static final String[] FUNDRAISE_CAMPAIGN = {"Fundraiser", "Toys for Tots", "Frary Dining Hall", "contribution"};
    public static final String[] ACADEMIC_CAMPAIGN = {"Academic", "Study Group", "Millikan Center", "contribution"};
    public static final String[] DEFAULT_CAMPAIGN = {"", "", "", ""};

    private String[] mCampaignInfo;

    public CreateGeneralSubFragment() {
        // Required empty public constructor
    }

    public static CreateGeneralSubFragment newInstance(int num) {

        Bundle args = new Bundle();
        switch (num) {
            case 0:
                args.putStringArray(CREATE_KEY, SOCIAL_CAMPAIGN);
                break;
            case 1:
                args.putStringArray(CREATE_KEY, CALL_CAMPAIGN);
                break;
            case 2:
                args.putStringArray(CREATE_KEY, CONSENT_CAMPAIGN);
                break;
            case 3:
                args.putStringArray(CREATE_KEY, FUNDRAISE_CAMPAIGN);
                break;
            case 4:
                args.putStringArray(CREATE_KEY, ACADEMIC_CAMPAIGN);
                break;
            case 5:
                args.putStringArray(CREATE_KEY, DEFAULT_CAMPAIGN);
                break;
            default:
                args.putStringArray(CREATE_KEY, DEFAULT_CAMPAIGN);
        }
        CreateGeneralSubFragment fragment = new CreateGeneralSubFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.campaigns_subfragment_create_general, container, false);

        Bundle args = getArguments();
        mCampaignInfo = args.getStringArray(CREATE_KEY);

        EditText etEventType = (EditText) rootView.findViewById(R.id.etEventType);
        etEventType.setText(mCampaignInfo[0]);

        EditText etEventTitle = (EditText) rootView.findViewById(R.id.etEventTitle);
        etEventTitle.setText(mCampaignInfo[1]);

        EditText etEventLocation = (EditText) rootView.findViewById(R.id.etEventLocation);
        etEventLocation.setText(mCampaignInfo[2]);

        EditText etEventDate = (EditText) rootView.findViewById(R.id.etEventDate);
//        etEventDate //todays date.

        EditText etEventTime = (EditText) rootView.findViewById(R.id.etEventTime);
//        etEventTime //current time.

        EditText etEventContribution = (EditText) rootView.findViewById(R.id.etEventContribution);
        etEventContribution.setText(mCampaignInfo[3]);

        return rootView;
    }

}
