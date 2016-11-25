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
import com.google.firebase.storage.UploadTask;

import static org.blocorganization.blocapp.campaigns.UploadButtonUtilities.GALLERY_INTENT;

class UploadActivityListener {

    private static final String PHOTOS = "photos";
    private static final int RESULT_OK = -1;
    private static final String UPLOADING = "Uploading...";
    private static final String UPLOAD_DONE = "UPLOAD_DONE";
    private static final String UPLOAD_FAILED = "UPLOAD_FAILED";

    private Activity activity;
    private ProgressDialog progressDialog;

    UploadActivityListener(Activity activity) {
        this.activity = activity;
        this.progressDialog = new ProgressDialog(activity);
    }

    void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == GALLERY_INTENT && resultCode == RESULT_OK) {
            startProgressDialog(progressDialog);
            putFileAtPathFrom(data);
            dismissProgressDialog(progressDialog);
        }
    }

    @NonNull
    private ProgressDialog startProgressDialog(ProgressDialog progressDialog) {
        progressDialog.setMessage(UPLOADING);
        progressDialog.show();
        return progressDialog;
    }

    private void putFileAtPathFrom(Intent data) {
        Uri imageUri = data.getData();
        StorageReference photosStorageReference = FirebaseStorage.getInstance().getReference().child(PHOTOS);
        StorageReference newImgFilepath = photosStorageReference.child(imageUri.getLastPathSegment());

        newImgFilepath.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                saveUriFrom(taskSnapshot);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(activity, UPLOAD_FAILED, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void saveUriFrom(UploadTask.TaskSnapshot taskSnapshot) {
        Uri newImgDownloadUri = taskSnapshot.getDownloadUrl();
        Toast.makeText(activity, UPLOAD_DONE, Toast.LENGTH_LONG).show();
//        campaign.setCampaignPhoto(downloadUri.toString());
//        ivUpload.setVisibility(View.VISIBLE);
//        Picasso.with(getActivity()).load(campaign.getCampaignPhoto()).into(ivUpload);
    }

    private void dismissProgressDialog(ProgressDialog progressDialog) {
        progressDialog.dismiss();
    }

    ProgressDialog getProgressDialog() {
        return progressDialog;
    }
}
