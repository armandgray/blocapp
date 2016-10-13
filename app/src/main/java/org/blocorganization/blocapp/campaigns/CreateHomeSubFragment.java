package org.blocorganization.blocapp.campaigns;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import org.blocorganization.blocapp.R;

import static org.blocorganization.blocapp.campaigns.CreateGeneralSubFragment.CREATE_KEY;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateHomeSubFragment extends Fragment {

    private CreateTypeSelectedListener mListener;

    public CreateHomeSubFragment() {
        // Required empty public constructor
    }

    public static CreateHomeSubFragment newInstance() {

        Bundle args = new Bundle();

        CreateHomeSubFragment fragment = new CreateHomeSubFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        assert getParentFragment() instanceof CreateTypeSelectedListener;
        mListener = (CreateTypeSelectedListener) getParentFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.campaigns_subfragment_create_home, container, false);

        ImageView socialImage = (ImageView) rootView.findViewById(R.id.social_image);
        socialImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { mListener.onCreateTypeSelected(0); }
        });

        ImageView callImage = (ImageView) rootView.findViewById(R.id.call_image);
        callImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { mListener.onCreateTypeSelected(1); }
        });

        ImageView consentImage = (ImageView) rootView.findViewById(R.id.consent_image);
        consentImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { mListener.onCreateTypeSelected(2); }
        });

        ImageView fundraiserImage = (ImageView) rootView.findViewById(R.id.fundraiser_image);
        fundraiserImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { mListener.onCreateTypeSelected(3); }
        });

        ImageView academicImage = (ImageView) rootView.findViewById(R.id.academic_image);
        academicImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { mListener.onCreateTypeSelected(4); }
        });

        ImageView otherImage = (ImageView) rootView.findViewById(R.id.other_image);
        otherImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { mListener.onCreateTypeSelected(5); }
        });

        return rootView;
    }

    public interface CreateTypeSelectedListener{
        void onCreateTypeSelected(int num);
    }

}
