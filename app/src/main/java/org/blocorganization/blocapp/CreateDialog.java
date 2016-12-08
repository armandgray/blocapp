package org.blocorganization.blocapp.campaigns;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.blocorganization.blocapp.R;

public class CreateDialog extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.create_dialog, container, false);

        TextView tvSubmitDialog = (TextView) rootView.findViewById(R.id.tvSubmitDialog);
        tvSubmitDialog.setText(R.string.create_resource);

        return rootView;
    }

}
