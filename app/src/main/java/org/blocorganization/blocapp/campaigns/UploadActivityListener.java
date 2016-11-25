package org.blocorganization.blocapp.campaigns;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask.TaskSnapshot;

import static org.blocorganization.blocapp.campaigns.UploadButtonIncluder.GALLERY_INTENT;

class UploadActivityListener {

    public static final int TIMER_DELAY = 1000;
    private static final String PHOTOS = "photos";
    private static final int RESULT_OK = -1;
    private static final String UPLOADING = "Uploading...";
    private static final String UPLOAD_DONE = "UPLOAD_DONE";
    private static final String UPLOAD_FAILED = "UPLOAD_FAILED";
    public static final int WAIT_TIME = 10;
    public static final int TIMER_PERIOD = 10000;

    private Activity activity;
    private ProgressDialog progressDialog;
    private boolean progressDialogIsShowing;
    private String imgDownloadUri = "";

    UploadActivityListener(Activity activity) {
        this.activity = activity;
        this.progressDialog = new ProgressDialog(activity);
    }

    void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == GALLERY_INTENT && resultCode == RESULT_OK) {
            startProgressDialog(progressDialog);
            setupFileForPathFrom(data);
        }
    }

    @NonNull
    private ProgressDialog startProgressDialog(ProgressDialog progressDialog) {
        progressDialog.setMessage(UPLOADING);
        progressDialog.show();
        progressDialogIsShowing = true;

        return progressDialog;
    }

    private void setupFileForPathFrom(Intent data) {
        Uri imageUri = data.getData();
        StorageReference imgFilepath = getNewImageFilepath(imageUri);
        putFileAtPathFrom(imageUri, imgFilepath);
    }

    @NonNull
    private StorageReference getNewImageFilepath(Uri imageUri) {
        StorageReference photosStorageReference = FirebaseStorage.getInstance().getReference().child(PHOTOS);
        return photosStorageReference.child(imageUri.getLastPathSegment());
    }

    private void putFileAtPathFrom(Uri imageUri, StorageReference imgFilepath) {
        imgFilepath.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<TaskSnapshot>() {
            @Override
            public void onSuccess(TaskSnapshot taskSnapshot) {
                saveUriFrom(taskSnapshot);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(activity, UPLOAD_FAILED, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void saveUriFrom(TaskSnapshot taskSnapshot) {
        this.imgDownloadUri = taskSnapshot.getDownloadUrl().toString();
        Toast.makeText(activity, UPLOAD_DONE, Toast.LENGTH_LONG).show();
        dismissProgressDialog();
    }

    private void dismissProgressDialog() {
        progressDialog.dismiss();
        progressDialogIsShowing = false;
    }

}
