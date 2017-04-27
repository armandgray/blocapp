package org.blocorganization.blocapp.utils;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import org.blocorganization.blocapp.R;

import java.util.List;

class ImageThemeAdapter extends
        RecyclerView.Adapter<ImageThemeAdapter.ViewHolder> {

    private List<String> mThemes;
    private Activity mActivity;
    private int mLayoutParams;
    private String setTheme;

    ImageThemeAdapter(@NonNull Activity activity, @NonNull List<String> themes, int layoutParams, @Nullable String theme) {
        mThemes = themes;
        mActivity = activity;
        mLayoutParams = layoutParams;
        setTheme = theme;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.themes_listitem, parent, false));
    }

    @Override
    public void onBindViewHolder(ImageThemeAdapter.ViewHolder viewHolder, int position) {
        String theme = mThemes.get(position);
        int layoutParams = GetDpMeasurement.getDPI(mActivity, mLayoutParams);

        ImageView ivCampaignTheme = viewHolder.ivCampaignTheme;
        ivCampaignTheme.setLayoutParams(new LinearLayout.LayoutParams(layoutParams, layoutParams));
        Picasso.with(mActivity).load(theme).into(ivCampaignTheme);

        if (setTheme != null && !setTheme.equals("")) {
            if (setTheme.equals(mThemes.get(position))) {
                LinearLayout setLayout = (LinearLayout) viewHolder.itemView;
                setLayout.setBackgroundResource(R.drawable.background_square_selected_shadow);
                ImageView setImg = (ImageView) setLayout.getChildAt(0);
                setImg.setColorFilter(Color.parseColor("#00000000"));
                setImg.setBackgroundColor(Color.parseColor("#00000000"));
            }
        }

    }

    @Override
    public int getItemCount() {
        return mThemes.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivCampaignTheme;

        ViewHolder(View itemView) {
            super(itemView);
            ivCampaignTheme = (ImageView) itemView.findViewById(R.id.ivCampaignTheme);
        }
    }

}
