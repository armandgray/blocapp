package org.blocorganization.blocapp.utils;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class SetupButtonIncluder {

    private ImageView ivButtonIcon;
    private LinearLayout buttonLayoutReference;

    public SetupButtonIncluder(View rootView, int buttonLayoutId) {
        buttonLayoutReference = (LinearLayout) rootView.findViewById(buttonLayoutId);
    }

    public void setupButtonWithPaddingWithin(Activity activity) {
        ivBtnIcon.setVisibility(View.GONE);
        TextView tvBtnText = (TextView) btnUpload.getChildAt(1);
        tvBtnText.setText("Upload");
        tvBtnText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
    }

    private setButtonIconAs(int drawableReferenceId) {
        this.ivButtonIcon = (ImageView) buttonLayoutReference.getChildAt(0);
    }

    private void setButtonPaddingWithin(Activity activity, int padding) {
        final int pad = GetDpMeasurement.getDPI(activity, padding);
        buttonLayoutReference.setPadding(pad, pad, pad, pad);
    }

}
