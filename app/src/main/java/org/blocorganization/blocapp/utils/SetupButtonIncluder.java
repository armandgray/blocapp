package org.blocorganization.blocapp.utils;

import android.app.Activity;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SetupButtonIncluder {

    private static final int GONE = 0x00000008;

    private final ImageView ivButtonIcon;
    private final TextView tvButtonText;
    private final LinearLayout buttonLayoutReference;

    public SetupButtonIncluder(View rootView) {
        buttonLayoutReference = (LinearLayout) rootView.findViewById(org.blocorganization.blocapp.R.id.btn_container_red);
        this.ivButtonIcon = (ImageView) buttonLayoutReference.getChildAt(0);
        this.tvButtonText = (TextView) buttonLayoutReference.getChildAt(1);
    }

    public void setButtonText() {
        this.tvButtonText.setText(org.blocorganization.blocapp.campaigns.UploadButtonIncluder.UPLOAD_BUTTON_TEXT);
    }

    public void setButtonTextSizeInSp() {
        this.tvButtonText.setTextSize(TypedValue.COMPLEX_UNIT_SP, org.blocorganization.blocapp.campaigns.UploadButtonIncluder.UPLOAD_BUTTON_TEXT_SIZE);
    }

    public void setButtonIconVisibility() {
        this.ivButtonIcon.setVisibility(SetupButtonIncluder.GONE);
    }

    public void setButtonPaddingWithin(Activity activity) {
        final int pad = GetDpMeasurement.getDPI(activity, org.blocorganization.blocapp.campaigns.UploadButtonIncluder.PADDING);
        buttonLayoutReference.setPadding(pad, pad, pad, pad);
    }

    public LinearLayout getUploadButton() {
        return buttonLayoutReference;
    }

}
