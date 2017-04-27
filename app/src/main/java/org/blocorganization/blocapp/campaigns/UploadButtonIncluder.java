package org.blocorganization.blocapp.campaigns;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;

import org.blocorganization.blocapp.utils.SetupButtonIncluder;

public class UploadButtonIncluder {

    static final int GALLERY_INTENT = 2;

    public static final String UPLOAD_BUTTON_TEXT = "Upload";
    public static final int UPLOAD_BUTTON_TEXT_SIZE = 12;
    public static final int PADDING = 5;

    static void setupUploadButtonFrom(View rootView, Fragment fragment) {
        SetupButtonIncluder buttonIncluder = new SetupButtonIncluder(rootView);
        setButtonAttributesWith(buttonIncluder, fragment);
        addUploadButtonClickListenerWith(buttonIncluder, fragment);
    }

    private static void setButtonAttributesWith(SetupButtonIncluder buttonIncluder, Fragment fragment) {
        buttonIncluder.setButtonIconVisibility();
        buttonIncluder.setButtonTextSizeInSp();
        buttonIncluder.setButtonText();
        buttonIncluder.setButtonPaddingWithin(fragment.getActivity());
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
