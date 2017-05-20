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

import java.util.ArrayList;
import java.util.List;

public class ImageThemeAdapter extends
        RecyclerView.Adapter<ImageThemeAdapter.ViewHolder> {

    private final List<String> themes;
    private final Activity mActivity;
    private final int mLayoutParams;
    private ArrayList<ViewHolder> viewHolders;
    private int lastThemePosition = -1;

    ImageThemeAdapter(@NonNull Activity activity, @NonNull List<String> themes, @Nullable String theme) {
        this.themes = themes;
        this.mActivity = activity;
        this.mLayoutParams = org.blocorganization.blocapp.campaigns.CreateCampaignDialog.THEME_LAYOUT_PARAMS;
        this.viewHolders = new ArrayList<>();
        lastThemePosition = themes.indexOf(theme);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.themes_listitem, parent, false));
    }

    @Override
    public void onBindViewHolder(ImageThemeAdapter.ViewHolder viewHolder, int position) {
        if (viewHolders.size() <= position || viewHolders.get(position) == null ) {
            viewHolders.add(position, viewHolder);
        }
        String theme = themes.get(position);
        int layoutParams = GetDpMeasurement.getDPI(mActivity, mLayoutParams);

        ImageView ivCampaignTheme = viewHolder.ivCampaignTheme;
        ivCampaignTheme.setLayoutParams(new LinearLayout.LayoutParams(layoutParams, layoutParams));
        Picasso.with(mActivity).load(theme).into(ivCampaignTheme);
        if (position != lastThemePosition) { disableLayout(position); }
        if (position == lastThemePosition) { highlightView(position); }
    }

    @Override
    public int getItemCount() {
        return themes.size();
    }

    public void toggleHighlight(int position) {
        if (lastThemePosition > -1) { disableLayout(lastThemePosition); }
        highlightView(position);
    }

    private void disableLayout(int lastThemePosition) {
        LinearLayout lastItemView = (LinearLayout) viewHolders.get(lastThemePosition).itemView;
        lastItemView.setBackgroundResource(R.drawable.background_square_shadow);
        ImageView lastImg = (ImageView) lastItemView.getChildAt(0);
        lastImg.setColorFilter(Color.parseColor("#59000000"));
        lastImg.setBackgroundColor(Color.parseColor("#59000000"));
    }

    private void highlightView(int position) {
        LinearLayout itemView = (LinearLayout) viewHolders.get(position).itemView;
        itemView.setBackgroundResource(R.drawable.background_square_selected_shadow);
        ImageView img = (ImageView) itemView.getChildAt(0);
        img.setColorFilter(Color.parseColor("#00000000"));
        img.setBackgroundColor(Color.parseColor("#00000000"));
        lastThemePosition = position;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView ivCampaignTheme;

        ViewHolder(View itemView) {
            super(itemView);
            ivCampaignTheme = (ImageView) itemView.findViewById(R.id.ivCampaignTheme);
        }
    }

}
