package org.blocorganization.blocapp.campaigns;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;

import org.blocorganization.blocapp.R;
import org.blocorganization.blocapp.utils.SetupButtonIncluder;

class UploadButtonIncluder {

    public static final int GALLERY_INTENT = 2;

    private static final String UPLOAD_BUTTON_TEXT = "Upload";
    private static final int UPLOAD_BUTTON_TEXT_SIZE = 12;
    private static final int PADDING = 5;

    static void setupUploadButtonFrom(View rootView, Fragment fragment) {
        SetupButtonIncluder buttonIncluder = new SetupButtonIncluder(rootView, R.id.btn_container_red);
        setButtonAttributesWith(buttonIncluder, fragment);
        addUploadButtonClickListenerWith(buttonIncluder, fragment);
    }

    private static void setButtonAttributesWith(SetupButtonIncluder buttonIncluder, Fragment fragment) {
        buttonIncluder.setButtonIconVisibility(SetupButtonIncluder.GONE);
        buttonIncluder.setButtonTextSizeInSp(UPLOAD_BUTTON_TEXT_SIZE);
        buttonIncluder.setButtonText(UPLOAD_BUTTON_TEXT);
        buttonIncluder.setButtonPaddingWithin(fragment.getActivity(), PADDING);
    }

    private static void addUploadButtonClickListenerWith(SetupButtonIncluder buttonIncluder, final Fragment fragment) {
        LinearLayout uploadButton = buttonIncluder.getUploadButton();
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                fragment.startActivityForResult(intent, GALLERY_INTENT);
            }
        });
    }

}
