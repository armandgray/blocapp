package org.blocorganization.blocapp.utils;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import org.blocorganization.blocapp.R;

import java.util.List;

import static org.blocorganization.blocapp.campaigns.CampaignDetailActivity.getDPI;

public class ImageThemeAdapter extends
        RecyclerView.Adapter<ImageThemeAdapter.ViewHolder> {

    private List<String> mThemes;
    private Activity mActivity;
    private int mLayoutParams;

    // Pass in the contact array into the constructor
    public ImageThemeAdapter(Activity activity, List<String> themes, int layoutParams) {
        mThemes = themes;
        mActivity = activity;
        mLayoutParams = layoutParams;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.themes_listitem, parent, false));
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(ImageThemeAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        String theme = mThemes.get(position);
        DisplayMetrics metrics = new DisplayMetrics();
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int layoutParams = getDPI(mLayoutParams, metrics);

        ImageView ivCampaignTheme = viewHolder.ivCampaignTheme;
        ivCampaignTheme.setLayoutParams(new LinearLayout.LayoutParams(layoutParams, layoutParams));
        Picasso.with(mActivity).load(theme).into(ivCampaignTheme);
    }

    @Override
    public int getItemCount() {
        return mThemes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivCampaignTheme;

        public ViewHolder(View itemView) {
            super(itemView);
            ivCampaignTheme = (ImageView) itemView.findViewById(R.id.ivCampaignTheme);
        }
    }

}
