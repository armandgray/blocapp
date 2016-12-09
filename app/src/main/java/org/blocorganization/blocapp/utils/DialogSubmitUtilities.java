package org.blocorganization.blocapp.utils;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;

import org.blocorganization.blocapp.R;

public class DialogSubmitUtilities {

    public static final String DIALOG = "DIALOG";

    private View rootView;
    private Fragment fragment;

    public DialogSubmitUtilities(View rootView, Fragment fragment) {
        this.rootView = rootView;
        this.fragment = fragment;
    }

    public void setupClickListeners() {
        ImageView ivSubmit = (ImageView) rootView.findViewById(R.id.ivSubmit);
        ivSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // field validation before confirming changes
                // TODO Change true in field verification
                if (true) {
                    new ConfirmChangesDialogFragment().show(
                            fragment.getChildFragmentManager(), DIALOG);
                }
            }
        });

        ImageView ivCancel = (ImageView) rootView.findViewById(R.id.ivCancel);
        ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragment.getArguments() == null) {
                    fragment.getActivity().onBackPressed();
                }
                fragment.getActivity().onBackPressed();
            }
        });
    }
}
