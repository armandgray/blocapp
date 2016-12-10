package org.blocorganization.blocapp.utils;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

public class FieldUtilities {

    private static final String DATE = "Date";

    static void setSelectionForSpinnerFromList(List<String> spListItems, String text, @NonNull Spinner spReference) {
        if (text != null && !text.equals("")) {
            findSpinnerItemIn(spListItems, text, spReference);
        }
    }

    private static void findSpinnerItemIn(List<String> spListItems, String text, @NonNull Spinner spReference) {
        int itemIndex = 0;
        for (String item : spListItems) {
            if (text.equals(item)) {
                spReference.setSelection(itemIndex);
            }
            itemIndex++;
        }
    }

    public static boolean verify(@NonNull EditText etReference) {
        String text = etReference.getText().toString();
        return !text.equals("");
    }

    public static boolean alertVerify(@NonNull EditText etReference, String alert) {
        String text = etReference.getText().toString();
        if (!text.equals("")) {
            return true;
        } else {
            Toast.makeText(etReference.getContext(), alert, Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public static boolean alertVerify(@NonNull TextView tvReference, String alert) {
        String text = tvReference.getText().toString();
        if (!text.equals("") && !text.equals(DATE)) {
            return true;
        } else {
            Toast.makeText(tvReference.getContext(), alert, Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public static boolean alertVerify(@NonNull Spinner spinner, String alert, CreateUtilities utilities) {
        if (spinner.getSelectedItemPosition() != 0 && utilities.getListItems() != null) {
            return true;
        } else {
            Toast.makeText(spinner.getContext(), alert, Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public static boolean alertVerify(Integer integer, String alert, CreateUtilities utilities) {
        if (integer != null && utilities.getListItems() != null) {
            return true;
        } else {
            Toast.makeText(utilities.getActivity(), alert, Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    public static String getTextFrom(@NonNull EditText etReference) {
        return etReference.getText().toString();
    }

    public static String getTextFrom(@NonNull List<String> list, Spinner spinner) {
        return list.get(spinner.getSelectedItemPosition());
    }

    public static void setTextForTextViewAndPrepend(String prependedText, @Nullable String text, @NonNull TextView tvReference) {
        if (text != null && !text.equals("")) {
            tvReference.setText(String.format("%s%s", prependedText, text));
        }
    }

    public static void setTextForTextViewWith(@Nullable String text, @NonNull TextView tvReference) {
        if (text != null && !text.equals("")) {
            tvReference.setText(text);
        }
    }

    public static void setTextForEditTextWith(@Nullable String text, @NonNull EditText etReference) {
        if (text != null && !text.equals("")) {
            etReference.setText(text);
        }
    }

    public static void setTextForEditTextAndPrepend(String prependedText, @Nullable String text, EditText etReference) {
        if (text != null && !text.equals("")) {
            etReference.setText(String.format("%s%s", prependedText, text));
        }
    }

    public static void loadUrlIntoImageViewWithActivity(@Nullable String url, ImageView ivReference, Activity activity) {
        if (url != null && !url.equals("")) {
            Picasso.with(activity).load(url).into(ivReference);
            ivReference.setVisibility(View.VISIBLE);
        }
    }

    public static void setResourceForImageViewWithId(int resourceId, ImageView ivReference) {
        ivReference.setImageResource(resourceId);
        ivReference.setVisibility(View.VISIBLE);
    }

}
