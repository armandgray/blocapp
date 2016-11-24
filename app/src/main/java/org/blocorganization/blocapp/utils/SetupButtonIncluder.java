package org.blocorganization.blocapp.utils;

import android.app.Activity;
import android.opengl.Visibility;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SetupButtonIncluder {

    public static final int VISIBLE = 0x00000000;
    public static final int INVISIBLE = 0x00000004;
    public static final int GONE = 0x00000008;

    private ImageView ivButtonIcon;
    private TextView tvButtonText;
    private LinearLayout buttonLayoutReference;

    public SetupButtonIncluder(View rootView, int buttonLayoutId) {
        buttonLayoutReference = (LinearLayout) rootView.findViewById(buttonLayoutId);
        this.ivButtonIcon = (ImageView) buttonLayoutReference.getChildAt(0);
        this.tvButtonText = (TextView) buttonLayoutReference.getChildAt(1);
    }

//    public void setupButtonWithPaddingWithin(Activity activity) {
//        ivBtnIcon.setVisibility(View.GONE);
//        TextView tvBtnText = (TextView) btnUpload.getChildAt(1);
//        tvBtnText.setText("Upload");
//        tvBtnText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
//    }

    private setButtonIconAs(int drawableReferenceId) {
    }

    /**
     *  @param visibility One of {@link #VISIBLE}, {@link #INVISIBLE}, or {@link #GONE}.
     */
    private void setButtonIconVisibility(int visibility) {
        this.ivButtonIcon.setVisibility(visibility);
    }

    private void setButtonPaddingWithin(Activity activity, int padding) {
        final int pad = GetDpMeasurement.getDPI(activity, padding);
        buttonLayoutReference.setPadding(pad, pad, pad, pad);
    }

}
