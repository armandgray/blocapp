package org.blocorganization.blocapp.campaigns;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import org.blocorganization.blocapp.R;
import org.blocorganization.blocapp.utils.SetupButtonIncluder;

class UploadButtonUtilities {

    public static final String UPLOAD_BUTTON_TEXT = "Upload";
    public static final int UPLOAD_BUTTON_TEXT_SIZE = 12;
    public static final int GALLERY_INTENT = 2;

    public static final String UPLOAD_DONE = "UPLOAD_DONE";
    public static final String UPLOAD_FAILED = "UPLOAD_FAILED";
    public static final int CAMERA_REQUEST_CODE = 1;
    public static final String PHOTOS = "photos";
    public static final String DOWNLOAD_FAILED = "DOWNLOAD_FAILED";

    public static void setupUploadButtonFrom(View rootView, Activity parentActivity) {
        SetupButtonIncluder buttonIncluder = new SetupButtonIncluder(rootView, R.id.btn_container_red);
        setButtonAttributesWith(buttonIncluder, parentActivity);
        addUploadButtonClickListenerWith(buttonIncluder, parentActivity);
    }

    private static void setButtonAttributesWith(SetupButtonIncluder buttonIncluder, Activity parentActivity) {
        buttonIncluder.setButtonIconVisibility(SetupButtonIncluder.GONE);
        buttonIncluder.setButtonTextSizeInSp(UPLOAD_BUTTON_TEXT_SIZE);
        buttonIncluder.setButtonText(UPLOAD_BUTTON_TEXT);
        buttonIncluder.setButtonPaddingWithin(parentActivity, 5);
    }

    private static void addUploadButtonClickListenerWith(SetupButtonIncluder buttonIncluder, final Activity parentActivity) {
        LinearLayout uploadButton = buttonIncluder.getUploadButton();
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                parentActivity.startActivityForResult(intent, GALLERY_INTENT);
            }
        });
    }

}
