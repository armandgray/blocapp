package org.blocorganization.blocapp.utils;

import android.view.View;
import android.widget.LinearLayout;

public class SetupButtonIncluder {

    private LinearLayout buttonLayoutReference;
    private View view;

    public SetupButtonIncluder(View view, int buttonLayoutId) {
        this.view = view;
        buttonLayoutReference = (LinearLayout) view.findViewById(buttonLayoutId);
    }

//    public static void setupUploadButtonWithPaddingFrom(View view, Activity activity) {
//        getButtonReference(view);
//        new SetupButtonIncluder();
//        final int padding = GetDpMeasurement.getDPI(activity, 5);
//        btnUpload.setPadding(padding, padding, padding, padding);
//        ImageView ivBtnIcon = (ImageView) btnUpload.getChildAt(0);
//        ivBtnIcon.setVisibility(View.GONE);
//        TextView tvBtnText = (TextView) btnUpload.getChildAt(1);
//        tvBtnText.setText("Upload");
//        tvBtnText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
//    }

}
