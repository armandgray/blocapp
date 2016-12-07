package org.blocorganization.blocapp.campaigns;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.blocorganization.blocapp.R;
import org.blocorganization.blocapp.models.Campaign;
import org.blocorganization.blocapp.utils.ConfirmChangesDialogFragment;
import org.blocorganization.blocapp.utils.DateTimePickerFragment;
import org.blocorganization.blocapp.utils.GetDpMeasurement;
import org.blocorganization.blocapp.utils.ImageThemeAdapter;
import org.blocorganization.blocapp.utils.RecyclerItemClickListener;
import org.blocorganization.blocapp.utils.SpinnerAdapter;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

import static org.blocorganization.blocapp.campaigns.CreateCampaignDialogUtilities.saveCampaignToDatabaseWith;
import static org.blocorganization.blocapp.campaigns.CreateCampaignDialogUtilities.startDetailActivityWith;
import static org.blocorganization.blocapp.campaigns.UploadButtonIncluder.setupUploadButtonFrom;
import static org.blocorganization.blocapp.utils.DateTimeHandler.setTextForDateWith;
import static org.blocorganization.blocapp.utils.FieldUtilities.AMBITION;
import static org.blocorganization.blocapp.utils.FieldUtilities.BENEFITS_TO_THE_COLLEGE;
import static org.blocorganization.blocapp.utils.FieldUtilities.DESCRIPTION;
import static org.blocorganization.blocapp.utils.FieldUtilities.loadUrlIntoImageViewWithActivity;
import static org.blocorganization.blocapp.utils.FieldUtilities.setSelectionForSpinnerFromList;
import static org.blocorganization.blocapp.utils.FieldUtilities.setTextForEditTextAndPrepend;
import static org.blocorganization.blocapp.utils.FieldUtilities.setTextForEditTextWith;

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
