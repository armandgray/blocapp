package org.blocorganization.blocapp.utils;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;

import org.blocorganization.blocapp.R;

public class DialogSubmitUtilities {

    private static final String DIALOG = "DIALOG";

    private View rootView;
    private Fragment fragment;

    public DialogSubmitUtilities(View rootView, Fragment fragment) {
        this.rootView = rootView;
        this.fragment = fragment;
    }

    public void setupClickListeners(@NonNull final DialogSubmitListener dialogSubmitListener) {
        ImageView ivSubmit = (ImageView) rootView.findViewById(R.id.ivSubmit);
        ivSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialogSubmitListener.verifyFields()) {
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

    public interface DialogSubmitListener {
        boolean verifyFields();
    }
}
